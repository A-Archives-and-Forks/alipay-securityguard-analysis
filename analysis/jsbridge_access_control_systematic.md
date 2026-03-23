# JSBridge Access Control — Systematic permit() Null Analysis

> Date: 2026-03-23 | MITRE Ticket: #2012254 | CVEs: 5 pending
> APK: Alipay Android v10.8.30.8000

## Systematic Finding

**396 of 408 BridgeExtension classes (97.1%)** return `null` from their `permit()` method, indicating no fine-grained access control at the Bridge layer.

## Affected Critical Interfaces

| Class | Line | Function | CVSS |
|-------|------|----------|------|
| DCEPWalletBridgeExtension | 1656-1666 | Digital Yuan (e-CNY) wallet | 7.5 |
| TradePayBridgeExtension | — | Payment processing | 7.5 |
| BindCardBridgeExtension | 111-118 | Bank card binding | 7.5 |
| LoginExtension | — | Authentication | 7.5 |

## DCEP Wallet Exposed RPC Operations

```
alipay.mobile.dcep.queryOnlineBalance      — Balance query
alipay.mobile.dcep.balanceEar/balanceUnEar  — Balance freeze/unfreeze
alipay.mobile.dcep.queryBindOfflineWalletInfo — Offline wallet info
alipay.mobile.dcep.queryOfflineTransInfo     — Offline transaction info
```

## Privacy Framework Remote Control (CVSS 7.5)

- `PrivacyCoreInterceptor`: 39 `ChangeQuickRedirect` fields
- Privacy enforcement logic remotely replaceable via PatchProxy
- Runtime privacy behavior can diverge from published privacy policy

## Cross-Application Tracking (CVSS 6.5)

- Exported ContentProvider: `content://com.alipay.android.app.share`
- Exposes: `tid`, `VIRTUAL_IMEI`, `VIRTUAL_IMSI`
- No Android permissions required
- Code: `ToolUtil.java:194`

## Server-Controlled Data Collection (CVSS 7.5)

- `AGENTSWITCH` / `EDGESWITCH` (Keys.java:15)
- Set via server response (DeviceDataReponseModel.java:180)
- Controls: biometric, location, contacts collection
- No user re-consent or notification required

## Note on permit() Semantics

`permit()` returning `null` indicates lack of fine-grained access control at the Bridge layer. Whether upper-layer domain whitelisting provides supplementary access control requires runtime verification.
