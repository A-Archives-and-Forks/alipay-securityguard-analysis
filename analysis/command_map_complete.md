# SecurityGuard Complete Native Command Map (78 Commands)

> Auto-extracted from 5 modules: sgmain, sgsecurity, sgmiddletier, sgmisc, sgnocaptcha
> All route through: `JNICLibrary.doCommandNative(int, Object...)`

## sgmain (Core Framework) — 24 Commands

| ID | Interface | Function | Class |
|----|-----------|----------|-------|
| 10101 | Init | SDK initialization + device registration | SecurityGuardMainPlugin |
| 10401 | ISecureSignatureComponent | **Request signing** | C0032→C0044 |
| 10501 | IDynamicDataEncryptComponent | Dynamic data encrypt/decrypt | C0028 |
| 10502 | IDynamicDataStoreComponent | Dynamic data store (write/delete) | C0029 |
| 10503 | IDynamicDataStoreComponent | Dynamic data store (read/exists) | C0029 |
| 10601 | IStaticDataEncryptComponent | **Static data encryption** | C0033 |
| 10602 | IStaticDataStoreComponent | Static data store (read by index) | C0034 |
| 10603 | IStaticDataStoreComponent | Static data store (read by key) | C0034 |
| 10604 | IStaticDataStoreComponent | Static data store (operation) | C0034 |
| 10605 | IStaticKeyEncryptComponent | **Static key encrypt/decrypt** | C0035 |
| 10901 | IDataCollectionComponent | Data collection control | C0013 |
| 11601 | IOpenSDKComponent | OpenSDK operations | C0030 |
| 11901 | IAtlasEncryptComponent | Atlas bundle encryption | C0024 |
| 12101 | ISafeTokenComponent | **Safe token generate/verify** | C0031 |
| 12102 | ISafeTokenComponent | Safe token batch operations | C0031 |
| 12501 | ILiteVMComponent | **Create LiteVM/AVMP instance** | C0038 |
| 12502 | ILiteVMComponent | Reload LiteVM bytecode | C0038 |
| 12503 | ILiteVMComponent | Destroy LiteVM instance | C0038 |
| 12605 | EdgeComputing | Edge task check | ECMiscInfo |
| 12611 | EdgeComputing | Edge activity report | ECMiscInfo |
| 12801 | ICompatComponent | Compatibility query | C0026 |

## sgsecurity (Security Body) — 17 Commands

| ID | Function | Class |
|----|----------|-------|
| 10701 | Security body init | a |
| 11001 | Unknown security op | d |
| 20101-20105 | **Device security operations** (UMID/fingerprint) | b |
| 20109 | Lifecycle: onResume callback | LifeCycle |
| 20110-20111 | Device security operations | b |
| 20112 | Lifecycle: activity monitoring | LifeCycle |
| 20113 | **Invocation handler adapter** (dynamic proxy) | InvocationHandlerAdapter |
| 20401 | Security body core operation | c |
| 20801-20803 | **Device data collection** | d |
| 22301-22304 | **DeviceSecuritySDK** (UMID init/token/env) | DeviceSecuritySDKImpl |

## sgmiddletier (Middle Tier) — 32 Commands

| ID | Function | Class |
|----|----------|-------|
| 12610 | Edge computing (middletier) | a |
| 13501 | Data report (middletier) | d |
| 13701-13702 | **Device info reporting** | b |
| 60201-60206 | **WUA (device proof) generation** | c |
| 60401 | **AVMP SafeToken operations** (save/remove/encrypt/decrypt/sign) | b |
| 60402 | **OTP generation** | b |
| 70101-70102 | MiddleTier generic ops | a |
| 70201 | **Create AVMP instance** (middletier path) | a |
| 70202 | **Invoke AVMP method** (middletier path) | a |
| 70203 | Destroy AVMP instance | a |
| 70204 | Invoke AVMP v2 | a |
| 70501-70503 | Risk control setup | a |
| 70504 | **Risk control result report** (HTTP 419/420) | FCComponent |
| 70505 | Risk control check | a |
| 70602 | **SGWindowManager** (security window) | SGWindowManager |
| 70701-70702 | MiddleTier ops | a |
| 70901-70902 | MiddleTier ops | a |
| 71101 | MiddleTier op | a |
| 71501 | MiddleTier op | a |

## sgnocaptcha (CAPTCHA) — 4 Commands

| ID | Function | Class |
|----|----------|-------|
| 40101-40104 | **NoCaptcha** (invisible CAPTCHA validation) | NoCaptchaComponent |

## sgmisc (Miscellaneous) — 5 Commands

| ID | Function | Class |
|----|----------|-------|
| 50101 | Misc operation | b |
| 50201-50204 | Misc data operations | e |

## Command ID Ranges Summary

```
10xxx — sgmain core (crypto, storage, signing, data collection)
11xxx — sgmain extended (OpenSDK, Atlas)
12xxx — sgmain VM (LiteVM/AVMP, SafeToken, EdgeComputing, Compat)
13xxx — data reporting
20xxx — sgsecurity (device security, UMID, lifecycle)
22xxx — DeviceSecuritySDK
40xxx — sgnocaptcha (CAPTCHA)
50xxx — sgmisc
60xxx — sgmiddletier (WUA, SafeToken, OTP)
70xxx — sgmiddletier (AVMP, risk control, window manager)
71xxx — sgmiddletier extended
```

## Critical Signing Flow

```
App: InnerSignImpl.avmpSign(input)
  → IAVMPGenericComponent.createAVMPInstance("mwua", "sgcipher")
    → sgmiddletier: doCommand(70201, ["mwua", "sgcipher"])
    → sgmain: doCommand(12501, [...])  [alternative path]
  → invokeAVMP("sign", params)
    → sgmiddletier: doCommand(70202, [instanceId, "sign", ...])
    → sgmain: doCommand(12504, [...])  [alternative path]
  → Returns opaque Base64 token

WUA Token: IMiddleTierGenericComponent.getWua(params)
  → sgmiddletier: doCommand(60201-60206, params)
  → Returns device identity proof string

Safe Cookie: ISecureSignatureComponent.getSafeCookie(input, appKey, authCode)
  → doCommand(10401, [paramMap, appKey, requestType, authCode])  → sign
  → doCommand(10601, [16, appKey, signedData, authCode])  → encrypt
  → "AE001" + encrypted result
```
