# Alipay SecurityGuard SDK — Architecture & Privacy Analysis

[![DOI](https://zenodo.org/badge/doi/10.5281%2Fzenodo.19186848.svg)](https://zenodo.org/records/19186848)

> **Alipay Mobile Payment Attack Surface — The Full-Stack Analysis by [Innora AI](https://innora.ai)**
>
> | Layer | Repository | Findings |
> |-------|-----------|----------|
> | Runtime | [alipay-deeplink-research](https://github.com/sgInnora/alipay-deeplink-research) | 17 findings, CVSS 9.3 |
> | SDK | **[alipay-securityguard-analysis](https://github.com/sgInnora/alipay-securityguard-analysis)** (this repo) | 36 MITRE reports, AVMP VM bypass, 97% unprotected APIs |
> | Cryptography | [hash-collision-lab](https://github.com/sgInnora/hash-collision-lab) | 15 PoCs, IACR ePrint [2026/526](https://eprint.iacr.org/2026/526) |

> **Disclaimer**: This research is conducted for educational and security research purposes only. All trademarks are property of their respective owners. Findings were reported to the vendor via responsible disclosure.

## Independent Verification (Docker)

You don't have to trust us. Verify everything yourself in ~15 minutes:

```bash
# Option A: Docker (recommended — handles jadx memory for 210MB APK)
docker build -t alipay-verify .
docker run -v /path/to/Alipay_10.8.30.8000.apk:/apk/Alipay.apk alipay-verify

# Option B: Manual (requires jadx with 12GB+ heap, ripgrep)
export JADX_OPTS="-Xmx12g"
./scripts/verify.sh /path/to/Alipay.apk
```

**Target APK:** Alipay v10.8.30.8000 — SHA-256: `2eebd18eb78b2bdcc737c568a8057345255b9660dbf6f5af83743644effcaad2`
Available on APKPure/APKMirror historical versions.

The verification script decompiles the APK and runs 40+ regex signature checks against the source. No patterns use absolute line numbers — all signatures are feature-based strings and class identifiers that survive obfuscation changes. See [`scripts/signatures.txt`](scripts/signatures.txt) for the full pattern list.

### Test Environment

| Component | Detail |
|-----------|--------|
| Static Analysis | jadx 1.5.1, apktool 2.10.0, Ghidra 11.0, radare2 5.9 |
| Dynamic Analysis | Google Pixel 6 (Android 13), Magisk 26.1, Frida 16.1.4 |
| Network Capture | mitmproxy 10.x with custom user-cert injection |
| Baseline Device | Unrooted Samsung Galaxy S23 (stock Android 14) — sideloaded APK, hardware packet capture via Wi-Fi AP |

Behaviors documented in this analysis were confirmed on **both** the rooted test device and the unrooted baseline device.

## Executive Summary

A reverse engineering analysis of the SecurityGuard SDK embedded in Alipay (v10.8.30.8000), documenting the internal architecture of the security framework in one of the world's largest mobile payment applications ([1B+ users per Ant Group IPO prospectus 2020](https://www.sec.gov/Archives/edgar/data/1823652/)).

**Key findings:**
- Mapped all ~80 native commands through the single JNI entry point
- Captured AVMP bytecode VM signatures via custom C gadget
- Reversed 3 XOR-based string encryption variants
- Documented 22 hidden behavior monitoring events
- Identified 29-point device super-fingerprinting
- Confirmed absence of certificate pinning
- Analyzed PatchProxy remote code modification capability (90,000-146,000+ methods depending on decompilation completeness)

## Why This Is NOT Standard Anti-Fraud

We anticipate the vendor will claim these mechanisms are "routine risk control." Here is why that defense does not hold:

| Standard Risk Control | What This App Does | Why It's Different |
|---|---|---|
| Collect Device ID for fraud scoring | Collects IMEI, IMSI, MAC, Bluetooth MAC, WiFi BSSID, installed app list | Scope far exceeds fraud detection; IMEI/IMSI restricted since Android 10 |
| Hot-fix critical crashes | 146,173 methods remotely replaceable via PatchProxy, including payment password verification | Hot-patching auth logic without user consent is not crash recovery |
| Use standard Android Keystore | Custom crypto with `java.util.Random` keys, DES, AES/ECB, hardcoded constants | Bypasses hardware-backed security; uses broken algorithms |
| Detect rooted/tampered devices | Intercepts ALL AudioRecord/MediaRecorder calls; monitors clipboard, screenshots, Bluetooth | Behavioral surveillance unrelated to device integrity |
| Server-side telemetry | 2 of 8 telemetry endpoints use plaintext HTTP; WiFi RTT indoor positioning | HTTP leaks PII in transit; sub-meter indoor tracking is surveillance, not analytics |

**The principle of least privilege requires that security mechanisms collect only what is necessary.** This architecture collects everything it technically can.

## Preprint Paper

**Feng, J. (2026).** "Broken By Design: A Longitudinal Analysis of Cryptographic Failures in Alipay Mobile Payment Infrastructure." *IACR Cryptology ePrint Archive*, 2026/526.

**URL:** [https://eprint.iacr.org/2026/526](https://eprint.iacr.org/2026/526)

**Note:** This is an **unreviewed preprint** — not a peer-reviewed publication. It is provided as a structured technical reference. The verifiable proof is in this repository's code-level analysis and the verification script above.

## MITRE Submissions

> **Note:** CVSS scores are preliminary self-assessments. Final scores will be determined by MITRE during CVE assignment.

**36 reports** submitted to MITRE across 10 tickets (status: all confirmed received, pending assignment). 30 are detailed below; 6 additional reports were submitted via CVE Services under tickets #2011320 and #2011322.

### Batch-4 (2026-03-23) — 8 Reports

| # | Finding | CWE | CVSS | Ticket |
|---|---------|-----|------|--------|
| 29 | Native ART Method Hook via libathena.so | CWE-749 | 7.8 | #2012320 |
| 30 | Predictable Database Encryption Key (java.util.Random) | CWE-330 | 6.5 | #2012325 |
| 31 | Predictable BLE Authentication Nonce | CWE-330 | 7.1 | #2012325 |
| 32 | Fingerprint Payment Auth Bypass (Missing CryptoObject) | CWE-287 | 7.1 | #2012325 |
| 33 | Push Notification Deserialization RCE | CWE-502 | 8.1 | #2012325 |
| 34-36 | Deprecated Crypto: DES, AES/ECB, SM4/ECB, RSA/PKCS1v1.5 | CWE-327 | 5.9-7.5 | #2012325 |

### Batch-3 (2026-03-23) — 10 Reports

| # | Finding | CWE | CVSS | Ticket |
|---|---------|-----|------|--------|
| 10 | PatchProxy: 146,173 Remotely Replaceable Methods | CWE-494 | 9.8 | #2012246 |
| 11 | Payment Password Verification Bypass (163 hot-patch points) | CWE-287 | 9.1 | #2012246 |
| 12 | Signature Verification MD5 Cache Collision Bypass | CWE-347 | 8.1 | #2012246 |
| 13 | Remote Code Execution via Embedded Lua VM | CWE-94 | 9.8 | #2012250 |
| 14 | Remote Code Execution via DynamicBundle Class Loading | CWE-494 | 8.1 | #2012250 |
| 15 | DCEP (Digital Yuan) Wallet — No Bridge Access Control | CWE-862 | 7.5 | #2012254 |
| 16 | Bank Card Binding — No Bridge Access Control | CWE-862 | 7.5 | #2012254 |
| 17 | Privacy Framework Remotely Controllable (39 PatchProxy hooks) | CWE-284 | 7.5 | #2012254 |
| 18 | Cross-App Device ID Exposure via Exported ContentProvider | CWE-200 | 6.5 | #2012254 |
| 19 | Server-Controlled Data Collection Without User Consent | CWE-284 | 7.5 | #2012254 |

### Batch-1 (2026-03-19) — 3 Reports

| # | Finding | CWE | CVSS |
|---|---------|-----|------|
| A | RPC Content Encryption Default Disabled | CWE-311 | 7.5 |
| B | Server-Controlled SM4 Encryption Downgrade | CWE-326 | 7.5 |
| C | Hardcoded HTTP Fallback for Telemetry | CWE-319 | 5.9 |

### Original (2026-03-12) — 9 Reports

| # | Finding | CWE | CVSS |
|---|---------|-----|------|
| 1 | DeepLink URL Scheme Access Control Bypass — 396/408 (97%) JSBridge APIs unprotected | CWE-939 | 9.1 |
| 2 | iOS Silent GPS Location Exfiltration | CWE-359 | 7.4 |
| 3 | iOS tradePay Unauthorized Payment Invocation | CWE-940 | 8.6 |
| 4 | UI Spoofing via showToast/setTitle | CWE-451 | 8.1 |
| 5 | End-to-End Data Exfiltration Chain | CWE-200 | 8.6 |
| 6 | ds.alipay.com Open Redirect Whitelist Bypass | CWE-601+CWE-939 | 9.3 |
| 7 | No Certificate Pinning (EmptyX509TrustManagerWrapper) | CWE-295 | 7.4 |
| 8 | AVMP Signature Capture-Replay | CWE-294 | 7.7 |
| 9 | SchemeNeedVerify Opt-In Design Flaw + Insecure Config Transport | CWE-863+CWE-319 | 8.6 |

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

## Responsible Disclosure Timeline

| Date | Event |
|------|-------|
| 2026-02-16 | Security analysis begins |
| 2026-03-07 | 17 findings reported to vendor (Ant Group) |
| 2026-03-10 | Vendor response: "normal functionality" |
| 2026-03-11 | Public disclosure at innora.ai/zfb/ |
| 2026-03-12 | First 9 reports submitted to MITRE |
| 2026-03-12 | Notifications sent to regulators in 20+ jurisdictions |
| 2026-03-15 | Vendor's law firm issues takedown requests; 4 articles removed |
| 2026-03-19 | Batch-1: 3 additional MITRE reports |
| 2026-03-20 | 4 more articles removed (8 total) |
| 2026-03-23 | Batch-3 + Batch-4: 18 additional MITRE reports (36 total) |
| 2026-03-23 | Evidence archived to IPFS |

## Vendor Response

Instead of technical clarification, the vendor responded with:

1. **Verbal dismissal** — "normal functionality"
2. **Legal takedowns** — Beijing Geyun Law Firm sent takedown requests to WeChat
3. **Server-side countermeasures** — Observed behavior: certain API endpoints began returning blank/mock data during the analysis period
4. **Platform removals** — 8 articles removed in two waves citing China's Cybersecurity Law, with no specific provision and no appeal channel

Archived evidence: IPFS CID `QmcAsSB84Kw7ZmKDqrQMZB1ig6Q4YpHARzv2jGv1tDhwob`

## Regulatory Status

Relevant data protection and financial regulators in multiple jurisdictions have been notified and have confirmed receipt. Details are not published here to avoid interfering with ongoing regulatory processes.

## Related Resources

- **Zenodo Archive (DOI)**: [10.5281/zenodo.19186848](https://zenodo.org/records/19186848)

- **Full technical report**: [innora.ai/zfb/](https://innora.ai/zfb/)
- **Vendor response documentation**: [innora.ai/zfb/article_censorship.html](https://innora.ai/zfb/article_censorship.html)
- **Packet Storm Advisory**: [#217089](https://packetstormsecurity.com/files/id/217089)
- **IPFS Evidence Archive**: `QmcAsSB84Kw7ZmKDqrQMZB1ig6Q4YpHARzv2jGv1tDhwob`
- **Mastodon**: [infosec.exchange/@Innora](https://infosec.exchange/@Innora)

## Reproduction

```bash
# Using Docker (recommended):
docker build -t alipay-verify .
docker run -v /path/to/Alipay.apk:/apk/Alipay.apk alipay-verify

# Quick manual verification (3 commands):
JADX_OPTS="-Xmx12g" jadx --show-bad-code --no-res -d src/ Alipay.apk
rg "ChangeQuickRedirect" src/ | wc -l          # expect: ~146,000+
rg "EmptyX509TrustManager" src/                  # expect: cert bypass class
rg "mdap.alipay.com" src/                        # expect: telemetry endpoints
rg "java.util.Random()" src/ | wc -l            # expect: 10+ (non-crypto PRNG)
```

**Readers are encouraged to fork this repository as backup against further takedown attempts.**

## License

GPLv3 — See [LICENSE](LICENSE) for details.

## Author

**Jiqiang Feng** — Innora AI Security Research
- Email: feng@innora.ai
- Web: [innora.ai](https://innora.ai)

## Legal Notice

This repository contains **original security research analysis** and **machine-generated pseudocode** (via Ghidra/radare2 decompilation). It does NOT contain original source code, copyrighted binaries, or trade secrets. The decompiled pseudocode is included under security research fair use (DMCA 1201(j), EU Directive 2009/24/EC Art. 6) for the purpose of identifying and documenting security issues affecting over 1 billion users.

No original APK, DEX, or SO files are included in this repository.
