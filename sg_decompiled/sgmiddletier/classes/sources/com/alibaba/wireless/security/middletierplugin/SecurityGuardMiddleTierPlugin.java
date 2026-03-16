package com.alibaba.wireless.security.middletierplugin;

import android.content.Context;
import com.alibaba.wireless.security.framework.IRouterComponent;
import com.alibaba.wireless.security.framework.ISGPluginInfo;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public class SecurityGuardMiddleTierPlugin implements ISecurityGuardPlugin {
    public static ClassLoader sClassLoader;
    private HashMap<Class, Object> a = null;
    private Context b = null;
    private ISGPluginInfo c = null;
    private IRouterComponent d = null;

    class a implements Runnable {
        final /* synthetic */ ExecutorService a;

        a(ExecutorService executorService) {
            this.a = executorService;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                com.alibaba.wireless.security.middletierplugin.c.a.b.a(SecurityGuardMiddleTierPlugin.this);
                com.alibaba.wireless.security.middletierplugin.c.a.a.a(SecurityGuardMiddleTierPlugin.this, SecurityGuardMiddleTierPlugin.this.b);
                this.a.shutdown();
            } catch (Throwable unused) {
            }
        }
    }

    public static ClassLoader getPluginClassLoader() {
        return sClassLoader;
    }

    public static Object getProxyInstance(Class cls, InvocationHandler invocationHandler) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler);
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
        this.d = iRouterComponent;
        this.b = context;
        this.c = iSGPluginInfo;
        this.a = new HashMap<>();
        com.alibaba.wireless.security.middletierplugin.a.a();
        try {
            sClassLoader = getClass().getClassLoader();
        } catch (Throwable unused) {
        }
        new b().a(this.a, this, context);
        try {
            System.loadLibrary(str);
            ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);
            newFixedThreadPool.execute(new a(newFixedThreadPool));
            return iRouterComponent;
        } catch (Throwable th) {
            th.toString();
            throw new SecException(103);
        }
    }
}
