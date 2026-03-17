# SecurityGuard Behavior Monitoring System — Complete Analysis

## Architecture Overview

```
┌─────────────────────────────────────────────────────────────┐
│                    SecurityGuardMainPlugin                   │
│                    onPluginLoaded()                          │
│                         │                                   │
│                    C0015.m57(context)                        │
│                    (3s delay timer)                          │
│                         │                                   │
│              ┌──────────┴──────────┐                        │
│              │                     │                        │
│     OrangeConfig Check      BroadcastReceiver               │
│     (Remote Kill Switch)    (C0016)                          │
│     key: "132"              22 events                       │
│              │                     │                        │
│              │              ┌──────┴──────┐                 │
│              │         onReceive()   Buffer (≥10)           │
│              │              │             │                 │
│              │         C0017 record  m59() flush            │
│              │         (index+timestamp)  │                 │
│              │                     ┌──────┴──────┐          │
│              │              UserTrackMethodJniBridge         │
│              │              addUtRecord("100184")            │
│              │                     │                        │
│              │              ┌──────┴──────┐                 │
│              │         [sgmain]    [sgsecurity]              │
│              │         JNI bridge   UserTrackBridge          │
│              │              │       UT Mini SDK              │
│              │              │       Page_EcoOneSDK           │
│              │              └──────┬──────┘                  │
│              │                     │                        │
│              │              doCommand(13701)                 │
│              │              Data Report Thread ("SGNE")      │
│              │              ThreadPool(1, queue=30)           │
│              │                     │                        │
│              │              ┌──────┴──────┐                 │
│              │         ACCS → MTOP → HTTP                    │
│              │         (priority fallback)                   │
│              └─────────────┴──────────────┘                 │
└─────────────────────────────────────────────────────────────┘
```

## 22 Monitored Events — Complete Mapping

### Category 1: Screen State (index 0-1)
| Index | Action | Description | Privacy Impact |
|-------|--------|-------------|----------------|
| 0 | `android.intent.action.SCREEN_OFF` | Screen turned off | Medium — tracks usage patterns |
| 1 | `android.intent.action.SCREEN_ON` | Screen turned on | Medium — tracks wake times |

### Category 2: App Lifecycle (index 2-3)
| Index | Action | Description | Privacy Impact |
|-------|--------|-------------|----------------|
| 2 | `com.alibaba.action.ENTER_FOREGROUND` | App entered foreground | High — tracks app switching |
| 3 | `com.alibaba.action.ENTER_BACKGROUND` | App entered background | High — knows when user leaves |

### Category 3: System Settings (index 4-5)
| Index | Action | Description | Privacy Impact |
|-------|--------|-------------|----------------|
| 4 | `android.intent.action.AIRPLANE_MODE` | Airplane mode toggled | Medium — detects network isolation |
| 5 | `android.intent.action.TIME_SET` | System time changed | High — anti-fraud (time manipulation detection) |

### Category 4: Screen Capture (index 6-7)
| Index | Action | Description | Privacy Impact |
|-------|--------|-------------|----------------|
| 6 | `com.alibaba.action.SCREEN_SHOT` | Screenshot taken | **Critical** — knows when user screenshots |
| 7 | `com.alibaba.action.SCREEN_RECORD` | Screen recording started/stopped | **Critical** — detects recording tools |

### Category 5: Bluetooth (index 8-10)
| Index | Action | Description | Privacy Impact |
|-------|--------|-------------|----------------|
| 8 | `android.bluetooth.adapter.action.STATE_CHANGED` | BT adapter on/off | Medium — device state |
| 9 | `android.bluetooth.adapter.action.ACL_CONNECTED` | BT device connected | High — tracks peripherals |
| 10 | `android.bluetooth.adapter.action.ACL_DISCONNECTED` | BT device disconnected | High — tracks peripherals |

### Category 6: Telephony (index 11-12)
| Index | Action | Description | Privacy Impact |
|-------|--------|-------------|----------------|
| 11 | `android.intent.action.PHONE_STATE` | Call state changed (ringing/offhook/idle) | **Critical** — knows when calls happen |
| 12 | `android.intent.action.HEADSET_PLUG` | Headset plugged/unplugged | Medium — physical state |

### Category 7: Clipboard (index 13)
| Index | Action | Description | Privacy Impact |
|-------|--------|-------------|----------------|
| 13 | `com.alibaba.action.PrimaryClipChanged` | Clipboard content changed | **Critical** — clipboard monitoring |

