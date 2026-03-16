package com.taobao.wireless.security.adapter.datacollection;

import android.content.Context;
import com.alibaba.wireless.security.mainplugin.SecurityGuardMainPlugin;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class DeviceInfoCapturer {

    /* renamed from: а, reason: contains not printable characters */
    private static Context f37 = null;

    /* renamed from: б, reason: contains not printable characters */
    private static C0013 f38 = null;

    /* renamed from: в, reason: contains not printable characters */
    private static SecurityGuardMainPlugin f39 = null;

    /* renamed from: г, reason: contains not printable characters */
    private static volatile boolean f40 = false;

    /* renamed from: д, reason: contains not printable characters */
    private static Object f41;

    private DeviceInfoCapturer() {
    }

    public static String doCommandForString(int i) {
        if (!f40) {
            synchronized (f41) {
                if (!f40) {
                    C0018.m61(f37);
                    C0014.m54(f37);
                    C0019.m65(f37);
                    f40 = true;
                }
            }
        }
        String str = null;
        if (i != 0) {
            if (i != 109) {
                if (i == 126) {
                    str = m43();
                } else if (i == 130) {
                    str = C0014.m52();
                } else if (i == 135) {
                    str = C0019.m64();
                } else if (i == 146) {
                    str = C0014.m55();
                } else if (i != 104 && i != 105) {
                    switch (i) {
                        case 121:
                            str = m42();
                            break;
                        case 122:
                            str = C0018.m63();
                            break;
                        case 123:
                            str = C0018.m60();
                            break;
                    }
                }
            }
            return str;
        }
        str = C0018.m62("android.hardware.wifi") ? "1" : "0";
        return str;
    }

    public static void initialize(ISecurityGuardPlugin iSecurityGuardPlugin, C0013 c0013) {
        f39 = (SecurityGuardMainPlugin) iSecurityGuardPlugin;
        f37 = f39.getContext();
        f38 = c0013;
        f41 = new Object();
    }

    /* renamed from: а, reason: contains not printable characters */
    private static String m42() {
        C0013 c0013 = f38;
        if (c0013 != null) {
            return c0013.m48();
        }
        return null;
    }

    /* renamed from: б, reason: contains not printable characters */
    private static String m43() {
        C0013 c0013 = f38;
        if (c0013 != null) {
            return c0013.m49(64);
        }
        return null;
    }
}
