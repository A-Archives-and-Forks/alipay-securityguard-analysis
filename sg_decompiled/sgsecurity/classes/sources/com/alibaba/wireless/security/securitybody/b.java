package com.alibaba.wireless.security.securitybody;

import android.content.Context;
import android.telephony.TelephonyManager;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
class b {

    /* renamed from: a, reason: collision with root package name */
    private static TelephonyManager f32a = null;
    private static volatile String b = null;
    private static volatile String c = null;
    private static final int d = 2;
    private static int e;
    private static int f;
    private static Context g;

    b() {
    }

    public static void a(Context context) {
        if (context != null && g == null) {
            g = context;
        }
        if (context == null || f32a != null) {
            return;
        }
        try {
            f32a = (TelephonyManager) context.getSystemService("phone");
        } catch (Throwable unused) {
        }
    }
}
