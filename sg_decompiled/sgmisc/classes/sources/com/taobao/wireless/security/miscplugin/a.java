package com.taobao.wireless.security.miscplugin;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmisc/classes.dex */
class a {
    public static boolean a(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        return (str == null || str2 == null || !str.equals(str2)) ? false : true;
    }

    public static boolean a(String... strArr) {
        for (String str : strArr) {
            if (str == null || "".equals(str)) {
                return true;
            }
        }
        return false;
    }
}
