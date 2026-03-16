package com.taobao.wireless.security.adapter.datacollection;

import android.content.Context;

/* renamed from: com.taobao.wireless.security.adapter.datacollection.д, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0019 {

    /* renamed from: а, reason: contains not printable characters */
    private static Context f52;

    /* JADX WARN: Removed duplicated region for block: B:6:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:9:? A[RETURN, SYNTHETIC] */
    /* renamed from: а, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m64() {
        String str;
        String str2 = null;
        try {
            str = (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", Context.class).invoke(null, f52);
            if (str != null) {
                try {
                    if (str.contains("?")) {
                        str = "";
                    }
                } catch (Exception unused) {
                    str2 = str;
                    str = str2;
                    if (str == null) {
                    }
                }
            }
        } catch (Exception unused2) {
        }
        return str == null ? str : "";
    }

    /* renamed from: а, reason: contains not printable characters */
    public static void m65(Context context) {
        if (context == null || f52 != null) {
            return;
        }
        f52 = context;
    }
}
