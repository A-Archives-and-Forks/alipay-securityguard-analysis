package com.alibaba.wireless.security.securitybody;

import java.lang.reflect.Method;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class ApmSecurityBodyPluginAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static Object f24a;
    private static Method b;
    private static Method c;

    public static void init() {
        try {
            Class<?> cls = Class.forName("com.alibaba.wireless.security.framework.SGApmMonitorManager");
            if (cls != null) {
                Method method = cls.getMethod("getInstance", new Class[0]);
                b = cls.getMethod("monitorStart", String.class);
                c = cls.getMethod("monitorEnd", String.class);
                if (method != null) {
                    f24a = method.invoke(cls, new Object[0]);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void monitorEnd(String str) {
        try {
            if (f24a == null || c == null) {
                return;
            }
            c.invoke(f24a, str);
        } catch (Exception unused) {
        }
    }

    public static void monitorStart(String str) {
        try {
            if (f24a == null || b == null) {
                return;
            }
            b.invoke(f24a, str);
        } catch (Exception unused) {
        }
    }
}
