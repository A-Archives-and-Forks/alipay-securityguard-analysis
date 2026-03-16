package com.alibaba.minilibc.android;

import android.os.Build;
import android.text.TextUtils;
import com.alibaba.wireless.security.framework.utils.UserTrackMethodJniBridge;
import com.taobao.wireless.security.adapter.datareport.C0020;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class HttpClientUtils {
    private static final boolean DEBUG = false;
    private static final int ERROR_HANDLER_METHOD_ID_DOWNLOAD_FILE_FAILED = 100168;
    private static final String TAG = "HttpClientUtils";
    public static final int TIMEOUT_DEFAULT = 22000;
    public static final int TIMEOUT_MIN = 0;

    public static class ResCode {
        public static final int CUSTOM_SSL_HANDSHAKE_ERROR = 1004;
        public static final int CUSTOM_UNKONW_HOST = 1003;
        public static final int EXCEPTION = 1002;
        public static final int HTTP_RESPONSE_NULL = 1000;
        public static final int REQ_PARAM_INVALID = 400;
        public static final int STATUS_OK = 200;
        public static final int UNKNOWN = -1;
    }

    private static void disconnect(URLConnection uRLConnection) {
        if (uRLConnection != null) {
            try {
                if (uRLConnection instanceof HttpsURLConnection) {
                    ((HttpsURLConnection) uRLConnection).disconnect();
                } else if (uRLConnection instanceof HttpURLConnection) {
                    ((HttpURLConnection) uRLConnection).disconnect();
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x012a, code lost:
    
        if (r11 != null) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x012c, code lost:
    
        r0 = r11.length;
        r10 = r10;
        r12 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x012d, code lost:
    
        r10.m72(r0);
        r10.m76(r12);
        r10.m79(r7);
        r10.m73(r13 - r2);
        r10.m77(r2);
        com.taobao.wireless.security.adapter.datareport.C0020.m67(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0187, code lost:
    
        if (r11 != null) goto L68;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 6, insn: 0x018c: MOVE (r1 I:??[OBJECT, ARRAY]) = (r6 I:??[OBJECT, ARRAY]), block:B:133:0x018c */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:108:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x018f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x016f A[Catch: Exception -> 0x016b, TRY_LEAVE, TryCatch #3 {Exception -> 0x016b, blocks: (B:89:0x0167, B:81:0x016f), top: B:88:0x0167 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0167 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0197 A[Catch: Exception -> 0x0193, TRY_LEAVE, TryCatch #7 {Exception -> 0x0193, blocks: (B:110:0x018f, B:98:0x0197), top: B:109:0x018f }] */
    /* JADX WARN: Type inference failed for: r10v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r10v32 */
    /* JADX WARN: Type inference failed for: r10v33 */
    /* JADX WARN: Type inference failed for: r10v4, types: [com.taobao.wireless.security.adapter.datareport.а$в] */
    /* JADX WARN: Type inference failed for: r12v1, types: [int] */
    /* JADX WARN: Type inference failed for: r12v17 */
    /* JADX WARN: Type inference failed for: r12v18 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static HttpClientResult doHttpPost(String str, Map<String, String> map, byte[] bArr, String str2, int i, int i2) {
        BufferedReader bufferedReader;
        ?? r12;
        ?? r10;
        OutputStream outputStream;
        Exception e;
        BufferedReader bufferedReader2;
        int i3;
        int i4;
        HttpClientResult httpClientResult;
        long currentTimeMillis;
        int i5;
        C0020.C0023 c0023;
        int i6;
        C0020.C0023 c00232;
        BufferedReader bufferedReader3 = null;
        if (TextUtils.isEmpty(str) || bArr == null) {
            return new HttpClientResult(null, ResCode.REQ_PARAM_INVALID);
        }
        URLConnection defaultURLConnection = getDefaultURLConnection(str, i, i2, "POST");
        if (defaultURLConnection == null) {
            return new HttpClientResult(null, ResCode.EXCEPTION);
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        try {
            if (map != null) {
                try {
                    for (String str3 : map.keySet()) {
                        defaultURLConnection.setRequestProperty(str3, map.get(str3));
                    }
                } catch (Exception e2) {
                    e = e2;
                    outputStream = null;
                    bufferedReader2 = null;
                    i3 = 0;
                    i4 = 0;
                    httpClientResult = new HttpClientResult(null, ResCode.EXCEPTION, e);
                    if (outputStream != null) {
                    }
                    if (bufferedReader2 != null) {
                    }
                    disconnect(defaultURLConnection);
                    currentTimeMillis = System.currentTimeMillis();
                    if (C0020.m70()) {
                    }
                    return httpClientResult;
                } catch (Throwable th) {
                    th = th;
                    outputStream = null;
                    i3 = 0;
                    r10 = outputStream;
                    r12 = i3;
                    if (r10 != 0) {
                    }
                    if (bufferedReader3 != null) {
                    }
                    disconnect(defaultURLConnection);
                    long currentTimeMillis3 = System.currentTimeMillis();
                    if (C0020.m70()) {
                    }
                }
            }
            defaultURLConnection.setDoInput(true);
            if (str2 != null && str2.length() > 0) {
                defaultURLConnection.setRequestProperty("Content-Type", str2);
            }
            defaultURLConnection.connect();
            outputStream = defaultURLConnection.getOutputStream();
        } catch (Throwable th2) {
            th = th2;
            bufferedReader3 = bufferedReader;
            r10 = map;
            r12 = str2;
        }
        try {
            outputStream.write(bArr);
            outputStream.flush();
            i3 = getResponseCode(defaultURLConnection);
            try {
            } catch (Exception e3) {
                e = e3;
                bufferedReader2 = null;
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e4) {
            e = e4;
            bufferedReader2 = null;
            outputStream = outputStream;
            i3 = 0;
            i4 = 0;
            httpClientResult = new HttpClientResult(null, ResCode.EXCEPTION, e);
            if (outputStream != null) {
            }
            if (bufferedReader2 != null) {
            }
            disconnect(defaultURLConnection);
            currentTimeMillis = System.currentTimeMillis();
            if (C0020.m70()) {
            }
            return httpClientResult;
        } catch (Throwable th4) {
            th = th4;
            outputStream = outputStream;
            i3 = 0;
            r10 = outputStream;
            r12 = i3;
            if (r10 != 0) {
                try {
                    r10.close();
                } catch (Exception unused) {
                    disconnect(defaultURLConnection);
                    long currentTimeMillis32 = System.currentTimeMillis();
                    if (C0020.m70()) {
                        throw th;
                    }
                    ?? c00233 = new C0020.C0023();
                    c00233.m74(str);
                    c00233.m72(bArr != null ? bArr.length : 0);
                    c00233.m76(r12);
                    c00233.m79(0);
                    c00233.m73(currentTimeMillis32 - currentTimeMillis2);
                    c00233.m77(currentTimeMillis2);
                    C0020.m67((C0020.C0023) c00233);
                    throw th;
                }
            }
            if (bufferedReader3 != null) {
                bufferedReader3.close();
            }
            disconnect(defaultURLConnection);
            long currentTimeMillis322 = System.currentTimeMillis();
            if (C0020.m70()) {
            }
        }
        if (i3 != 200) {
            HttpClientResult httpClientResult2 = new HttpClientResult(null, i3);
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception unused2) {
                }
            }
            disconnect(defaultURLConnection);
            long currentTimeMillis4 = System.currentTimeMillis();
            if (C0020.m70()) {
                C0020.C0023 c00234 = new C0020.C0023();
                c00234.m74(str);
                c00234.m72(bArr != null ? bArr.length : 0);
                c00234.m76(i3);
                c00234.m79(0);
                c00234.m73(currentTimeMillis4 - currentTimeMillis2);
                c00234.m77(currentTimeMillis2);
                C0020.m67(c00234);
            }
            return httpClientResult2;
        }
        InputStream inputStream = defaultURLConnection.getInputStream();
        if (inputStream == null) {
            HttpClientResult httpClientResult3 = new HttpClientResult(null, ResCode.HTTP_RESPONSE_NULL);
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Exception unused3) {
                }
            }
            disconnect(defaultURLConnection);
            long currentTimeMillis5 = System.currentTimeMillis();
            if (C0020.m70()) {
                C0020.C0023 c00235 = new C0020.C0023();
                c00235.m74(str);
                c00235.m72(bArr != null ? bArr.length : 0);
                c00235.m76(i3);
                c00235.m79(0);
                c00235.m73(currentTimeMillis5 - currentTimeMillis2);
                c00235.m77(currentTimeMillis2);
                C0020.m67(c00235);
            }
            return httpClientResult3;
        }
        bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        try {
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader2.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            i4 = sb.length();
            try {
                httpClientResult = new HttpClientResult(sb.toString(), ResCode.STATUS_OK);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception unused4) {
                    }
                }
                bufferedReader2.close();
                disconnect(defaultURLConnection);
                currentTimeMillis = System.currentTimeMillis();
                if (C0020.m70()) {
                    C0020.C0023 c00236 = new C0020.C0023();
                    c00236.m74(str);
                    c00232 = c00236;
                    i6 = i3;
                    c0023 = c00236;
                    i5 = i3;
                }
            } catch (Exception e5) {
                e = e5;
                httpClientResult = new HttpClientResult(null, ResCode.EXCEPTION, e);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Exception unused5) {
                        disconnect(defaultURLConnection);
                        currentTimeMillis = System.currentTimeMillis();
                        if (C0020.m70()) {
                            C0020.C0023 c00237 = new C0020.C0023();
                            c00237.m74(str);
                            c00232 = c00237;
                            i6 = i3;
                            c0023 = c00237;
                            i5 = i3;
                        }
                        return httpClientResult;
                    }
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                disconnect(defaultURLConnection);
                currentTimeMillis = System.currentTimeMillis();
                if (C0020.m70()) {
                }
                return httpClientResult;
            }
        } catch (Exception e6) {
            e = e6;
            i4 = 0;
            httpClientResult = new HttpClientResult(null, ResCode.EXCEPTION, e);
            if (outputStream != null) {
            }
            if (bufferedReader2 != null) {
            }
            disconnect(defaultURLConnection);
            currentTimeMillis = System.currentTimeMillis();
            if (C0020.m70()) {
            }
            return httpClientResult;
        } catch (Throwable th5) {
            th = th5;
            bufferedReader3 = bufferedReader2;
            r10 = outputStream;
            r12 = i3;
            if (r10 != 0) {
            }
            if (bufferedReader3 != null) {
            }
            disconnect(defaultURLConnection);
            long currentTimeMillis3222 = System.currentTimeMillis();
            if (C0020.m70()) {
            }
        }
        return httpClientResult;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0204 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x01fd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x019c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0195 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0273  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0261 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x025a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02a3 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x029c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0216  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean downloadFile(String str, String str2) {
        URLConnection defaultURLConnection;
        int i;
        int i2;
        long j;
        long j2;
        FileOutputStream fileOutputStream;
        InputStream inputStream;
        long j3;
        long j4;
        long j5;
        int i3;
        long j6;
        long currentTimeMillis;
        long j7;
        int i4;
        C0020.C0023 c0023;
        long j8;
        int i5;
        long j9;
        int i6;
        long j10;
        int i7;
        C0020.C0023 c00232;
        long j11;
        int i8;
        long j12;
        int i9;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str) || (defaultURLConnection = getDefaultURLConnection(str, TIMEOUT_DEFAULT, TIMEOUT_DEFAULT, "GET")) == null) {
            return DEBUG;
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        try {
            try {
                defaultURLConnection.setDoInput(true);
                defaultURLConnection.connect();
                i = getResponseCode(defaultURLConnection);
                if (i != 200) {
                    try {
                        i9 = i;
                        try {
                            UserTrackMethodJniBridge.addUtRecord("100168", 2, 0, "", 0L, "" + i, str, "", "", "");
                            disconnect(defaultURLConnection);
                            long currentTimeMillis3 = System.currentTimeMillis();
                            if (C0020.m70()) {
                                C0020.C0023 c00233 = new C0020.C0023();
                                c00233.m74(str);
                                c00233.m72(0);
                                c00233.m76(i9);
                                c00233.m79(0);
                                c00233.m73(currentTimeMillis3 - currentTimeMillis2);
                                c00233.m77(currentTimeMillis2);
                                C0020.m67(c00233);
                            }
                            return DEBUG;
                        } catch (UnknownHostException e) {
                            e = e;
                            i = i9;
                            j4 = currentTimeMillis2;
                            fileOutputStream = null;
                            inputStream = null;
                            j11 = j4;
                            i8 = i;
                            String str3 = "";
                            j12 = j11;
                            String str4 = "";
                            try {
                                UserTrackMethodJniBridge.addUtRecord("100168", 3, 0, "", 0L, e.getMessage(), str, str3, str4, "");
                                if (inputStream != null) {
                                }
                                if (fileOutputStream != null) {
                                }
                                disconnect(defaultURLConnection);
                                long currentTimeMillis4 = System.currentTimeMillis();
                                i6 = str3;
                                currentTimeMillis2 = str4;
                                if (C0020.m70()) {
                                }
                                return DEBUG;
                            } catch (Throwable th) {
                                th = th;
                                i2 = i8;
                                j = j12;
                                if (inputStream != null) {
                                }
                                if (fileOutputStream != null) {
                                }
                                disconnect(defaultURLConnection);
                                long currentTimeMillis5 = System.currentTimeMillis();
                                if (C0020.m70()) {
                                }
                            }
                        } catch (SSLHandshakeException e2) {
                            e = e2;
                            i = i9;
                            j3 = currentTimeMillis2;
                            fileOutputStream = null;
                            inputStream = null;
                            j8 = j3;
                            i5 = i;
                            String str5 = "";
                            j9 = j8;
                            String str6 = "";
                            try {
                                UserTrackMethodJniBridge.addUtRecord("100168", 4, 0, "", 0L, e.getMessage(), str, str5, str6, "");
                                if (inputStream != null) {
                                }
                                if (fileOutputStream != null) {
                                }
                                disconnect(defaultURLConnection);
                                currentTimeMillis = System.currentTimeMillis();
                                i6 = str5;
                                currentTimeMillis2 = str6;
                                if (C0020.m70()) {
                                }
                                return DEBUG;
                            } catch (Throwable th2) {
                                th = th2;
                                i2 = i5;
                                j = j9;
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (Exception unused) {
                                    }
                                }
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (Exception unused2) {
                                    }
                                }
                                disconnect(defaultURLConnection);
                                long currentTimeMillis52 = System.currentTimeMillis();
                                if (C0020.m70()) {
                                    throw th;
                                }
                                C0020.C0023 c00234 = new C0020.C0023();
                                c00234.m74(str);
                                c00234.m72(0);
                                c00234.m76(i2);
                                c00234.m79(0);
                                c00234.m73(currentTimeMillis52 - j);
                                c00234.m77(j);
                                C0020.m67(c00234);
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            currentTimeMillis2 = currentTimeMillis2;
                            i = i9;
                            j2 = currentTimeMillis2;
                            fileOutputStream = null;
                            inputStream = null;
                            j5 = j2;
                            i3 = i;
                            String str7 = "";
                            j6 = j5;
                            String str8 = "";
                            try {
                                UserTrackMethodJniBridge.addUtRecord("100168", 5, 0, "", 0L, th.getMessage(), str, str7, str8, "");
                                if (inputStream != null) {
                                }
                                if (fileOutputStream != null) {
                                }
                                disconnect(defaultURLConnection);
                                currentTimeMillis = System.currentTimeMillis();
                                i6 = str7;
                                currentTimeMillis2 = str8;
                                if (C0020.m70()) {
                                }
                                return DEBUG;
                            } catch (Throwable th4) {
                                th = th4;
                                i2 = i3;
                                j = j6;
                                if (inputStream != null) {
                                }
                                if (fileOutputStream != null) {
                                }
                                disconnect(defaultURLConnection);
                                long currentTimeMillis522 = System.currentTimeMillis();
                                if (C0020.m70()) {
                                }
                            }
                        }
                    } catch (UnknownHostException e3) {
                        e = e3;
                        j4 = currentTimeMillis2;
                    } catch (SSLHandshakeException e4) {
                        e = e4;
                        j3 = currentTimeMillis2;
                    } catch (Throwable th5) {
                        th = th5;
                        j2 = currentTimeMillis2;
                        fileOutputStream = null;
                        inputStream = null;
                        j5 = j2;
                        i3 = i;
                        String str72 = "";
                        j6 = j5;
                        String str82 = "";
                        UserTrackMethodJniBridge.addUtRecord("100168", 5, 0, "", 0L, th.getMessage(), str, str72, str82, "");
                        if (inputStream != null) {
                        }
                        if (fileOutputStream != null) {
                        }
                        disconnect(defaultURLConnection);
                        currentTimeMillis = System.currentTimeMillis();
                        i6 = str72;
                        currentTimeMillis2 = str82;
                        if (C0020.m70()) {
                        }
                        return DEBUG;
                    }
                } else {
                    i9 = i;
                    try {
                        File file = new File(str2);
                        if (file.exists()) {
                            file.delete();
                        }
                        file.createNewFile();
                        InputStream inputStream2 = defaultURLConnection.getInputStream();
                        if (inputStream2 == null) {
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (Exception unused3) {
                                }
                            }
                            disconnect(defaultURLConnection);
                            long currentTimeMillis6 = System.currentTimeMillis();
                            if (C0020.m70()) {
                                C0020.C0023 c00235 = new C0020.C0023();
                                c00235.m74(str);
                                c00235.m72(0);
                                c00235.m76(i9);
                                c00235.m79(0);
                                c00235.m73(currentTimeMillis6 - currentTimeMillis2);
                                c00235.m77(currentTimeMillis2);
                                C0020.m67(c00235);
                            }
                            return DEBUG;
                        }
                        try {
                            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                            try {
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int read = inputStream2.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    fileOutputStream2.write(bArr, 0, read);
                                }
                                inputStream2.close();
                                try {
                                    fileOutputStream2.close();
                                    disconnect(defaultURLConnection);
                                    long currentTimeMillis7 = System.currentTimeMillis();
                                    if (C0020.m70()) {
                                        C0020.C0023 c00236 = new C0020.C0023();
                                        c00236.m74(str);
                                        c00236.m72(0);
                                        c00236.m76(i9);
                                        c00236.m79(0);
                                        c00236.m73(currentTimeMillis7 - currentTimeMillis2);
                                        c00236.m77(currentTimeMillis2);
                                        C0020.m67(c00236);
                                    }
                                    return true;
                                } catch (UnknownHostException e5) {
                                    e = e5;
                                    i = i9;
                                    inputStream = null;
                                    fileOutputStream = fileOutputStream2;
                                    j11 = currentTimeMillis2;
                                    i8 = i;
                                    String str32 = "";
                                    j12 = j11;
                                    String str42 = "";
                                    UserTrackMethodJniBridge.addUtRecord("100168", 3, 0, "", 0L, e.getMessage(), str, str32, str42, "");
                                    if (inputStream != null) {
                                    }
                                    if (fileOutputStream != null) {
                                    }
                                    disconnect(defaultURLConnection);
                                    long currentTimeMillis42 = System.currentTimeMillis();
                                    i6 = str32;
                                    currentTimeMillis2 = str42;
                                    if (C0020.m70()) {
                                    }
                                    return DEBUG;
                                } catch (SSLHandshakeException e6) {
                                    e = e6;
                                    i = i9;
                                    inputStream = null;
                                    fileOutputStream = fileOutputStream2;
                                    j8 = currentTimeMillis2;
                                    i5 = i;
                                    String str52 = "";
                                    j9 = j8;
                                    String str62 = "";
                                    UserTrackMethodJniBridge.addUtRecord("100168", 4, 0, "", 0L, e.getMessage(), str, str52, str62, "");
                                    if (inputStream != null) {
                                    }
                                    if (fileOutputStream != null) {
                                    }
                                    disconnect(defaultURLConnection);
                                    currentTimeMillis = System.currentTimeMillis();
                                    i6 = str52;
                                    currentTimeMillis2 = str62;
                                    if (C0020.m70()) {
                                    }
                                    return DEBUG;
                                } catch (Throwable th6) {
                                    th = th6;
                                    i = i9;
                                    inputStream = null;
                                    fileOutputStream = fileOutputStream2;
                                    j5 = currentTimeMillis2;
                                    i3 = i;
                                    String str722 = "";
                                    j6 = j5;
                                    String str822 = "";
                                    UserTrackMethodJniBridge.addUtRecord("100168", 5, 0, "", 0L, th.getMessage(), str, str722, str822, "");
                                    if (inputStream != null) {
                                    }
                                    if (fileOutputStream != null) {
                                    }
                                    disconnect(defaultURLConnection);
                                    currentTimeMillis = System.currentTimeMillis();
                                    i6 = str722;
                                    currentTimeMillis2 = str822;
                                    if (C0020.m70()) {
                                    }
                                    return DEBUG;
                                }
                            } catch (UnknownHostException e7) {
                                e = e7;
                                inputStream = inputStream2;
                                i = i9;
                            } catch (SSLHandshakeException e8) {
                                e = e8;
                                inputStream = inputStream2;
                                i = i9;
                            } catch (Throwable th7) {
                                th = th7;
                                inputStream = inputStream2;
                                i = i9;
                            }
                        } catch (UnknownHostException e9) {
                            e = e9;
                            inputStream = inputStream2;
                            i = i9;
                            fileOutputStream = null;
                            j11 = currentTimeMillis2;
                        } catch (SSLHandshakeException e10) {
                            e = e10;
                            inputStream = inputStream2;
                            i = i9;
                            fileOutputStream = null;
                            j8 = currentTimeMillis2;
                        } catch (Throwable th8) {
                            th = th8;
                            inputStream = inputStream2;
                            i = i9;
                            fileOutputStream = null;
                            j5 = currentTimeMillis2;
                        }
                    } catch (UnknownHostException e11) {
                        e = e11;
                        i = i9;
                        j4 = currentTimeMillis2;
                        fileOutputStream = null;
                        inputStream = null;
                        j11 = j4;
                        i8 = i;
                        String str322 = "";
                        j12 = j11;
                        String str422 = "";
                        UserTrackMethodJniBridge.addUtRecord("100168", 3, 0, "", 0L, e.getMessage(), str, str322, str422, "");
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception unused4) {
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception unused5) {
                            }
                        }
                        disconnect(defaultURLConnection);
                        long currentTimeMillis422 = System.currentTimeMillis();
                        i6 = str322;
                        currentTimeMillis2 = str422;
                        if (C0020.m70()) {
                            C0020.C0023 c00237 = new C0020.C0023();
                            c00237.m74(str);
                            c00237.m72(0);
                            c00237.m76(i8);
                            c00237.m79(0);
                            c00237.m73(currentTimeMillis422 - j12);
                            c00237.m77(j12);
                            c00232 = c00237;
                            i7 = str322;
                            j10 = str422;
                            C0020.m67(c00232);
                            i6 = i7;
                            currentTimeMillis2 = j10;
                        }
                        return DEBUG;
                    } catch (SSLHandshakeException e12) {
                        e = e12;
                        i = i9;
                        j3 = currentTimeMillis2;
                        fileOutputStream = null;
                        inputStream = null;
                        j8 = j3;
                        i5 = i;
                        String str522 = "";
                        j9 = j8;
                        String str622 = "";
                        UserTrackMethodJniBridge.addUtRecord("100168", 4, 0, "", 0L, e.getMessage(), str, str522, str622, "");
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception unused6) {
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception unused7) {
                            }
                        }
                        disconnect(defaultURLConnection);
                        currentTimeMillis = System.currentTimeMillis();
                        i6 = str522;
                        currentTimeMillis2 = str622;
                        if (C0020.m70()) {
                            C0020.C0023 c00238 = new C0020.C0023();
                            c00238.m74(str);
                            c00238.m72(0);
                            c00238.m76(i5);
                            c00238.m79(0);
                            j7 = j9;
                            c0023 = c00238;
                            i4 = str522;
                            c0023.m73(currentTimeMillis - j7);
                            c0023.m77(j7);
                            c00232 = c0023;
                            i7 = i4;
                            j10 = j7;
                            C0020.m67(c00232);
                            i6 = i7;
                            currentTimeMillis2 = j10;
                        }
                        return DEBUG;
                    } catch (Throwable th9) {
                        th = th9;
                        i = i9;
                        j2 = currentTimeMillis2;
                        fileOutputStream = null;
                        inputStream = null;
                        j5 = j2;
                        i3 = i;
                        String str7222 = "";
                        j6 = j5;
                        String str8222 = "";
                        UserTrackMethodJniBridge.addUtRecord("100168", 5, 0, "", 0L, th.getMessage(), str, str7222, str8222, "");
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception unused8) {
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Exception unused9) {
                            }
                        }
                        disconnect(defaultURLConnection);
                        currentTimeMillis = System.currentTimeMillis();
                        i6 = str7222;
                        currentTimeMillis2 = str8222;
                        if (C0020.m70()) {
                            C0020.C0023 c00239 = new C0020.C0023();
                            c00239.m74(str);
                            c00239.m72(0);
                            c00239.m76(i3);
                            c00239.m79(0);
                            j7 = j6;
                            c0023 = c00239;
                            i4 = str7222;
                            c0023.m73(currentTimeMillis - j7);
                            c0023.m77(j7);
                            c00232 = c0023;
                            i7 = i4;
                            j10 = j7;
                            C0020.m67(c00232);
                            i6 = i7;
                            currentTimeMillis2 = j10;
                        }
                        return DEBUG;
                    }
                }
            } catch (Throwable th10) {
                th = th10;
                i2 = i6;
                j = currentTimeMillis2;
                if (inputStream != null) {
                }
                if (fileOutputStream != null) {
                }
                disconnect(defaultURLConnection);
                long currentTimeMillis5222 = System.currentTimeMillis();
                if (C0020.m70()) {
                }
            }
        } catch (UnknownHostException e13) {
            e = e13;
            i = 0;
            j4 = currentTimeMillis2;
        } catch (SSLHandshakeException e14) {
            e = e14;
            i = 0;
            j3 = currentTimeMillis2;
        } catch (Throwable th11) {
            th = th11;
            i = 0;
            j2 = currentTimeMillis2;
        }
    }

    private static URLConnection getDefaultURLConnection(String str, int i, int i2, String str2) {
        URLConnection uRLConnection = null;
        try {
        } catch (Throwable th) {
            UserTrackMethodJniBridge.addUtRecord("100168", 1, 0, "", 0L, th.getMessage(), str, "", "", "");
        }
        if (!str.startsWith("http")) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
        uRLConnection = new URL(str).openConnection();
        if (i <= 0) {
            i = TIMEOUT_DEFAULT;
        }
        if (i2 <= 0) {
            i2 = TIMEOUT_DEFAULT;
        }
        if (str.startsWith("https")) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) uRLConnection;
            httpsURLConnection.setConnectTimeout(i);
            httpsURLConnection.setReadTimeout(i2);
            httpsURLConnection.setRequestMethod(str2);
        } else {
            HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnection;
            httpURLConnection.setConnectTimeout(i);
            httpURLConnection.setReadTimeout(i2);
            httpURLConnection.setRequestMethod(str2);
        }
        uRLConnection.setUseCaches(DEBUG);
        if ("POST".equals(str2)) {
            uRLConnection.setRequestProperty("Content-Type", "text/plain;charset=UTF-8");
            uRLConnection.setDoOutput(true);
        }
        uRLConnection.setRequestProperty("Accept-Charset", "UTF-8");
        return uRLConnection;
    }

    private static int getResponseCode(URLConnection uRLConnection) throws IOException {
        return uRLConnection instanceof HttpsURLConnection ? ((HttpsURLConnection) uRLConnection).getResponseCode() : ((HttpURLConnection) uRLConnection).getResponseCode();
    }
}
