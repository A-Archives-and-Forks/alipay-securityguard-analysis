# SecurityGuard Security Findings

## Critical Findings

### 1. No Certificate Pinning (MITM Viable)
- `EmptyX509TrustManagerWrapper`: `checkServerTrusted()` is a no-op
- `X509TrustManagerWrapper`: Only validates system CA store
- Every TLS method has `ChangeQuickRedirect` bypass hook
- GM-TLS (SM2/SM4 mutual auth) is optional, controlled by grayscale switch

**Impact**: Install proxy CA → decrypt all traffic. PatchProxy can activate accept-all mode remotely.

### 2. Self-Modifying ELF (Anti-Static Analysis)
- `libsgmainso-6.6.230507.so`: Zero exports, zero imports, corrupted section headers
- Code segment encrypted at rest, decrypted in `JNI_OnLoad`
- Standard r2/IDA analysis sees garbage — need runtime dump

**Bypass**: Frida `Process.enumerateModules()` → `Memory.readByteArray()` after `JNI_OnLoad` completes

### 3. AVMP Bytecode VM (Opaque Signing)
- Signing algorithm runs inside custom LiteVM bytecode interpreter
- VM instance name: "mwua" (Mobile WUA), cipher: "sgcipher"
- Java/Native boundary only passes opaque token
- Algorithm not in standard ARM code — custom bytecode

**Bypass**: Hook `doCommand(70202)` / `doCommand(12504)` to capture input/output pairs → pattern analysis

### 4. PatchProxy Pervasive (Remote Code Modification)
- Every BridgeExtension `permit()` has `ChangeQuickRedirect` hook
- Every TLS validation method has the hook
- Every signing method has the hook
- Server can push patches that override any security check

**Impact**: Vendor can silently modify security behavior without app store review

### 5. SM4 National Cipher (Non-Standard Encryption)
- RPC body encryption uses SM4 (Chinese GB/T 32907-2016)
- Implementation in native layer (not Java Crypto API)
- Key management through SecurityGuard command 10601/10605

### 6. Device Super-Fingerprinting
- Collects: IMEI, OAID, WiFi MAC, MediaDrm ID, audio route, all permissions, app signature, timezone, screen, SIM
- Generates persistent cross-install device ID (UMID/apdid)
- Periodically uploads to server
- Survives app reinstall (persisted in system keystore)

### 7. Inline Hook Framework (APSE/BlueShield)
- Built-in `myhook` engine for runtime function hooking
- Can hook system calls (`mprotect`, `ptrace`)
- Used defensively: detect/block Frida/Xposed/Magisk
- Also used offensively: wrap location/crypto APIs via DexAOP

### 8. Cookie Encryption in Transit
- `X-Ant-Encrypt-Entity` header signals encrypted cookie values
- Server encrypts Set-Cookie values
- Client decrypts via SecurityGuard before storing
- Prevents cookie theft even with TLS decrypted

## Vulnerability Classes

### permit() Returns Null Anti-Pattern
Found in multiple BridgeExtensions:
- `TradePayBridgeExtension.permit()` → null (payment!)
- `H5LocationExtension.permit()` → null (GPS!)
- `WalletUserInfoExtension.permit()` → null (user data!)
- `TitleBarBridgeExtension.permit()` → null (UI spoofing!)
- `ToastBridgeExtension.permit()` → null (UI spoofing!)

All five mean: no permission guard enforced for sensitive JSAPIs.

### Default-Allow Security Checks
- `LocationController.hasLBSPermission()`: returns `true` for non-TinyApp pages
- `SchemeLauncher.verify()`: 3 unconditional bypass paths
- `ee.checkBizParamPermission()`: `ta_tradePayCheck` defaults to "0" (disabled)
- `ib.java:592`: HTTP URLs return IGNORE (bypass domain check)

### Server-Controlled Security Policy
All security configurations fetched via `RVConfigService.getConfigWithProcessCache()`:
- Domain whitelists
- JSAPI permission levels
- Trade payment check enable/disable
- FlowCustoms rules
- Can be changed server-side without app update

## Critical Update: sgmain SO Code NOT Encrypted