### Category 8: Network (index 14)
| Index | Action | Description | Privacy Impact |
|-------|--------|-------------|----------------|
| 14 | `android.net.conn.CONNECTIVITY_CHANGE` | Network connectivity changed | Medium — WiFi/mobile switch |

### Category 9: Activity Lifecycle (index 15-21)
| Index | Action | Description | Privacy Impact |
|-------|--------|-------------|----------------|
| 15 | `com.alibaba.action.LC_ON_ACT_CREATED` | Activity created | High — full navigation tracking |
| 16 | `com.alibaba.action.LC_ON_ACT_STARTED` | Activity started | High — knows every screen |
| 17 | `com.alibaba.action.LC_ON_ACT_RESUMED` | Activity resumed | High — active screen time |
| 18 | `com.alibaba.action.LC_ON_ACT_PAUSED` | Activity paused | High — interaction patterns |
| 19 | `com.alibaba.action.LC_ON_ACT_STOPPED` | Activity stopped | High — tracks navigation |
| 20 | `com.alibaba.action.LC_ON_ACT_SAVE_INSTANCE_STATE` | Activity saving state | Medium — crash/rotation detection |
| 21 | `com.alibaba.action.LC_ON_ACT_DESTROYED` | Activity destroyed | Medium — cleanup tracking |

## Data Collection Pipeline — 6 Classes

### Class 1: C0015 (Behavior Monitor)
**File**: `com.taobao.wireless.security.adapter.datacollection.в`
- **m57(context)**: Entry point — registers BroadcastReceiver for all 22 events
- **m58()**: Remote kill switch check via OrangeConfig
  - Namespace: `securityguard_orange_namespace`
  - Key: `132`
  - Default: `"0"` (disabled)
  - Value `"1"` = enabled
  - Loaded via reflection: `OrangeListener.getOrangeConfig()`
- **C0016**: BroadcastReceiver — converts event to (index, timestamp) pair
- **C0017**: Event record — `{index: int, timestamp: long}`, serialized as `"index_timestamp"`
- **m59()**: Flush buffer — concatenates records with `|` separator, sends via UserTrackMethodJniBridge
  - Event ID: `"100184"`
  - Type: `234`
  - Includes: SDK version, PID, event data string
- **Buffer**: Events buffered until 10 accumulated, then batch-flushed

### Class 2: C0013 (DataCollection Controller)
**File**: `com.taobao.wireless.security.adapter.datacollection.а`
- Singleton pattern with double-checked locking
- Initializes DeviceInfoCapturer + DataReportJniBridge
- **m46(int)**: Triggers native data collection via `doCommand(10901, [type])`
- **m48/m49**: Gets user nickname (from Taobao login or SharedPreferences cache)
- **m50/m51**: Sets nickname, triggers native re-collection if changed
- Storage: `SharedPreferences("DataCollectionData")`

### Class 3: C0014 (Device Data)
**File**: `com.taobao.wireless.security.adapter.datacollection.б`
- **m52()**: Gets `android_id` from `Settings.Secure`
- **m53(file)**: Gets available storage bytes via `StatFs`
- **m55()**: Gets data directory available space

### Class 4: C0018 (Hardware Info)
**File**: `com.taobao.wireless.security.adapter.datacollection.г`
- **m60()**: Gets app version name
- **m62(feature)**: Checks hardware feature (e.g., `"android.hardware.wifi"`)
- **m63()**: Gets package name

### Class 5: C0019 (Network/UTDID)
**File**: `com.taobao.wireless.security.adapter.datacollection.д`
- **m64()**: Gets UTDID (Taobao device fingerprint)
  - Via reflection: `com.ut.device.UTDevice.getUtdid(context)`
  - Filters out invalid UTDIDs containing `"?"`

### Class 6: DeviceInfoCapturer (Router)
**File**: `com.taobao.wireless.security.adapter.datacollection.DeviceInfoCapturer`
- Routes `doCommandForString(index)` to appropriate collector
- **Command → Collector mapping**:
  | Index | Collector | Data |
  |-------|-----------|------|
  | 0 | C0018.m62 | WiFi capability ("1"/"0") |
  | 121 | C0013.m48 | User nickname |
  | 122 | C0018.m63 | Package name |
  | 123 | C0018.m60 | App version |
  | 126 | C0013.m49(64) | Unknown identifier |
  | 130 | C0014.m52 | android_id |
  | 135 | C0019.m64 | UTDID |
  | 146 | C0014.m55 | Available storage |

