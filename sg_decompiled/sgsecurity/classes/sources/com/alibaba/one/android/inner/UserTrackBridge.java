package com.alibaba.one.android.inner;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Process;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class UserTrackBridge {

    /* renamed from: a, reason: collision with root package name */
    public static Context f6a;
    public static String b;
    public static long c = System.nanoTime();
    public static long d = System.currentTimeMillis();
    public static volatile int e = 0;
    public static volatile int f = 0;
    public static Class g = null;
    public static Class h = null;
    public static Class i = null;
    public static Constructor j = null;
    public static Method k = null;
    public static Method l = null;
    public static Method m = null;
    public static Method n = null;

    public static void addUtRecord(String str, String str2, String str3) {
        Map map;
        Object invoke;
        Object invoke2;
        if (isUtAvaiable() == 0 || str2 == null || str2.length() == 0) {
            return;
        }
        try {
            String valueOf = String.valueOf(str);
            HashMap hashMap = new HashMap();
            hashMap.put("pn", getProcessName());
            hashMap.put("pid", "" + Process.myPid());
            hashMap.put("ppid", "" + Process.myTid());
            hashMap.put("pa", "" + b);
            long nanoTime = (d * 1000) + ((System.nanoTime() - c) / 1000);
            hashMap.put("ct", "" + System.currentTimeMillis());
            hashMap.put("ctu", "" + nanoTime);
            String convertWithIteration = convertWithIteration(hashMap);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("v", String.valueOf(str3));
            Object newInstance = j.newInstance("Page_EcoOneSDK", 19997, str2, valueOf, convertWithIteration, hashMap2);
            if (newInstance == null || (map = (Map) k.invoke(newInstance, new Object[0])) == null || map.size() == 0 || (invoke = l.invoke(h, new Object[0])) == null || (invoke2 = m.invoke(invoke, new Object[0])) == null) {
                return;
            }
            n.invoke(invoke2, map);
        } catch (Exception unused) {
        }
    }

    public static String convertWithIteration(Map<String, ?> map) {
        if (map == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : map.keySet()) {
            sb.append(str + "=" + map.get(str) + ",");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0063, code lost:
    
        if (r2 == null) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String getProcessName() {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                return (String) Class.forName("android.app.Application").getDeclaredMethod("getProcessName", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception unused) {
            }
        }
        try {
            bufferedReader = new BufferedReader(new FileReader(new File("/proc/" + Process.myPid() + "/cmdline")));
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String trim = readLine.trim();
                    try {
                        bufferedReader.close();
                    } catch (IOException unused2) {
                    }
                    return trim;
                }
            } catch (Exception unused3) {
            } catch (Throwable th) {
                th = th;
                bufferedReader2 = bufferedReader;
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception unused5) {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            bufferedReader.close();
        } catch (IOException unused6) {
        }
        return null;
    }

    public static void init(Context context) {
        ApplicationInfo applicationInfo;
        if (context != null) {
            f6a = context;
            if (context == null || (applicationInfo = context.getApplicationInfo()) == null) {
                return;
            }
            b = applicationInfo.packageName;
        }
    }

    public static int isUtAvaiable() {
        if (f == 0) {
            synchronized (UserTrackBridge.class) {
                if (f == 0) {
                    try {
                        g = Class.forName("com.ut.mini.internal.UTOriginalCustomHitBuilder");
                        h = Class.forName("com.ut.mini.UTAnalytics");
                        i = Class.forName("com.ut.mini.UTTracker");
                        j = g.getConstructor(String.class, Integer.TYPE, String.class, String.class, String.class, Map.class);
                        k = g.getMethod("build", new Class[0]);
                        l = h.getMethod("getInstance", new Class[0]);
                        m = h.getMethod("getDefaultTracker", new Class[0]);
                        n = i.getMethod("send", Map.class);
                        e = 1;
                    } catch (ClassNotFoundException | NoSuchMethodException unused) {
                    }
                    f = 1;
                }
            }
        }
        return e;
    }
}
