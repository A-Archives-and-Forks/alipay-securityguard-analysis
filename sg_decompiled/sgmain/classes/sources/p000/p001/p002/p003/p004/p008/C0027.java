package p000.p001.p002.p003.p004.p008;

import com.alibaba.wireless.security.open.datacollection.IDataCollectionComponent;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.taobao.wireless.security.adapter.datacollection.C0013;

/* renamed from: а.а.а.а.а.г.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0027 implements IDataCollectionComponent {

    /* renamed from: а, reason: contains not printable characters */
    private C0013 f79;

    public C0027(ISecurityGuardPlugin iSecurityGuardPlugin) {
        init(iSecurityGuardPlugin, new Object[0]);
    }

    public String getNick() {
        return this.f79.m48();
    }

    public int init(ISecurityGuardPlugin iSecurityGuardPlugin, Object... objArr) {
        this.f79 = C0013.m44(iSecurityGuardPlugin);
        return 0;
    }

    public boolean setNick(String str) {
        return this.f79.m51(str);
    }
}
