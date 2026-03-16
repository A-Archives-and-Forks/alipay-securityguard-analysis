package com.alibaba.one.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class a {
    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            return th.getClass().getName() + ": " + th.getMessage();
        } catch (Exception unused) {
            return "";
        }
    }

    public static byte[] a(Context context) {
        long j;
        PackageInfo packageInfo;
        long j2 = 0;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception unused) {
        }
        if (packageInfo != null) {
            j = packageInfo.firstInstallTime;
            try {
                j2 = packageInfo.lastUpdateTime;
            } catch (Exception unused2) {
            }
            return (System.currentTimeMillis() + "," + j + "," + j2).getBytes();
        }
        j = 0;
        return (System.currentTimeMillis() + "," + j + "," + j2).getBytes();
    }
}
