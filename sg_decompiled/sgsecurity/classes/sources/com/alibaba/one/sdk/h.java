package com.alibaba.one.sdk;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import org.json.JSONObject;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    public static volatile String f14a;
    public static volatile PackageManager b;
    public static Context c;

    public static String a() {
        PackageManager packageManager;
        Context context = c;
        if (context == null || (packageManager = context.getPackageManager()) == null) {
            return null;
        }
        String packageName = c.getPackageName();
        JSONObject jSONObject = new JSONObject();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            if (applicationInfo != null) {
                jSONObject.put("flag", applicationInfo.flags);
                jSONObject.put("dlp", packageManager.getApplicationLabel(applicationInfo).toString());
            }
        } catch (Exception unused) {
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            if (packageInfo != null) {
                jSONObject.put("fi", packageInfo.firstInstallTime);
                jSONObject.put("lu", packageInfo.lastUpdateTime);
            }
        } catch (Exception unused2) {
        }
        return jSONObject.toString();
    }

    public static String b() {
        String str;
        String str2;
        try {
            StringBuilder sb = new StringBuilder();
            for (FeatureInfo featureInfo : c.getPackageManager().getSystemAvailableFeatures()) {
                String str3 = featureInfo.name;
                if (str3 != null) {
                    if (str3.startsWith("android.hardware")) {
                        str = "android.hardware.";
                        str2 = "H.";
                    } else if (str3.startsWith("android.software")) {
                        str = "android.software.";
                        str2 = "S.";
                    }
                    sb.append(str3.replace(str, str2));
                    sb.append(",");
                }
            }
            return sb.toString();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean c() {
        try {
            int myPid = Process.myPid();
            if (c == null) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) c.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.importance == 100;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean d() {
        PackageInfo packageInfo;
        try {
            if (b != null && f14a != null && (packageInfo = b.getPackageInfo(f14a, 0)) != null && (packageInfo.applicationInfo.flags & 1) != 0) {
                if ((packageInfo.applicationInfo.flags & 128) == 0) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }
}
