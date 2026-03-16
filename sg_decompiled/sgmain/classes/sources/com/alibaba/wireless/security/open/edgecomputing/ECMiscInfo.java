package com.alibaba.wireless.security.open.edgecomputing;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.alibaba.wireless.security.framework.IRouterComponent;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class ECMiscInfo {

    /* renamed from: а, reason: contains not printable characters */
    private static volatile boolean f11 = false;

    /* renamed from: б, reason: contains not printable characters */
    private static volatile boolean f12 = false;

    /* renamed from: в, reason: contains not printable characters */
    private static Context f13 = null;

    /* renamed from: г, reason: contains not printable characters */
    static Handler f14 = null;

    /* renamed from: д, reason: contains not printable characters */
    private static volatile int f15 = 0;

    /* renamed from: е, reason: contains not printable characters */
    private static volatile int f16 = 99;

    /* renamed from: com.alibaba.wireless.security.open.edgecomputing.ECMiscInfo$а, reason: contains not printable characters */
    static class RunnableC0005 implements Runnable {

        /* renamed from: а, reason: contains not printable characters */
        final /* synthetic */ int f17;

        RunnableC0005(int i) {
            this.f17 = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            IRouterComponent m21;
            try {
                if (this.f17 == 0) {
                    int unused = ECMiscInfo.f15 = 0;
                } else if (this.f17 == 1 && ECMiscInfo.isBackgroundNew(ECMiscInfo.f13) == 1) {
                    int unused2 = ECMiscInfo.f15 = 1;
                }
                if (ECMiscInfo.f16 == ECMiscInfo.f15 || (m21 = C0008.m21()) == null || ((Integer) m21.doCommand(12605, new Object[]{Integer.valueOf(this.f17)})).intValue() != 1) {
                    return;
                }
                int unused3 = ECMiscInfo.f16 = ECMiscInfo.f15;
            } catch (Exception unused4) {
            }
        }
    }

    /* renamed from: com.alibaba.wireless.security.open.edgecomputing.ECMiscInfo$б, reason: contains not printable characters */
    private static class C0006 implements Application.ActivityLifecycleCallbacks {
        C0006(Context context) {
            if (ECMiscInfo.f14 == null) {
                HandlerThread handlerThread = new HandlerThread("EC-ALFC");
                handlerThread.start();
                Looper looper = handlerThread.getLooper();
                if (looper != null) {
                    ECMiscInfo.f14 = new Handler(looper);
                }
            }
            ProxyUtil.init(ECMiscInfo.f14);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            activity.sendBroadcast(new Intent("com.alibaba.action.LC_ON_ACT_CREATED"));
            ECMiscInfo.m15(activity, 0);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            activity.sendBroadcast(new Intent("com.alibaba.action.LC_ON_ACT_DESTROYED"));
            ECMiscInfo.m15(activity, 5);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            activity.sendBroadcast(new Intent("com.alibaba.action.LC_ON_ACT_PAUSED"));
            ECMiscInfo.m15(activity, 3);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            activity.sendBroadcast(new Intent("com.alibaba.action.LC_ON_ACT_RESUMED"));
            ECMiscInfo.m15(activity, 2);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            activity.sendBroadcast(new Intent("com.alibaba.action.LC_ON_ACT_SAVE_INSTANCE_STATE"));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            activity.sendBroadcast(new Intent("com.alibaba.action.LC_ON_ACT_STARTED"));
            ECMiscInfo.m15(activity, 1);
            ECMiscInfo.m14(0, this, ECMiscInfo.f14);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            activity.sendBroadcast(new Intent("com.alibaba.action.LC_ON_ACT_STOPPED"));
            ECMiscInfo.m15(activity, 4);
            ECMiscInfo.m14(1, this, ECMiscInfo.f14);
        }
    }

    public static boolean getAppFirstRunState() {
        Context m19 = C0008.m19();
        if (m19 != null) {
            return m19.getSharedPreferences("edge_computing_sp", 0).getBoolean("appFirstRun", true);
        }
        return false;
    }

    public static String getLastAppVersion() {
        Context m19 = C0008.m19();
        if (m19 != null) {
            return m19.getSharedPreferences("edge_computing_sp", 0).getString("lastAppVersion", null);
        }
        return null;
    }

    public static int isBackgroundNew(Context context) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                return runningAppProcessInfo.importance == 400 ? 1 : 0;
            }
        }
        return 99;
    }

    public static void registerAppLifeCyCleCallBack() {
        Context m19;
        f13 = C0008.m19();
        if (Build.VERSION.SDK_INT >= 14 && (m19 = C0008.m19()) != null) {
            Application application = m19 instanceof Application ? (Application) m19 : (Application) m19.getApplicationContext();
            if (application != null) {
                application.registerActivityLifecycleCallbacks(new C0006(m19));
            }
        }
    }

    public static void updateAppFirstRunState() {
        if (f12) {
            return;
        }
        try {
            SharedPreferences sharedPreferences = C0008.m19().getSharedPreferences("edge_computing_sp", 0);
            boolean z = sharedPreferences.getBoolean("appRunOnce", false);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("appFirstRun", z ? false : true);
            edit.putBoolean("appRunOnce", true);
            edit.apply();
        } catch (Exception unused) {
        }
    }

    public static void updateAppVersion(String str) {
        if (f11) {
            return;
        }
        try {
            SharedPreferences sharedPreferences = C0008.m19().getSharedPreferences("edge_computing_sp", 0);
            String string = sharedPreferences.getString("appVersion", null);
            if (string != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("lastAppVersion", string);
                edit.apply();
            }
            if (str != null) {
                SharedPreferences.Editor edit2 = sharedPreferences.edit();
                edit2.putString("appVersion", str);
                edit2.apply();
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: б, reason: contains not printable characters */
    public static void m14(int i, C0006 c0006, Handler handler) {
        if (handler == null) {
            HandlerThread handlerThread = new HandlerThread("EC-FB");
            handlerThread.start();
            handler = new Handler(handlerThread.getLooper());
        }
        if (handler != null) {
            handler.postDelayed(new RunnableC0005(i), 2L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: б, reason: contains not printable characters */
    public static void m15(Activity activity, int i) {
        try {
            C0008.m21().doCommand(12611, new Object[]{activity, Integer.valueOf(i), activity.getClass().getCanonicalName(), f14});
        } catch (Exception unused) {
        }
    }
}
