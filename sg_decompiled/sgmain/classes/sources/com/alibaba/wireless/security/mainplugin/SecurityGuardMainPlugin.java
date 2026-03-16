package com.alibaba.wireless.security.mainplugin;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import com.alibaba.wireless.security.framework.IRouterComponent;
import com.alibaba.wireless.security.framework.ISGPluginInfo;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.edgecomputing.C0008;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.taobao.wireless.security.adapter.common.C0011;
import com.taobao.wireless.security.adapter.common.SPUtility2;
import com.taobao.wireless.security.adapter.datacollection.C0015;
import com.taobao.wireless.security.adapter.datareport.C0020;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class SecurityGuardMainPlugin implements ISecurityGuardPlugin {
    public static ClassLoader sClassLoader;
    public static ClassLoader sParentClassLoader;

    /* renamed from: а, reason: contains not printable characters */
    private HashMap<Class, Object> f0 = null;

    /* renamed from: б, reason: contains not printable characters */
    private boolean f1 = true;

    /* renamed from: в, reason: contains not printable characters */
    private Context f2 = null;

    /* renamed from: г, reason: contains not printable characters */
    private ISGPluginInfo f3 = null;

    /* renamed from: д, reason: contains not printable characters */
    private IRouterComponent f4 = null;

    /* renamed from: е, reason: contains not printable characters */
    private C0004 f5 = new C0004();

    /* renamed from: com.alibaba.wireless.security.mainplugin.SecurityGuardMainPlugin$а, reason: contains not printable characters */
    class C0000 extends TimerTask {

        /* renamed from: com.alibaba.wireless.security.mainplugin.SecurityGuardMainPlugin$а$а, reason: contains not printable characters */
        class C0001 extends Thread {
            C0001() {
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    C0015.m57(SecurityGuardMainPlugin.this.getContext());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }

        C0000() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            new C0001().start();
        }
    }

    public static ClassLoader getMainPluginClassLoader() {
        return sClassLoader;
    }

    public static ClassLoader getMainPluginParentClassLoader() {
        return sParentClassLoader;
    }

    /* renamed from: а, reason: contains not printable characters */
    private void m0() {
        this.f0 = new HashMap<>();
        this.f5.m6(this.f0, this);
    }

    /* renamed from: а, reason: contains not printable characters */
    private void m1(String str) {
        if (str == null) {
            return;
        }
        this.f5.m7(this.f0, str, this);
    }

    public Context getContext() {
        return this.f2;
    }

    public <T> T getInterface(Class<T> cls) {
        if (cls == null) {
            return null;
        }
        T t = (T) this.f0.get(cls);
        if (t != null && cls.isAssignableFrom(t.getClass())) {
            return t;
        }
        try {
            m1(cls.getName());
            return (T) this.f0.get(cls);
        } catch (Exception unused) {
            return null;
        }
    }

    public IRouterComponent getRouter() {
        return this.f4;
    }

    public ISGPluginInfo getSGPluginInfo() {
        return this.f3;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0111  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public IRouterComponent onPluginLoaded(Context context, IRouterComponent iRouterComponent, ISGPluginInfo iSGPluginInfo, String str, Object... objArr) throws SecException {
        String str2;
        String str3;
        String str4;
        int i;
        String str5;
        String str6;
        ActivityManager activityManager;
        int intValue;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (this.f1) {
            this.f2 = context;
            this.f3 = iSGPluginInfo;
            C0002.m2();
            try {
                sClassLoader = getClass().getClassLoader();
                if (sClassLoader != null) {
                    sParentClassLoader = sClassLoader.getParent();
                }
            } catch (Throwable unused) {
            }
            SPUtility2.init(context);
            C0011.m36(context);
            m0();
            this.f4 = new C0003();
            C0008.m20(context, this.f4, this);
            try {
                System.loadLibrary(str);
                String str7 = "";
                if (objArr.length >= 4 && (objArr[0] instanceof Integer) && (objArr[1] instanceof String) && (objArr[2] instanceof String)) {
                    i = ((Integer) objArr[0]).intValue();
                    str3 = (String) objArr[1];
                    str4 = (String) objArr[2];
                    str2 = objArr[3] instanceof String ? (String) objArr[3] : "";
                } else {
                    str2 = "";
                    str3 = str2;
                    str4 = str3;
                    i = 0;
                }
                String packageName = context.getPackageName();
                try {
                    str5 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                    try {
                        str6 = String.valueOf(Build.VERSION.RELEASE);
                    } catch (Exception unused2) {
                        str6 = "";
                        int myPid = Process.myPid();
                        activityManager = (ActivityManager) context.getSystemService("activity");
                        if (activityManager != null) {
                            while (r4.hasNext()) {
                            }
                        }
                        intValue = ((Integer) getRouter().doCommand(10101, new Object[]{context, Integer.valueOf(i), str3, str4, str2, packageName, str5, str6, str7})).intValue();
                        if (intValue == 0) {
                        }
                    }
                } catch (Exception unused3) {
                    str5 = "";
                }
                int myPid2 = Process.myPid();
                activityManager = (ActivityManager) context.getSystemService("activity");
                if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null) {
                    for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                        if (runningAppProcessInfo.pid == myPid2) {
                            str7 = runningAppProcessInfo.processName;
                        }
                    }
                }
                intValue = ((Integer) getRouter().doCommand(10101, new Object[]{context, Integer.valueOf(i), str3, str4, str2, packageName, str5, str6, str7})).intValue();
                if (intValue == 0) {
                    throw new SecException(intValue);
                }
                C0020.m66(this);
                new Timer().schedule(new C0000(), 3000L);
                this.f1 = false;
            } catch (Throwable th) {
                throw new SecException(th.toString(), 103);
            }
        }
        return this.f4;
    }
}
