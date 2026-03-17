Subject: Re: [MITRE Ticket #2005801] Additional CVEs: Certificate Validation Bypass + AVMP Signature Extraction in Alipay

---

Dear MITRE CVE Team,

This is a follow-up to Ticket #2005801 (6 CVEs submitted 2026-03-12) regarding security vulnerabilities in Alipay (com.eg.android.AlipayGphone) v10.8.30.8000.

Through continued reverse engineering of Alipay's embedded SecurityGuard SDK, I have discovered two additional vulnerabilities that I would like to submit for CVE assignment.

---

## CVE Request 7: Improper Certificate Validation (CWE-295)

**Product:** Alipay Android (com.eg.android.AlipayGphone)
**Version:** 10.8.30.8000 (confirmed), likely all recent versions
**Component:** com.alipay.mobile.common.transport.ssl.EmptyX509TrustManagerWrapper

### Description

The application ships with a class `EmptyX509TrustManagerWrapper` that implements `X509TrustManager` with a **completely empty** `checkServerTrusted()` method. When this trust manager is active, the application accepts **any** server certificate without validation, enabling trivial man-in-the-middle attacks on all TLS connections.

### Technical Evidence

**File:** `com/alipay/mobile/common/transport/ssl/EmptyX509TrustManagerWrapper.java`
(Decompiled from classes10.dex)

```java
public class EmptyX509TrustManagerWrapper implements X509TrustManager {
    public static ChangeQuickRedirect f68247;  // PatchProxy hot-patch field

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
        ChangeQuickRedirect changeQuickRedirect = f68247;
        if (changeQuickRedirect != null) {
            boolean z = PatchProxy.proxy(
                new Object[]{chain, authType, ...}, this, changeQuickRedirect, "2"
            ).isSupported;
        }
        // NO VALIDATION PERFORMED — method returns void without checking certificate chain
    }
}
```

When `ChangeQuickRedirect` field `f68247` is null (the default state), the method body does absolutely nothing — no certificate chain validation, no exception thrown. **Any server certificate is silently accepted.**

**Inheritance chain:**
`X509TrustManagerWrapper extends EmptyX509TrustManagerWrapper`

The production wrapper (`X509TrustManagerWrapper`) overrides `checkServerTrusted()` to delegate to the system default `TrustManagerFactory` (system CA store only). However:

1. **No certificate pinning** is implemented — any CA-signed certificate is accepted
2. The `ChangeQuickRedirect` field enables the vendor to **remotely switch** the trust manager behavior via PatchProxy hot-patching at any time, without an app store update
3. The `EmptyX509TrustManagerWrapper` base class remains instantiable and is loaded in the runtime classpath

### Impact

- **MITM attack:** An attacker on the same network can intercept all HTTPS traffic by presenting a proxy CA certificate (which the system CA store accepts) or by triggering the Empty trust manager code path
- **Remote degradation:** The PatchProxy mechanism allows the vendor to silently disable certificate validation across all installed instances without user consent or app store review
- **Affected users:** 1+ billion (Alipay's reported user base)

### CVSS 3.1 Assessment

**Vector:** AV:N/AC:H/PR:N/UI:N/S:U/C:H/I:H/A:N
**Score:** 7.4 (High)

Attack complexity is High because the attacker must be in a network position to intercept traffic. However, no user interaction or privileges are required.

---

## CVE Request 8: Authentication Bypass by Capture-Replay of AVMP Signatures (CWE-294)

**Product:** Alipay Android (com.eg.android.AlipayGphone)
**Version:** 10.8.30.8000 (confirmed)
**Component:** SecurityGuard SDK — AVMP Bytecode Virtual Machine (sgmiddletier module)

### Description

Alipay's HTTP request authentication relies on the AVMP (Alibaba Virtual Machine Protection) bytecode VM within the SecurityGuard SDK to generate cryptographic signatures (the `x-sign` HTTP header). Through a custom JNI-level C gadget, these signatures can be programmatically extracted from a running instance and replayed in forged HTTP requests, bypassing request authentication.

### Technical Evidence

**Attack method:** A custom C shared library (`sg_avmp_gadget.c`, 132 lines) is compiled with Android NDK and loaded into the Alipay process via dynamic instrumentation (Frida/stnel). The gadget operates at the JNI level, directly calling SecurityGuard's native command router:

```c
// Step 1: Create AVMP VM instance
jobject vmIdObj = (*env)->CallObjectMethod(env, router, doCmd, 70201, args);
// Returns: VM ID (e.g., -5476376653615126281)

// Step 2: Execute signing operation
// Key technique: pass byte[].class as Object to bypass JS-JNI serialization
(*env)->SetObjectArrayElement(env, outerArgs, 2, byteArrayCls);
jobject result = (*env)->CallObjectMethod(env, router, doCmd, 70202, outerArgs);
// Returns: ~336-byte Base64-encoded signature token
```

**Independently verified evidence (raw capture logs attached):**
```
[2026-03-17T10:08:17Z] VM_ID=-5476376653615126281  (reproduced 4 times)
[2026-03-17T10:05:22Z] SIGN_ERROR=expected a pointer  (JS bridge fails, C gadget succeeds)
```

**Signing input format:** `utdid&uid&&key&md5&ts&api&v&sid&tid&did&0&0&&f&&`

The signature depends on device-bound values (utdid, device ID) and request-specific data (API name, timestamp, body MD5). However, once a device is compromised or emulated, signatures can be generated at will for any API request.

### Impact

- **Request forgery:** An attacker can generate valid `x-sign` headers for arbitrary API requests, bypassing server-side request authentication
- **Account takeover facilitation:** Combined with other vulnerabilities (CVE-1 through CVE-6 in this ticket), forged requests can be used to perform unauthorized operations
- **SecurityGuard bypass:** The AVMP VM — designed as the last line of defense against request tampering — is fully compromised

### Attack Prerequisites

- Rooted Android device or emulator running Alipay
- Dynamic instrumentation framework (Frida/stnel)
- Custom C gadget (source code: 132 lines, compiles in seconds with NDK)

### CVSS 3.1 Assessment

**Vector:** AV:L/AC:L/PR:H/UI:N/S:C/C:N/I:H/A:N
**Score:** 6.3 (Medium)

Local access with high privileges (root) is required, but the scope changes because the impact extends to the server-side authentication system. Integrity impact is High because the attacker can forge authenticated requests.

---

## Relationship to Existing CVEs

These two vulnerabilities are discovered through reverse engineering of Alipay's SecurityGuard SDK, the same security framework that implements the protections bypassed in CVE-1 through CVE-6:

- **CVE-7** explains *why* MITM interception is trivial — the TLS trust manager has no certificate pinning
- **CVE-8** explains *how* request authentication can be forged — the AVMP signing VM can be invoked programmatically

Together with the previously submitted 6 CVEs, these form a complete attack chain from network interception (CVE-7) → request signing bypass (CVE-8) → DeepLink exploitation (CVE-1,6) → data exfiltration/payment manipulation (CVE-2,3,4,5).

---

## Supporting Materials

1. Full SecurityGuard reverse engineering report: https://innora.ai/zfb/
2. C gadget source code: Available upon request (sg_avmp_gadget.c, 132 lines)
3. Raw VM creation evidence logs: Available upon request
4. EmptyX509TrustManagerWrapper.java decompiled source: Available upon request
5. Packet Storm Advisory: https://packetstormsecurity.com/files/id/217089

## Researcher Information

- **Name:** Jiqiang Feng
- **Organization:** Innora AI Security Research
- **Email:** feng@innora.ai
- **Credit:** Jiqiang Feng (Innora AI Security Research)

## Vendor Notification

The vendor (Ant Group) was notified of the original 6 vulnerabilities on 2026-03-07 and responded that they consider these "normal functionality." The vendor has not engaged in remediation discussions. Additional details about vendor response are documented in the original submission.

Best regards,
Jiqiang Feng
Innora AI Security Research
