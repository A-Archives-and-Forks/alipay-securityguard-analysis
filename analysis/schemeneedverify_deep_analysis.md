# SchemeNeedVerify — Deep Analysis of URL Verification Kill Switch

> Critical finding: URL scheme verification uses an OPT-IN model.
> Only schemes explicitly listed in a server-pushed configuration are verified.
> Everything else is automatically allowed.

## Complete Data Flow

```
[Server] MobileSyncDataService RPC (Protobuf)
    ↓ (via HTTPS — NO cert pinning, CVE-7)
[ConfigServiceImpl] getConfig("SchemeNeedVerify")
    ↓
[AlipayApplication:914] config3 = configService.getConfig("SchemeNeedVerify")
    ↓
[AlipayApplication:917] E("SchemeNeedVerify", config3)
    ↓
[SharedPreferences("SchemeVerifyReg")] key="SchemeNeedVerify", value="prefix1|prefix2|..."
    ↓
[SchemeLauncher constructor:446] mSchemePref = str2  (passed from caller)
    ↓
[SchemeLauncher.isNeedVerify(scheme):1070]
    split mSchemePref by "|"
    check if scheme matches any prefix
    NO MATCH → return false → SKIP ALL VERIFICATION
```

## isNeedVerify() — The Core Logic

```java
// SchemeLauncher.java:1064
public boolean isNeedVerify(String str) {
    // PatchProxy check (can be remotely overridden)
    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(mSchemePref)) {
        String[] split = str.split("|");
        for (String s : split) {
            if (mSchemePref.equals(s)) {
                return true;  // scheme IS in the verification list
            }
        }
    }
    return false;  // scheme NOT in list → NO VERIFICATION NEEDED
}
```

**Key insight**: This is an **OPT-IN** security model. Schemes must be explicitly listed
to receive verification. Any scheme not in the server-pushed list **silently bypasses
all URL verification**.

## Storage Layer

```
SharedPreferences name: "SchemeVerifyReg"
Keys stored:
  - SchemeNeedVerify       → pipe-separated list of scheme prefixes requiring verification
  - SchemeVerifyRegList    → old regex whitelist
  - SchemeVerifyNewRegList → new regex whitelist
  - SchemePeriodTime       → verification period
  - bl_bundleid_scheme     → blacklist
  - fatigue_bundleid_scheme → fatigue rules
  - SchemeVerifyRegBlackList → regex blacklist
```

All values are server-pushed via `ConfigService.getConfig()` → `AlipayApplication.E()`.

## ConfigService — Server-Side Configuration

```java
// ConfigServiceImpl.java — imports reveal RPC-based sync
import com.alipay.mobileappcommon.biz.rpc.sync.MobileSyncDataService;
import com.alipay.mobileappcommon.biz.rpc.sync.model.pb.BatchSyncDataReqPb;
import com.alipay.mobileappcommon.biz.rpc.sync.model.pb.SyncDataRespPb;
```

Configuration is fetched via **Protobuf RPC** (MobileSyncDataService). This RPC call
goes through the same HTTP transport layer that lacks certificate pinning (CVE-7).

## Attack Vectors

### Vector 1: MITM Configuration Injection (CRITICAL)

**Chain: CVE-7 (no cert pinning) → config RPC interception → SchemeNeedVerify manipulation**

```
1. Attacker positions on network (WiFi, ARP spoof, etc.)
2. No cert pinning (CVE-7) → MITM all HTTPS traffic
3. Intercept MobileSyncDataService RPC response
4. Modify "SchemeNeedVerify" value to empty string ""
5. App stores "" in SharedPreferences
6. isNeedVerify() returns false for ALL schemes
7. All URL verification disabled → CVE-1/CVE-6 attacks succeed without restriction
```

**Prerequisites**: Network position + proxy CA installed (trivial due to CVE-7)
**Impact**: Complete bypass of ALL URL scheme verification for the target device

### Vector 2: Root/ADB SharedPreferences Modification

```
1. Rooted device or ADB access
2. Edit /data/data/com.eg.android.AlipayGphone/shared_prefs/SchemeVerifyReg.xml
3. Set SchemeNeedVerify to "" (empty)
4. All subsequent URL schemes bypass verification
```

**Prerequisites**: Root access or ADB
**Impact**: Persistent bypass until next config sync

### Vector 3: First-Launch Race Condition

```
1. On fresh install, SharedPreferences is empty
2. ConfigService sync hasn't completed yet
3. mSchemePref = "" (empty from SP)
4. isNeedVerify() returns false for ALL schemes
5. During the boot window, all URL schemes are allowed
```

**Prerequisites**: Fresh install or cache clear
**Impact**: Temporary bypass during app initialization

### Vector 4: Incomplete Server-Side List

```
1. Server pushes SchemeNeedVerify = "alipays|https"
2. Attacker uses scheme "alipay://" (note: no 's')
3. "alipay" doesn't match "alipays" or "https"
4. isNeedVerify() returns false → bypass
```

**Prerequisites**: Knowledge of the verification list (observable via SP dump)
**Impact**: Targeted bypass for unlisted scheme prefixes

## Severity Assessment

| Factor | Assessment |
|--------|-----------|
| **Design pattern** | OPT-IN (deny-by-default would be secure) |
| **Server dependency** | Single point of failure — server config determines security |
| **Transport security** | Config fetched over HTTPS with no pinning (CVE-7) |
| **Persistence** | Stored in SharedPreferences — survives app restart |
| **Remote manipulation** | PatchProxy on `isNeedVerify()` AND `getStringFromSp()` |
| **Audit visibility** | Logged to "AlipayApplication" trace logger (visible to attacker) |
| **Combined CVE impact** | CVE-7 (no pinning) → config MITM → SchemeNeedVerify bypass → CVE-1/6 |

## Correction from Earlier Analysis

Previous analysis described SchemeNeedVerify as a "boolean kill switch" (true/false).

**Corrected understanding**: SchemeNeedVerify is a **pipe-separated list of scheme prefixes
requiring verification**. The actual vulnerability is worse than a kill switch:
- A kill switch requires explicitly turning security OFF
- The opt-in model means security is OFF BY DEFAULT for any unlisted scheme
- The server must maintain a COMPLETE list to provide coverage
- Any gap in the list creates an automatic bypass

## Also Server-Pushed via Same Channel

```java
// AlipayApplication.java:904-949 — ALL FlowCustoms config is server-pushed
configService.getConfig("SchemeVerifyRegList")        // old regex whitelist
configService.getConfig("SchemeVerifyNewRegList")      // new regex whitelist
configService.getConfig("SchemeNeedVerify")            // verification prefix list
configService.getConfig("SchemePeriodTime")            // verification period
configService.getConfig("bl_bundleid_scheme")          // blacklist
configService.getConfig("fatigue_bundleid_scheme")     // fatigue rules
configService.getConfig("SchemeVerifyRegBlackList")    // regex blacklist
configService.getConfig("SchemeVerifyRegNewBlackList") // new regex blacklist
```

ALL 8 FlowCustoms security configurations are fetched via the same RPC channel.
A single MITM interception point can manipulate the entire URL verification system.
