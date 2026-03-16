package p000.p018.p019.p020.p021.p029;

import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.taobao.wireless.security.sdk.staticdataencrypt.IStaticDataEncryptComponent;

/* renamed from: а.б.а.а.а.ё.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0046 implements IStaticDataEncryptComponent {

    /* renamed from: а, reason: contains not printable characters */
    private com.alibaba.wireless.security.open.staticdataencrypt.IStaticDataEncryptComponent f101;

    public C0046(ISecurityGuardPlugin iSecurityGuardPlugin) {
        this.f101 = (com.alibaba.wireless.security.open.staticdataencrypt.IStaticDataEncryptComponent) iSecurityGuardPlugin.getInterface(com.alibaba.wireless.security.open.staticdataencrypt.IStaticDataEncryptComponent.class);
    }

    public byte[] staticBinarySafeDecrypt(int i, String str, byte[] bArr) {
        try {
            return this.f101.staticBinarySafeDecrypt(i, str, bArr, "");
        } catch (SecException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] staticBinarySafeDecryptNoB64(int i, String str, byte[] bArr) {
        try {
            return this.f101.staticBinarySafeDecryptNoB64(i, str, bArr, "");
        } catch (SecException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] staticBinarySafeEncrypt(int i, String str, byte[] bArr) {
        try {
            return this.f101.staticBinarySafeEncrypt(i, str, bArr, "");
        } catch (SecException e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] staticBinarySafeEncryptNoB64(int i, String str, byte[] bArr) {
        try {
            return this.f101.staticBinarySafeEncryptNoB64(i, str, bArr, "");
        } catch (SecException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String staticSafeDecrypt(int i, String str, String str2) {
        try {
            return this.f101.staticSafeDecrypt(i, str, str2, "");
        } catch (SecException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String staticSafeEncrypt(int i, String str, String str2) {
        try {
            return this.f101.staticSafeEncrypt(i, str, str2, "");
        } catch (SecException e) {
            e.printStackTrace();
            return null;
        }
    }
}
