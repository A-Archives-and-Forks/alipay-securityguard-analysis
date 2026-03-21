# Alipay SecurityGuard SDK — Complete Security Analysis

> **Alipay Mobile Payment Attack Surface — The Full-Stack Analysis by [Innora AI](https://innora.ai)**
>
> | Layer | Repository | Findings |
> |-------|-----------|----------|
> | Runtime | [alipay-deeplink-research](https://github.com/sgInnora/alipay-deeplink-research) | 17 vulnerabilities, CVSS 9.3 |
> | SDK | **[alipay-securityguard-analysis](https://github.com/sgInnora/alipay-securityguard-analysis)** (this repo) | 9 CVEs, AVMP VM bypass, 97% unprotected APIs |
> | Cryptography | [hash-collision-lab](https://github.com/sgInnora/hash-collision-lab) | 15 PoCs, IACR ePrint [2026/526](https://eprint.iacr.org/2026/526) |

> **Disclaimer**: This research is conducted for educational and security research purposes only. The author assumes no liability for any direct or indirect damages arising from the use of information in this repository. All trademarks are property of their respective owners. Findings were reported to the vendor via responsible disclosure.

## Executive Summary

A comprehensive reverse engineering analysis of the SecurityGuard SDK embedded in Alipay (v10.8.30.8000), revealing the complete internal architecture of the security framework protecting one of the world's largest mobile payment applications (1B+ users).

**Key achievements:**
- Mapped all ~80 native commands through the single JNI entry point
- Captured AVMP bytecode VM signatures via custom C gadget
- Reversed 3 XOR-based string encryption variants
- Documented 22 hidden behavior monitoring events
- Identified 29-point device super-fingerprinting
- Confirmed absence of certificate pinning
- Analyzed PatchProxy remote code modification capability


## Academic Publication

**Feng, J. (2026).** "Broken By Design: A Longitudinal Analysis of Cryptographic Failures in Alipay Mobile Payment Infrastructure." *IACR Cryptology ePrint Archive*, 2026/526.

**URL:** [https://eprint.iacr.org/2026/526](https://eprint.iacr.org/2026/526)

**Category:** Attacks and cryptanalysis | **Keywords:** MD5 collision, SHA-1, RSA-1024, APK signing, batch GCD, mobile payment security, cryptographic debt

## CVE References

9 CVEs submitted to MITRE (Ticket #2005801), pending assignment:

| # | Vulnerability | CWE | CVSS |
|---|--------------|-----|------|
| CVE-1 | DeepLink URL Scheme Access Control Bypass — 396/408 (97%) JSBridge APIs unprotected | CWE-939 | 9.1 |
| CVE-2 | iOS Silent GPS Location Exfiltration | CWE-359 | 7.4 |
| CVE-3 | iOS tradePay Unauthorized Payment Invocation | CWE-940 | 8.6 |
| CVE-4 | UI Spoofing via showToast/setTitle | CWE-451 | 8.1 |
| CVE-5 | End-to-End Data Exfiltration Chain | CWE-200 | 8.6 |
| CVE-6 | ds.alipay.com Open Redirect Whitelist Bypass | CWE-601+CWE-939 | 9.3 |
| CVE-7 | No Certificate Pinning (EmptyX509TrustManagerWrapper) | CWE-295 | 7.4 |
| CVE-8 | AVMP Signature Capture-Replay | CWE-294 | 7.7 |
| CVE-9 | SchemeNeedVerify Opt-In Design Flaw + Insecure Config Transport | CWE-863+CWE-319 | 8.6 |

## Architecture

```
SecurityGuard SDK
├── sgmain — Core plugin, single JNI entry: doCommandNative(int, Object...)
│   ├── ~80 command IDs routing to 12+ security interfaces
│   ├── AVMP/LiteVM bytecode virtual machine for request signing
│   ├── SM4 (Chinese national cipher) for RPC encryption
│   ├── Static key management via SharedPreferences
│   └── Self-protecting ELF with corrupted section headers
├── sgsecurity — Device security body
│   ├── 29-item device fingerprinting (IMEI, OAID, WiFi MAC, MediaDrm...)
│   ├── UMID persistent cross-install device ID
│   └── Encrypted strings (nearly all obfuscated)
├── sgmiddletier — Middle tier
│   ├── AVMP VM instance management (create/invoke/destroy)
│   ├── WUA device proof generation
│   └── OTP generation
├── APSE/BlueShield — Anti-tampering
│   ├── myhook inline hook engine
│   ├── ptrace anti-debug
│   ├── /proc/maps scanning (Frida/Xposed detection)
│   └── mprotect memory protection
└── Network Layer
    ├── EmptyX509TrustManagerWrapper — NO certificate pinning
    ├── PatchProxy/ChangeQuickRedirect on ALL security methods
    ├── SM4 content encryption (via SecurityGuard commands)
    └── AVMP signature tokens (x-sign HTTP header)
```

## Key Findings

### 1. AVMP Signature Bypass (C Gadget)

The AVMP bytecode VM — Alipay's last line of defense for request authentication — can be invoked programmatically via a 132-line C gadget operating at the JNI level:

```c
// Create VM instance
jobject vmIdObj = (*env)->CallObjectMethod(env, router, doCmd, 70201, args);
// Execute signing
jobject result = (*env)->CallObjectMethod(env, router, doCmd, 70202, outerArgs);
```

The gadget bypasses Frida's JS-JNI serialization limitation by passing `byte[].class` directly as a JNI object. Full source: [`tools/sg_avmp_gadget.c`](tools/sg_avmp_gadget.c)

### 2. No Certificate Pinning

`EmptyX509TrustManagerWrapper.checkServerTrusted()` is an empty method. The production `X509TrustManagerWrapper` only validates against system CA store. PatchProxy can remotely degrade to accept-all behavior.

### 3. Behavior Monitoring (22 Events)

SecurityGuard silently monitors: screen on/off, app foreground/background, airplane mode, time changes, screenshots, screen recording, Bluetooth connections, phone calls, headphone state, clipboard access, network changes, and all Activity lifecycle events.

### 4. DexAOP Audio API Interception (14 Points)

All `AudioRecord` and `MediaRecorder` API calls are intercepted at bytecode level via DexAOP, monitoring when any app component accesses the microphone.

### 5. permit() = null Anti-Pattern (396/408 = 97%)

A comprehensive scan of ALL 408 `BridgeExtension` classes reveals **396 (97.1%) return null from `permit()`**, meaning virtually the entire JSBridge API surface operates without access control:

```
Total BridgeExtension classes with permit(): 408
Classes where permit() returns null:         396 (97.1%)
Classes with actual permission checks:        12 (2.9%)
```

High-risk unprotected categories include:
- **Payment** (6): TradePayBridgeExtension, DCEPWalletBridgeExtension (Digital Yuan!)
- **Authentication** (5): LoginExtension, VerifyIdentityBridgeExtension
- **NFC/Contactless** (3): NFCBridgeExtension, NfcPayExtension
- **Device Hardware** (6): ScanBridgeExtension, ClipboardBridgeExtension
- **File Operations** (6): FileBridgeExtension, UploadFileBridgeExtension

See [`analysis/permit_null_full_scan.md`](analysis/permit_null_full_scan.md) for full results.

### 6. SchemeNeedVerify Opt-In Design Flaw (CVE-9)

The `SchemeNeedVerify` security gate uses an **opt-in model** — verification must be explicitly enabled per-API. The vast majority of APIs are unprotected by default. Combined with insecure plaintext HTTP transport of security configurations, this creates a systemic architectural vulnerability.

### 7. PatchProxy Remote Code Modification

Every security-critical method contains a `ChangeQuickRedirect` field that allows the vendor to remotely modify method behavior via hot-patching — without app store review or user consent.

## Verification

All findings underwent **3-LLM cross-verification** (Claude Opus 4.6 + Sonnet 4.6 + Gemini 2.5 Pro):
- **21/23** claims verified TRUE against source code
- **2/23** SUSPECT (signing output byte count lacks preserved raw evidence)
- **0/23** FALSE

## Responsible Disclosure Timeline

| Date | Event |
|------|-------|
| 2026-02-16 | Security analysis begins |
| 2026-03-07 | 17 vulnerabilities reported to vendor (Ant Group) |
| 2026-03-10 | Vendor response: "normal functionality" |
| 2026-03-11 | Public disclosure at innora.ai/zfb/ |
| 2026-03-11 | Vendor's law firm files takedown complaint (4 hours later) |
| 2026-03-12 | 6 CVEs submitted to MITRE (Ticket #2005801) |
| 2026-03-12 | 189 notifications sent to global regulators |
| 2026-03-15 | 4 WeChat articles force-deleted via legal pressure |
| 2026-03-15 | All 4 WeChat articles force-deleted via law firm complaint |
| 2026-03-15 | Server-side PoC blocking confirmed (API returns blank/mock data) |
| 2026-03-17 | SecurityGuard SDK reverse engineering completed |
| 2026-03-17 | 3 additional CVEs submitted: cert validation, AVMP replay, opt-in design flaw |
| 2026-03-17 | permit() scan: 396/408 (97%) JSBridge APIs have no permission check |

## Vendor Suppression Pattern

1. **Verbal denial** — "normal functionality"
2. **Legal threats** — Beijing Geyun Law Firm takedown complaints
3. **Technical countermeasures** — Server-side PoC blocking (API returns mock data)
4. **Platform censorship** — 4 WeChat articles deleted citing "Cybersecurity Law"

## Global Regulatory Response

38+ institutions across 22 countries responded, including:
- **MITRE**: 9 CVEs under review (Ticket #2005801)
- **Apple Product Security**: iOS investigation (Case OE01052449093014)
- **Google Play**: Policy violation review (#9-7515000040640)
- **Packet Storm Security**: Advisory #217089 published
- **CSSF Luxembourg**: Whistleblowing case CSSFWB-2026-080
- **HKMA Hong Kong**: Complaint CE20260313175412
- **FCA UK**: Whistleblowing team acknowledged

## Related Resources

- **Full vulnerability report**: [innora.ai/zfb/](https://innora.ai/zfb/)
- **Censorship documentation**: [innora.ai/zfb/article_censorship.html](https://innora.ai/zfb/article_censorship.html)
- **Packet Storm Advisory**: [#217089](https://packetstormsecurity.com/files/id/217089)
- **Independent reproduction**: [cxxsheng (GitHub)](https://github.com/nickcxxsheng) confirmed PoC

## Reproduction

```bash
# 1. Obtain APK (v10.8.30.8000 from APKPure)
# 2. Extract SecurityGuard modules
unzip Alipay.apk "lib/arm64-v8a/libsg*.so" -d sg_unpacked/
unzip Alipay.apk "lib/arm64-v8a/libAPSE*" -d sg_unpacked/

# 3. Decompile Java layer
jadx --show-bad-code -d sg_decompiled/ sg_unpacked/modules/sgmain/classes.dex

# 4. Analyze native code (segment-based loading for corrupted headers)
r2 -e bin.segments=true sg_unpacked/native_libs/libsgmainso-6.6.230507.so

# 5. Dynamic analysis (requires rooted device + Frida/stnel)
# See tools/sg_avmp_gadget.c for AVMP signature capture
```

## License

GPLv3 — See [LICENSE](LICENSE) for details.

## Author

**Jiqiang Feng** — Innora AI Security Research
- Email: feng@innora.ai
- Web: [innora.ai](https://innora.ai)

---

*This research demonstrates that security through obscurity is not security. The complete SecurityGuard architecture was reversed through standard static analysis techniques, proving that client-side security controls alone cannot protect a payment application.*

## Legal Notice

This repository contains **original security research analysis** and **machine-generated pseudocode** (via Ghidra/radare2 decompilation). It does NOT contain original source code, copyrighted binaries, or trade secrets. The decompiled pseudocode is included under security research fair use (DMCA §1201(j), EU Directive 2009/24/EC Art. 6) for the purpose of identifying and documenting security vulnerabilities affecting over 1 billion users.

No original APK, DEX, or SO files are included in this repository.
