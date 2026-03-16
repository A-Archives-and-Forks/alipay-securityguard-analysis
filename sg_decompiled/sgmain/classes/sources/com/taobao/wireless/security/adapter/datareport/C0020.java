package com.taobao.wireless.security.adapter.datareport;

import com.alibaba.wireless.security.framework.IRouterComponent;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: com.taobao.wireless.security.adapter.datareport.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0020 {

    /* renamed from: а, reason: contains not printable characters */
    private static IRouterComponent f66;

    /* renamed from: б, reason: contains not printable characters */
    private static Executor f67;

    /* renamed from: в, reason: contains not printable characters */
    private static Boolean f68;

    /* renamed from: com.taobao.wireless.security.adapter.datareport.а$а, reason: contains not printable characters */
    static class ThreadFactoryC0021 implements ThreadFactory {
        ThreadFactoryC0021() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "SGNE");
        }
    }

    /* renamed from: com.taobao.wireless.security.adapter.datareport.а$б, reason: contains not printable characters */
    static class RunnableC0022 implements Runnable {

        /* renamed from: а, reason: contains not printable characters */
        final /* synthetic */ C0023 f69;

        RunnableC0022(C0023 c0023) {
            this.f69 = c0023;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                C0020.f66.doCommand(13701, new Object[]{this.f69.m82(), Integer.valueOf(this.f69.m71()), Integer.valueOf(this.f69.m75()), Integer.valueOf(this.f69.m78()), Long.valueOf(this.f69.m80()), Long.valueOf(this.f69.m81())});
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: com.taobao.wireless.security.adapter.datareport.а$в, reason: contains not printable characters */
    public static class C0023 {

        /* renamed from: а, reason: contains not printable characters */
        String f70;

        /* renamed from: б, reason: contains not printable characters */
        int f71;

        /* renamed from: в, reason: contains not printable characters */
        int f72;

        /* renamed from: г, reason: contains not printable characters */
        int f73;

        /* renamed from: д, reason: contains not printable characters */
        long f74;

        /* renamed from: е, reason: contains not printable characters */
        long f75;

        /* renamed from: а, reason: contains not printable characters */
        public int m71() {
            return this.f71;
        }

        /* renamed from: а, reason: contains not printable characters */
        public void m72(int i) {
            this.f71 = i;
        }

        /* renamed from: а, reason: contains not printable characters */
        public void m73(long j) {
            this.f74 = j;
        }

        /* renamed from: а, reason: contains not printable characters */
        public void m74(String str) {
            this.f70 = str;
        }

        /* renamed from: б, reason: contains not printable characters */
        public int m75() {
            return this.f72;
        }

        /* renamed from: б, reason: contains not printable characters */
        public void m76(int i) {
            this.f72 = i;
        }

        /* renamed from: б, reason: contains not printable characters */
        public void m77(long j) {
            this.f75 = j;
        }

        /* renamed from: в, reason: contains not printable characters */
        public int m78() {
            return this.f73;
        }

        /* renamed from: в, reason: contains not printable characters */
        public void m79(int i) {
            this.f73 = i;
        }

        /* renamed from: г, reason: contains not printable characters */
        public long m80() {
            return this.f74;
        }

        /* renamed from: д, reason: contains not printable characters */
        public long m81() {
            return this.f75;
        }

        /* renamed from: е, reason: contains not printable characters */
        public String m82() {
            return this.f70;
        }
    }

    /* renamed from: а, reason: contains not printable characters */
    public static void m66(ISecurityGuardPlugin iSecurityGuardPlugin) {
        f66 = iSecurityGuardPlugin.getRouter();
        f67 = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque(30), new ThreadFactoryC0021(), new ThreadPoolExecutor.DiscardPolicy());
    }

    /* renamed from: а, reason: contains not printable characters */
    public static void m67(C0023 c0023) {
        if (f66 == null || f67 == null || c0023 == null || !m68()) {
            return;
        }
        f67.execute(new RunnableC0022(c0023));
    }

    /* renamed from: а, reason: contains not printable characters */
    private static boolean m68() {
        IRouterComponent iRouterComponent = f66;
        if (iRouterComponent == null) {
            return false;
        }
        if (f68 == null) {
            try {
                Integer num = (Integer) iRouterComponent.doCommand(13702, new Object[0]);
                if (num != null) {
                    f68 = Boolean.valueOf(num.equals(1));
                }
            } catch (Throwable unused) {
            }
        }
        Boolean bool = f68;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    /* renamed from: в, reason: contains not printable characters */
    public static boolean m70() {
        return f66 != null && m68();
    }
}
