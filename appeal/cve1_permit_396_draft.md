Subject: Re: [MITRE Ticket #2005801] Critical Update for CVE-1: 396 of 408 JSBridge APIs Have No Permission Check (97%)

---

Dear MITRE CVE Team,

This is a critical update to CVE-1 (Ticket #2005801) — DeepLink URL Scheme Access Control Bypass (CWE-939).

Our original report identified 5 specific BridgeExtension classes where `permit()` returns null (no permission check). A comprehensive automated scan of ALL 408 BridgeExtension classes in the application now reveals that **396 (97.1%) return null from permit()**, meaning virtually the entire JSBridge API surface operates without access control.

---

## Finding: Systemic Absence of Permission Checks

### Methodology

We scanned all Java classes implementing `BridgeExtension` in the decompiled APK (v10.8.30.8000) for the `permit()` method return value:

```
Total BridgeExtension classes with permit(): 408
Classes where permit() returns null:         396 (97.1%)
Classes with actual permission checks:        12 (2.9%)
```

### Framework Behavior

As previously documented, in the Ariver framework (`DefaultAccessController.java:132`):

```java
if (guard2 != null && guard2.permit() != null) {
    z = this.asyncInterceptJsapi(guard2.permit(), accessor);
}
```

When `permit()` returns null, `asyncInterceptJsapi()` is NOT invoked — **no permission check occurs**.

### Scale of Impact

Our original CVE-1 submission identified 5 unprotected extensions. The actual number is **396**, spanning every functional category:

| Category | Unprotected Classes | Examples |
|----------|-------------------|----------|
| **Payment** | 6 | TradePayBridgeExtension, PayBridgeExtension, BindCardBridgeExtension, **DCEPWalletBridgeExtension** (central bank digital currency wallet) |
| **Authentication** | 5 | LoginExtension, HavanaLoginExtension, MyBankLoginExtension, VerifyIdentityBridgeExtension |
| **NFC/Contactless** | 3 | NFCBridgeExtension, NfcPayExtension, NfcTapShareExtension |
| **Contacts/Social** | 4 | AddPhoneContactBridgeExtension, SocialH5ContactExtension |
| **Device Hardware** | 6 | ScanBridgeExtension, ClipboardBridgeExtension, MakePhoneCallBridgeExtension, BluetoothScannerExtension |
| **Security** | 7 | SecurityExtension, OpenAuthExtension, SecurityBodyWuaBridgeExtension |
| **Data/Storage** | 6 | FileBridgeExtension, DownloadFileBridgeExtension, UploadFileBridgeExtension |
| **Financial** | 4 | DCEPWalletBridgeExtension, TransferSearchBridgeExtension, WalletUserInfoExtension |
| **Other** | 355 | Navigation, UI, multimedia, network, logging, etc. |

### Notable: Central Bank Digital Currency Interface

`DCEPWalletBridgeExtension` — a JSBridge interface for digital currency wallet operations — has `permit()` returning null. This high-value financial interface follows the same unprotected pattern as CVE-3 (payment operations), indicating that even critical monetary APIs lack permission guards.

### Implications for CVE-1 Severity

1. **Not isolated defects**: The original 5 unprotected classes are not exceptions — they are the norm. 97% of the JSBridge API surface lacks permission checks.

2. **Design pattern, not implementation bug**: This is a systemic architectural decision where security was not implemented by default. Only 12 classes (2.9%) opted into permission checking.

3. **Attack surface multiplication**: Combined with CVE-6 (whitelist bypass via `ds.alipay.com`), an attacker gaining JSBridge access can invoke 396 APIs without restriction — including payment, authentication, NFC, and device hardware operations.

4. **Consistent with CVE-9**: The opt-in security pattern identified in SchemeNeedVerify (CVE-9) extends to the JSBridge permission layer — both use allowlist models where protection must be explicitly added rather than being the default.

---

## Researcher Information

- **Name:** Jiqiang Feng
- **Organization:** Innora AI Security Research
- **Email:** feng@innora.ai

Best regards,
Jiqiang Feng
Innora AI Security Research
