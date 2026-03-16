package com.alibaba.wireless.security.misc;

import android.content.Context;
import com.alibaba.wireless.security.framework.IRouterComponent;
import com.alibaba.wireless.security.framework.ISGPluginInfo;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.taobao.wireless.security.miscplugin.g;
import java.util.HashMap;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmisc/classes.dex */
public class MiscPlugin implements ISecurityGuardPlugin {
    private HashMap<Class, Object> a = null;
    private Context b = null;
    private IRouterComponent c = null;
    private ISGPluginInfo d = null;

    public Context getContext() {
        return this.b;
    }

    public <T> T getInterface(Class<T> cls) {
        T t;
        if (cls == null || (t = (T) this.a.get(cls)) == null || !cls.isAssignableFrom(t.getClass())) {
            return null;
        }
        return t;
    }

    public IRouterComponent getRouter() {
        return this.c;
    }

    public ISGPluginInfo getSGPluginInfo() {
        return this.d;
    }

    public IRouterComponent onPluginLoaded(Context context, IRouterComponent iRouterComponent, ISGPluginInfo iSGPluginInfo, String str, Object... objArr) throws SecException {
        this.b = context;
        this.c = iRouterComponent;
        this.d = iSGPluginInfo;
        this.a = g.a(this, context);
        System.loadLibrary(str);
        return iRouterComponent;
    }
}
