package com.alibaba.wireless.security.middletierplugin.d.d;

import android.content.Context;
import com.alibaba.wireless.security.middletierplugin.b;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public class a {
    private static Object a;
    private static ISecurityGuardPlugin b;
    private static a c;
    private static InvocationHandler d = new C0005a();

    /* renamed from: com.alibaba.wireless.security.middletierplugin.d.d.a$a, reason: collision with other inner class name */
    static class C0005a implements InvocationHandler {
        C0005a() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            char c;
            String name = method.getName();
            int hashCode = name.hashCode();
            if (hashCode != 3237136) {
                if (hashCode == 165313390 && name.equals("getSecurityFactors")) {
                    c = 1;
                }
                c = 65535;
            } else {
                if (name.equals("init")) {
                    c = 0;
                }
                c = 65535;
            }
            if (c == 0) {
                a.c.b((HashMap) objArr[0]);
                return null;
            }
            if (c != 1) {
                return null;
            }
            return a.c.a((HashMap<String, Object>) objArr[0]);
        }
    }

    private a(ISecurityGuardPlugin iSecurityGuardPlugin) {
        b = iSecurityGuardPlugin;
    }

    public static Object a(Class cls, ISecurityGuardPlugin iSecurityGuardPlugin, Context context) {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = b.a(cls, d);
                    c = new a(iSecurityGuardPlugin);
                }
            }
        }
        return a;
    }

    private String a(String str) {
        if (str != null) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private String c(HashMap<String, String> hashMap) {
        String a2;
        StringBuilder sb = new StringBuilder();
        if (hashMap != null) {
            Iterator<String> it = hashMap.keySet().iterator();
            while (it.hasNext()) {
                String a3 = a(it.next());
                if (a3 != null && a3.length() > 0 && (a2 = a(hashMap.get(a3))) != null) {
                    if (sb.length() != 0) {
                        sb.append("&");
                    }
                    sb.append(a3);
                    sb.append("=");
                    sb.append(a2);
                }
            }
        }
        return sb.toString();
    }

    public HashMap<String, String> a(HashMap<String, Object> hashMap) {
        if (hashMap == null || hashMap.isEmpty()) {
            throw new SecException(2401);
        }
        if (hashMap.isEmpty() || !hashMap.containsKey("appkey") || !hashMap.containsKey("data") || !hashMap.containsKey("useWua") || hashMap.get("appkey") == null || hashMap.get("data") == null || hashMap.get("useWua") == null) {
            throw new SecException(2401);
        }
        com.alibaba.wireless.security.middletierplugin.a.b("getSecurityFactors");
        String str = (String) hashMap.get("appkey");
        String str2 = (String) hashMap.get("data");
        boolean booleanValue = ((Boolean) hashMap.get("useWua")).booleanValue();
        String str3 = null;
        String str4 = (!hashMap.containsKey("authCode") || hashMap.get("authCode") == null) ? null : (String) hashMap.get("authCode");
        String str5 = (!hashMap.containsKey("signKey") || hashMap.get("signKey") == null) ? null : (String) hashMap.get("signKey");
        int intValue = (!hashMap.containsKey("env") || hashMap.get("env") == null) ? 0 : ((Integer) hashMap.get("env")).intValue();
        String str6 = hashMap.get("api") != null ? (String) hashMap.get("api") : null;
        String c2 = (!hashMap.containsKey("extendParas") || hashMap.get("extendParas") == null) ? null : c((HashMap) hashMap.get("extendParas"));
        String str7 = (!hashMap.containsKey("miniWua") || hashMap.get("miniWua") == null) ? null : (String) hashMap.get("miniWua");
        if (hashMap.containsKey("requestId") && hashMap.get("requestId") != null) {
            str3 = (String) hashMap.get("requestId");
        }
        HashMap<String, String> hashMap2 = (HashMap) b.getRouter().doCommand(70102, new Object[]{str, str2, Boolean.valueOf(booleanValue), Integer.valueOf(intValue), str6, c2, str4, str5, str7, str3, Integer.valueOf((!hashMap.containsKey("bizId") || hashMap.get("bizId") == null) ? 0 : ((Integer) hashMap.get("bizId")).intValue()), Integer.valueOf((!hashMap.containsKey("flag") || hashMap.get("flag") == null) ? 0 : ((Integer) hashMap.get("flag")).intValue())});
        com.alibaba.wireless.security.middletierplugin.a.a("getSecurityFactors");
        return hashMap2;
    }

    public void b(HashMap<String, Object> hashMap) {
        b.getRouter().doCommand(70101, new Object[]{(hashMap == null || hashMap.isEmpty() || !hashMap.containsKey("authCode")) ? "" : (String) hashMap.get("authCode"), Integer.valueOf(hashMap.containsKey("flag") ? ((Integer) hashMap.get("flag")).intValue() : 0)});
    }
}
