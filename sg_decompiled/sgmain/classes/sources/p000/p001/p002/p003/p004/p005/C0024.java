package p000.p001.p002.p003.p004.p005;

import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.atlasencrypt.IAtlasEncryptComponent;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import java.io.UnsupportedEncodingException;

/* renamed from: а.а.а.а.а.а.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0024 implements IAtlasEncryptComponent {

    /* renamed from: а, reason: contains not printable characters */
    private ISecurityGuardPlugin f76;

    public C0024(ISecurityGuardPlugin iSecurityGuardPlugin) {
        init(iSecurityGuardPlugin, new Object[0]);
    }

    /* renamed from: а, reason: contains not printable characters */
    private byte[] m83(String str, String str2, String str3) {
        return (byte[]) this.f76.getRouter().doCommand(11901, new Object[]{str, str2, str3});
    }

    public String atlasSafeEncrypt(String str, String str2) throws SecException {
        if (str == null || str.length() <= 0) {
            throw new SecException("", 1001);
        }
        byte[] m83 = m83("a", str, str2);
        if (m83 == null) {
            return null;
        }
        try {
            return new String(m83, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public int init(ISecurityGuardPlugin iSecurityGuardPlugin, Object... objArr) {
        this.f76 = iSecurityGuardPlugin;
        return 0;
    }
}