### Discovery
`libsgmainso-6.6.230507.so` was previously reported as "self-modifying ELF" requiring runtime dump. **This was incorrect.**

**Actual state:**
- Section headers: **intentionally corrupted** (sizes set to 0x10, addresses zeroed)
- Program headers: **intact and valid** (LOAD0: 2MB r-x code segment)
- Code segment: **NOT encrypted** — valid ARM64 instructions throughout
- STRTAB: **intact** — JNI_OnLoad, RegisterExtForAndroid, LiteVM visible
- Some strings: **encrypted** (especially in sgsecurity), but many readable in sgmain

### Newly Found Strings in sgmain
- `sgcookiecheck` — Cookie security validation
- `uvm_sw2` — UVM (Universal VM) software v2
- `wkcookiesync` — WebKit cookie synchronization  
- `$tIsys_epolptrace_s` — Obfuscated ptrace anti-debug detection
- `LiteVM` — AVMP bytecode VM identifier
- `UVM_LD_LIBRARY_*` — UVM dynamic library loading (PREFIX/SUFFIX/EXTENSION/PATH)
- `__uvm_call_registry_` — UVM native function registration
- `__builtin_uvm_bind_native_callback` — UVM native callback binding
- `[insndebug]` — Debug detection logging
- `hja8796ac83accsgg84` — Hardcoded key/salt (ACCS related)
- `ju38csh37822hf3hc` — Hardcoded key/salt
- `uxd2dkwj294db3/4` — Hardcoded key/salt pair

### Obfuscated Export Names
- `HlupqL3ZAR`, `J2qaFN4xHz`, `KEl52mTtCb`, `pDhsAJkF7h` — randomized function names in .dynstr

### Bypass for Section Header Corruption
Use `r2 -e bin.segments=true` or `readelf -l` (program headers) instead of `readelf -S` (section headers).
The code can be fully analyzed using segment-based loading.

### sgsecurity vs sgmain
| Property | sgmain | sgsecurity |
|----------|--------|------------|
| Code encrypted | **NO** | **NO** (same pattern) |
| Strings encrypted | Partial (STRTAB intact, some .rodata encrypted) | **YES** (nearly all) |
| Section headers | Corrupted | Corrupted |
| Program headers | Valid | Valid |
| Static analysis | **FULLY POSSIBLE** | Limited (strings obfuscated) |

## 3-LLM Cross-Verification Results

### Verified (High Confidence)
- 78 commands via doCommandNative → **CONFIRMED** by all 3 LLMs
- permit() returning null = no guard → **CONFIRMED** (Ariver framework standard)
- AVMP/LiteVM for signing → **CONFIRMED** (known Alibaba security VM)

### Verified with Correction
- **SO "encryption"**: Code NOT encrypted, section headers corrupted. LLM1 said CORRECT, LLM2 said SUSPECT. **Verdict: Code is readable but may have lightweight instruction-level obfuscation (OLLVM)**
- **Certificate pinning**: LLM1 hallucinated "SSL_CTX_set_custom_verify in sgmain" — **verified false**. sgmain has ZERO SSL-related strings. SSL handled by libopenssl.so. Java-layer EmptyX509TrustManagerWrapper is real. **No certificate pinning confirmed.**

### LLM Hallucination Detected
- LLM1 fabricated "0x2A3F0C address" and "SSL_CTX_set_custom_verify" in sgmain — these do not exist in the binary
- This demonstrates why static code verification is essential alongside LLM analysis

## Full Claims Verification (29 claims)

### Code-Level Auto-Verified (13/13 PASS)
- A1: SecurityGuardMainPlugin.onPluginLoaded ✓
- A2: JNICLibrary.doCommandNative native method ✓
- A3: C0004 switch (41 cases for 12+ interfaces) ✓
- A4: System.loadLibrary(str) in onPluginLoaded ✓
- A5: UMIDComponent → DeviceSecuritySDKImpl ✓
- A7: "mwua" confirmed in APK resources (yw_1222_mwua) ✓
- C1: 80 unique command IDs (corrected from 78) ✓
- S1: EmptyX509TrustManagerWrapper exists in APK ✓
- S6: SharedPreferences("StaticKey") in C0035 ✓
- S7: "AE001" prefix in C0032.getSafeCookie() ✓
- S9: doCommand(20113) in InvocationHandlerAdapter ✓
- S10: OnDetectionJNI in MalDetect ✓
- B4: abc_wb_textfield_exf.jpg in PkgValidUtils ✓

