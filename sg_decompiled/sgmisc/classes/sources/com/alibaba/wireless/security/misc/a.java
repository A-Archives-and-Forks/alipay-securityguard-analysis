package com.alibaba.wireless.security.misc;

import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.alibaba.wireless.security.open.pkgvaliditycheck.IPkgValidityCheckComponent;
import com.taobao.wireless.security.miscplugin.e;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmisc/classes.dex */
public class a implements IPkgValidityCheckComponent {
    private e a;

    public a(ISecurityGuardPlugin iSecurityGuardPlugin) {
        init(iSecurityGuardPlugin, new Object[0]);
    }

    public int checkEnvAndFiles(String... strArr) throws SecException {
        return this.a.a(strArr);
    }

    public int init(ISecurityGuardPlugin iSecurityGuardPlugin, Object... objArr) {
        this.a = new e(iSecurityGuardPlugin);
        return 0;
    }
}
