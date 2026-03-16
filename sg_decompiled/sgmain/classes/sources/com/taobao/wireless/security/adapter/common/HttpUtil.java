package com.taobao.wireless.security.adapter.common;

import java.util.HashMap;
import java.util.Map;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class HttpUtil {

    /* renamed from: а, reason: contains not printable characters */
    private static int f23 = 1000;

    public static String downloadFileBridge(String str, String str2) {
        return C0011.m34(str, str2);
    }

    public static String sendSyncHttpGetRequestBridge(String str) {
        C0010 m32 = C0011.m32(str, (Map<String, String>) null);
        if (m32 != null) {
            return m32.f26;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0060 A[Catch: Exception -> 0x0063, TRY_LEAVE, TryCatch #0 {Exception -> 0x0063, blocks: (B:19:0x004a, B:21:0x0060), top: B:18:0x004a }] */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String sendSyncHttpPostRequestBridge(String str, String str2, String str3, int i, int i2) {
        byte[] bytes;
        C0010 m33;
        if (str == null || str.length() == 0 || str3 == null || str3.length() == 0) {
            return null;
        }
        try {
            if (i == 1) {
                if (str2 == null || str2.length() == 0) {
                    return null;
                }
                if (C0009.m30(str2)) {
                    new HashMap().put("keyindex", str2);
                }
            } else if (i == 2) {
                bytes = str3.replace("+", "%2B").getBytes();
                m33 = C0011.m33(str, null, bytes, "application/x-www-form-urlencoded;charset=UTF-8", i2 * f23, i2 * f23);
                if (m33.f27 != 200) {
                    return m33.f26;
                }
                return null;
            }
            m33 = C0011.m33(str, null, bytes, "application/x-www-form-urlencoded;charset=UTF-8", i2 * f23, i2 * f23);
            if (m33.f27 != 200) {
            }
        } catch (Exception unused) {
            return null;
        }
        bytes = str3.getBytes();
    }
}
