package com.alibaba.one.sdk;

import android.app.UiModeManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import org.json.JSONObject;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    public static Context f12a;
    public static long b;
    public static String c;

    public static String a(File file) {
        if (file != null) {
            try {
                if (file.getAbsolutePath().length() != 0) {
                    StatFs statFs = new StatFs(file.getPath());
                    return Build.VERSION.SDK_INT >= 18 ? String.valueOf(statFs.getAvailableBytes()) : String.valueOf(statFs.getAvailableBlocks() * statFs.getBlockSize());
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    public static synchronized void a() {
        Intent registerReceiver;
        synchronized (f.class) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                if (b == 0 || currentTimeMillis - b >= 10000 || c == null) {
                    b = currentTimeMillis;
                    Context context = f12a;
                    if (context != null && (registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"))) != null) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("level", registerReceiver.getIntExtra("level", -1));
                        jSONObject.put("voltage", registerReceiver.getIntExtra("voltage", -1));
                        jSONObject.put("temperature", registerReceiver.getIntExtra("temperature", -1));
                        jSONObject.put("status", registerReceiver.getIntExtra("status", -1));
                        jSONObject.put("health", registerReceiver.getIntExtra("health", -1));
                        jSONObject.put("present", registerReceiver.getIntExtra("present", -1));
                        jSONObject.put("plugged", registerReceiver.getIntExtra("plugged", -1));
                        c = jSONObject.toString();
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static boolean a(Context context) {
        try {
            return context.getPackageManager().hasSystemFeature("android.hardware.type.television");
        } catch (Exception unused) {
            return false;
        }
    }

    public static String b(File file) {
        if (file != null) {
            try {
                if (file.getAbsolutePath().length() != 0) {
                    StatFs statFs = new StatFs(file.getPath());
                    return Build.VERSION.SDK_INT >= 18 ? String.valueOf(statFs.getFreeBytes()) : String.valueOf(statFs.getFreeBlocks() * statFs.getBlockSize());
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x009e, code lost:
    
        if (r3 == null) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static JSONObject b() {
        JSONObject jSONObject;
        BufferedReader bufferedReader;
        boolean z;
        boolean z2;
        boolean z3;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                jSONObject = new JSONObject();
                try {
                    bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"));
                    z = false;
                    z2 = false;
                    z3 = false;
                } catch (Exception unused) {
                    bufferedReader = null;
                }
            } catch (Exception unused2) {
                jSONObject = null;
                bufferedReader = null;
            }
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        if (readLine.startsWith("MemTotal")) {
                            jSONObject.put("t", readLine.substring(readLine.indexOf("MemTotal:") + 9 + 1, readLine.indexOf("kB")).trim());
                            z = true;
                        }
                        if (readLine.startsWith("MemFree")) {
                            jSONObject.put("f", readLine.substring(readLine.indexOf("MemFree:") + 8 + 1, readLine.indexOf("kB")).trim());
                            z2 = true;
                        }
                        if (readLine.startsWith("MemAvailable")) {
                            jSONObject.put("a", readLine.substring(readLine.indexOf("MemAvailable:") + 13 + 1, readLine.indexOf("kB")).trim());
                            z3 = true;
                        }
                        if (!z || !z2 || !z3) {
                        }
                    }
                } catch (Exception unused3) {
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception unused4) {
                        }
                    }
                    throw th;
                }
                try {
                    bufferedReader.close();
                    break;
                } catch (Exception unused5) {
                }
            }
            return jSONObject;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean b(Context context) {
        return ((UiModeManager) context.getSystemService("uimode")).getCurrentModeType() == 4;
    }

    public static String c() {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        try {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("ram", b());
            try {
                jSONObject = new JSONObject();
                try {
                    File dataDirectory = Environment.getDataDirectory();
                    jSONObject.put("t", c(dataDirectory));
                    jSONObject.put("f", b(dataDirectory));
                    jSONObject.put("a", a(dataDirectory));
                } catch (Throwable unused) {
                }
            } catch (Throwable unused2) {
                jSONObject = null;
            }
            jSONObject3.put("rom", jSONObject);
            try {
                jSONObject2 = new JSONObject();
                try {
                    if ("mounted".equals(Environment.getExternalStorageState())) {
                        File externalStorageDirectory = Environment.getExternalStorageDirectory();
                        jSONObject2.put("t", c(externalStorageDirectory));
                        jSONObject2.put("f", b(externalStorageDirectory));
                        jSONObject2.put("a", a(externalStorageDirectory));
                    }
                } catch (Throwable unused3) {
                }
            } catch (Throwable unused4) {
                jSONObject2 = null;
            }
            jSONObject3.put("sdcard", jSONObject2);
            return jSONObject3.toString();
        } catch (Exception unused5) {
            return null;
        }
    }

    public static String c(File file) {
        if (file != null) {
            try {
                if (file.getAbsolutePath().length() != 0) {
                    StatFs statFs = new StatFs(file.getPath());
                    return Build.VERSION.SDK_INT >= 18 ? String.valueOf(statFs.getTotalBytes()) : String.valueOf(statFs.getBlockCount() * statFs.getBlockSize());
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static boolean c(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 3;
    }

    public static String d() {
        int i;
        int i2;
        Context context = f12a;
        if (context == null) {
            return "";
        }
        try {
            Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getMetrics(displayMetrics);
            float f = displayMetrics.xdpi;
            float f2 = displayMetrics.ydpi;
            int i3 = Build.VERSION.SDK_INT;
            if (i3 < 14 || i3 >= 17) {
                if (i3 >= 17) {
                    defaultDisplay.getRealMetrics(displayMetrics);
                }
                i = displayMetrics.widthPixels;
                i2 = displayMetrics.heightPixels;
            } else {
                Method method = Display.class.getMethod("getRawWidth", new Class[0]);
                Method method2 = Display.class.getMethod("getRawHeight", new Class[0]);
                int intValue = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
                int intValue2 = ((Integer) method2.invoke(defaultDisplay, new Object[0])).intValue();
                i = intValue;
                i2 = intValue2;
            }
            double d = i;
            double d2 = f;
            Double.isNaN(d);
            Double.isNaN(d2);
            double d3 = d / d2;
            double d4 = i2;
            double d5 = f2;
            Double.isNaN(d4);
            Double.isNaN(d5);
            return new DecimalFormat("#.##").format(d3) + "*" + new DecimalFormat("#.##").format(d4 / d5) + "*" + Float.toString(defaultDisplay.getRefreshRate());
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String e() {
        Context context = f12a;
        if (context == null) {
            return "";
        }
        try {
            new DisplayMetrics();
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            return String.valueOf(Math.min(i, i2)) + "*" + String.valueOf(Math.max(i, i2));
        } catch (Exception unused) {
            return "";
        }
    }
}
