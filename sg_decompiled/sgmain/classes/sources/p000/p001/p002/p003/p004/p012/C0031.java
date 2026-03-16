package p000.p001.p002.p003.p004.p012;

import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.alibaba.wireless.security.open.safetoken.ISafeTokenComponent;
import java.io.UnsupportedEncodingException;

/* renamed from: а.а.а.а.а.з.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0031 implements ISafeTokenComponent {

    /* renamed from: а, reason: contains not printable characters */
    private ISecurityGuardPlugin f83;

    public C0031(ISecurityGuardPlugin iSecurityGuardPlugin) {
        init(iSecurityGuardPlugin, new Object[0]);
    }

    /* renamed from: а, reason: contains not printable characters */
    private Object m95(int i, String str, byte[] bArr, String str2, int i2) throws SecException {
        if (str == null || str.length() <= 0) {
            throw new SecException("", 1601);
        }
        if (i > 0 && i < 4) {
            return (Boolean) this.f83.getRouter().doCommand(12101, new Object[]{Integer.valueOf(i), str, bArr, str2, Integer.valueOf(i2)});
        }
        if (i <= 3 || i > 6) {
            return null;
        }
        byte[] bArr2 = (byte[]) this.f83.getRouter().doCommand(12101, new Object[]{Integer.valueOf(i), str, bArr, str2, Integer.valueOf(i2)});
        return i == 6 ? new String(bArr2) : bArr2;
    }

    public byte[] decryptWithToken(String str, byte[] bArr, int i) throws SecException {
        return (byte[]) m95(5, str, bArr, null, i);
    }

    public byte[] encryptWithToken(String str, byte[] bArr, int i) throws SecException {
        return (byte[]) m95(4, str, bArr, null, i);
    }

    public byte[] getOtp(String str, int i, String[] strArr, byte[][] bArr) throws SecException {
        if (str == null || str.length() <= 0) {
            throw new SecException("", 1601);
        }
        return (byte[]) this.f83.getRouter().doCommand(12102, new Object[]{str, Integer.valueOf(i), strArr, bArr, ""});
    }

    public byte[] getOtp(String str, int i, String[] strArr, byte[][] bArr, String str2) throws SecException {
        if (str == null || str.length() <= 0) {
            throw new SecException("", 1601);
        }
        return (byte[]) this.f83.getRouter().doCommand(12102, new Object[]{str, Integer.valueOf(i), strArr, bArr, str2});
    }

    public int init(ISecurityGuardPlugin iSecurityGuardPlugin, Object... objArr) {
        this.f83 = iSecurityGuardPlugin;
        return 0;
    }

    public boolean isTokenExisted(String str) throws SecException {
        return ((Boolean) m95(2, str, null, null, 0)).booleanValue();
    }

    public void removeToken(String str) throws SecException {
        m95(3, str, null, null, 0);
    }

    public boolean saveToken(String str, String str2, String str3, int i) throws SecException {
        if (str2 == null || str2.length() <= 0) {
            throw new SecException("", 1601);
        }
        try {
            return ((Boolean) m95(1, str, str2.getBytes("UTF-8"), str3, i)).booleanValue();
        } catch (UnsupportedEncodingException unused) {
            throw new SecException("", 1601);
        }
    }

    public String signWithToken(String str, byte[] bArr, int i) throws SecException {
        return (String) m95(6, str, bArr, null, i);
    }
}
