package com.taobao.wireless.security.adapter.common;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class SPUtility2 {

    /* renamed from: а, reason: contains not printable characters */
    private static Context f24;

    /* renamed from: б, reason: contains not printable characters */
    private static C0012 f25;

    public static String getTempFile(String str) throws IOException {
        String rootDir = rootDir();
        if (rootDir == null) {
            return "";
        }
        File file = new File(rootDir, str);
        return (file.exists() || file.createNewFile()) ? file.getAbsolutePath() : "";
    }

    public static void init(Context context) {
        if (context != null) {
            f24 = context;
            f25 = new C0012(context, "sp.lock");
        }
    }

    public static String read(String str) {
        String str2 = "";
        if (!m27(1)) {
            return "";
        }
        String tempFile = getTempFile(str);
        synchronized (SPUtility2.class) {
            try {
                if (f25.m40()) {
                    str2 = m24(tempFile, true);
                }
            } finally {
                f25.m41();
            }
        }
        return str2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0026, code lost:
    
        if (r3.length() > 0) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String readFromSPUnified(String str, String str2, String str3) {
        if (str2 != null && str2.length() > 0 && str != null && str.length() > 0) {
            synchronized (SPUtility2.class) {
                try {
                    if (f25.m40() && (r3 = m23(str, str2, str3)) != null) {
                    }
                    String str4 = str3;
                    f25.m41();
                    str3 = str4;
                } catch (Exception unused) {
                    f25.m41();
                } catch (Throwable th) {
                    f25.m41();
                    throw th;
                }
            }
        }
        return str3;
    }

    public static String readSS(Context context, String str) {
        String str2 = null;
        if (context != null) {
            try {
                if ((Build.VERSION.SDK_INT < 23 || context.getApplicationInfo().targetSdkVersion < 23) && str != null && str.length() > 0) {
                    synchronized (SPUtility2.class) {
                        try {
                            if (f25.m40()) {
                                str2 = Settings.System.getString(context.getContentResolver(), str);
                            }
                        } finally {
                            f25.m41();
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return str2;
    }

    public static boolean removeFromSPUnified(String str, String str2, boolean z) {
        if (str2 == null || str2.length() <= 0 || str == null || str.length() <= 0) {
            return false;
        }
        synchronized (SPUtility2.class) {
            try {
                if (!f25.m40()) {
                    return false;
                }
                return m28(str, str2);
            } finally {
                f25.m41();
            }
        }
    }

    public static String rootDir() {
        try {
            if (!"mounted".equals(Environment.getExternalStorageState())) {
                return null;
            }
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/.com.taobao.dp");
            if (file.exists() || file.mkdir()) {
                return file.getAbsolutePath();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static int saveToFileUnifiedForNative(String str, String str2, String str3, boolean z) {
        int i = 1;
        if (str2 != null && str2.length() > 0 && str != null && str.length() > 0) {
            synchronized (SPUtility2.class) {
                try {
                    if (f25.m40()) {
                        i = m29("SGMANAGER_DATA2", str + "_" + str2, str3);
                    }
                } finally {
                    f25.m41();
                }
            }
        }
        return i;
    }

    public static boolean saveToSPUnified(String str, String str2, String str3, boolean z) {
        return saveToFileUnifiedForNative(str, str2, str3, z) == 0;
    }

    public static void write(String str, String str2) {
        String tempFile;
        try {
            if (m27(0) && (tempFile = getTempFile(str)) != null && tempFile.length() > 0) {
                synchronized (SPUtility2.class) {
                    try {
                        if (f25.m40()) {
                            m26(tempFile, str2, false);
                        }
                    } finally {
                        f25.m41();
                    }
                }
            }
        } catch (IOException unused) {
        }
    }

    public static boolean writeSS(Context context, String str, String str2) {
        boolean z = false;
        if (context != null) {
            try {
                if ((Build.VERSION.SDK_INT < 23 || context.getApplicationInfo().targetSdkVersion < 23) && str != null && str.length() > 0) {
                    synchronized (SPUtility2.class) {
                        try {
                            if (f25.m40()) {
                                z = Settings.System.putString(context.getContentResolver(), str, str2);
                            }
                        } finally {
                            f25.m41();
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return z;
    }

    /* renamed from: а, reason: contains not printable characters */
    private static int m22(Context context, String str, String str2) {
        OutputStreamWriter outputStreamWriter = null;
        try {
            String absolutePath = context.getFilesDir().getAbsolutePath();
            if (absolutePath != null && absolutePath.length() > 0) {
                File file = new File(absolutePath + File.separator + str + ".tmp");
                StringBuilder sb = new StringBuilder();
                sb.append(absolutePath);
                sb.append(File.separator);
                sb.append(str);
                File file2 = new File(sb.toString());
                OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(file));
                try {
                    outputStreamWriter2.write(str2);
                    outputStreamWriter2.close();
                    file2.delete();
                    return file.renameTo(file2) ? 0 : 5;
                } catch (Exception unused) {
                    outputStreamWriter = outputStreamWriter2;
                    if (outputStreamWriter == null) {
                        return 6;
                    }
                    try {
                        outputStreamWriter.close();
                        return 6;
                    } catch (Exception unused2) {
                        return 6;
                    }
                } catch (Throwable th) {
                    outputStreamWriter = outputStreamWriter2;
                    th = th;
                    if (outputStreamWriter != null) {
                        try {
                            outputStreamWriter.close();
                        } catch (Exception unused3) {
                        }
                    }
                    throw th;
                }
            }
            return 4;
        } catch (Exception unused4) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* renamed from: а, reason: contains not printable characters */
    private static String m23(String str, String str2, String str3) {
        if (str2 == null || str2.length() <= 0 || str == null || str.length() <= 0) {
            return str3;
        }
        try {
            String str4 = str + "_" + str2;
            JSONObject m25 = m25("SGMANAGER_DATA2");
            return m25 != null ? m25.getString(str4) : str3;
        } catch (Throwable unused) {
            return str3;
        }
    }

    /* renamed from: а, reason: contains not printable characters */
    private static String m24(String str, boolean z) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(str))));
        String readLine = bufferedReader.readLine();
        if (readLine == null) {
            readLine = "";
        }
        sb.append(readLine);
        if (!z) {
            while (true) {
                String readLine2 = bufferedReader.readLine();
                if (readLine2 == null) {
                    break;
                }
                sb.append(readLine2);
            }
        }
        bufferedReader.close();
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x005b, code lost:
    
        if (r4 == null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0056, code lost:
    
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0054, code lost:
    
        if (r4 == null) goto L20;
     */
    /* renamed from: а, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static JSONObject m25(String str) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(f24.getFilesDir().getAbsolutePath() + File.separator + str))));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        bufferedReader.close();
                        return new JSONObject(sb.toString());
                    }
                    sb.append(readLine);
                }
            } catch (Exception unused) {
            } catch (Throwable unused2) {
            }
        } catch (Exception unused3) {
            bufferedReader = null;
        } catch (Throwable unused4) {
            bufferedReader = null;
        }
        return null;
    }

    /* renamed from: а, reason: contains not printable characters */
    private static void m26(String str, String str2, boolean z) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(str), z);
        fileOutputStream.write(str2.getBytes());
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    /* renamed from: а, reason: contains not printable characters */
    private static boolean m27(int i) {
        Context context = f24;
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        if (context == null) {
            return false;
        }
        try {
            if (context.getApplicationInfo().targetSdkVersion < 23) {
                return true;
            }
            try {
                Method method = Context.class.getMethod("checkSelfPermission", String.class);
                if (method != null) {
                    return ((Integer) method.invoke(context, i == 0 ? "android.permission.WRITE_EXTERNAL_STORAGE" : "android.permission.READ_EXTERNAL_STORAGE")).intValue() == 0;
                }
            } catch (Throwable unused) {
            }
            return false;
        } catch (Throwable unused2) {
            return true;
        }
    }

    /* renamed from: а, reason: contains not printable characters */
    private static boolean m28(String str, String str2) {
        Context context = f24;
        if (context == null || str2 == null || str2.length() <= 0 || str == null || str.length() <= 0) {
            return false;
        }
        try {
            String str3 = str + "_" + str2;
            JSONObject m25 = m25("SGMANAGER_DATA2");
            if (m25 == null) {
                return false;
            }
            m25.remove(str3);
            return m22(context, "SGMANAGER_DATA2", m25.toString()) == 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: б, reason: contains not printable characters */
    private static int m29(String str, String str2, String str3) {
        Context context = f24;
        if (str2 != null && str2.length() > 0 && str != null && str.length() > 0) {
            try {
                JSONObject m25 = m25(str);
                if (m25 == null) {
                    m25 = new JSONObject();
                }
                m25.put(str2, str3);
                return m22(context, str, m25.toString());
            } catch (Throwable unused) {
            }
        }
        return 2;
    }
}
