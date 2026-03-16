package p000.p001.p002.p003.p004.p007;

import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.compat.ICompatComponent;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;

/* renamed from: а.а.а.а.а.в.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0026 implements ICompatComponent {

    /* renamed from: а, reason: contains not printable characters */
    private static ISecurityGuardPlugin f78;

    public C0026(ISecurityGuardPlugin iSecurityGuardPlugin) {
        init(iSecurityGuardPlugin, new Object[0]);
    }

    /* renamed from: а, reason: contains not printable characters */
    private String m86(int i) {
        return (String) f78.getRouter().doCommand(12801, new Object[]{Integer.valueOf(i)});
    }

    public String getCachedSecurityToken(int i) throws SecException {
        String m86 = m86(i);
        return (m86 == null || "".equals(m86) || !(m86.length() == 32 || m86.length() == 24)) ? "000000000000000000000000" : m86;
    }

    public int init(ISecurityGuardPlugin iSecurityGuardPlugin, Object... objArr) {
        f78 = iSecurityGuardPlugin;
        return 0;
    }
}
