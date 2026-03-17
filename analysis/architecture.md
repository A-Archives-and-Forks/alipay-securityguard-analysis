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

## sgmiddletier Complete Component Registry

| Interface | Implementation | Commands | Function |
|-----------|---------------|----------|----------|
| IMiddleTierGenericComponent | d.b.a | 70101-70102, 70501-70505, 70701-70902, 71101, 71501 | WUA generation, risk control |
| IFCComponent | FCComponent | 70504 | Flow Control (HTTP 419/420 handling) |
| IUnifiedSecurityComponent | d.d.a | — | Unified signing/encryption |
| ISensorComponent | d.c.a | — | Sensor/behavior data collection |
| IAVMPSafeTokenComponent | d.a.b | 60401-60402 | Safe token (encrypt/decrypt/sign/OTP) |
| IAVMPSoftCertComponent | d.a.c | 60201-60206 | Software PKI (CSR/sign/verify/install cert) |
| IAVMPGenericComponent | d.a.a | 70201-70204 | AVMP bytecode VM execution |
| SGWindowManager | SGWindowManager | 70602 | Security window overlay |

## Device Fingerprint Collection Map (29 items)

| Index | Data Collected | Privacy Impact |
|-------|---------------|----------------|
| 0 | Screen resolution | Low |
| 1 | DPI density | Low |
| 2 | Screen size | Low |
| 3 | Device type (Phone/Tablet/TV) | Low |
| 4 | CPU architecture | Low |
| 5 | SIM operator name | Medium |
| 6 | HTTP proxy detection | Medium (anti-analysis) |
| 8 | Storage space | Low |
| 10 | Timezone | Low |
| 11 | Default font size | Low |
| 12 | Taobao login nickname | **High** |
| 13 | App label | Low |
| 14 | First install time | Medium |
| 15 | Last update time | Medium |
| 16-17 | Emulator/root detection flags | Medium (anti-analysis) |
| 18 | AppKey from SecurityGuard store | Medium |
| 19 | All requested permissions | Medium |
| 20 | Installed app list | **High** |
| 21 | Android system signature hash | Medium |
| 22 | Global user data | **High** |
| 23 | MediaDrm device unique ID (Widevine) | **High** (cross-app tracking) |
| 24 | Build fingerprint | Medium |
| 25 | Persistent UUID (survives reinstall) | **High** (persistent tracking) |
| 26 | Audio configuration | Low |
| 28 | Network info | Medium |

## Anti-Tampering Mechanisms

| Mechanism | Component | Detection Method |
|-----------|-----------|-----------------|
| View tree hashing | LifeCycle | Hash all View elements, detect UI overlay |
| Accessibility monitoring | LifeCycle.c (delegate) | Inject AccessibilityDelegate into clickable views |
| Behavior broadcast | C0015 | Monitor 22 system events (screen/BT/clipboard/etc) |
| Method proxy | InvocationHandlerAdapter | Forward all calls via doCommand(20113) |
| Malware detection | MalDetect | JNI callback OnDetectionJNI(type, name, detail) |
| Inline hooks | APSE/myhook | Patch function prologues at runtime |
| Memory protection | APSE/mprotect | Monitor page permission changes |
| Self-ptrace | APSE | Block debugger attachment |
| /proc/maps scanning | APSE | Detect Frida/Xposed/Magisk injected libraries |
| Config-as-JPG | PkgValidUtils | Hide config in res/drawable/abc_wb_textfield_exf.jpg |

## Remaining Class Analysis

### C0011 (HTTP通信层) — 1491 lines
- `m32(url, headers)` → HTTP GET请求
- `m33(url, headers, body, contentType, connectTimeout, readTimeout)` → HTTP POST请求
- `m34(url, dir)` → 文件下载
- `m35(url, connectTimeout, readTimeout, method)` → 创建URLConnection
- 特殊处理: `com.alibaba.android.rimet`(钉钉)使用TrafficStats标记
- SSL: 使用`HttpsURLConnection`, 无自定义TrustManager(用系统默认)

### HttpClientUtils (HTTP客户端工具) — 962 lines
- 自定义错误码: 1000(空响应), 1002(异常), 1003(未知主机), 1004(SSL握手错误)
- 默认超时: 22000ms
- 用于SG内部的HTTP通信(数据上报、配置下载等)

### MtopMethodJniBridge (MTOP协议桥) — 170 lines
- 通过反射调用MTOP SDK(`mtopsdk.mtop.intf.Mtop`)
- 支持`MtopBusiness`(淘宝远程业务)和`MtopBuilder`
- API: `setApiName()`, `setApiVersion()`, `setData()`, `syncRequest()`
- 用于SG向蚂蚁服务器发送RPC请求

### DataReportJniBridge (数据上报桥) — 212 lines
- `accsAvaiableBridge()` → 检查ACCS(长连接)是否可用
- `orangeAvailableBridge()` → 检查Orange(A/B测试配置)是否可用
- `mtopAvaiableBridge()` → 检查MTOP是否可用
- Cyrillic字段名: а,б,в,г,д,е,ж,з,и,й,к,л(12个!)
- 优先使用ACCS长连接上报, 降级到MTOP RPC, 再降级到HTTP直连

### SPUtility2 (安全存储工具) — 392 lines
- `readFromSPUnified(name, key, default)` → 统一SP读取
- `saveToSPUnified(name, key, value, commit)` → 统一SP写入(内部用`saveToFileUnifiedForNative`)
- `removeFromSPUnified(name, key, commit)` → 统一SP删除
- `readSS/writeSS` → 加密安全存储
- `rootDir()` → SG数据根目录
- `getTempFile(name)` → 临时文件路径
- **关键**: StaticKey等加密密钥通过此接口存储

### StrategyCenter (风控策略中心) — 622 lines
- `needFCProcessOrNot(statusCode, headers)` → 判断是否需要风控处理
- `processFCContent(statusCode, headers, callback, headerType)` → 执行风控策略
- 策略类型: BXUserReport(用户上报), 超时处理, 锁定异常处理
- 处理HTTP 419/420状态码(风控拦截)
- 支持全量处理和单条处理两种模式
- 后台线程执行策略(`Handler` + `HandlerThread`)

### UserTrackBridge (用户行为追踪桥) — 161 lines
- sgsecurity模块的用户行为数据桥接
- 通过反射调用主app的UT(UserTrack)SDK
- 上报设备安全事件到蚂蚁大数据平台

### C0042 (taobao DynamicDataStore适配器) — 277 lines
- 淘宝SDK接口的DynamicDataStore实现
- 封装sgmain的C0029(IDynamicDataStoreComponent)
- 提供`getByteArray`, `getBoolean`, `putByteArray`等方法