### Secondary Verification (6/6 PASS)
- A6: 8 hashMap.put calls = 8 components ✓
- A8: 4 availability methods (ACCS/MTOP/Orange/HTTP) ✓
- A9: 29 switch cases in DeviceInfoCapturerFull.doCommandForString ✓
- S4: mwua resource name exists in APK ✓
- S8: 16 view/hash related calls in LifeCycle ✓
- C1 correction: 80 IDs (not 78; 12504 passed via variable)

### Corrections Applied
1. Command count: 78 → **80** (12504 passed as variable, 2 others miscounted)
2. SO encryption: Corrected — code NOT encrypted, only section headers corrupted
3. SSL pinning: Corrected — sgmain has ZERO SSL strings, no native pinning

### Verification Status: **19/19 claims verified against source code**

## Dynamic Analysis Results (emulator-5554)

### Runtime Module Map
All SG SOs confirmed loaded in memory:

| Module | Runtime Size | Base Address | Status |
|--------|-------------|--------------|--------|
| libsgmainso-6.6.230507.so | 2432KB | 0x770436c000 | Loaded, mprotect protected |
| libsgsecuritybodyso-6.6.230507.so | 1912KB | 0x76d0005000 | Loaded, mprotect protected |
| libsgmiddletierso-6.6.230507.so | 1152KB | 0x778b016000 | Loaded |
| libAPSE_1.9.41.so | 4276KB | 0x7742a8b000 | Loaded, APSE code dumped (3.5MB) |
| **libmyhook.so** | 516KB | 0x773b20f000 | **Inline hook engine** |
| **libSgCoreCpt.so** | 224KB | 0x77a2084000 | **New: SG Core Component** |
| libxriver-core.so | 5668KB | 0x7710c6e000 | Loaded |
| libBifrost.so | 5952KB | 0x770980e000 | Loaded |

### Memory Protection
- `mprotect` actively enforced by APSE/myhook on sgmain and sgsecurity code pages
- Direct memory dump via stnel blocked by access violations
- dd from /proc/mem partially works (APSE succeeded, sgmain blocked)
- Modifying page permissions triggers APSE anti-tampering (timeout/crash)

### JNI Hook Limitation
- `JNICLibrary` class not accessible via default classloader
- SG uses custom classloader loaded from ZIP modules
- Need to enumerate classloaders to find SG classes at runtime

### Previously Dumped Data (from earlier sessions)
- 46 runtime DEX files in `/data/local/tmp/dumped_dex/`
- 65 v9000 DEX files in `/data/local/tmp/v9000_dump/`
- memscan tools and results available

## Dynamic Analysis: doCommandNative Successfully Hooked

### Classloader Discovery
JNICLibrary found in classloader #6 of 10:
```
dalvik.system.PathClassLoader[DexPathList[
  [zip file "libsgmain.so"],
  nativeLibraryDirectories=[
    .../lib/arm64,
    .../app_SGLib/app_1773709757/main,
    /system/lib64
  ]
]]
```

### Runtime Command Trace
Before APSE crash, captured:
- **70102**: 27 calls (sgmiddletier generic operation — most frequent at idle)

### APSE Anti-Tampering Response
- Hook triggered SIGSEGV (signal 11, code SEGV_ACCERR)
- Crash at NativeThread (not main thread — APSE uses dedicated monitoring thread)
- Fault address in SG memory region
- APSE detected bytecode modification (JNICLibrary.doCommandNative replaced)

### Bypass Strategy
To capture all commands without APSE crashing the app:
1. Hook APSE first to disable its monitoring thread
2. Or hook at native level (Interceptor.attach on the C function, not Java method)
3. Or use `Java.registerClass` to create a proxy instead of replacing the method

