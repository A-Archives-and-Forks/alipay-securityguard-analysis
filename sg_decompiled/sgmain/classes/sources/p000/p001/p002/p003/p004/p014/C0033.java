package p000.p001.p002.p003.p004.p014;

import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.alibaba.wireless.security.open.staticdataencrypt.IStaticDataEncryptComponent;
import java.io.UnsupportedEncodingException;

/* renamed from: а.а.а.а.а.й.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0033 implements IStaticDataEncryptComponent {

    /* renamed from: а, reason: contains not printable characters */
    private ISecurityGuardPlugin f85 = null;

    public C0033(ISecurityGuardPlugin iSecurityGuardPlugin) {
        init(iSecurityGuardPlugin, new Object[0]);
    }

    /* renamed from: а, reason: contains not printable characters */
    private byte[] m96(int i, int i2, int i3, String str, byte[] bArr, String str2) {
        return (byte[]) this.f85.getRouter().doCommand(10601, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), str, bArr, str2});
    }

    /* renamed from: а, reason: contains not printable characters */
    private byte[] m97(int i, String str, byte[] bArr, String str2) {
        return m96(2, i, 0, str, bArr, str2);
    }

    /* renamed from: б, reason: contains not printable characters */
    private byte[] m98(int i, String str, byte[] bArr, String str2) {
        return m96(1, i, 0, str, bArr, str2);
    }

    public int init(ISecurityGuardPlugin iSecurityGuardPlugin, Object... objArr) {
        this.f85 = iSecurityGuardPlugin;
        return 0;
    }

    public byte[] staticBinarySafeDecrypt(int i, String str, byte[] bArr, String str2) throws SecException {
        if (str == null || str.length() <= 0 || i < 3 || i >= 19 || bArr == null || bArr.length <= 0) {
            throw new SecException("", 301);
        }
        return m96(2, i, 1, str, bArr, str2);
    }

    public byte[] staticBinarySafeDecryptNoB64(int i, String str, byte[] bArr, String str2) throws SecException {
        if (str == null || str.length() <= 0 || i < 3 || i >= 19 || bArr == null || bArr.length <= 0) {
            throw new SecException("", 301);
        }
        return m97(i, str, bArr, str2);
    }

    public byte[] staticBinarySafeEncrypt(int i, String str, byte[] bArr, String str2) throws SecException {
        if (str == null || str.length() <= 0 || i < 3 || i >= 19 || bArr == null || bArr.length <= 0) {
            throw new SecException("", 301);
        }
        return m96(1, i, 1, str, bArr, str2);
    }

    public byte[] staticBinarySafeEncryptNoB64(int i, String str, byte[] bArr, String str2) throws SecException {
        if (str == null || str.length() <= 0 || i < 3 || i >= 19 || bArr == null || bArr.length <= 0) {
            throw new SecException("", 301);
        }
        return m98(i, str, bArr, str2);
    }

    public String staticSafeDecrypt(int i, String str, String str2, String str3) throws SecException {
        if (str == null || str.length() <= 0 || i < 3 || i >= 19 || str2 == null || str2.length() <= 0) {
            throw new SecException("", 301);
        }
        byte[] m96 = m96(2, i, 1, str, str2.getBytes(), str3);
        if (m96 == null) {
            return null;
        }
        try {
            return new String(m96, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public String staticSafeEncrypt(int i, String str, String str2, String str3) throws SecException {
        if (str == null || str.length() <= 0 || i < 3 || i >= 19 || str2 == null || str2.length() <= 0) {
            throw new SecException("", 301);
        }
        byte[] m96 = m96(1, i, 1, str, str2.getBytes(), str3);
        if (m96 == null) {
            return null;
        }
        try {
            return new String(m96, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }
}
