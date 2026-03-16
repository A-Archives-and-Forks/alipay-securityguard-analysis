package com.alibaba.wireless.security.mainplugin;

import com.alibaba.wireless.security.framework.IRouterComponent;
import com.taobao.wireless.security.adapter.JNICLibrary;

/* renamed from: com.alibaba.wireless.security.mainplugin.б, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0003 implements IRouterComponent {
    public Object doCommand(int i, Object... objArr) {
        return JNICLibrary.doCommandNative(i, objArr);
    }
}