## Runtime Analysis: 0 Exports, 0 Symbols Confirmed

All SG native SOs have **zero exports and zero symbols** at runtime:
- sgmain: 0 exports, 0 symbols (2.4MB code)
- sgsecurity: 0 exports, 0 symbols (1.9MB code)
- sgmiddletier: 0 exports, 0 symbols (1.1MB code)

JNI methods registered via `art::JNI::RegisterNatives` at startup.
`RegisterNatives` successfully hooked at `_ZN3art3JNIILb0EE15RegisterNativesEP7_JNIEnvP7_jclassPK15JNINativeMethodi`
but registration already completed before attach (need spawn mode).

### doCommandNative Successfully Intercepted
- Found in classloader #6 (PathClassLoader loading from libsgmain.so ZIP)
- 27x command 70102 captured before APSE killed process
- APSE response time: ~200ms (detects Java-level bytecode modification)

## Stealth Hook SUCCESS — APSE Bypassed

### Working Method
Hook `Router.doCommand` via `Java.choose()` in SG's custom classloader:
1. Enumerate classloaders → find loader #6 (loads JNICLibrary)
2. `Java.choose("SecurityGuardMainPlugin")` → get live instance
3. `instance.getRouter()` → get Router object (class: `б` Cyrillic)
4. `Java.use(router.$className).doCommand.implementation = ...`

This hooks at the Java Router level (inside SG), NOT at JNICLibrary bytecode level.
**APSE does not detect this hook** — it only monitors native code modifications.

### Captured Commands (idle state, no login)
| Command | Count | Module | Purpose |
|---------|-------|--------|---------|
| 70102 | 3+ | sgmiddletier | Generic heartbeat/polling |
| 70901 | 3+ | sgmiddletier | Periodic check |

### Limitation
Only heartbeat commands visible without login. Signing/Token/AVMP commands require authenticated session.

### Complete Working Script
Path: `/Users/anwu/Documents/code/tools/frida/sg_router_hook2.js`
Covers all 80 known command IDs with human-readable names.

## Dynamic Command Capture: SIGN + TOKEN

### Commands Successfully Triggered
| Command | Name | Trigger | Result |
|---------|------|---------|--------|
| 10401 | SIGN | signRequest(paramCtx, authCode) | SecException (invalid params) |
| 10602 | STATIC_READ | getStaticDataStoreComp() | 27+ reads during init |
| 22302 | TOKEN | getUMIDComp().getSecurityToken() | Token: `abiqRO0vVA8DAP7YdykwhzF6` |
| 70102 | GENERIC | Idle heartbeat | Repeating |
| 70901 | PERIODIC | Idle check | Repeating |

### UMID Token Captured
```
UMID Token: abiqRO0vVA8DAP7YdykwhzF6
Length: 24 characters (matches UTDID_LENGTH constant in DeviceSecuritySDKImpl)
```

### Emulator Detection Implementation (MiscUtils.isEmulator)
Checks the following Build properties:
1. `BRAND.startsWith("generic")`
2. `DEVICE.startsWith("generic")`
3. `FINGERPRINT.startsWith("generic"|"unknown")`
4. `HARDWARE.contains("goldfish"|"ranchu")`
5. `MODEL.contains("google_sdk"|"Emulator"|"Android SDK built for x86")`
6. `MANUFACTURER.contains("Genymotion")`
7. `PRODUCT.contains("sdk_google"|"google_sdk"|"sdk"|"sdk_x86"|"sdk_gphone64_arm64"|"vbox86p"|"emulator"|"simulator")`

File: `com/alipay/mobile/common/transport/utils/MiscUtils.java:858`

### SecurityGuard Interfaces Confirmed at Runtime
- `IRootDetectComponent.isRoot()` — single method, in securitybody plugin
- `ISimulatorDetectComponent.isSimulator()` — single method, in securitybody plugin
- `ILBSRiskComponent` — 4 methods: init/put/get/clear location data

## Deep Dynamic Analysis: Encryption + Simulator Bypass Confirmed

### Dynamic Encryption (10501)
```
Input:  "test_plaintext"
Output: xhBEoV1/pbHxXrRBxBRY9A== (Base64, 16 bytes decoded)
```
This confirms SM4/AES encryption is functional in the emulator environment.

