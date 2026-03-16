package p000.p001.p002.p003.p004.p011;

import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.alibaba.wireless.security.open.opensdk.IOpenSDKComponent;
import java.nio.ByteBuffer;

/* renamed from: а.а.а.а.а.ж.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0030 implements IOpenSDKComponent {

    /* renamed from: а, reason: contains not printable characters */
    private ISecurityGuardPlugin f82;

    public C0030(ISecurityGuardPlugin iSecurityGuardPlugin) {
        init(iSecurityGuardPlugin, new Object[0]);
    }

    /* renamed from: а, reason: contains not printable characters */
    private long m93(byte[] bArr) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.put(bArr, 0, bArr.length);
        allocate.flip();
        return allocate.getLong();
    }

    /* renamed from: а, reason: contains not printable characters */
    private byte[] m94(String str, String str2, String str3, byte[] bArr, String str4) {
        return (byte[]) this.f82.getRouter().doCommand(11601, new Object[]{str, str2, str3, bArr, str4});
    }

    public Long analyzeOpenId(String str, String str2, String str3, byte[] bArr, String str4) throws SecException {
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0 || str3 == null || str2.length() == 0 || bArr == null || bArr.length == 0) {
            throw new SecException(1101);
        }
        byte[] m94 = m94(str, str2, str3, bArr, str4);
        if (m94 == null || m94.length > 8 || m94.length <= 0) {
            return null;
        }
        return Long.valueOf(m93(m94));
    }

    public int init(ISecurityGuardPlugin iSecurityGuardPlugin, Object... objArr) {
        this.f82 = iSecurityGuardPlugin;
        return 0;
    }
}
