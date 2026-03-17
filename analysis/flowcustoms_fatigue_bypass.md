# FlowCustoms Fatigue Bypass — Deep Analysis

> Source: SchemeLauncher.java (1500+ lines), FCFatigueHelper.java
> Key method: isSchemeAndFatigueMatch() @ line 1080

## Fatigue Mechanism Overview

The "fatigue" system is a time-based URL scheme verification cooldown:

```
User clicks scheme URL → SchemeLauncher.verify()
  → isBundleIdInFatigueList(bundleId, scheme)
    → initRuleModelList(getFatigueListString(), 0)  [server-pushed rules]
    → For each rule: isSchemeAndFatigueMatch(bundleId, scheme, regexList, fatigueTime)
```

## Critical Code: isSchemeAndFatigueMatch()

```java
// SchemeLauncher.java:1080
public boolean isSchemeAndFatigueMatch(String bundleId, String scheme,
                                        List<String> regexList, long fatigueTime) {
    for (String regex : regexList) {
        String decoded = URLDecoder.decode(regex, "UTF-8");
        if (scheme.matches(decoded)) {
            // Read last verification timestamp from SharedPreferences
            long lastShowTime = SharedPreferences("scheme_bundleId_verify_fatigue")
                .getLong(bundleId + "_" + decoded, 0L);

            // KEY LOGIC: if elapsed time > fatigueTime → ALLOW
            if (System.currentTimeMillis() - lastShowTime > fatigueTime) {
                return true;  // fatigue expired → AUTO-ALLOW
            }
        }
    }
    return false;
}
```

## Bypass Vectors

### Vector 1: Default fatigueTime = -1 (Always Allow)

```java
// SchemeLauncher.java:410
this.fatigueTime = -1L;  // DEFAULT VALUE
```

When `fatigueTime = -1`:
```
System.currentTimeMillis() - lastShowTime > -1
→ Always TRUE (any positive number > -1)
→ ALL matching URLs auto-allowed
```

**Any server-configured rule with fatigueTime unset or set to -1 effectively
disables verification for matching URL patterns.**

### Vector 2: SharedPreferences Manipulation

The fatigue state is stored in SharedPreferences:
```
Name: "scheme_bundleId_verify_fatigue"
Key:  bundleId + "_" + regex
Value: timestamp (long)
```

With root access, an attacker can:
1. Clear all fatigue timestamps → reset all cooldowns
2. Set timestamps to 0 → all fatigue checks pass (time > fatigueTime)
3. Modify the fatigue rule list itself

### Vector 3: allowLaunch() is Opaque

```java
// SchemeLauncher.java:561
public int allowLaunch(android.net.Uri r11) {
    // Method dump skipped, instructions count: 342
    throw new UnsupportedOperationException(
        "Method not decompiled: SchemeLauncher.allowLaunch(android.net.Uri):int");
}
```

The CORE security check (`allowLaunch`) **failed JADX decompilation**
(342 instructions, complex control flow). This means:
- The actual URL validation logic cannot be statically audited
- It may contain intentional obfuscation
- The 3 bypass paths in `verify()` skip `allowLaunch()` entirely

### Vector 4: BUNDLE_DEFAULT_ALL Wildcard

```java
if (ruleModel.bundleIdMap.containsKey(BUNDLE_DEFAULT_ALL)) {
    if (isSchemeAndFatigueMatch(bundleId, scheme, ruleModel.regList, fatigueTime)) {
        return true;  // ANY bundleId matches
    }
}
```

Rules with `BUNDLE_DEFAULT_ALL` key apply to ALL bundle IDs, creating a
universal bypass for matching URL patterns.

### Vector 5: Regex-Based Matching (Overly Broad Patterns)

```java
if (scheme.matches(decoded)) {  // Java regex match
```

If server-pushed regex patterns are too broad (e.g., `.*`, `https://.*`),
they could match legitimate attack URLs.

## Fatigue Configuration Source

```
fatigueListString = getStringFromSp(FATIGUE_BUNDLEID_SCHEME)
```

The fatigue rules come from SharedPreferences key `"fatigue_bundleid_scheme"`,
which is populated by server-pushed configuration. This means:
- The vendor controls which URLs bypass verification
- Server misconfiguration could open bypass paths
- The rules can change without app update

## Behavioral Analytics

When fatigue triggers, the app logs to analytics:
```java
// addBehavorWhenHitFatigue
behavor.setSeedID("a248.b6016.c19574.d83102");
behavor.addExtParam("scheme_url", url);
behavor.addExtParam("bundleId", bundleId);
```

This means EVERY URL that triggers the fatigue bypass is tracked and reported
to the server, including the full scheme URL and source bundle ID.

## Circuit Breaker (FCFatigueHelper)

