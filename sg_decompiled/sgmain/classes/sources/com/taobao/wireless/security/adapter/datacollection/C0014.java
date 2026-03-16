package com.taobao.wireless.security.adapter.datacollection;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import java.io.File;

/* renamed from: com.taobao.wireless.security.adapter.datacollection.б, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0014 {

    /* renamed from: а, reason: contains not printable characters */
    private static Context f45;

    @TargetApi(3)
    /* renamed from: а, reason: contains not printable characters */
    public static String m52() {
        try {
            String string = Settings.Secure.getString(f45.getContentResolver(), "android_id");
            return string != null ? string.trim() : "";
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: а, reason: contains not printable characters */
    private static String m53(File file) {
        if (file == null) {
            return null;
        }
        StatFs statFs = new StatFs(file.getPath());
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                Long l = (Long) statFs.getClass().getDeclaredMethod("getAvailableBytes", new Class[0]).invoke(statFs, new Object[0]);
                if (l != null) {
                    return l.toString();
                }
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
        long availableBlocks = statFs.getAvailableBlocks();
        return (statFs.getBlockCount() * availableBlocks) + "";
    }

    /* renamed from: а, reason: contains not printable characters */
    public static void m54(Context context) {
        if (context == null || f45 != null) {
            return;
        }
        f45 = context;
    }

    /* renamed from: б, reason: contains not printable characters */
    public static String m55() {
        try {
            File dataDirectory = Environment.getDataDirectory();
            if (dataDirectory != null && dataDirectory.getAbsolutePath().length() != 0) {
                return m53(dataDirectory);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
