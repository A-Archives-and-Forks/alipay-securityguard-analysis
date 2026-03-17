# Full Scan: permit() Returns Null — 396 of 408 BridgeExtensions (97%)

> Scan date: 2026-03-17
> Source: jadx_output of Alipay v10.8.30.8000
> Method: grep -A5 "public Permission permit()" | check "return null"

## Statistics

- **Total BridgeExtension classes with permit()**: 408
- **Classes where permit() returns null**: 396 (97.1%)
- **Classes with actual permission checks**: 12 (2.9%)
- **High-risk classes (payment/auth/data/NFC)**: 62

## Impact

In the Ariver framework, `DefaultAccessController.java:132`:
```java
if (guard2 != null && guard2.permit() != null) {
    z = this.asyncInterceptJsapi(guard2.permit(), accessor);
}
```
When `permit()` returns null, `asyncInterceptJsapi()` is **NOT called**.
This means **97% of all JSBridge APIs have no permission check**.

## High-Risk Categories (62 classes)

### Payment (6 classes — NO permission check)
- `TradePayBridgeExtension` — Payment SDK invocation
- `PayBridgeExtension` — Payment operations  
- `BindCardBridgeExtension` — Card binding
- `GamePaymentExtension` — Game payments
- `H5ScenePayBridgeExtension` — Scene-based payments
- `DCEPWalletBridgeExtension` — **Digital Yuan (e-CNY) wallet**

### Authentication (5 classes)
- `LoginExtension` — Login operations
- `HavanaLoginExtension` — Havana auth system
- `MyBankLoginExtension` — MyBank login
- `AliAutoLoginBridgeExtension` — Auto-login
- `VerifyIdentityBridgeExtension` — Identity verification

### NFC/Contactless (3 classes)
- `NFCBridgeExtension` — NFC operations
- `NfcPayExtension` — NFC payment
- `NfcTapShareExtension` — NFC tap sharing

### Contacts/Social (4 classes)
- `AddPhoneContactBridgeExtension` — Add contacts
- `SocialH5ContactExtension` × 2 — Social contacts
- `SocialBundleContactsExtension` — Contact bundles

### Device Access (6 classes)
- `ScanBridgeExtension` / `ScanCodeBridgeExtension` — Camera/scanner
- `ClipboardBridgeExtension` — Clipboard access
- `MakePhoneCallBridgeExtension` — Phone calls
- `BluetoothScannerExtension` — BT scanning
- `BluetoothPermissionExtension` — BT permissions

### Security/Auth (7 classes)
- `SecurityExtension` — IoT security
- `SecurityBodyWuaBridgeExtension` — SecurityGuard WUA
- `SecurityUserInfoExtension` — User security info
- `OpenAuthExtension` — OAuth
- `TBAuthorizeBridgeExtension` / `TBAuthorizeBridge` — TB auth
- `PreAuthExtension` — Pre-authorization

### Data/Storage (6 classes)
- `APDataStorageBridgeExtension` × 2 — App data storage
- `TinyAppStorageBridgeExtension` — TinyApp storage
- `FileBridgeExtension` — File operations
- `DownloadFileBridgeExtension` — File downloads
- `UploadFileBridgeExtension` — File uploads

### Financial (4 classes)
- `DCEPWalletBridgeExtension` — Digital Yuan wallet
- `TransferSearchBridgeExtension` — Transfer search
- `WalletUserInfoExtension` — Wallet user info
- `WalletConfigBridgeExtension` — Wallet configuration
