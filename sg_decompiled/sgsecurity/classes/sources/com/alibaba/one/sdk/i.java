package com.alibaba.one.sdk;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import java.util.List;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    public static SensorManager f15a;
    public static Context b;

    /* JADX WARN: Removed duplicated region for block: B:5:0x0052 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a() {
        SensorManager sensorManager;
        List<Sensor> sensorList;
        String a2;
        Context context = b;
        if (context != null) {
            try {
                sensorManager = (SensorManager) context.getSystemService("sensor");
            } catch (Exception unused) {
            }
            if (sensorManager != null && (sensorList = sensorManager.getSensorList(-1)) != null && sensorList.size() > 0) {
                StringBuilder sb = new StringBuilder();
                for (Sensor sensor : sensorList) {
                    sb.append(sensor.getName());
                    sb.append(sensor.getVersion());
                    sb.append(sensor.getVendor());
                }
                a2 = g.a(sb.toString());
                return a2 != null ? "" : a2;
            }
        }
        a2 = null;
        if (a2 != null) {
        }
    }
}
