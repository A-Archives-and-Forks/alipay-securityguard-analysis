package com.alibaba.wireless.security.securitybody;

import android.content.Context;
import com.alibaba.wireless.security.framework.IRouterComponent;
import com.alibaba.wireless.security.framework.ISGPluginInfo;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.alibaba.wireless.security.open.umid.IUMIDComponent;
import com.alibaba.wireless.security.open.umid.UMIDComponent;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class SecurityGuardSecurityBodyPlugin implements ISecurityGuardPlugin {
    public static final int FLAG_ENABLE_NETWORK = 1;
    private static final String e = "SecurityBodyPlugin";
    private static final boolean f = false;
    public static ClassLoader sClassLoader;

    /* renamed from: a, reason: collision with root package name */
    private HashMap<Class, Object> f29a = null;
    private Context b = null;
    private ISGPluginInfo c = null;
    private IRouterComponent d = null;

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ScheduledExecutorService f30a;

        a(ScheduledExecutorService scheduledExecutorService) {
            this.f30a = scheduledExecutorService;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                d.a(SecurityGuardSecurityBodyPlugin.this.getRouter());
                this.f30a.shutdown();
            } catch (Throwable unused) {
            }
        }
    }

    private void a() {
        this.f29a = new HashMap<>();
        new e().a(this.f29a, this);
    }

    public static ClassLoader getPluginClassLoader() {
        return sClassLoader;
    }

    public Context getContext() {
        return this.b;
    }

    public <T> T getInterface(Class<T> cls) {
        T t;
        if (cls == null || (t = (T) this.f29a.get(cls)) == null || !cls.isAssignableFrom(t.getClass())) {
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
        ApmSecurityBodyPluginAdapter.init();
        try {
            sClassLoader = getClass().getClassLoader();
        } catch (Throwable unused) {
        }
        a();
        UMIDComponent.a(this);
        System.loadLibrary(str);
        if (((objArr.length >= 1 ? ((Integer) objArr[0]).intValue() : 0) & 1) != 0) {
            ((UMIDComponent) this.f29a.get(IUMIDComponent.class)).a();
        }
        LifeCycle.init(context, iRouterComponent);
        SecurityBodyAdapter.initialize(getContext());
        InvocationHandlerAdapter.init(context, iRouterComponent);
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        newSingleThreadScheduledExecutor.schedule(new a(newSingleThreadScheduledExecutor), 2L, TimeUnit.SECONDS);
        return iRouterComponent;
    }
}
