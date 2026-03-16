package com.alibaba.one.android.inner;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Paint;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.media.MediaDrm;
import android.net.Proxy;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.telephony.TelephonyManager;
import android.util.Base64;
import com.alibaba.one.sdk.a;
import com.alibaba.one.sdk.e;
import com.alibaba.one.sdk.f;
import com.alibaba.one.sdk.g;
import com.alibaba.one.sdk.h;
import com.alibaba.one.sdk.j;
import com.alibaba.one.sdk.l;
import com.alibaba.wireless.security.securitybody.LifeCycle;
import java.lang.reflect.Method;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;
import org.json.JSONObject;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class DeviceInfoCapturerFull {

    /* renamed from: a, reason: collision with root package name */
    public static Context f5a = null;
    public static volatile boolean b = false;
    public static Object c = new Object();

    public static String a() {
        Object invoke;
        try {
            Class<?> cls = Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager");
            Method declaredMethod = cls.getDeclaredMethod("getInstance", Context.class);
            if (declaredMethod != null) {
                Object invoke2 = declaredMethod.invoke(cls, f5a);
                Method declaredMethod2 = cls.getDeclaredMethod("getStaticDataStoreComp", new Class[0]);
                if (invoke2 != null && declaredMethod2 != null && (invoke = declaredMethod2.invoke(invoke2, new Object[0])) != null) {
                    Method declaredMethod3 = invoke.getClass().getDeclaredMethod("getAppKeyByIndex", Integer.TYPE, String.class);
                    if (declaredMethod3 != null) {
                        return (String) declaredMethod3.invoke(invoke, 0, "");
                    }
                }
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static int audioRoute() {
        AudioManager audioManager;
        Context context = f5a;
        if (context == null || (audioManager = (AudioManager) context.getSystemService("audio")) == null) {
            return -1;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            AudioDeviceInfo[] devices = audioManager.getDevices(2);
            if (devices != null && devices[0] != null) {
                return devices[0].getType();
            }
        } else {
            if (audioManager.isWiredHeadsetOn()) {
                return 3;
            }
            if (audioManager.isBluetoothScoOn()) {
                return 7;
            }
            if (audioManager.isBluetoothA2dpOn()) {
                return 8;
            }
        }
        return -1;
    }

    public static String b() {
        if (Build.VERSION.SDK_INT >= 16) {
            try {
                PackageInfo packageInfo = f5a.getPackageManager().getPackageInfo(f5a.getPackageName(), 4096);
                if (packageInfo != null && packageInfo.requestedPermissions != null && packageInfo.requestedPermissions.length != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                        String str = packageInfo.requestedPermissions[i];
                        if (str != null) {
                            if (str.startsWith("android.permission.")) {
                                str = str.substring(19);
                            }
                            if ((packageInfo.requestedPermissionsFlags[i] & 2) != 0) {
                                sb.append(str);
                                sb.append(",");
                            }
                        }
                    }
                    return sb.toString();
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static String c() {
        int i;
        String str;
        KeyStore keyStore;
        String str2;
        String str3;
        Certificate[] certificateChain;
        int length;
        String str4;
        byte[] bArr = {110, 111, 53, 48, 54, 98, 51, 56, 50, 50, 119, 98};
        byte[] bArr2 = {65, 110, 100, 114, 111, 105, 100, 75, 101, 121, 83, 116, 111, 114, 101};
        byte[] bArr3 = {49, 46, 51, 46, 54, 46, 49, 46, 52, 46, 49, 46, 49, 49, 49, 50, 57, 46, 50, 46, 49, 46, 49, 55};
        KeyStore keyStore2 = null;
        if (f5a == null || (i = Build.VERSION.SDK_INT) < 24 || i < 28) {
            return null;
        }
        try {
            byte[] bArr4 = new byte[8];
            new Random().nextBytes(bArr4);
            String str5 = new String(bArr2);
            str = new String(bArr);
            try {
                str3 = new String(bArr3);
                keyStore = KeyStore.getInstance(str5);
                try {
                    keyStore.load(null);
                    if (keyStore.containsAlias(str)) {
                        keyStore.deleteEntry(str);
                    }
                    KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder(str, 12);
                    builder.setAttestationChallenge(bArr4);
                    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", str5);
                    keyPairGenerator.initialize(builder.build());
                    keyPairGenerator.generateKeyPair();
                    certificateChain = keyStore.getCertificateChain(str);
                    length = certificateChain.length;
                } catch (Throwable unused) {
                    keyStore2 = keyStore;
                    keyStore = keyStore2;
                    str2 = "0e|";
                    if (str != null) {
                        try {
                            keyStore.deleteEntry(str);
                        } catch (Throwable unused2) {
                        }
                    }
                    return str2 == null ? str2 : str2;
                }
            } catch (Throwable unused3) {
            }
        } catch (Throwable unused4) {
            str = null;
            keyStore = null;
        }
        if (length < 2) {
            return "0f|";
        }
        for (int i2 = 1; i2 < length; i2++) {
            try {
                PublicKey publicKey = certificateChain[i2].getPublicKey();
                int i3 = i2 - 1;
                ((X509Certificate) certificateChain[i3]).checkValidity();
                certificateChain[i3].verify(publicKey);
            } catch (Throwable unused5) {
                str4 = "1";
            }
        }
        str4 = "0";
        int i4 = length - 1;
        try {
            ((X509Certificate) certificateChain[i4]).checkValidity();
            certificateChain[i4].verify(certificateChain[i4].getPublicKey());
        } catch (Throwable unused6) {
            str4 = "1";
        }
        str2 = str4 + "|" + Base64.encodeToString(((X509Certificate) certificateChain[0]).getExtensionValue(str3), 2);
        if (str != null && keyStore != null) {
            keyStore.deleteEntry(str);
        }
        if (str2 == null && str2.length() > 400) {
            return "0a|" + str2.length();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x016d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String d() {
        boolean z;
        String a2;
        String a3;
        KeyStore keyStore;
        Context context = f5a;
        JSONObject jSONObject = new JSONObject();
        try {
        } catch (Exception e) {
            e = e;
            z = false;
        }
        if (context == null) {
            jSONObject.put("e", -1);
        } else if (Build.VERSION.SDK_INT > 26 && Build.VERSION.SDK_INT >= 28) {
            try {
                keyStore = KeyStore.getInstance("AndroidKeyStore");
                keyStore.load(null);
                if (keyStore.containsAlias("no506b3822wbTB")) {
                    keyStore.deleteEntry("no506b3822wbTB");
                }
            } catch (Exception e2) {
                jSONObject.put("e", -3);
                a3 = a.a(e2);
            }
            try {
                byte[] a4 = a.a(context);
                KeyGenParameterSpec.Builder builder = new KeyGenParameterSpec.Builder("no506b3822wbTB", 12);
                builder.setAttestationChallenge(a4);
                if (Build.VERSION.SDK_INT >= 31) {
                    try {
                        Method declaredMethod = Class.forName("android.security.keystore.KeyGenParameterSpec$Builder").getDeclaredMethod("setDevicePropertiesAttestationIncluded", Boolean.TYPE);
                        if (declaredMethod != null) {
                            declaredMethod.setAccessible(true);
                            declaredMethod.invoke(builder, true);
                        }
                    } catch (Exception e3) {
                        jSONObject.put("31_e", -10);
                        jSONObject.put("31_exp", a.a(e3));
                    }
                }
                try {
                    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "AndroidKeyStore");
                    keyPairGenerator.initialize(builder.build());
                    keyPairGenerator.generateKeyPair();
                    try {
                        Certificate[] certificateChain = keyStore.getCertificateChain("no506b3822wbTB");
                        if (certificateChain != null) {
                            jSONObject.put("s", certificateChain.length);
                            for (int i = 1; i < certificateChain.length; i++) {
                                int i2 = i - 1;
                                try {
                                    ((X509Certificate) certificateChain[i2]).checkValidity();
                                    certificateChain[i2].verify(certificateChain[i].getPublicKey());
                                    jSONObject.put("v", true);
                                } catch (Throwable th) {
                                    jSONObject.put("v", false);
                                    jSONObject.put("v_exp", a.a(th));
                                }
                            }
                            try {
                                jSONObject.put("c0t", Base64.encodeToString(certificateChain[0].getEncoded(), 2));
                            } catch (Exception e4) {
                                jSONObject.put("c0t_e", -8);
                                jSONObject.put("c0t_exp", a.a(e4));
                            }
                            try {
                                jSONObject.put("c1pk", Base64.encodeToString(certificateChain[1].getPublicKey().getEncoded(), 2));
                            } catch (Exception e5) {
                                jSONObject.put("c1pk_e", -9);
                                jSONObject.put("c1pk_exp", a.a(e5));
                            }
                            try {
                                jSONObject.put("ret", true);
                                jSONObject.put("t", "0");
                                z = true;
                            } catch (Exception e6) {
                                e = e6;
                                z = true;
                                try {
                                    jSONObject.put("f_exp", a.a(e));
                                } catch (Exception unused) {
                                }
                                if (!z) {
                                }
                                return jSONObject.toString();
                            }
                            if (!z) {
                                jSONObject = new JSONObject();
                                try {
                                    if (context == null) {
                                        jSONObject.put("e", -1);
                                    } else {
                                        if (Build.VERSION.SDK_INT > 26 && Build.VERSION.SDK_INT >= 28) {
                                            try {
                                                KeyStore keyStore2 = KeyStore.getInstance("AndroidKeyStore");
                                                keyStore2.load(null);
                                                if (keyStore2.containsAlias("no506b3822wb")) {
                                                    keyStore2.deleteEntry("no506b3822wb");
                                                }
                                                try {
                                                    KeyGenParameterSpec.Builder builder2 = new KeyGenParameterSpec.Builder("no506b3822wb", 12);
                                                    builder2.setAttestationChallenge(a.a(context));
                                                    try {
                                                        KeyPairGenerator keyPairGenerator2 = KeyPairGenerator.getInstance("EC", "AndroidKeyStore");
                                                        keyPairGenerator2.initialize(builder2.build());
                                                        keyPairGenerator2.generateKeyPair();
                                                        try {
                                                            Certificate[] certificateChain2 = keyStore2.getCertificateChain("no506b3822wb");
                                                            if (certificateChain2 == null) {
                                                                jSONObject.put("e", -7);
                                                            } else {
                                                                jSONObject.put("s", certificateChain2.length);
                                                                for (int i3 = 1; i3 < certificateChain2.length; i3++) {
                                                                    int i4 = i3 - 1;
                                                                    try {
                                                                        ((X509Certificate) certificateChain2[i4]).checkValidity();
                                                                        certificateChain2[i4].verify(certificateChain2[i3].getPublicKey());
                                                                        jSONObject.put("v", true);
                                                                    } catch (Throwable th2) {
                                                                        jSONObject.put("v", false);
                                                                        jSONObject.put("v_exp", a.a(th2));
                                                                    }
                                                                }
                                                                try {
                                                                    jSONObject.put("c0t", Base64.encodeToString(certificateChain2[0].getEncoded(), 2));
                                                                } catch (Exception e7) {
                                                                    jSONObject.put("c0t_e", -8);
                                                                    jSONObject.put("c0t_exp", a.a(e7));
                                                                }
                                                                try {
                                                                    jSONObject.put("c1pk", Base64.encodeToString(certificateChain2[1].getPublicKey().getEncoded(), 2));
                                                                } catch (Exception e8) {
                                                                    jSONObject.put("c1pk_e", -9);
                                                                    jSONObject.put("c1pk_exp", a.a(e8));
                                                                }
                                                                jSONObject.put("ret", true);
                                                                jSONObject.put("t", "1");
                                                            }
                                                        } catch (Exception e9) {
                                                            jSONObject.put("e", -6);
                                                            a2 = a.a(e9);
                                                            jSONObject.put("exp", a2);
                                                            return jSONObject.toString();
                                                        }
                                                    } catch (Exception e10) {
                                                        e = e10;
                                                        jSONObject.put("e", -5);
                                                        a2 = a.a(e);
                                                        jSONObject.put("exp", a2);
                                                        return jSONObject.toString();
                                                    }
                                                } catch (Exception e11) {
                                                    e = e11;
                                                    jSONObject.put("e", -4);
                                                }
                                            } catch (Exception e12) {
                                                e = e12;
                                                jSONObject.put("e", -3);
                                            }
                                        }
                                        jSONObject.put("e", -2);
                                    }
                                    return jSONObject.toString();
                                } catch (Exception e13) {
                                    try {
                                        jSONObject.put("f_exp", a.a(e13));
                                    } catch (Exception unused2) {
                                    }
                                }
                            }
                            return jSONObject.toString();
                        }
                        jSONObject.put("e", -7);
                    } catch (Exception e14) {
                        jSONObject.put("e", -6);
                        a3 = a.a(e14);
                        jSONObject.put("exp", a3);
                        return jSONObject.toString();
                    }
                } catch (Exception e15) {
                    jSONObject.put("e", -5);
                    a3 = a.a(e15);
                }
            } catch (Exception e16) {
                jSONObject.put("e", -4);
                a3 = a.a(e16);
                jSONObject.put("exp", a3);
                return jSONObject.toString();
            }
        } else {
            jSONObject.put("e", -2);
        }
        return jSONObject.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:172:0x0209, code lost:
    
        if (r8.length() > 0) goto L136;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x020f A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0212 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:155:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:183:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String doCommandForString(int i) {
        long j;
        PackageInfo packageInfo;
        String valueOf;
        Object invoke;
        ApplicationInfo applicationInfo;
        Signature[] signatureArr;
        boolean z = true;
        if (!b) {
            synchronized (c) {
                if (!b) {
                    Context context = f5a;
                    if (context != null && h.b == null) {
                        h.c = context;
                        try {
                            h.f14a = context.getPackageName();
                        } catch (Throwable unused) {
                        }
                        try {
                            h.b = context.getPackageManager();
                        } catch (Throwable unused2) {
                        }
                    }
                    Context context2 = f5a;
                    if (context2 != null && j.f16a == null) {
                        try {
                            j.f16a = (TelephonyManager) context2.getSystemService("phone");
                        } catch (Throwable unused3) {
                        }
                    }
                    Context context3 = f5a;
                    if (context3 != null && l.f20a == null) {
                        l.f20a = context3;
                    }
                    if (context3 != null && l.b == null) {
                        try {
                            l.b = (WifiManager) context3.getSystemService("wifi");
                        } catch (Throwable unused4) {
                        }
                    }
                    Context context4 = f5a;
                    if (context4 != null && f.f12a == null) {
                        f.f12a = context4;
                    }
                    b = true;
                }
            }
        }
        try {
            switch (i) {
                case LifeCycle.APP_BACKGROUND /* 0 */:
                    valueOf = f.d();
                    return valueOf;
                case 1:
                    try {
                        valueOf = "" + f.f12a.getResources().getDisplayMetrics().densityDpi;
                        return valueOf;
                    } catch (Exception unused5) {
                        break;
                    }
                case LifeCycle.APP_IGNORE /* 2 */:
                    valueOf = f.e();
                    return valueOf;
                case 3:
                    return f.c(f.f12a) ? "Tablet" : (f.a(f.f12a) || f.b(f.f12a)) ? "TV" : "Phone";
                case 4:
                    valueOf = f.c();
                    return valueOf;
                case 5:
                    valueOf = j.f16a.getSimOperatorName();
                    return valueOf;
                case 6:
                    if (Build.VERSION.SDK_INT > 14) {
                        String property = System.getProperty("http.proxyHost");
                        if (property != null && property.length() > 0) {
                        }
                        z = false;
                    } else {
                        String host = Proxy.getHost(l.f20a);
                        if (host != null) {
                            break;
                        }
                        z = false;
                    }
                    return z ? "1" : "0";
                case 7:
                case 9:
                case 27:
                default:
                    return null;
                case 8:
                    f.a();
                    String str = f.c;
                    return str == null ? "-1" : str;
                case 10:
                    TimeZone timeZone = TimeZone.getDefault();
                    valueOf = timeZone.getID() + " " + timeZone.getDisplayName(false, 0);
                    return valueOf;
                case 11:
                    if (f.f12a == null) {
                        return "";
                    }
                    valueOf = Float.toString(new Paint().getTextSize());
                    return valueOf;
                case 12:
                    Method method = Class.forName("com.taobao.login4android.Login").getMethod("getNick", new Class[0]);
                    if (method == null) {
                        return null;
                    }
                    invoke = method.invoke(null, new Object[0]);
                    valueOf = (String) invoke;
                    return valueOf;
                case 13:
                    PackageManager packageManager = h.b;
                    if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(h.f14a, 0)) == null) {
                        return null;
                    }
                    valueOf = packageManager.getApplicationLabel(applicationInfo).toString();
                    return valueOf;
                case 14:
                    if (Build.VERSION.SDK_INT > 8 && (packageInfo = f5a.getPackageManager().getPackageInfo(f5a.getPackageName(), 0)) != null) {
                        j = System.currentTimeMillis() - packageInfo.firstInstallTime;
                        if (j == -1) {
                            return null;
                        }
                        valueOf = String.valueOf(j);
                        return valueOf;
                    }
                    j = -1;
                    if (j == -1) {
                    }
                    valueOf = String.valueOf(j);
                    return valueOf;
                case 15:
                    if (Build.VERSION.SDK_INT > 8) {
                        j = f5a.getPackageManager().getPackageInfo(f5a.getPackageName(), 0).lastUpdateTime;
                        if (j == -1) {
                            return null;
                        }
                        valueOf = String.valueOf(j);
                        return valueOf;
                    }
                    j = -1;
                    if (j == -1) {
                    }
                    valueOf = String.valueOf(j);
                    return valueOf;
                case 16:
                    if (h.d()) {
                    }
                    break;
                case 17:
                    if (h.c()) {
                    }
                case 18:
                    valueOf = a();
                    return valueOf;
                case 19:
                    valueOf = b();
                    return valueOf;
                case 20:
                    valueOf = h.a();
                    return valueOf;
                case 21:
                    if (h.c == null || (signatureArr = h.c.getPackageManager().getPackageInfo("android", 64).signatures) == null || signatureArr.length <= 0) {
                        return null;
                    }
                    valueOf = g.a(signatureArr[0].toCharsString());
                    return valueOf;
                case 22:
                    Method declaredMethod = Class.forName("com.alibaba.wireless.security.open.SecurityGuardManager").getDeclaredMethod("getGlobalUserData", new Class[0]);
                    if (declaredMethod == null) {
                        return null;
                    }
                    declaredMethod.setAccessible(true);
                    invoke = declaredMethod.invoke(null, new Object[0]);
                    valueOf = (String) invoke;
                    return valueOf;
                case 23:
                    if (Build.VERSION.SDK_INT < 18 || Build.VERSION.SDK_INT >= 30) {
                        return null;
                    }
                    MediaDrm mediaDrm = new MediaDrm(new UUID(-1301668207276963122L, -6645017420763422227L));
                    byte[] propertyByteArray = mediaDrm.getPropertyByteArray("deviceUniqueId");
                    String encodeToString = propertyByteArray != null ? Base64.encodeToString(propertyByteArray, 2) : null;
                    mediaDrm.release();
                    return encodeToString;
                case 24:
                    valueOf = h.b();
                    return valueOf;
                case 25:
                    SharedPreferences sharedPreferences = f5a.getSharedPreferences("sgPrefs", 0);
                    String string = sharedPreferences.getString("2144d8c39b6aea0", null);
                    if (string == null) {
                        new Thread(new e(sharedPreferences)).start();
                    }
                    return string;
                case 26:
                    valueOf = c();
                    return valueOf;
                case 28:
                    valueOf = d();
                    return valueOf;
            }
        } catch (AssertionError | Exception unused6) {
            return null;
        }
    }

    public static String getAudioVolume() {
        Context context = f5a;
        if (context == null) {
            return null;
        }
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        return String.valueOf(audioManager.getStreamVolume(3) / audioManager.getStreamMaxVolume(3));
    }

    public static String getUid(String str) {
        Context context = f5a;
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo != null) {
                return String.valueOf(applicationInfo.uid);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static void initialize(Context context) {
        f5a = context;
    }
}