## Data Reporting Pipeline

### C0020 (Report Scheduler)
- Thread pool: 1 thread, queue capacity 30, named "SGNE"
- Overflow policy: `DiscardPolicy` (silently drops if queue full)
- **m66(plugin)**: Initializes thread pool + gets router reference
- **m67(record)**: Submits report record to thread pool
- **m68()**: Checks if reporting is enabled via `doCommand(13702)`
- **doCommand(13701)**: Sends report with 6 params:
  1. String: report data
  2. int: report type
  3. int: sub-type
  4. int: flags
  5. long: timestamp1
  6. long: timestamp2

### C0023 (Report Record)
- Fields (Cyrillic-obfuscated):
  - `f70` (а/String): Report payload
  - `f71` (б/int): Report type ID
  - `f72` (в/int): Sub-type
  - `f73` (г/int): Flags
  - `f74` (д/long): Start timestamp
  - `f75` (е/long): End timestamp

### UserTrackBridge (sgsecurity)
- Sends events to UT Mini SDK (`com.ut.mini`)
- Page: `"Page_EcoOneSDK"`, EventID: `19997`
- Includes in each report:
  - `pn`: Process name (from `/proc/<pid>/cmdline`)
  - `pid`: Process ID
  - `ppid`: Thread ID
  - `pa`: Package name
  - `ct`: Current time (milliseconds)
  - `ctu`: High-precision time (microseconds via nanoTime)

## Security Implications

### 1. Comprehensive User Activity Profiling
The 22-event system creates a complete behavioral profile:
- When the user wakes/sleeps their phone
- Every app screen visited (Activity lifecycle)
- Call patterns (PHONE_STATE)
- Clipboard usage (potential password/sensitive data detection)
- Screenshot/recording detection (anti-leakage)

### 2. Remote Activation
The OrangeConfig kill switch (`key=132`) means this monitoring can be:
- Enabled silently by server push
- Disabled without app update
- Targeted per-user or per-region

### 3. Batch Reporting
Events are buffered (10 per batch) and sent via prioritized channels:
1. ACCS (persistent connection) — real-time
2. MTOP RPC — reliable
3. HTTP direct — fallback

### 4. Anti-Fraud Correlation
Time change detection (index 5) + airplane mode (index 4) + screen recording (index 7) together enable detection of:
- Replay attacks (time manipulation)
- Network isolation (offline fraud attempts)
- Evidence gathering (screen recording)

### 5. Cross-Process Tracking
Activity lifecycle events (index 15-21) are broadcast via custom intents (`com.alibaba.action.*`), enabling the main process to track activities across all app processes.

## Bypass Techniques

### 1. Disable via OrangeConfig Hook
```javascript
Java.perform(function() {
    // Force monitoring to always be disabled
    var C0015 = Java.use("com.taobao.wireless.security.adapter.datacollection.C0015");
    // m58 is the kill switch check
    C0015.m58.implementation = function() { return false; };
});
```

### 2. Block BroadcastReceiver Registration
```javascript
Java.perform(function() {
    var Context = Java.use("android.content.Context");
    var orig = Context.registerReceiver.overload(
        "android.content.BroadcastReceiver", "android.content.IntentFilter");
    orig.implementation = function(receiver, filter) {
        if (receiver.$className.indexOf("C0016") !== -1 ||
            receiver.$className.indexOf("datacollection") !== -1) {
            console.log("[BLOCKED] SG behavior monitor registration");
            return null;
        }
        return orig.call(this, receiver, filter);
    };
});
```

### 3. Block Data Reporting
```javascript
Java.perform(function() {
    var JNI = Java.use("com.taobao.wireless.security.adapter.JNICLibrary");
    var orig = JNI.doCommandNative;
    JNI.doCommandNative.implementation = function(cmd) {
        if (cmd === 13701) {
            console.log("[BLOCKED] Data report suppressed");
            return null;
        }
        return orig.apply(this, arguments);
    };
});
```

### 4. Intercept UserTrack
```javascript
Java.perform(function() {
    try {
        var UT = Java.use("com.alibaba.wireless.security.framework.utils.UserTrackMethodJniBridge");
        UT.addUtRecord.implementation = function() {
            console.log("[UT] Blocked report: " + arguments[0]);
            // Don't call original — silently drop
        };
    } catch(e) {}
});
```
