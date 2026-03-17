Subject: Re: [MITRE Ticket #2005801] Supplemental Evidence for CVE-1: 10 Bypass Paths in URL Scheme Verification

---

Dear MITRE CVE Team,

This is supplemental evidence for CVE-1 (Ticket #2005801) — DeepLink URL Scheme Access Control Bypass (CWE-939, CVSS 9.1).

Through continued reverse engineering, we have fully decompiled the core URL scheme security method `SchemeLauncher.allowLaunch()`, which JADX failed to decompile (342 bytecode instructions). Using apktool/baksmali, we reconstructed the complete method (704 lines of Smali code), revealing **10 distinct bypass paths** — significantly more than originally reported.

---

## Summary of 10 Bypass Paths

### Category 1: verify() Level (4 paths — skip allowLaunch() entirely)

| # | Bypass | Mechanism | Code Reference |
|---|--------|-----------|---------------|
| A1 | Inner Bundle ID Whitelist | `isUriMatchInnerBundleId()` → `mIsAllowScheme=1` | SchemeLauncher.java:1432 |
| A2 | Inner Regex Whitelist | `isUriMatchNewInnerRegEx()` → `mIsAllowScheme=1` | SchemeLauncher.java:1436 |
| A3 | Native Landing Page Override | `isNativeLandingPage()` when `outOfControlSwitch != "0"` | SchemeLauncher.java:1442 |
| A4 | Business Logic Override | `checkForBiz()` returns >= 0 → overrides decision | SchemeLauncher.java:1446 |

### Category 2: allowLaunch() Level (6 paths — Smali reconstruction)

| # | Bypass | Mechanism | Severity |
|---|--------|-----------|----------|
| **B2** | **Global Kill Switch** | `SchemeNeedVerify=false` in SharedPreferences → **ALL URL verification disabled** | CRITICAL |
| **B6** | **Fatigue Logic Flaw** | Default `fatigueTime=-1` → condition `currentTime - lastTime > -1` is **always true** | HIGH |
| B1 | Remote Override | `ChangeQuickRedirect`/PatchProxy field → vendor can modify entire method remotely | HIGH |
| B5 | Dual Regex Whitelist | Server-pushed new + legacy regex lists → auto-allow matching URLs | MEDIUM |
| B3 | Internal Scheme | Schemes tagged "inner" → unconditional allow | LOW |
| B4 | Legacy Version Skip | Android < 5.0 → unconditional allow | LOW |

---

## Critical Finding 1: SchemeNeedVerify Global Kill Switch (B2)

The `SchemeNeedVerify` value is **server-configured and stored locally**:

```java
// AlipayApplication.java:914
String config3 = configService.getConfig("SchemeNeedVerify");
E("SchemeNeedVerify", config3);  // saves to SharedPreferences
```

In `allowLaunch()` (Smali line 110):
```
const-string v0, "SchemeNeedVerify"
invoke-virtual → isNeedVerify() → if false → return 1 (ALLOW ALL)
```

When this server-pushed flag is `false`, **every URL scheme is automatically allowed** without any verification. This represents a single point of failure that can disable the entire FlowCustoms protection layer.

**Attack vector**: If the server configuration endpoint is compromised or manipulated (e.g., via MITM — enabled by CVE-7's lack of certificate pinning), an attacker could push `SchemeNeedVerify=false` to disable all URL scheme verification across all affected devices.

---

## Critical Finding 2: Fatigue Time Logic Flaw (B6)

The fatigue mechanism contains a code-level logic defect:

```java
// SchemeLauncher.java:410
this.fatigueTime = -1L;  // DEFAULT VALUE

// SchemeLauncher.java:1080 — isSchemeAndFatigueMatch()
if (System.currentTimeMillis() - lastShowTime > fatigueTime) {
    return true;  // ALLOW
}
```

When `fatigueTime = -1` (the default), the condition `System.currentTimeMillis() - lastShowTime > -1` is **mathematically always true** (any positive number exceeds -1). This means any URL matching a fatigue rule with default configuration is **permanently auto-allowed**.

This is not a configuration issue — it is a **code defect** in the default initialization of `fatigueTime`.

---

## Impact on CVE-1 Assessment

These findings strengthen CVE-1 in several ways:

1. **Reduced Attack Complexity**: The original CVE-1 described DeepLink exploitation requiring specific URL construction. The 10 bypass paths demonstrate that the defensive layer itself has systemic weaknesses, making exploitation easier than initially assessed.

2. **Defense-in-Depth Failure**: The `SchemeLauncher` is the PRIMARY defense against malicious URL schemes. With 10 bypass paths (4 in `verify()` + 6 in `allowLaunch()`), the defense-in-depth principle is comprehensively violated.

3. **Combined with CVE-6**: The `ds.alipay.com` open redirect (CVE-6, CVSS 9.3) enables whitelisted domain access. Combined with these 10 bypass paths, an attacker has multiple redundant paths to achieve URL scheme exploitation.

4. **Remote Disablement**: Both `SchemeNeedVerify` (B2) and `PatchProxy` (B1) allow the vendor or an attacker (via MITM) to remotely disable URL verification — without user consent or app store review.

---

## Methodology Note

The `allowLaunch()` method could not be decompiled by JADX v1.5.1 (error: "Method not decompiled, instructions count: 342"). We used apktool v2.12.1's baksmali to extract Dalvik bytecode, then manually analyzed the 704-line Smali output to reconstruct the complete control flow.

## Supporting Materials

- Complete `allowLaunch()` Smali output (704 lines): Available upon request
- `SchemeLauncher.java` decompiled source (1500+ lines): Available upon request
- `AlipayApplication.java:914` showing SchemeNeedVerify server configuration: Available upon request

## Researcher Information

- **Name:** Jiqiang Feng
- **Organization:** Innora AI Security Research
- **Email:** feng@innora.ai

Best regards,
Jiqiang Feng
Innora AI Security Research
