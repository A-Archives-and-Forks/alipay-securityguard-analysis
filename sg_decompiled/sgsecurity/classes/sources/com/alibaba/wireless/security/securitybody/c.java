package com.alibaba.wireless.security.securitybody;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
class c {

    /* renamed from: a, reason: collision with root package name */
    private static Context f33a = null;
    private static WifiManager b = null;
    private static final String c = "02:00:00:00:00:00";
    private static final String d = "00:00:00:00:00:00";

    c() {
    }

    public static void a(Context context) {
        if (context != null && f33a == null) {
            f33a = context;
        }
        if (context == null || b != null) {
            return;
        }
        try {
            b = (WifiManager) context.getSystemService("wifi");
        } catch (Throwable unused) {
        }
    }

    public static boolean a() {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        Context context = f33a;
        return context != null && context.checkSelfPermission("android.permission.INTERNET") == 0;
    }

    public static boolean a(String str) {
        return (str == null || str.equalsIgnoreCase(c) || str.equalsIgnoreCase(d)) ? false : true;
    }
}
