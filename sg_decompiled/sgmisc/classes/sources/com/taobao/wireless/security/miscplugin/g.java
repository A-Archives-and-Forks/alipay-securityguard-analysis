package com.taobao.wireless.security.miscplugin;

import android.content.Context;
import com.alibaba.wireless.security.misc.MiscPlugin;
import com.taobao.wireless.security.sdk.indiekit.IIndieKitComponent;
import com.taobao.wireless.security.sdk.pkgvaliditycheck.IPkgValidityCheckComponent;
import java.util.HashMap;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmisc/classes.dex */
public class g {

    static class a extends HashMap<Class, Object> {
        final /* synthetic */ MiscPlugin a;
        final /* synthetic */ Context b;

        a(MiscPlugin miscPlugin, Context context) {
            this.a = miscPlugin;
            this.b = context;
            put(IIndieKitComponent.class, new d(this.a, this.b));
            put(IPkgValidityCheckComponent.class, new f(this.a, this.b));
            put(com.alibaba.wireless.security.open.pkgvaliditycheck.IPkgValidityCheckComponent.class, new com.alibaba.wireless.security.misc.a(this.a));
        }
    }

    public static HashMap<Class, Object> a(MiscPlugin miscPlugin, Context context) {
        return new a(miscPlugin, context);
    }
}
