package com.alibaba.wireless.security.middletierplugin.open.fc;

import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public class d {
    private static ISecurityGuardPlugin a;

    public static void a(int i) {
        try {
            a.getRouter().doCommand(13501, new Object[]{Integer.valueOf(i)});
        } catch (Exception unused) {
        }
    }

    public static void a(ISecurityGuardPlugin iSecurityGuardPlugin) {
        a = iSecurityGuardPlugin;
    }
}
