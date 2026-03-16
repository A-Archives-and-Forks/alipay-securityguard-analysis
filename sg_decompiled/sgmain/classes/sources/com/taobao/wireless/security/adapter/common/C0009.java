package com.taobao.wireless.security.adapter.common;

/* renamed from: com.taobao.wireless.security.adapter.common.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0009 {
    /* renamed from: а, reason: contains not printable characters */
    public static boolean m30(String... strArr) {
        return !m31(strArr);
    }

    /* renamed from: б, reason: contains not printable characters */
    public static boolean m31(String... strArr) {
        for (String str : strArr) {
            if (str == null || "".equals(str)) {
                return true;
            }
        }
        return false;
    }
}
