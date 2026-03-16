# ***pay SecurityGuard Reverse Engineering Toolkit

> Comprehensive static analysis toolkit for ***baba/***Group's SecurityGuard SDK — the security framework protecting ***pay and other ***baba ecosystem apps.

## Overview

SecurityGuard is a multi-layered security SDK embedded in a major Chinese mobile payment app that provides:

- **Request signing** via AVMP bytecode virtual machine
- **Data encryption** using SM4 (Chinese national cipher) and AES
- **Device fingerprinting** for risk control
- **Anti-tampering** via APSE/BlueShield inline hooks
- **Certificate management** with GM-TLS (Chinese national crypto TLS)
- **Encrypted storage** via SQLCipher

This project documents the complete architecture discovered through static reverse engineering of ***pay APK v10.8.30.8000.

## Architecture

```
SecurityGuard SDK
├── sgmain (Main Plugin)
│   ├── SecurityGuardMainPlugin.java — Entry point
│   ├── JNICLibrary.doCommandNative(int, Object...) — Single JNI entry
│   ├── IRouterComponent — Command routing to Native
│   └── libsgmainso-6.6.230507.so — Self-modifying ELF (encrypted)
├── sgsecurity (Security Body)
│   ├── SecurityGuardSecurityBodyPlugin.java — Entry point
│   ├── UMIDComponent — Unique Mobile ID generation
│   ├── DeviceInfoCapturerFull — Device fingerprint collection
│   └── libsgsecuritybodyso-6.6.230507.so — Encrypted strings
├── APSE (Application Protection Security Engine)
│   ├── libAPSE_1.9.41.so
│   ├── BlueShield anti-tampering framework
│   ├── myhook inline hook engine
│   └── Device integrity checking (d4checkup)
├── Network Layer
│   ├── SM4 content encryption (Sm4Client.java)
│   ├── AVMP bytecode signing (InnerSignImpl.java)
│   ├── EmptyX509TrustManagerWrapper — NO certificate pinning!
│   └── GM-TLS mutual authentication (GmStrategy.java)
└── Storage
    └── SQLCipher encrypted database
```

## Native Command ID Map

All SecurityGuard functionality is routed through a single JNI function:
```java
public static native Object doCommandNative(int commandId, Object... args);
```

| Command ID | Component | Function |
|-----------|-----------|----------|
| 10101 | Init | Device registration / SDK init |
| 10401 | SecureSignature | **Request signing** |
| 10501 | DynamicDataEncrypt | Dynamic data encrypt/decrypt |
| 10502-10503 | DynamicDataStore | Dynamic data storage |
| 10601 | StaticDataEncrypt | **Static data encryption** |
| 10602-10604 | StaticDataStore | Static data storage |
| 10605 | StaticKeyEncrypt | **Static key encryption** |
| 10901 | DataCollection | Data collection control |
| 11601 | OpenSDK | OpenSDK operations |
| 11901 | AtlasEncrypt | Atlas bundle encryption |
| 12101-12102 | SafeToken | **Security token generation** |
| 12501 | LiteVM | **Create AVMP VM instance** |
| 12502 | LiteVM | Reload AVMP bytecode |
| 12503 | LiteVM | Destroy AVMP VM instance |
| 12504 | LiteVM | **Execute AVMP method** (signing entry) |
| 12605/12611 | EdgeComputing | Edge computing tasks |
| 12801 | Compat | Compatibility layer |
| 13701-13702 | DataReport | Device info reporting |

## Key Security Findings

### 1. No Certificate Pinning
`EmptyX509TrustManagerWrapper` accepts all certificates. System CA validation only via `X509TrustManagerWrapper`. MITM is possible with user-installed proxy CA.

### 2. PatchProxy Pervasive Bypass
Every security-sensitive method contains `ChangeQuickRedirect` + `PatchProxy.proxy()` hooks. The server can remotely override ANY security check via hot-patch without app update.

### 3. Self-Modifying ELF
`libsgmainso-6.6.230507.so` has no exports, no imports, corrupted section headers. The SO decrypts itself at runtime — static analysis requires runtime memory dump.

### 4. AVMP Virtual Machine
Request signing runs inside an opaque bytecode VM ("mwua"/"sgcipher"). The signing algorithm is not in Java or standard ARM code — it's in custom AVMP bytecode executed by the LiteVM engine in native code.

### 5. SM4 National Cipher
Chinese national SM4 cipher used for RPC body encryption, with native implementation.

## Tools

### `scripts/sg_unpack.sh`
Unpacks SecurityGuard ZIP modules from APK.

### `scripts/sg_command_trace.sh`
Traces all doCommandNative calls in decompiled Java code.

### `scripts/so_string_extract.sh`
Extracts and categorizes security-relevant strings from native SO files.

### `scripts/version_diff.sh`
Binary-diffs native security functions between APK versions.

## Usage

```bash
# 1. Unpack SecurityGuard modules
./scripts/sg_unpack.sh /path/to/***pay.apk

# 2. Decompile with jadx
./scripts/sg_decompile.sh

# 3. Extract native strings
./scripts/so_string_extract.sh

# 4. Map command IDs
./scripts/sg_command_trace.sh

# 5. Compare versions
./scripts/version_diff.sh /path/to/v8000.apk /path/to/v9000.apk
```

## Related CVEs

- **CVE-2026-XXXXX**: 6 CVEs submitted to MITRE (Ticket #2005801)
  - DeepLink URL Scheme Bypass (CWE-939)
  - GPS Silent Exfiltration (CWE-359)
  - Unauthorized Payment Invocation (CWE-940)
  - UI Spoofing (CWE-451)
  - End-to-End Data Exfiltration (CWE-200)
  - Open Redirect Whitelist Bypass (CWE-601)

## Disclaimer

This research is conducted for authorized security testing and responsible disclosure purposes. All findings have been reported to the vendor (***Group) and relevant regulatory authorities.

## Author

Jiqiang Feng — [Innora AI Security Research](https://innora.ai)

## License

MIT
