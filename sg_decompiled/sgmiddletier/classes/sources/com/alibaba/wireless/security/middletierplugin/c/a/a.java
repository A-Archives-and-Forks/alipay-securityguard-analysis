package com.alibaba.wireless.security.middletierplugin.c.a;

import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alibaba.wireless.security.framework.utils.UserTrackMethodJniBridge;
import com.alibaba.wireless.security.middletierplugin.c.a.b;
import com.alibaba.wireless.security.middletierplugin.open.fc.FCComponent;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public class a {
    private static Method i = null;
    private static ISecurityGuardPlugin j = null;
    private static Context k = null;
    private static volatile boolean l = false;
    private static CookieManager m;
    private static Random n = new Random();
    private static ExecutorService o;
    private Method a;
    private Method b;
    private Method c;
    private Method d;
    private Method e;
    private Method f;
    private Method g;
    private Class<?> h;

    /* renamed from: com.alibaba.wireless.security.middletierplugin.c.a.a$a, reason: collision with other inner class name */
    class RunnableC0000a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;

        RunnableC0000a(String str, String str2, int i) {
            this.a = str;
            this.b = str2;
            this.c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a.this.a(this.a, this.b, this.c);
            } catch (Throwable unused) {
            }
        }
    }

    private static class b implements InvocationHandler {
        private final b.c a;
        private final Object b;

        b(b.c cVar, Object obj) {
            this.a = cVar;
            this.b = obj;
        }

        /* JADX WARN: Code restructure failed: missing block: B:13:0x005c, code lost:
        
            if (r0 == 1) goto L20;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x005f, code lost:
        
            r5.a.b(((java.lang.Integer) com.alibaba.wireless.security.middletierplugin.c.a.a.i.invoke(r8[0], new java.lang.Object[0])).intValue());
            r5.a.a(java.lang.System.currentTimeMillis() - r5.a.e());
            com.alibaba.wireless.security.middletierplugin.c.a.b.a(r5.a);
         */
        @Override // java.lang.reflect.InvocationHandler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, objArr);
            }
            try {
                a.a("url = " + this.a.a + ", method =" + method.getName());
                String name = method.getName();
                char c = 65535;
                int hashCode = name.hashCode();
                if (hashCode != 1029576603) {
                    if (hashCode == 1123967826 && name.equals("onFinish")) {
                        c = 1;
                    }
                } else if (name.equals("onDataReceiveSize")) {
                    c = 0;
                }
                if (objArr.length >= 3) {
                    this.a.c(((Integer) objArr[1]).intValue());
                }
                if (this.b == null) {
                    return null;
                }
                method.invoke(this.b, objArr);
                return null;
            } catch (Throwable th) {
                a.a("" + th);
                return null;
            }
        }
    }

    private class c implements InvocationHandler {
        private c() {
        }

        /* synthetic */ c(a aVar, RunnableC0000a runnableC0000a) {
            this();
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            if (method.getDeclaringClass() == Object.class) {
                return method.invoke(this, objArr);
            }
            if (!"intercept".equals(method.getName()) || objArr == null || objArr.length < 1) {
                return null;
            }
            Object obj2 = objArr[0];
            Object invoke = a.this.d.invoke(obj2, new Object[0]);
            Object invoke2 = a.this.e.invoke(obj2, new Object[0]);
            a.this.a(invoke);
            if (com.alibaba.wireless.security.middletierplugin.c.a.b.c()) {
                b.c cVar = new b.c();
                Object invoke3 = a.this.c.invoke(invoke, new Object[0]);
                if (invoke3 instanceof URL) {
                    cVar.a(invoke3.toString());
                }
                Object invoke4 = a.this.g.invoke(invoke, new Object[0]);
                if (invoke4 instanceof byte[]) {
                    cVar.a(((byte[]) invoke4).length);
                }
                cVar.b(System.currentTimeMillis());
                invoke2 = Proxy.newProxyInstance(a.this.h.getClassLoader(), new Class[]{a.this.h}, new b(cVar, invoke2));
            }
            return a.this.f.invoke(obj2, invoke, invoke2);
        }
    }

    public static synchronized void a(Context context) {
        synchronized (a.class) {
            if (l) {
                return;
            }
            try {
                if (Build.VERSION.SDK_INT < 21) {
                    CookieSyncManager.createInstance(context);
                }
                m = CookieManager.getInstance();
                m.setAcceptCookie(true);
                if (Build.VERSION.SDK_INT < 21) {
                    m.removeExpiredCookie();
                }
            } catch (Throwable unused) {
            }
            l = true;
        }
    }

    public static void a(ISecurityGuardPlugin iSecurityGuardPlugin, Context context) {
        j = iSecurityGuardPlugin;
        k = context;
        a aVar = new a();
        o = Executors.newSingleThreadExecutor();
        aVar.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object obj) {
        try {
            int intValue = ((Integer) j.getRouter().doCommand(70901, new Object[0])).intValue();
            if (intValue == 0) {
                return;
            }
            Map map = (Map) this.b.invoke(obj, new Object[0]);
            String obj2 = map != null ? map.toString() : "";
            if (obj2.indexOf("x-sign") < 0) {
                return;
            }
            URL url = (URL) this.c.invoke(obj, new Object[0]);
            o.execute(new RunnableC0000a(url != null ? url.toString() : "", obj2, intValue));
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(String str) {
    }

    private void a(String str, String str2) {
        try {
            a(k);
            m.setCookie(d(str2), str);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, int i2) {
        try {
            String c2 = c(str);
            if (c2 != null && c2.indexOf("sgcookie") < 0) {
                if (str2.indexOf("x-uid") >= 0) {
                    String str3 = (String) j.getRouter().doCommand(71101, new Object[0]);
                    if (str3.length() > 0) {
                        a(str, "sgcookie=" + str3);
                        String c3 = c(str);
                        UserTrackMethodJniBridge.addUtRecord("100142", 0, 7, FCComponent.getPluginVersion(), 0L, "&urlLen=" + str.length() + "&originalCookielength=" + c2.length() + "&nowCookielength=" + c3.length(), c2, c3, str, (String) null);
                    }
                }
            }
            int length = str.length() + str2.length() + c2.length();
            String str4 = "totalLen=" + length + "&urlLen=" + str.length() + "&headersLen=" + str2.length() + "&cookiesLen=" + c2.length();
            j.getRouter().doCommand(70902, new Object[]{Integer.valueOf(length), Integer.valueOf(str.length()), Integer.valueOf(str2.length()), Integer.valueOf(c2.length())});
            if (n.nextInt(i2) % i2 != 0) {
                return;
            }
            UserTrackMethodJniBridge.addUtRecord("100133", 0, 7, FCComponent.getPluginVersion(), 0L, str4, str, (String) null, (String) null, (String) null);
        } catch (Exception e) {
            UserTrackMethodJniBridge.addUtRecord("100142", 0, 7, FCComponent.getPluginVersion(), 0L, e.toString(), str, (String) null, (String) null, (String) null);
        }
    }

    private String c(String str) {
        try {
            a(k);
            return m.getCookie(d(str));
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a5, code lost:
    
        if (r1 <= 65535) goto L120;
     */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00d9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00c9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String d(String str) {
        int i2;
        String str2;
        String str3;
        int i3;
        int i4;
        int i5;
        String substring;
        StringBuilder sb;
        if (str != null && str.length() != 0) {
            String trim = str.trim();
            int i6 = 0;
            if (trim.startsWith("//")) {
                str2 = null;
                i2 = 0;
            } else if (trim.regionMatches(true, 0, "https:", 0, 6)) {
                i2 = 6;
                str2 = "https";
            } else if (trim.regionMatches(true, 0, "http:", 0, 5)) {
                i2 = 5;
                str2 = "http";
            }
            int length = trim.length();
            int i7 = i2 + 2;
            int i8 = i7;
            boolean z = false;
            while (i8 < length) {
                char charAt = trim.charAt(i8);
                if (charAt != '[') {
                    if (charAt != ']') {
                        if (charAt == '/' || charAt == '?' || charAt == '#' || (charAt == ':' && !z)) {
                            str3 = trim.substring(i7, i8);
                            break;
                        }
                    } else {
                        z = false;
                    }
                } else {
                    z = true;
                }
                i8++;
            }
            str3 = "";
            if (i8 == length) {
                str3 = trim.substring(i7);
            }
            int i9 = 0;
            while (i8 < length) {
                char charAt2 = trim.charAt(i8);
                if (charAt2 == ':' && i9 == 0) {
                    i9 = i8 + 1;
                } else if (charAt2 == '/' || charAt2 == '#' || charAt2 == '?') {
                    i3 = i8;
                    break;
                }
                i8++;
            }
            i3 = length;
            if (i9 != 0) {
                try {
                    i4 = Integer.parseInt(trim.substring(i9, i3));
                    if (i4 > 0) {
                    }
                } catch (NumberFormatException unused) {
                }
                return null;
            }
            i4 = 0;
            while (i8 < length) {
                char charAt3 = trim.charAt(i8);
                if (charAt3 == '/' && i6 == 0) {
                    i6 = i8;
                } else if (charAt3 == '?' || charAt3 == '#') {
                    if (i6 != 0) {
                        i5 = i8;
                        substring = i6 != 0 ? trim.substring(i6, i5) : null;
                        if (str2 == null) {
                            if (i4 == 80) {
                                str2 = "http";
                            } else if (i4 == 443) {
                                str2 = "https";
                            }
                        }
                        if (str2 != null && str3 != null) {
                            sb = new StringBuilder(str2);
                            sb.append("://");
                            sb.append(str3);
                            if (i4 != 0 && (("http".equals(str2) && i4 != 80) || ("https".equals(str2) && i4 != 443))) {
                                sb.append(":");
                                sb.append(i4);
                            }
                            if (substring == null) {
                                if (i8 != length) {
                                    substring = "/";
                                }
                                return sb.toString();
                            }
                            sb.append(substring);
                            return sb.toString();
                        }
                    }
                    i5 = length;
                    if (i6 != 0) {
                    }
                    if (str2 == null) {
                    }
                    if (str2 != null) {
                        sb = new StringBuilder(str2);
                        sb.append("://");
                        sb.append(str3);
                        if (i4 != 0) {
                            sb.append(":");
                            sb.append(i4);
                        }
                        if (substring == null) {
                        }
                        sb.append(substring);
                        return sb.toString();
                    }
                }
                i8++;
            }
            i5 = length;
            if (i6 != 0) {
            }
            if (str2 == null) {
            }
            if (str2 != null) {
            }
        }
        return null;
    }

    public void a() {
        try {
            Class<?> cls = Class.forName("anetwork.channel.interceptor.InterceptorManager");
            Class<?> cls2 = Class.forName("anetwork.channel.interceptor.Interceptor");
            Class<?> cls3 = Class.forName("anet.channel.request.Request");
            this.h = Class.forName("anetwork.channel.interceptor.Callback");
            Class<?> cls4 = Class.forName("anetwork.channel.aidl.DefaultFinishEvent");
            Class<?> cls5 = Class.forName("anetwork.channel.interceptor.Interceptor$Chain");
            this.a = cls.getDeclaredMethod("addInterceptor", cls2);
            this.b = cls3.getDeclaredMethod("getHeaders", new Class[0]);
            this.c = cls3.getDeclaredMethod("getUrl", new Class[0]);
            this.d = cls5.getDeclaredMethod("request", new Class[0]);
            this.e = cls5.getDeclaredMethod("callback", new Class[0]);
            this.f = cls5.getDeclaredMethod("proceed", cls3, this.h);
            this.g = cls3.getDeclaredMethod("getBodyBytes", new Class[0]);
            i = cls4.getDeclaredMethod("getHttpCode", new Class[0]);
            try {
                this.a.invoke(cls, Proxy.newProxyInstance(cls2.getClassLoader(), new Class[]{cls2}, new c(this, null)));
            } catch (Throwable unused) {
            }
        } catch (Throwable th) {
            a("Reflect NetworkSDK interceptor failed." + th);
        }
    }
}
