# Lua VM & DynamicBundle — Two Additional Remote Code Execution Channels

> Date: 2026-03-23 | MITRE Ticket: #2012250 | CVEs: 2 pending
> APK: Alipay Android v10.8.30.8000

## Channel 2: Lua Virtual Machine (CVSS 9.8)

- **CWE**: CWE-94 (Code Injection)
- **Script download**: `RpcConfigRequester.preloadLuaEngine()`
- **Script execution**: `ScriptLauncher.executeMethod()`
- **Method replacement**: `REPLACE_RESULT_WITH_LUA = 1000` (InterceptResult.java:39)
- **Capability**: Lua scripts can replace return values of DexAOP-intercepted methods; Java reflection provides arbitrary method invocation

## Channel 3: DynamicBundle Class Loading (CVSS 8.1)

- **CWE**: CWE-494
- **Bundle download**: `DynamicBundleHelper.java:47-72`
- **ClassLoader creation**: `DynamicBundleLoader.getDynamicBundleClassLoader()`
- **Infrastructure**: 111 files in `com.alipay.instantrun.*`

## Architecture Implication

Three independent channels (PatchProxy + Lua VM + DynamicBundle) mean:
1. Remediation requires addressing all three simultaneously
2. App store review is bypassed through all three channels
3. Runtime behavior can diverge from audited code through any channel
