# SecurityGuard SDK Architecture — Complete Reverse Engineering

## Plugin Loading Chain

```
APK Boot
  → SecurityGuardManager.getInstance(context)
  → Load sgmain (libsgmain.zip → classes.dex + libsgmainso-6.6.230507.so)
    → SecurityGuardMainPlugin.onPluginLoaded()
      → System.loadLibrary("sgmainso-6.6.230507")
      → doCommandNative(10101, [context, appId, appKey, authCode, pkgName, ver, osVer, processName])
      → Register 12 component interfaces via C0004 plugin registry
      → Start DataCollection timer (3s delay)
  → Load sgsecurity (libsgsecuritybody.zip → classes.dex + libsgsecuritybodyso-6.6.230507.so)
    → SecurityGuardSecurityBodyPlugin.onPluginLoaded()
      → UMIDComponent.init() → DeviceSecuritySDKImpl
      → System.loadLibrary("sgsecuritybodyso-6.6.230507")
      → LifeCycle.init()
      → SecurityBodyAdapter.initialize()
      → InvocationHandlerAdapter.init()
  → Load sgmiddletier (libsgmiddletier.zip → classes.dex + libsgmiddletierso-6.6.230507.so)
    → SecurityGuardMiddleTierPlugin.onPluginLoaded()
      → System.loadLibrary("sgmiddletierso-6.6.230507")
      → Register AVMP, WUA, SafeToken, FC components
  → Load sgmisc + sgnocaptcha (similar pattern)
```

## Component Interface Registry (sgmain C0004)

| Interface | Impl | Description |
|-----------|------|-------------|
| IAtlasEncryptComponent | C0024 | Atlas bundle encryption |
| ICompatComponent | C0026 | Backward compatibility |
| IDataCollectionComponent | C0027 | Device data collection |
| IDynamicDataEncryptComponent | C0028 | Dynamic (session) encryption |
| IDynamicDataStoreComponent | C0029 | Dynamic (session) key-value store |
| IOpenSDKComponent | C0030 | Open platform SDK |
| ISafeTokenComponent | C0031 | Hardware-backed token operations |
| ISecureSignatureComponent | C0032 | Request signing (HMAC/AVMP) |
| IStaticDataEncryptComponent | C0033 | Static (persistent) encryption |
| IStaticDataStoreComponent | C0034 | Static (persistent) key-value store |
| IStaticKeyEncryptComponent | C0035 | Static key management |
| ILiteVMComponent | C0036→C0038 | AVMP bytecode virtual machine |

## Native SO Protection Analysis

### libsgmainso-6.6.230507.so (2.0MB)
- **Self-modifying ELF**: No exports, no imports, corrupted section headers
- **Runtime decryption**: Code segment encrypted, decrypted via `JNI_OnLoad`
- **Static analysis limitation**: Requires runtime memory dump to analyze
- SM4 encryption confirmed ("sm4 E!" string)
- `sgcookiecheck` — cookie validation function
- Cyrillic-named fields in Java layer (а,б,в,г,д,е,ё)

### libsgsecuritybodyso-6.6.230507.so (1.0MB)
- **String encryption**: All meaningful strings encrypted at compile time
- Known encrypted strings: `rSA7B`, `leSsluS3`, `cDRSa` (likely API names)
- Runtime string decryption via custom table lookup

### libsgmiddletierso-6.6.230507.so (900KB)
- WUA generation engine
- AVMP bytecode interpreter (middletier path)
- Flow Control (FC) strategy execution

### libAPSE_1.9.41.so (4.0MB) — Application Protection Security Engine
- **BlueShield**: Main anti-tampering framework
- **myhook**: Built-in inline hook engine
  - `myhook_hook_filter` / `myhook_unhook` / `myhook_get_prev_func`
  - `libmyhook.so` (internal reference)
- **Memory protection**: `mprotect` syscall wrappers
- **Sandbox**: `chroot` capability
- **Device integrity**: `d4checkup` / `InitCheckupManagerOnce`
- **Fingerprinting**: Hardware fingerprint, `deviceColor`
- **Risk engine**: `fastRiskControl` / `getRiskResult` / `riskReason`
- **TLS**: Custom cert chain verification, session key derivation
- **Keystore**: `android/security/keystore/KeyProtection$Builder` integration

## Encryption Algorithms Used

| Algorithm | Component | Purpose |
|-----------|-----------|---------|
| SM4 | sgmain native | RPC body encryption (Chinese national cipher) |
| AES | sgmain/sgsecurity | General data encryption |
| HMAC-SHA256 | ISecureSignatureComponent | Request signing (pre-AVMP) |
| RSA | sgsecurity | Key exchange / token signing |
| SM2 | GM-TLS (GmStrategy) | Chinese national asymmetric crypto |
| PBKDF2 | SQLCipher | Database key derivation |
| Custom AVMP | LiteVM | Opaque signing algorithm in bytecode VM |

## Data Flow: Request Signing

```
1. App constructs RPC request body
2. ContentEncryptUtils.encrypt() → SM4 body encryption (command 10601)
3. InnerSignImpl builds canonical sign input string:
   utdid & uid & MD5(body) & timestamp & api & sid & deviceId & lat & lng
4. avmpSign(input) → LiteVM (command 70202/12504) → opaque token
5. Headers injected: x-sign, x-sgext, x-umt, wua
6. HttpSignUtil.doSignHttpRequest() → x-mmtc-sign header
7. Cookie signed: getSafeCookie() → "AE001" + encrypt(sign + input)
8. Request sent over SPDY/HTTP with GM-TLS (optional)
```

## Data Flow: Device Fingerprint Collection

```
1. SecurityGuardMainPlugin.onPluginLoaded() → doCommand(10101)
   → Registers device with server
2. DeviceInfoCapturer.doCommandForString(i) → collects:
   - WiFi capability (i=0)
   - Device model (i=121)
   - Screen info (i=122, 123)
   - SIM info (i=126)
   - Installed apps (i=130)
   - Network info (i=135)
   - Build props (i=146)
3. DeviceInfoCapturerFull (sgsecurity) → collects:
   - Audio route (headset/BT detection)
   - All requested permissions
   - App signature hash
   - MediaDrm ID
   - WiFi MAC/BSSID
   - IMEI (pre-Android 10) / OAID
   - Timezone
   - Build fingerprint
4. UMIDComponent.initUMID() → doCommand(22301-22304)
   → Generates unique mobile ID from fingerprint
5. Data reported: doCommand(13701, [deviceData])
   → Periodically uploaded to server
```
