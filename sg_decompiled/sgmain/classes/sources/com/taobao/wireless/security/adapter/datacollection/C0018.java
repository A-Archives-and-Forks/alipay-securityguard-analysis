package com.taobao.wireless.security.adapter.datacollection;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/* renamed from: com.taobao.wireless.security.adapter.datacollection.г, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0018 {

    /* renamed from: а, reason: contains not printable characters */
    private static String f50;

    /* renamed from: б, reason: contains not printable characters */
    private static PackageManager f51;

    /* renamed from: а, reason: contains not printable characters */
    public static String m60() {
        PackageInfo packageInfo;
        String str;
        try {
            if (f51 == null || f50 == null || (packageInfo = f51.getPackageInfo(f50, 0)) == null || (str = packageInfo.versionName) == null) {
                return null;
            }
            if (str.length() != 0) {
                return str;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: а, reason: contains not printable characters */
    public static void m61(Context context) {
        if (context == null || f51 != null) {
            return;
        }
        try {
            f50 = context.getPackageName();
        } catch (Throwable unused) {
        }
        try {
            f51 = context.getPackageManager();
        } catch (Throwable unused2) {
        }
    }

    /* renamed from: а, reason: contains not printable characters */
    public static boolean m62(String str) {
        try {
            PackageManager packageManager = f51;
            if (packageManager != null) {
                return packageManager.hasSystemFeature(str);
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: б, reason: contains not printable characters */
    public static String m63() {
        return f50;
    }
}