```java
CONFIG_KEY_ENABLE_STARTAPP_CIRCUIT_BREAKER = "fc_enable_startapp_circuit_breaker"
CONFIG_KEY_CIRCUIT_BREAKER_CONFIG = "fc_circuit_breaker_config"
```

A separate "circuit breaker" mechanism exists in `FCFatigueHelper` for `startApp`
operations. When too many startApp calls occur, the circuit breaker may
trigger — but this is also server-configurable and can be disabled.

## Relationship to CVE-1 (DeepLink Bypass)

The fatigue mechanism adds another dimension to the DeepLink bypass chain:
```
Attacker URL → ds.alipay.com redirect (CVE-6)
  → SchemeLauncher.verify()
    → isUriMatchInnerBundleId() [Bypass 1]
    → isUriMatchNewInnerRegEx() [Bypass 2]
    → isBundleIdInFatigueList() [Bypass 4 — fatigue]
    → isNativeLandingPage() [Bypass 3]
    → allowLaunch() [OPAQUE — cannot audit]
```

The fatigue bypass is the **4th bypass path** not previously documented.
Combined with the 3 known bypasses, there are now **4 confirmed bypass paths**
in SchemeLauncher.verify().

---

## allowLaunch() — Fully Decompiled via Smali (704 lines)

JADX failed to decompile this method (342 bytecode instructions → 704 smali lines).
Using apktool's baksmali output, the COMPLETE security decision flow is now revealed.

### Security Decision Flow (11 return points)

```
allowLaunch(Uri uri) → int (0=deny, 1=allow)
│
├─ [1] PatchProxy check → vendor can remotely override ENTIRE method
│
├─ [2] isNeedVerify("SchemeNeedVerify") from SharedPreferences
│      if FALSE → return 1 (ALLOW) — master bypass switch!
│
├─ [3] "inner scheme" check
│      if internal scheme → return 1 (ALLOW)
│
├─ [4] Android < 5.0 check
│      "under 5.0" → return 1 (ALLOW) — version bypass
│
├─ [5] Extract appId from URI query parameters
│
├─ [6] isMatchNewSchemeVerifyReg() — new generation regex whitelist
│      if match → return 1 (ALLOW) — regex whitelist bypass
│
├─ [7] isMatchedSchemeVerifyRegBlackList() — blacklist check
│      if match → return 0 (DENY) + log behavior
│
├─ [8] isMatchOldSchemeVerifyReg() — legacy regex whitelist
│      if match → log "allowLaunch, match verifyReg online"
│      → return 1 (ALLOW) — legacy regex bypass
│
├─ [9] isBundleIdInBlackList(bundleId, scheme)
│      if in blacklist → log "allowLaunch, isInBlack="
│      → decision based on blacklist result
│
├─ [10] isBundleIdInFatigueList(bundleId, scheme)
│       if in fatigue list → log "allowLaunch, isInFatigue="
│       → return 1 (ALLOW) — fatigue bypass (our 4th bypass)
│       → addBehavorWhenHitFatigue() logs to analytics
│
└─ [11] "scheme is null" → default return
```

### Total Bypass Paths in allowLaunch (6 discovered)

| # | Bypass | Type | Evidence |
|---|--------|------|----------|
| 1 | PatchProxy remote override | Remote | `ChangeQuickRedirect` field at method start |
| 2 | SchemeNeedVerify=false | Config | SharedPreferences master switch |
| 3 | Internal scheme detection | Implicit | "inner scheme" log message |
| 4 | Android < 5.0 | Version | "under 5.0" check |
| 5 | New/Old regex whitelist match | Whitelist | isMatchNewSchemeVerifyReg + isMatchOldSchemeVerifyReg |
| 6 | Fatigue list match | Timing | isBundleIdInFatigueList (fatigueTime=-1 always true) |

### Combined with verify() bypasses

```
verify() {
  → isUriMatchInnerBundleId()     [bypass A — skips allowLaunch entirely]
  → isUriMatchNewInnerRegEx()     [bypass B — skips allowLaunch entirely]
  → allowLaunch(uri)              [6 internal bypasses discovered]
  → isNativeLandingPage()         [bypass C — post-check override]
  → checkForBiz()                 [bypass D — business logic override]
}
```

**Grand total: 10 bypass paths** (4 in verify() + 6 in allowLaunch())

### Critical: SchemeNeedVerify Master Switch

```smali
const-string v0, "SchemeNeedVerify"
invoke-virtual {p0, v0}, ...getStringFromSp(...)
invoke-virtual {p0, v0}, ...isNeedVerify(...)
if-eqz v0, :cond_2    // if needVerify=false → skip ALL checks
const-string/jumbo v1, "needVerify false"
return v3              // return 1 (ALLOW)
```

If `SchemeNeedVerify` is set to false in SharedPreferences (server-configurable),
ALL URL scheme verification is disabled. This is a **global kill switch** for
the entire FlowCustoms protection layer.
