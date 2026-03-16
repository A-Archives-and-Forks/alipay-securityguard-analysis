package com.alibaba.wireless.security.securitybody;

import com.alibaba.wireless.security.framework.IRouterComponent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static final boolean f34a = false;
    private static final String b = "SecurityBodyUTCallback";
    private static boolean c = false;
    private static final String d = "YTEC";
    private static IRouterComponent e;

    static class a implements InvocationHandler {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ int[] f35a;

        a(int[] iArr) {
            this.f35a = iArr;
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x003c, code lost:
        
            if (r0 == 1) goto L24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x003e, code lost:
        
            if (r0 == 2) goto L23;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:?, code lost:
        
            return null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:?, code lost:
        
            return com.alibaba.wireless.security.securitybody.d.d;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0046, code lost:
        
            if (r9.length <= 4) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0048, code lost:
        
            r8 = new java.util.HashMap();
            r8.put("eventID", java.lang.String.valueOf(r9[1]));
            r8.put("packageName", java.lang.String.valueOf(r9[0]));
            r8.put("arg1", java.lang.String.valueOf(r9[2]));
            r8.put("arg2", java.lang.String.valueOf(r9[3]));
            r8.put("arg3", java.lang.String.valueOf(r9[4]));
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0085, code lost:
        
            com.alibaba.wireless.security.securitybody.d.b(new org.json.JSONObject(r8).toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:?, code lost:
        
            return null;
         */
        @Override // java.lang.reflect.InvocationHandler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Object invoke(Object obj, Method method, Object[] objArr) {
            HashMap hashMap;
            try {
                String name = method.getName();
                char c = 65535;
                int hashCode = name.hashCode();
                if (hashCode != -1377993658) {
                    if (hashCode != -767441515) {
                        if (hashCode == -684964780 && name.equals("getPluginName")) {
                            c = 2;
                        }
                    } else if (name.equals("onEventDispatch")) {
                        c = 1;
                    }
                } else if (name.equals("getAttentionEventIds")) {
                    c = 0;
                }
                return this.f35a;
                return hashMap;
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    public static synchronized void a(IRouterComponent iRouterComponent) {
        Class<?> cls;
        Class<?> cls2;
        Method method;
        synchronized (d.class) {
            if (c) {
                return;
            }
            e = iRouterComponent;
            c = true;
            if (b()) {
                int[] a2 = a();
                if (a2 == null) {
                    return;
                }
                try {
                    cls = Class.forName("com.alibaba.wireless.security.open.securityguardaccsadapter.usertrack.UserTrackAdaptor");
                    try {
                        cls2 = Class.forName("com.alibaba.wireless.security.open.securityguardaccsadapter.usertrack.IUserTrackPlugin");
                    } catch (Throwable unused) {
                        cls2 = null;
                    }
                } catch (Throwable unused2) {
                    cls = null;
                    cls2 = null;
                }
                try {
                    method = cls.getDeclaredMethod("registerListener", cls2);
                } catch (Throwable unused3) {
                    method = null;
                    if (cls != null) {
                    }
                    return;
                }
                if (cls != null || method == null || cls2 == null) {
                    return;
                }
                try {
                    method.invoke(null, Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new a(a2)));
                } catch (Throwable unused4) {
                }
            }
        }
    }

    private static int[] a() {
        IRouterComponent iRouterComponent = e;
        if (iRouterComponent == null) {
            return null;
        }
        try {
            return (int[]) iRouterComponent.doCommand(20802, new Object[]{0});
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str) {
        IRouterComponent iRouterComponent = e;
        if (iRouterComponent != null) {
            try {
                iRouterComponent.doCommand(20801, new Object[]{0, str});
            } catch (Throwable unused) {
            }
        }
    }

    private static boolean b() {
        IRouterComponent iRouterComponent = e;
        if (iRouterComponent != null) {
            try {
                return ((Boolean) iRouterComponent.doCommand(20803, new Object[]{0})).booleanValue();
            } catch (Throwable unused) {
            }
        }
        return false;
    }
}
