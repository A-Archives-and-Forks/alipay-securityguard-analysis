package com.taobao.wireless.security.adapter.datareport;

import android.content.Context;
import android.util.Log;
import com.taobao.wireless.security.adapter.common.C0009;
import com.taobao.wireless.security.adapter.common.C0010;
import com.taobao.wireless.security.adapter.common.C0011;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class DataReportJniBridge {

    /* renamed from: а, reason: contains not printable characters */
    private static volatile int f53;

    /* renamed from: б, reason: contains not printable characters */
    private static volatile int f54;

    /* renamed from: в, reason: contains not printable characters */
    private static Class f55;

    /* renamed from: г, reason: contains not printable characters */
    private static Method f56;

    /* renamed from: д, reason: contains not printable characters */
    private static volatile int f57;

    /* renamed from: е, reason: contains not printable characters */
    private static volatile int f58;

    /* renamed from: ж, reason: contains not printable characters */
    private static volatile int f59;

    /* renamed from: з, reason: contains not printable characters */
    private static Class f60;

    /* renamed from: и, reason: contains not printable characters */
    private static Class f61;

    /* renamed from: й, reason: contains not printable characters */
    private static Method f62;

    /* renamed from: к, reason: contains not printable characters */
    private static Method f63;

    /* renamed from: л, reason: contains not printable characters */
    private static Context f64;

    /* renamed from: ё, reason: contains not printable characters */
    private static volatile int f65;

    public static int accsAvaiableBridge() {
        if (f54 == 0) {
            synchronized (DataReportJniBridge.class) {
                if (f54 == 0) {
                    try {
                        f55 = Class.forName("com.alibaba.wireless.security.open.securityguardaccsadapter.AccsAdapter");
                        Class.forName("com.alibaba.wireless.security.open.securityguardaccsadapter.AccsListner");
                        f56 = f55.getMethod("registerListner", Context.class);
                        f53 = 1;
                    } catch (ClassNotFoundException | NoSuchMethodException unused) {
                    }
                    f54 = 1;
                }
            }
        }
        return f53;
    }

    public static void initialize(Context context) {
        f64 = context;
    }

    public static int orangeAvailableBridge() {
        if (f65 == 0) {
            synchronized (DataReportJniBridge.class) {
                if (f65 == 0) {
                    try {
                        f60 = Class.forName("com.alibaba.wireless.security.open.securityguardaccsadapter.OrangeAdapter");
                        Class.forName("com.alibaba.wireless.security.open.securityguardaccsadapter.OrangeListener");
                        f62 = f60.getMethod("registerListener", Context.class);
                        f57 = 1;
                    } catch (ClassNotFoundException | NoSuchMethodException unused) {
                    }
                    f65 = 1;
                }
            }
        }
        return f57;
    }

    public static int registerAccsListnerBridge() {
        try {
            if (accsAvaiableBridge() != 0 && f64 != null) {
                f56.invoke(f55, f64);
                return 1;
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static int registerOrangeListenerBridge() {
        try {
            if (orangeAvailableBridge() != 0 && f64 != null) {
                f62.invoke(f60, f64);
                return 1;
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }

    public static int registerWindVaneListenerBridge() {
        int i = 0;
        try {
            if (windVaneCallBackAvailableBridge() != 0 && f64 != null) {
                f63.invoke(f61, f64);
                try {
                    Log.e("DataReportJniBridge", "registerWindVaneListenerBridge invoke success !!!");
                    return 1;
                } catch (Exception e) {
                    e = e;
                    i = 1;
                    Log.e("DataReportJniBridge", e.toString());
                    return i;
                }
            }
            return 0;
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static String sendReportBridge(String str, String str2, Map<String, String> map, byte[] bArr) {
        String str3;
        String str4;
        HashMap hashMap = new HashMap();
        if (C0009.m30(str2)) {
            hashMap.put("keyindex", str2);
        }
        if (map != null) {
            hashMap.putAll(map);
        }
        C0010 m33 = C0011.m33(str, hashMap, bArr, null, 11000, 11000);
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(m33.f27));
        sb.append("|");
        int i = m33.f27;
        if (i != 200) {
            if (i == 1002 && (str3 = m33.f28) != null && str3.length() > 0) {
                str4 = m33.f28;
            }
            return sb.toString();
        }
        str4 = m33.f26;
        sb.append(str4);
        return sb.toString();
    }

    public static String sendReportBridge(String str, String str2, byte[] bArr) {
        return sendReportBridge(str, str2, null, bArr);
    }

    public static String sendReportBridgeHttps(String str, String str2, byte[] bArr) {
        HashMap hashMap;
        String str3;
        String str4;
        if (C0009.m30(str2)) {
            hashMap = new HashMap();
            hashMap.put("keyindex", str2);
        } else {
            hashMap = null;
        }
        C0010 m33 = C0011.m33(str, hashMap, bArr, null, 11000, 11000);
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(m33.f27));
        sb.append("|");
        int i = m33.f27;
        if (i != 200) {
            if (i == 1002 && (str3 = m33.f28) != null && str3.length() > 0) {
                str4 = m33.f28;
            }
            return sb.toString();
        }
        str4 = m33.f26;
        sb.append(str4);
        return sb.toString();
    }

    public static int windVaneCallBackAvailableBridge() {
        if (f59 == 0) {
            synchronized (DataReportJniBridge.class) {
                if (f59 == 0) {
                    try {
                        f61 = Class.forName("com.alibaba.wireless.security.open.securityguardaccsadapter.WindvaneAdapter");
                        Class.forName("com.alibaba.wireless.security.open.securityguardaccsadapter.WindvaneListener");
                        f63 = f61.getMethod("registerWindVaneListener", Context.class);
                        f58 = 1;
                    } catch (ClassNotFoundException | NoSuchMethodException unused) {
                    }
                    f59 = 1;
                }
            }
        }
        return f58;
    }
}
