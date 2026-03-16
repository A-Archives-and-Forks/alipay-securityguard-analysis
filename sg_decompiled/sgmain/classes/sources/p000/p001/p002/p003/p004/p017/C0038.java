package p000.p001.p002.p003.p004.p017;

import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.alibaba.wireless.security.open.litevm.LiteVMParameterWrapper;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: а.а.а.а.а.ё.б, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
class C0038 {

    /* renamed from: а, reason: contains not printable characters */
    private ISecurityGuardPlugin f91;

    /* renamed from: б, reason: contains not printable characters */
    private volatile long f92;

    /* renamed from: в, reason: contains not printable characters */
    private AtomicBoolean f93 = new AtomicBoolean(false);

    private C0038(ISecurityGuardPlugin iSecurityGuardPlugin, long j, String str, String str2) {
        this.f92 = j;
        this.f91 = iSecurityGuardPlugin;
        this.f93.set(true);
    }

    /* renamed from: а, reason: contains not printable characters */
    static C0038 m121(ISecurityGuardPlugin iSecurityGuardPlugin, String str, String str2, byte[] bArr, long j) {
        return new C0038(iSecurityGuardPlugin, ((Long) iSecurityGuardPlugin.getRouter().doCommand(12501, new Object[]{str, str2, bArr, Long.valueOf(j)})).longValue(), str, str2);
    }

    /* renamed from: а, reason: contains not printable characters */
    static C0038 m122(ISecurityGuardPlugin iSecurityGuardPlugin, String str, String str2, byte[] bArr, Object[] objArr) {
        return new C0038(iSecurityGuardPlugin, ((Long) iSecurityGuardPlugin.getRouter().doCommand(12501, new Object[]{str, str2, bArr, 0L})).longValue(), str, str2);
    }

    /* renamed from: в, reason: contains not printable characters */
    private Long m123() {
        return Long.valueOf(this.f92);
    }

    /* renamed from: а, reason: contains not printable characters */
    Object m124(int i, LiteVMParameterWrapper[] liteVMParameterWrapperArr, int i2, int i3) throws SecException {
        if (!m127()) {
            throw new SecException("LVM instance not valid", 2101);
        }
        return this.f91.getRouter().doCommand(i3, new Object[]{Long.valueOf(m123().longValue()), Integer.valueOf(liteVMParameterWrapperArr != null ? liteVMParameterWrapperArr.length : 0), liteVMParameterWrapperArr, Integer.valueOf(i), Integer.valueOf(i2)});
    }

    /* renamed from: а, reason: contains not printable characters */
    void m125() throws SecException {
        if (!m127()) {
            throw new SecException("LVM instance not valid", 2101);
        }
        this.f93.set(false);
        this.f91.getRouter().doCommand(12503, new Object[]{Long.valueOf(m123().longValue())});
    }

    /* renamed from: а, reason: contains not printable characters */
    void m126(byte[] bArr) throws SecException {
        if (!m127()) {
            throw new SecException("LVM instance not valid", 2101);
        }
        this.f91.getRouter().doCommand(12502, new Object[]{Long.valueOf(m123().longValue()), bArr});
    }

    /* renamed from: б, reason: contains not printable characters */
    boolean m127() {
        return this.f93.get();
    }
}