### Simulator Detection BYPASSED (11001)
```
ISimulatorDetectComponent.isSimulator() → false
```
Our Build property spoofing (HARDWARE=qcom) successfully bypasses the native-level simulator detection in sgsecurity module.

### x-sgext Header Captured (70102)
```
x-sgext=JBIc038BaqcOBdvReOTlrdMt4y3qJPAt4CXqKPAu4T/wLeQr4CvhJOYo5T/jLuMs4yzjLLJ...
```
This is the SecurityGuard extension data injected into HTTP request headers.
It's generated by command 70102 (sgmiddletier generic operation).
Contains encrypted device/session fingerprint data.

### Component Availability at Runtime
| Component | Available | Can Trigger |
|-----------|-----------|-------------|
| DynamicDataEncrypt | ✅ | ✅ Encrypts data |
| StaticDataEncrypt | ✅ | ❌ SecException (needs valid key) |
| StaticKeyEncrypt | ✅ | ✅ isSecretExist works |
| AVMP/LiteVM | ❌ null | Needs MiddleTier classloader |
| SafeToken | ✅ | Available |
| SecurityBody | ✅ | Available |
| SimulatorDetect | ✅ | ✅ Returns false (bypassed!) |
| DataCollection | ✅ | ❌ Method mismatch |

### Commands Captured (Total)
| ID | Name | Count | Result |
|----|------|-------|--------|
| 10401 | SIGN | 1 | SecException (test params) |
| 10501 | DYN_ENCRYPT | 1 | `xhBEoV1/pbHxXrRBxBRY9A==` |
| 10601 | STATIC_ENCRYPT | 1 | SecException |
| 10602 | STATIC_READ | 27+ | Multiple reads |
| 11001 | SIMULATOR_DETECT | 1 | `false` |
| 22302 | TOKEN | 1 | `abiqRO0vVA8DAP7YdykwhzF6` |
| 70102 | GENERIC | many | `x-sgext=JBIc...` |
| 70901 | PERIODIC | many | `10000` (interval ms) |

## AVMP Virtual Machine: Instance Created Successfully

### doCommand(70201) — Create VM
```
Input: ["mwua", "sgcipher"]
Output: -5476376623598933845 (Long, VM instance ID)
```
VM instance successfully created in emulator environment.
This confirms the AVMP/LiteVM bytecode engine is functional.

### doCommand(70202) — Invoke VM Method
Not yet successful due to JS↔Java Long type conversion issue.
The VM ID (Long) needs to be passed correctly to the native dispatcher.

### Architecture Confirmed
```
Java: avmp.createAVMPInstance("mwua", "sgcipher")
  → doCommand(70201, ["mwua", "sgcipher"])
  → Native: sgmiddletier allocates VM, returns Long ID
  
Java: vm.invokeAVMP("sign", byte[].class, args...)
  → doCommand(70202, [vmId, "sign", retType, argsArray])
  → Native: sgmiddletier dispatches to AVMP bytecode interpreter
  → Returns: opaque signature bytes
```

## AVMP Sign: VM Created, Invoke Blocked by stnel Limitation

### Successful
- `doCommand(70201, ["mwua", "sgcipher"])` → VM ID (Long) ✅
- VM instance created successfully in emulator environment
- VM IDs observed: -5476376623598933845, -5476376623807317487, -5476376624574623528

### Blocked
- `doCommand(70202, [vmId, "sign", byte[].class, args])` → `expected a pointer`
- Root cause: stnel's JS-to-JNI bridge cannot serialize `java.lang.Class` objects inside `Object[]` arrays
- Java `Method.invoke()` reflection also blocked by same limitation
- This is a **stnel/frida engine limitation**, not a SecurityGuard protection

### Workaround Options
1. Use frida's Python bindings instead of JS (`frida.core.Script.exports`)
2. Compile a custom stnel gadget that calls invokeAVMP from native code
3. Use Xposed framework instead of stnel (full Java-level API access)
4. Hook at the native C level inside sgmiddletier.so after runtime dump
