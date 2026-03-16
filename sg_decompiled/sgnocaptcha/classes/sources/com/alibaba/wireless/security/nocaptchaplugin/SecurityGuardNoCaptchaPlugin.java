package com.alibaba.wireless.security.nocaptchaplugin;

import android.content.Context;
import com.alibaba.wireless.security.framework.IRouterComponent;
import com.alibaba.wireless.security.framework.ISGPluginInfo;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.nocaptcha.SecurityGuardNoCaptchaPluginAdapter;
import java.util.HashMap;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgnocaptcha/classes.dex */
public class SecurityGuardNoCaptchaPlugin implements ISecurityGuardPlugin {
    private HashMap<Class, Object> a = null;
    private Context b = null;
    private ISGPluginInfo c = null;
    private IRouterComponent d = null;

    private void a() {
        this.a = new HashMap<>();
        new SecurityGuardNoCaptchaPluginAdapter().a(this.a, this);
    }

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
        return this.d;
    }

    public ISGPluginInfo getSGPluginInfo() {
        return this.c;
    }

    public IRouterComponent onPluginLoaded(Context context, IRouterComponent iRouterComponent, ISGPluginInfo iSGPluginInfo, String str, Object... objArr) {
        this.b = context;
        this.d = iRouterComponent;
        this.c = iSGPluginInfo;
        a();
        System.loadLibrary(str);
        return iRouterComponent;
    }
}
