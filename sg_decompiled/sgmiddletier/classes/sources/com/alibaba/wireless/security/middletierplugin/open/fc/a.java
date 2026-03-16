package com.alibaba.wireless.security.middletierplugin.open.fc;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.os.SystemClock;
import android.util.Base64;
import com.alibaba.wireless.security.framework.utils.UserTrackMethodJniBridge;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import java.util.HashMap;
import java.util.List;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public class a {
    private static volatile int a;
    private static ISecurityGuardPlugin b;
    public static volatile Handler c;

    /* renamed from: com.alibaba.wireless.security.middletierplugin.open.fc.a$a, reason: collision with other inner class name */
    static class RunnableC0007a implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ int b;
        final /* synthetic */ long c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;
        final /* synthetic */ String f;
        final /* synthetic */ String g;
        final /* synthetic */ String h;
        final /* synthetic */ boolean i;

        RunnableC0007a(int i, int i2, long j, String str, String str2, String str3, String str4, String str5, boolean z) {
            this.a = i;
            this.b = i2;
            this.c = j;
            this.d = str;
            this.e = str2;
            this.f = str3;
            this.g = str4;
            this.h = str5;
            this.i = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.a(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i);
        }
    }

    public static int a(Context context) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                return runningAppProcessInfo.importance == 400 ? 1 : 0;
            }
        }
        return 99;
    }

    public static int a(String str, boolean z) {
        try {
            String str2 = "";
            String str3 = "saveAndReportLocalLogBy9006---> content = " + str;
            if (str != null && !str.isEmpty()) {
                str2 = new String(Base64.encode(("BXREPORT=2|BackGroud=" + c() + "|BackGroundNew=" + a(b.getContext()) + "|Pid=" + b() + "|Content=" + str).getBytes(), 0));
            }
            return ((Integer) b.getRouter().doCommand(70502, new Object[]{str2, Boolean.valueOf(z)})).intValue();
        } catch (Exception e) {
            String str4 = "reportBy9006Impl : " + e.getMessage();
            return -1;
        }
    }

    public static void a() {
        try {
            b.getRouter().doCommand(70503, new Object[0]);
        } catch (Exception e) {
            String str = "deleteSampleLog : " + e.getMessage();
        }
    }

    public static void a(int i, int i2, long j, String str, String str2, String str3, String str4, String str5) {
        a("{mn:" + i + ",ec:" + i2 + ",msg:" + str + ",tc:" + j + ",cp:" + str2 + ",ext:" + str3 + ",sid:" + str4 + ",bxuid:" + str5 + "}");
    }

    public static void a(int i, int i2, long j, String str, String str2, String str3, String str4, String str5, int i3, boolean z) {
        if ((i3 & 1) == 1) {
            try {
                b(i, i2, j, str, str2, str3, str4, str5);
            } catch (Exception e) {
                String str6 = "makeBXMutiReport : " + e.getMessage();
                return;
            }
        }
        if ((i3 & 2) == 2) {
            a(i, i2, j, str, str2, str3, str4, str5);
        }
        if ((i3 & 4) == 4) {
            if (c == null) {
                synchronized (a.class) {
                    if (c == null) {
                        HandlerThread handlerThread = new HandlerThread("FC-SampleHandler");
                        handlerThread.start();
                        c = new Handler(handlerThread.getLooper());
                    }
                }
            }
            if (c != null) {
                c.post(new RunnableC0007a(i, i2, j, str, str2, str3, str4, str5, z));
            }
        }
    }

    public static void a(int i, int i2, long j, String str, String str2, String str3, String str4, String str5, boolean z) {
        a("{mn:" + i + ",ec:" + i2 + ",msg:" + str + ",tc:" + j + ",cp:" + str2 + ",ext:" + str3 + ",sid:" + str4 + ",bxuid:" + str5 + "}", z);
    }

    public static void a(ISecurityGuardPlugin iSecurityGuardPlugin) {
        b = iSecurityGuardPlugin;
    }

    public static void a(String str) {
        try {
            b.getRouter().doCommand(70501, new Object[]{new String(Base64.encode(("BXREPORT=1|BackGroud=" + c() + "|BackGroundNew=" + a(b.getContext()) + "|Pid=" + b() + "|Content=" + str).getBytes(), 0))});
        } catch (Exception e) {
            String str2 = "reportBy9006Impl : " + e.getMessage();
        }
    }

    public static void a(boolean z, int i, String str) {
        try {
            String str2 = "[UIBridge] start updating sampling status is status=" + i + " isSample=" + z + " bxuuid=" + str;
            if (b == null || !z) {
                return;
            }
            b.getRouter().doCommand(71501, new Object[]{Integer.valueOf(i), Long.valueOf(SystemClock.elapsedRealtime()), str});
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x002b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002c A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean a(int i, HashMap hashMap) {
        String str;
        if (hashMap != null) {
            if (hashMap.containsKey("bx-action")) {
                Object obj = hashMap.get("bx-action");
                if (i == 0) {
                    str = (String) obj;
                } else if (i == 1) {
                    str = (String) ((List) obj).get(0);
                }
                return !"sample".equals(str);
            }
        }
        str = "";
        if (!"sample".equals(str)) {
        }
    }

    private static int b() {
        if (a == 0) {
            synchronized (a.class) {
                if (a == 0) {
                    try {
                        a = Process.myPid();
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return a;
    }

    public static void b(int i, int i2, long j, String str, String str2, String str3, String str4, String str5) {
        UserTrackMethodJniBridge.addUtRecord("" + i, i2, 7, FCComponent.getPluginVersion(), j, "BXREPORT=1|BackGroud=" + c() + "|BackGroundNew=" + a(b.getContext()) + "|Pid=" + b() + "|Errmsg=" + str, str2, str3, str4, str5);
    }

    public static void b(String str) {
        try {
            if (b != null) {
                b.getRouter().doCommand(70505, new Object[]{str});
            }
        } catch (Exception unused) {
        }
    }

    public static int c() {
        int i = 2;
        try {
            if (b != null) {
                i = ((Integer) b.getRouter().doCommand(12610, new Object[0])).intValue();
                String str = "BXLOG isBackground ret1 = : " + i;
            }
        } catch (Exception e) {
            String str2 = "isBackground : " + e.getMessage();
        }
        String str3 = "isBackground ret2 = : " + i;
        return i + 1;
    }

    public static int d() {
        return a("", true);
    }
}
