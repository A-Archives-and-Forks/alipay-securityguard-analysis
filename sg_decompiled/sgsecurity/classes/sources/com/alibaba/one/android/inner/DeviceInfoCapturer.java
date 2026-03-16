package com.alibaba.one.android.inner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.alibaba.one.sdk.b;
import com.alibaba.one.sdk.c;
import com.alibaba.one.sdk.d;
import com.alibaba.one.sdk.g;
import com.alibaba.one.sdk.i;
import com.alibaba.one.sdk.j;
import com.alibaba.one.sdk.k;
import com.alibaba.wireless.security.securitybody.LifeCycle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint({"MissingPermission"})
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class DeviceInfoCapturer {

    /* renamed from: a, reason: collision with root package name */
    public static Context f4a;
    public static volatile String b;
    public static volatile String c;
    public static Map<Integer, String> d = new HashMap();
    public static Map<Integer, Integer> e = new HashMap();
    public static final List<Integer> f = Arrays.asList(0, 1, 2, 13, 11, 15, 16, 12, 18, 25, 26, 27, 28, 24);
    public static final List<Integer> g = Arrays.asList(25, 26, 27, 28);

    /* JADX WARN: Removed duplicated region for block: B:25:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String a(String str) {
        BufferedReader bufferedReader;
        FileInputStream fileInputStream;
        String str2;
        if (str == null) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(new File("/sys/class/net/" + str + "/address"));
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream), 256);
                try {
                    str2 = bufferedReader.readLine();
                    try {
                        bufferedReader.close();
                    } catch (IOException unused) {
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException unused2) {
                    }
                } catch (Throwable unused3) {
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException unused4) {
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException unused5) {
                        }
                    }
                    str2 = null;
                    if (str2 == null) {
                        if ((str2 == null || str2.equalsIgnoreCase("02:00:00:00:00:00") || str2.equalsIgnoreCase("00:00:00:00:00:00")) ? false : true) {
                        }
                    }
                    if ((str2 == null || str2.equalsIgnoreCase("02:00:00:00:00:00") || str2.equalsIgnoreCase("00:00:00:00:00:00")) ? false : true) {
                    }
                }
            } catch (Throwable unused6) {
                bufferedReader = null;
            }
        } catch (Throwable unused7) {
            bufferedReader = null;
            fileInputStream = null;
        }
        if ((str2 == null || str2.equalsIgnoreCase("02:00:00:00:00:00") || str2.equalsIgnoreCase("00:00:00:00:00:00")) ? false : true) {
            return str2;
        }
        return null;
    }

    public static String a(boolean z) {
        WindowManager windowManager = (WindowManager) f4a.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (!z) {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        } else if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        }
        return displayMetrics.widthPixels + "*" + displayMetrics.heightPixels;
    }

    public static /* synthetic */ boolean a() {
        return false;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x003e -> B:8:0x003f). Please report as a decompilation issue!!! */
    public static String b() {
        long j;
        String str;
        if (Build.VERSION.SDK_INT > 8) {
            PackageInfo packageInfo = f4a.getPackageManager().getPackageInfo(f4a.getPackageName(), 0);
            if (packageInfo != null) {
                j = packageInfo.firstInstallTime;
            }
            j = -1;
        } else {
            ApplicationInfo applicationInfo = f4a.getPackageManager().getApplicationInfo(f4a.getPackageName(), 0);
            if (applicationInfo != null && (str = applicationInfo.sourceDir) != null) {
                j = new File(str).lastModified();
            }
            j = -1;
        }
        if (j != -1) {
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Long.valueOf(j));
            } catch (Exception unused) {
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x001f A[Catch: all -> 0x003f, TryCatch #1 {all -> 0x003f, blocks: (B:3:0x0001, B:6:0x0015, B:8:0x001f, B:10:0x0028, B:12:0x002c, B:14:0x0031, B:25:0x0011, B:22:0x000b), top: B:2:0x0001, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String c() {
        String packageName;
        PackageInfo packageInfo;
        try {
            PackageManager packageManager = f4a.getPackageManager();
            Context context = f4a;
            if (context != null) {
                try {
                    packageName = context.getPackageName();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                packageInfo = packageManager.getPackageInfo(packageName, 64);
                if (g.f13a == null) {
                    g.f13a = new g();
                }
                if (packageInfo != null || packageInfo.signatures == null || packageInfo.signatures.length <= 0) {
                    return null;
                }
                return g.a(packageInfo.signatures[0].toCharsString());
            }
            packageName = null;
            packageInfo = packageManager.getPackageInfo(packageName, 64);
            if (g.f13a == null) {
            }
            return packageInfo != null ? null : null;
        } catch (Throwable th2) {
            th2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0030, code lost:
    
        r1 = r3[1].trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0036, code lost:
    
        r2.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String d() {
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        String str = null;
        bufferedReader = null;
        try {
            fileReader = new FileReader("/proc/cpuinfo");
            try {
                BufferedReader bufferedReader2 = new BufferedReader(fileReader, 8192);
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            if (readLine.length() > 0) {
                                String[] split = readLine.split(":");
                                if (split.length > 1 && split[0].contains("BogoMIPS")) {
                                    break;
                                }
                            }
                        }
                    } catch (Throwable unused) {
                        bufferedReader = bufferedReader2;
                        if (fileReader != null) {
                            try {
                                fileReader.close();
                            } catch (Throwable unused2) {
                            }
                        }
                        if (bufferedReader == null) {
                            return "";
                        }
                        try {
                            bufferedReader.close();
                            return "";
                        } catch (Throwable unused3) {
                            return "";
                        }
                    }
                }
                try {
                    bufferedReader2.close();
                } catch (Throwable unused4) {
                }
                return str;
                return str;
            } catch (Throwable unused5) {
            }
        } catch (Throwable unused6) {
            fileReader = null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x0253, code lost:
    
        if (r1 == null) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x02a8, code lost:
    
        if (r2 == null) goto L163;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x0309, code lost:
    
        if (r3 != null) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x0179, code lost:
    
        if (r3 != null) goto L188;
     */
    /* JADX WARN: Code restructure failed: missing block: B:218:0x017e, code lost:
    
        if (r3 == null) goto L183;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01cb, code lost:
    
        if (r3.length() != 0) goto L188;
     */
    /* JADX WARN: Removed duplicated region for block: B:157:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x02b5 A[Catch: all -> 0x02bb, TRY_LEAVE, TryCatch #18 {all -> 0x02bb, blocks: (B:52:0x00e2, B:121:0x0255, B:155:0x02ae, B:158:0x02b5), top: B:19:0x0062 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String doCommandForString(int i) {
        String valueOf;
        NetworkInfo activeNetworkInfo;
        File[] listFiles;
        FileReader fileReader;
        BufferedReader bufferedReader;
        String readLine;
        BufferedReader bufferedReader2;
        FileReader fileReader2;
        String str;
        if (f.contains(Integer.valueOf(i))) {
            String str2 = d.get(Integer.valueOf(i));
            Integer num = e.get(Integer.valueOf(i));
            int i2 = g.contains(Integer.valueOf(i)) ? 2 : 1;
            if (str2 != null || (num != null && num.intValue() >= i2)) {
                return str2;
            }
            e.put(Integer.valueOf(i), Integer.valueOf(num == null ? 1 : num.intValue() + 1));
        }
        String str3 = "0";
        long j = 0;
        BufferedReader bufferedReader3 = null;
        switch (i) {
            case LifeCycle.APP_BACKGROUND /* 0 */:
            case 1:
            case LifeCycle.APP_IGNORE /* 2 */:
            case 14:
            case 16:
            default:
                str3 = null;
                break;
            case 3:
                valueOf = a(false);
                str3 = valueOf;
                break;
            case 4:
                valueOf = a(true);
                str3 = valueOf;
                break;
            case 5:
                try {
                    Class<?> cls = Class.forName("android.os.SystemProperties");
                    str3 = (String) cls.getMethod("get", String.class, String.class).invoke(cls.newInstance(), "gsm.version.baseband", "no message");
                    break;
                } catch (Throwable unused) {
                    str3 = "";
                    break;
                }
            case 6:
                try {
                    listFiles = new File("/sys/devices/system/cpu/").listFiles(new c());
                } catch (Throwable unused2) {
                    valueOf = String.valueOf(1);
                }
                if (listFiles != null) {
                    valueOf = String.valueOf(listFiles.length);
                    str3 = valueOf;
                    break;
                }
                break;
            case 7:
                try {
                    fileReader = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
                    try {
                        bufferedReader = new BufferedReader(fileReader, 8192);
                        try {
                            readLine = bufferedReader.readLine();
                        } catch (Throwable unused3) {
                            bufferedReader3 = bufferedReader;
                            if (bufferedReader3 != null) {
                                try {
                                    bufferedReader3.close();
                                    break;
                                } catch (Throwable unused4) {
                                    break;
                                }
                            }
                        }
                    } catch (Throwable unused5) {
                    }
                } catch (Throwable unused6) {
                    fileReader = null;
                }
                if (readLine == null || readLine.length() <= 0) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable unused7) {
                    }
                    try {
                        fileReader.close();
                    } catch (Throwable unused8) {
                    }
                    str3 = "";
                    if (str3.length() > 0) {
                        valueOf = d();
                        str3 = valueOf;
                        break;
                    } else {
                        break;
                    }
                } else {
                    String trim = readLine.trim();
                    try {
                        bufferedReader.close();
                    } catch (Throwable unused9) {
                    }
                    try {
                        fileReader.close();
                    } catch (Throwable unused10) {
                    }
                    str3 = trim;
                    if (str3.length() > 0) {
                    }
                }
                break;
            case 8:
                StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                j = statFs.getBlockCount() * statFs.getBlockSize();
                valueOf = String.valueOf(j);
                str3 = valueOf;
                break;
            case 9:
                try {
                    fileReader2 = new FileReader("/proc/meminfo");
                    try {
                        bufferedReader2 = new BufferedReader(fileReader2, 8192);
                        try {
                            String readLine2 = bufferedReader2.readLine();
                            if (readLine2 != null) {
                                String[] split = readLine2.split("\\s+");
                                if (split.length > 1) {
                                    str3 = split[1];
                                }
                            }
                            try {
                                fileReader2.close();
                            } catch (Throwable unused11) {
                            }
                        } catch (Throwable unused12) {
                            if (fileReader2 != null) {
                                try {
                                    fileReader2.close();
                                    break;
                                } catch (Throwable unused13) {
                                    break;
                                }
                            }
                        }
                    } catch (Throwable unused14) {
                        bufferedReader2 = null;
                    }
                } catch (Throwable unused15) {
                    bufferedReader2 = null;
                    fileReader2 = null;
                }
                bufferedReader2.close();
                break;
            case 10:
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    StatFs statFs2 = new StatFs(Environment.getExternalStorageDirectory().getPath());
                    j = statFs2.getBlockSize() * statFs2.getBlockCount();
                }
                valueOf = String.valueOf(j);
                str3 = valueOf;
                break;
            case 11:
                try {
                    String[] strArr = {"wlan0", "eth1", "eth0"};
                    str = null;
                    while (r7 < 3) {
                        str = a(strArr[r7]);
                        if (str != null) {
                            str3 = str;
                            break;
                        } else {
                            r7++;
                        }
                    }
                } catch (Throwable unused16) {
                    str = null;
                }
                if (str == null) {
                    str = "";
                }
                valueOf = str.toLowerCase();
                str3 = valueOf;
                break;
            case 12:
                TelephonyManager telephonyManager = j.f16a;
                if (telephonyManager != null) {
                    str3 = telephonyManager.getNetworkOperatorName();
                    if (str3 != null) {
                        break;
                    }
                }
                str3 = null;
                break;
            case 13:
                try {
                    TelephonyManager telephonyManager2 = (TelephonyManager) f4a.getSystemService("phone");
                    r7 = telephonyManager2 != null ? telephonyManager2.getNetworkType() : 0;
                    if (r7 == 0 && (activeNetworkInfo = ((ConnectivityManager) f4a.getSystemService("connectivity")).getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected()) {
                        if (activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI")) {
                            r7 = -1;
                        }
                    }
                } catch (Throwable unused17) {
                }
                valueOf = String.valueOf(r7);
                str3 = valueOf;
                break;
            case 15:
                try {
                    str3 = a("p2p0");
                    break;
                } catch (Throwable unused18) {
                    str3 = null;
                    break;
                }
            case 17:
                valueOf = i.a();
                str3 = valueOf;
                break;
            case 18:
                String string = Settings.Secure.getString(f4a.getContentResolver(), "android_id");
                if (string != null) {
                    valueOf = string.trim();
                    str3 = valueOf;
                    break;
                }
                str3 = "";
                break;
            case 19:
                valueOf = String.valueOf(System.currentTimeMillis() - SystemClock.elapsedRealtime());
                str3 = valueOf;
                break;
            case 20:
                Context context = f4a;
                if (context != null) {
                    try {
                        valueOf = context.getPackageName();
                        str3 = valueOf;
                        break;
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
                str3 = null;
                break;
            case 21:
                valueOf = f4a.getPackageManager().getPackageInfo(f4a.getPackageName(), 16).versionName;
                str3 = valueOf;
                break;
            case 22:
                valueOf = c();
                str3 = valueOf;
                break;
            case 23:
                valueOf = e();
                str3 = valueOf;
                break;
            case 24:
                valueOf = k.a(f4a);
                str3 = valueOf;
                break;
            case 25:
                valueOf = b;
                str3 = valueOf;
                break;
            case 26:
                Class<?> cls2 = Class.forName("com.android.id.IdentifierManager");
                Method method = cls2.getMethod("getOAID", Context.class);
                if (method != null) {
                    method.setAccessible(true);
                    valueOf = (String) method.invoke(cls2, f4a);
                    str3 = valueOf;
                    break;
                }
                str3 = null;
                break;
            case 27:
                Cursor query = f4a.getContentResolver().query(Uri.parse("content://com.vivo.vms.IdProvider/IdentifierId/OAID"), null, null, null, null);
                if (query != null) {
                    str3 = query.moveToNext() ? query.getString(query.getColumnIndex("value")) : null;
                    query.close();
                    break;
                }
                str3 = null;
                break;
            case 28:
                valueOf = c;
                str3 = valueOf;
                break;
            case 29:
                Context context2 = f4a;
                try {
                    Class<?> cls3 = Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager");
                    Object invoke = cls3.getMethod("getInstance", Context.class).invoke(null, context2);
                    if (invoke != null) {
                        valueOf = (String) cls3.getMethod("getSDKVerison", new Class[0]).invoke(invoke, new Object[0]);
                        str3 = valueOf;
                        break;
                    }
                } catch (Exception unused19) {
                }
                str3 = "";
                break;
            case 30:
                Method method2 = Class.forName("com.yunos.tvtaobao.uuid.CloudUUID").getMethod("getCloudUUID", new Class[0]);
                if (method2 != null) {
                    valueOf = (String) method2.invoke(null, new Object[0]);
                    str3 = valueOf;
                    break;
                }
                str3 = null;
                break;
            case 31:
                valueOf = b();
                str3 = valueOf;
                break;
        }
        if (f.contains(Integer.valueOf(i)) && str3 != null) {
            d.put(Integer.valueOf(i), str3);
        }
        return str3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0031, code lost:
    
        if (r3.contains("?") == false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String e() {
        Context context = f4a;
        try {
            String str = (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", Context.class).invoke(null, context);
            if (str != null) {
                try {
                    if (!str.isEmpty()) {
                    }
                } catch (Exception unused) {
                }
            }
            String str2 = (String) Class.forName("com.ta.utdid2.device.UTDevice").getMethod("getUtdid", Context.class).invoke(null, context);
            try {
                if (str2.contains("?")) {
                    return "";
                }
            } catch (Exception unused2) {
            }
            return str2;
            return str;
        } catch (Exception unused3) {
            return "";
        }
    }

    public static void initialize(Context context) {
        f4a = context;
        try {
            context.getApplicationContext().getClassLoader();
            Context context2 = f4a;
            if (context2 != null && i.f15a == null) {
                i.b = context2;
                i.f15a = (SensorManager) context2.getSystemService("sensor");
            }
        } catch (Throwable unused) {
        }
        Context context3 = f4a;
        if (context3 != null) {
            try {
                if (("Huawei".equalsIgnoreCase(Build.BRAND) || "honor".equalsIgnoreCase(Build.BRAND)) && Build.VERSION.SDK_INT > 24) {
                    new Thread(new b(context3)).start();
                }
            } catch (Throwable unused2) {
            }
        }
        Context context4 = f4a;
        if (context4 != null) {
            try {
                if ("Oppo".equalsIgnoreCase(Build.BRAND) || "Realme".equalsIgnoreCase(Build.BRAND) || "OnePlus".equalsIgnoreCase(Build.BRAND)) {
                    new Thread(new d(context4)).start();
                }
            } catch (Throwable unused3) {
            }
        }
        UserTrackBridge.init(f4a);
    }
}
