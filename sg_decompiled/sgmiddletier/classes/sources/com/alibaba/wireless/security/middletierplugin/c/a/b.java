package com.alibaba.wireless.security.middletierplugin.c.a;

import com.alibaba.wireless.security.framework.IRouterComponent;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public class b {
    private static IRouterComponent a;
    private static Executor b;
    private static Boolean c;

    static class a implements ThreadFactory {
        a() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "SGNE");
        }
    }

    /* renamed from: com.alibaba.wireless.security.middletierplugin.c.a.b$b, reason: collision with other inner class name */
    static class RunnableC0001b implements Runnable {
        final /* synthetic */ c a;

        RunnableC0001b(c cVar) {
            this.a = cVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                b.a.doCommand(13701, new Object[]{this.a.f(), Integer.valueOf(this.a.a()), Integer.valueOf(this.a.b()), Integer.valueOf(this.a.c()), Long.valueOf(this.a.d()), Long.valueOf(this.a.e())});
            } catch (Throwable unused) {
            }
        }
    }

    public static class c {
        String a;
        int b;
        int c;
        int d;
        long e;
        long f;

        public int a() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }

        public void a(long j) {
            this.e = j;
        }

        public void a(String str) {
            this.a = str;
        }

        public int b() {
            return this.c;
        }

        public void b(int i) {
            this.c = i;
        }

        public void b(long j) {
            this.f = j;
        }

        public int c() {
            return this.d;
        }

        public void c(int i) {
            this.d = i;
        }

        public long d() {
            return this.e;
        }

        public long e() {
            return this.f;
        }

        public String f() {
            return this.a;
        }
    }

    public static void a(c cVar) {
        if (a == null || b == null || cVar == null || !a()) {
            return;
        }
        b.execute(new RunnableC0001b(cVar));
    }

    public static void a(ISecurityGuardPlugin iSecurityGuardPlugin) {
        a = iSecurityGuardPlugin.getRouter();
        b = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(30), new a(), new ThreadPoolExecutor.DiscardPolicy());
    }

    private static boolean a() {
        IRouterComponent iRouterComponent = a;
        if (iRouterComponent == null) {
            return false;
        }
        if (c == null) {
            try {
                Integer num = (Integer) iRouterComponent.doCommand(13702, new Object[0]);
                if (num != null) {
                    c = Boolean.valueOf(num.equals(1));
                }
            } catch (Throwable unused) {
            }
        }
        Boolean bool = c;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public static boolean c() {
        return a != null && a();
    }
}
