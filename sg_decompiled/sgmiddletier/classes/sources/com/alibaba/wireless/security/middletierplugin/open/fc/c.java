package com.alibaba.wireless.security.middletierplugin.open.fc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public class c {
    private static volatile boolean a = false;
    public static int b = 10;
    private static volatile long c;

    public static String a(int i, HashMap hashMap, String str, String str2, String str3) {
        String substring;
        try {
            if (!hashMap.containsKey(str2)) {
                if (str == null || str.isEmpty()) {
                    return "";
                }
                for (String str4 : str.split("&")) {
                    if (str4.startsWith(str3)) {
                        substring = str4.substring(str3.length());
                    }
                }
                return "";
            }
            Object obj = hashMap.get(str2);
            substring = i == 0 ? (String) obj : i == 1 ? (String) ((List) obj).get(0) : "";
            return substring;
        } catch (Exception e) {
            String str5 = "getBXUUID : " + e.getMessage();
            return "";
        }
    }

    public static HashMap<String, Object> a(JSONObject jSONObject) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.get(next));
            }
        } catch (Exception e) {
            String str = "JsonToMap Exception : " + e.getMessage();
        }
        return hashMap;
    }

    public static void a(com.alibaba.wireless.security.middletierplugin.open.fc.strategy.c cVar) {
        if (cVar.c() == 0) {
            return;
        }
        c = System.currentTimeMillis() + cVar.c();
    }

    public static void a(boolean z) {
        a = z;
    }

    public static boolean a() {
        return a;
    }

    public static boolean a(long j, String str) {
        boolean z = j % ((long) b) == 0;
        if (str == null) {
            return z;
        }
        try {
            return j % ((long) b) < ((long) Integer.parseInt(str));
        } catch (Exception e) {
            String str2 = "shouldUtOrNot needUT = " + str + ", error : " + e.getMessage();
            return z;
        }
    }

    public static boolean a(com.alibaba.wireless.security.middletierplugin.open.fc.strategy.c cVar, boolean z) {
        if (cVar.c() == 0 || z) {
            return true;
        }
        return c <= 0 || System.currentTimeMillis() >= c;
    }
}
