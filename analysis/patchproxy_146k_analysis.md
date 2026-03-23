# PatchProxy Hot-Patch Framework Analysis — 146,173 Remotely Replaceable Methods

> Date: 2026-03-23 | MITRE Ticket: #2012246 | CVEs: 3 pending
> APK: Alipay Android v10.8.30.8000 (SHA-256: 2eebd18e...caad2)

## Summary

Alipay for Android contains **146,173 instances** of `ChangeQuickRedirect` fields, each allowing runtime method replacement via the PatchProxy hot-patch mechanism without app store review or user notification.

## Key Findings

### 1. Mass Remote Method Replacement (CVSS 9.8)
- **CWE**: CWE-494, CWE-829
- **Evidence**: `grep -r "public static ChangeQuickRedirect" *.java | wc -l` → 146,173
- **Mechanism**: `PatchProxy.proxy()` intercepts every method call; if `ChangeQuickRedirect` is non-null, replacement code executes instead of original
- **Entry point**: `com.alipay.instantrun.runtime.PatchProxy:accessDispatch()`

### 2. Payment Password Verification Bypass (CVSS 9.1)
- **CWE**: CWE-287, CWE-494
- **Class**: `com.alipay.android.phone.mobilecommon.cashier.PayPwdDialogActivity`
- **Hot-patch points**: 163 `ChangeQuickRedirect` fields
- **Impact**: Password verification logic itself can be replaced to accept any input

### 3. Signature Verification MD5 Cache Bypass (CVSS 8.1)
- **CWE**: CWE-347, CWE-328
- **Code**: `SecurityChecker.java:539-541` — `mVerifiedSet` uses MD5 hash cache
- **Issue**: MD5 is cryptographically broken (Google/CWI SHAttered, 2017)
- **Impact**: Malicious patch with colliding MD5 bypasses signature verification
- **Note**: `SecurityChecker.verifyApk()` (line 527) is itself subject to PatchProxy override

## Verification Method

```bash
# Count all PatchProxy hook points
grep -r "public static ChangeQuickRedirect" decompiled_sources/ | wc -l
# Result: 146,173

# Verify PayPwdDialogActivity
grep -c "ChangeQuickRedirect" PayPwdDialogActivity.java
# Result: 163

# Verify SecurityChecker MD5 usage
grep -n "MD5\|getFileMD5\|mVerifiedSet" SecurityChecker.java
# Lines: 539-541 (MD5 cache), getFileMD5() method
```

## Critical Architecture Issue

The signature verification mechanism (`SecurityChecker.verifyApk()`) is itself protected by PatchProxy — creating a self-referential paradox where the integrity checker can be replaced by the mechanism it's supposed to verify.

## Three Independent Code Modification Channels

| Channel | Mechanism | Entry Point |
|---------|-----------|-------------|
| PatchProxy | Java method replacement | `PatchProxy.accessDispatch()` |
| Lua VM | Script download + execution | `RpcConfigRequester.preloadLuaEngine()` |
| DynamicBundle | ClassLoader creation | `DynamicBundleHelper.java:47-72` |

Patching one channel leaves two others operational.

## References

- IACR ePrint 2026/526
- MITRE Tickets: #2005801, #2010319, #2012246, #2012250, #2012254
- Regulatory filings: CNPD, CSSF, CIRCL, HKMA, PDPC/MAS, CNNVD, CNCERT
