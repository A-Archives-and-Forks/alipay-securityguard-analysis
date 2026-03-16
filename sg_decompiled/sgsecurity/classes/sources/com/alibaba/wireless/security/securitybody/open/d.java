package com.alibaba.wireless.security.securitybody.open;

import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.alibaba.wireless.security.open.simulatordetect.ISimulatorDetectComponent;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class d implements ISimulatorDetectComponent {

    /* renamed from: a, reason: collision with root package name */
    private ISecurityGuardPlugin f41a;

    public d(ISecurityGuardPlugin iSecurityGuardPlugin) {
        init(iSecurityGuardPlugin, new Object[0]);
    }

    public int init(ISecurityGuardPlugin iSecurityGuardPlugin, Object... objArr) {
        this.f41a = iSecurityGuardPlugin;
        return 0;
    }

    public boolean isSimulator() {
        return ((Boolean) this.f41a.getRouter().doCommand(11001, new Object[0])).booleanValue();
    }
}
