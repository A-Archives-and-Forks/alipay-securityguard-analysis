package com.alibaba.wireless.security.mainplugin;

import java.lang.reflect.Method;

/* renamed from: com.alibaba.wireless.security.mainplugin.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0002 {

    /* renamed from: а, reason: contains not printable characters */
    private static Object f8;

    /* renamed from: б, reason: contains not printable characters */
    private static Method f9;

    /* renamed from: в, reason: contains not printable characters */
    private static Method f10;

    /* renamed from: а, reason: contains not printable characters */
    public static void m2() {
        try {
            Class<?> cls = Class.forName("com.alibaba.wireless.security.framework.SGApmMonitorManager");
            if (cls != null) {
                Method method = cls.getMethod("getInstance", new Class[0]);
                f9 = cls.getMethod("monitorStart", String.class);
                f10 = cls.getMethod("monitorEnd", String.class);
                if (method != null) {
                    f8 = method.invoke(cls, new Object[0]);
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: а, reason: contains not printable characters */
    public static void m3(String str) {
        try {
            if (f8 == null || f10 == null) {
                return;
            }
            f10.invoke(f8, str);
        } catch (Exception unused) {
        }
    }

    /* renamed from: б, reason: contains not printable characters */
    public static void m4(String str) {
        try {
            if (f8 == null || f9 == null) {
                return;
            }
            f9.invoke(f8, str);
        } catch (Exception unused) {
        }
    }
}
