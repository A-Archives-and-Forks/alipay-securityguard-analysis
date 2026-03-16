package com.taobao.wireless.security.adapter.common;

import android.content.Context;
import android.net.TrafficStats;
import android.os.Build;
import android.text.TextUtils;
import com.alibaba.minilibc.android.HttpClientUtils;
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

/* renamed from: com.taobao.wireless.security.adapter.common.в, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0011 {

    /* renamed from: а, reason: contains not printable characters */
    private static Context f29;

    /* JADX WARN: Code restructure failed: missing block: B:102:0x0223, code lost:
    
        if (com.taobao.wireless.security.adapter.common.C0011.f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet") != false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x01dc, code lost:
    
        if (com.taobao.wireless.security.adapter.common.C0011.f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet") != false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0177, code lost:
    
        if (com.taobao.wireless.security.adapter.common.C0011.f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet") != false) goto L74;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0179, code lost:
    
        android.net.TrafficStats.clearThreadStatsTag();
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x026b, code lost:
    
        if (com.taobao.wireless.security.adapter.common.C0011.f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet") != false) goto L74;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x0271: IF  (r10 I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) == (0 ??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:149:0x0278, block:B:148:0x0271 */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x0293: INVOKE (r0v4 ?? I:com.taobao.wireless.security.adapter.datareport.а$в), (r11 I:int) VIRTUAL call: com.taobao.wireless.security.adapter.datareport.а.в.в(int):void A[MD:(int):void (m)], block:B:151:0x0285 */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0219 A[Catch: all -> 0x026f, TRY_LEAVE, TryCatch #10 {all -> 0x026f, blocks: (B:69:0x0169, B:71:0x016d, B:73:0x0179, B:113:0x01ce, B:115:0x01d2, B:99:0x0215, B:101:0x0219, B:85:0x025d, B:87:0x0261), top: B:11:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01e8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01d2 A[Catch: all -> 0x026f, TRY_LEAVE, TryCatch #10 {all -> 0x026f, blocks: (B:69:0x0169, B:71:0x016d, B:73:0x0179, B:113:0x01ce, B:115:0x01d2, B:99:0x0215, B:101:0x0219, B:85:0x025d, B:87:0x0261), top: B:11:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01a1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0261 A[Catch: all -> 0x026f, TRY_LEAVE, TryCatch #10 {all -> 0x026f, blocks: (B:69:0x0169, B:71:0x016d, B:73:0x0179, B:113:0x01ce, B:115:0x01d2, B:99:0x0215, B:101:0x0219, B:85:0x025d, B:87:0x0261), top: B:11:0x0030 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0230 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01fa  */
    /* renamed from: а, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static C0010 m32(String str, Map<String, String> map) {
        BufferedReader bufferedReader;
        int m79;
        C0010 c0010;
        Throwable th;
        BufferedReader bufferedReader2;
        int i;
        SSLHandshakeException e;
        UnknownHostException e2;
        int i2;
        StringBuilder sb;
        if (TextUtils.isEmpty(str)) {
            return new C0010(null, HttpClientUtils.ResCode.REQ_PARAM_INVALID);
        }
        URLConnection m35 = m35(str, HttpClientUtils.TIMEOUT_DEFAULT, HttpClientUtils.TIMEOUT_DEFAULT, "GET");
        if (m35 == null) {
            return new C0010(null, HttpClientUtils.ResCode.EXCEPTION);
        }
        new C0010(null, -1);
        long currentTimeMillis = System.currentTimeMillis();
        try {
            try {
                if (map != 0) {
                    try {
                        for (String str2 : map.keySet()) {
                            m35.setRequestProperty(str2, (String) map.get(str2));
                        }
                    } catch (UnknownHostException e3) {
                        e2 = e3;
                        bufferedReader2 = null;
                        i = 0;
                        i2 = 0;
                        c0010 = new C0010(null, HttpClientUtils.ResCode.CUSTOM_UNKONW_HOST, e2);
                        if (bufferedReader2 != null) {
                        }
                        m37(m35);
                        long currentTimeMillis2 = System.currentTimeMillis();
                        if (C0020.m70()) {
                        }
                        if (Build.VERSION.SDK_INT >= 14) {
                        }
                        return c0010;
                    } catch (SSLHandshakeException e4) {
                        e = e4;
                        bufferedReader2 = null;
                        i = 0;
                        i2 = 0;
                        c0010 = new C0010(null, HttpClientUtils.ResCode.CUSTOM_SSL_HANDSHAKE_ERROR, e);
                        if (bufferedReader2 != null) {
                        }
                        m37(m35);
                        long currentTimeMillis3 = System.currentTimeMillis();
                        if (C0020.m70()) {
                        }
                        if (Build.VERSION.SDK_INT >= 14) {
                        }
                        return c0010;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader2 = null;
                        i = 0;
                        i2 = 0;
                        c0010 = new C0010(null, HttpClientUtils.ResCode.EXCEPTION, th);
                        if (bufferedReader2 != null) {
                        }
                        m37(m35);
                        long currentTimeMillis4 = System.currentTimeMillis();
                        if (C0020.m70()) {
                        }
                        if (Build.VERSION.SDK_INT >= 14) {
                        }
                        return c0010;
                    }
                }
                try {
                    if (Build.VERSION.SDK_INT >= 14 && f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet")) {
                        TrafficStats.setThreadStatsTag(61453);
                    }
                } catch (Throwable unused) {
                }
                m35.setDoInput(true);
                m35.connect();
                i = m38(m35);
                try {
                } catch (UnknownHostException e5) {
                    e2 = e5;
                    bufferedReader2 = null;
                } catch (SSLHandshakeException e6) {
                    e = e6;
                    bufferedReader2 = null;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader2 = null;
                }
            } catch (Throwable unused2) {
            }
            if (i != 200) {
                C0010 c00102 = new C0010(null, i);
                m37(m35);
                long currentTimeMillis5 = System.currentTimeMillis();
                if (C0020.m70()) {
                    C0020.C0023 c0023 = new C0020.C0023();
                    c0023.m74(str);
                    c0023.m72(0);
                    c0023.m76(i);
                    c0023.m79(0);
                    c0023.m73(currentTimeMillis5 - currentTimeMillis);
                    c0023.m77(currentTimeMillis);
                    C0020.m67(c0023);
                }
                try {
                    if (Build.VERSION.SDK_INT >= 14 && f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet")) {
                        TrafficStats.clearThreadStatsTag();
                    }
                } catch (Throwable unused3) {
                }
                return c00102;
            }
            InputStream inputStream = m35.getInputStream();
            if (inputStream == null) {
                C0010 c00103 = new C0010(null, HttpClientUtils.ResCode.HTTP_RESPONSE_NULL);
                m37(m35);
                long currentTimeMillis6 = System.currentTimeMillis();
                if (C0020.m70()) {
                    C0020.C0023 c00232 = new C0020.C0023();
                    c00232.m74(str);
                    c00232.m72(0);
                    c00232.m76(i);
                    c00232.m79(0);
                    c00232.m73(currentTimeMillis6 - currentTimeMillis);
                    c00232.m77(currentTimeMillis);
                    C0020.m67(c00232);
                }
                try {
                    if (Build.VERSION.SDK_INT >= 14 && f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet")) {
                        TrafficStats.clearThreadStatsTag();
                    }
                } catch (Throwable unused4) {
                }
                return c00103;
            }
            bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            try {
                sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader2.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                }
                i2 = sb.length();
            } catch (UnknownHostException e7) {
                e2 = e7;
                i2 = 0;
                c0010 = new C0010(null, HttpClientUtils.ResCode.CUSTOM_UNKONW_HOST, e2);
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Throwable unused5) {
                    }
                }
                m37(m35);
                long currentTimeMillis22 = System.currentTimeMillis();
                if (C0020.m70()) {
                    C0020.C0023 c00233 = new C0020.C0023();
                    c00233.m74(str);
                    c00233.m72(0);
                    c00233.m76(i);
                    c00233.m79(i2);
                    c00233.m73(currentTimeMillis22 - currentTimeMillis);
                    c00233.m77(currentTimeMillis);
                    C0020.m67(c00233);
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            } catch (SSLHandshakeException e8) {
                e = e8;
                i2 = 0;
                c0010 = new C0010(null, HttpClientUtils.ResCode.CUSTOM_SSL_HANDSHAKE_ERROR, e);
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Throwable unused6) {
                    }
                }
                m37(m35);
                long currentTimeMillis32 = System.currentTimeMillis();
                if (C0020.m70()) {
                    C0020.C0023 c00234 = new C0020.C0023();
                    c00234.m74(str);
                    c00234.m72(0);
                    c00234.m76(i);
                    c00234.m79(i2);
                    c00234.m73(currentTimeMillis32 - currentTimeMillis);
                    c00234.m77(currentTimeMillis);
                    C0020.m67(c00234);
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            } catch (Throwable th4) {
                th = th4;
                i2 = 0;
                c0010 = new C0010(null, HttpClientUtils.ResCode.EXCEPTION, th);
                if (bufferedReader2 != null) {
                    try {
                        bufferedReader2.close();
                    } catch (Throwable unused7) {
                    }
                }
                m37(m35);
                long currentTimeMillis42 = System.currentTimeMillis();
                if (C0020.m70()) {
                    C0020.C0023 c00235 = new C0020.C0023();
                    c00235.m74(str);
                    c00235.m72(0);
                    c00235.m76(i);
                    c00235.m79(i2);
                    c00235.m73(currentTimeMillis42 - currentTimeMillis);
                    c00235.m77(currentTimeMillis);
                    C0020.m67(c00235);
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            }
            try {
                c0010 = new C0010(sb.toString(), HttpClientUtils.ResCode.STATUS_OK);
                try {
                    bufferedReader2.close();
                } catch (Throwable unused8) {
                }
                m37(m35);
                long currentTimeMillis7 = System.currentTimeMillis();
                if (C0020.m70()) {
                    C0020.C0023 c00236 = new C0020.C0023();
                    c00236.m74(str);
                    c00236.m72(0);
                    c00236.m76(i);
                    c00236.m79(i2);
                    c00236.m73(currentTimeMillis7 - currentTimeMillis);
                    c00236.m77(currentTimeMillis);
                    C0020.m67(c00236);
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
            } catch (UnknownHostException e9) {
                e2 = e9;
                c0010 = new C0010(null, HttpClientUtils.ResCode.CUSTOM_UNKONW_HOST, e2);
                if (bufferedReader2 != null) {
                }
                m37(m35);
                long currentTimeMillis222 = System.currentTimeMillis();
                if (C0020.m70()) {
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            } catch (SSLHandshakeException e10) {
                e = e10;
                c0010 = new C0010(null, HttpClientUtils.ResCode.CUSTOM_SSL_HANDSHAKE_ERROR, e);
                if (bufferedReader2 != null) {
                }
                m37(m35);
                long currentTimeMillis322 = System.currentTimeMillis();
                if (C0020.m70()) {
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            } catch (Throwable th5) {
                th = th5;
                c0010 = new C0010(null, HttpClientUtils.ResCode.EXCEPTION, th);
                if (bufferedReader2 != null) {
                }
                m37(m35);
                long currentTimeMillis422 = System.currentTimeMillis();
                if (C0020.m70()) {
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            }
            return c0010;
        } catch (Throwable th6) {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Throwable unused9) {
                }
            }
            m37(m35);
            long currentTimeMillis8 = System.currentTimeMillis();
            if (C0020.m70()) {
                C0020.C0023 c00237 = new C0020.C0023();
                c00237.m74(str);
                c00237.m72(0);
                c00237.m76((int) map);
                c00237.m79(m79);
                c00237.m73(currentTimeMillis8 - currentTimeMillis);
                c00237.m77(currentTimeMillis);
                C0020.m67(c00237);
            }
            try {
                if (Build.VERSION.SDK_INT >= 14 && f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet")) {
                    TrafficStats.clearThreadStatsTag();
                }
            } catch (Throwable unused10) {
            }
            throw th6;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:159:0x019a, code lost:
    
        if (com.taobao.wireless.security.adapter.common.C0011.f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet") != false) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x02b3, code lost:
    
        if (com.taobao.wireless.security.adapter.common.C0011.f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet") != false) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x019c, code lost:
    
        android.net.TrafficStats.clearThreadStatsTag();
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0264, code lost:
    
        if (com.taobao.wireless.security.adapter.common.C0011.f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet") != false) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x0216, code lost:
    
        if (com.taobao.wireless.security.adapter.common.C0011.f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet") != false) goto L89;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 7, insn: 0x02c0: IF  (r7 I:??[int, boolean, OBJECT, ARRAY, byte, short, char]) == (0 ??[int, boolean, OBJECT, ARRAY, byte, short, char])  -> B:196:0x02c7, block:B:195:0x02c0 */
    /* JADX WARN: Not initialized variable reg: 8, insn: 0x02e2: INVOKE (r11v3 ?? I:com.taobao.wireless.security.adapter.datareport.а$в), (r8 I:int) VIRTUAL call: com.taobao.wireless.security.adapter.datareport.а.в.в(int):void A[MD:(int):void (m)], block:B:198:0x02d4 */
    /* JADX WARN: Removed duplicated region for block: B:36:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x02a9 A[Catch: all -> 0x02b7, TRY_LEAVE, TryCatch #15 {all -> 0x02b7, blocks: (B:156:0x018c, B:158:0x0190, B:41:0x019c, B:84:0x0208, B:86:0x020c, B:62:0x0256, B:64:0x025a, B:37:0x02a5, B:39:0x02a9), top: B:10:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0278 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0271 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x025a A[Catch: all -> 0x02b7, TRY_LEAVE, TryCatch #15 {all -> 0x02b7, blocks: (B:156:0x018c, B:158:0x0190, B:41:0x019c, B:84:0x0208, B:86:0x020c, B:62:0x0256, B:64:0x025a, B:37:0x02a5, B:39:0x02a9), top: B:10:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0229 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0222 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01ed  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x020c A[Catch: all -> 0x02b7, TRY_LEAVE, TryCatch #15 {all -> 0x02b7, blocks: (B:156:0x018c, B:158:0x0190, B:41:0x019c, B:84:0x0208, B:86:0x020c, B:62:0x0256, B:64:0x025a, B:37:0x02a5, B:39:0x02a9), top: B:10:0x002a }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01db A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01d4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: а, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static C0010 m33(String str, Map<String, String> map, byte[] bArr, String str2, int i, int i2) {
        C0010 c0010;
        Throwable th;
        int i3;
        OutputStream outputStream;
        int i4;
        BufferedReader bufferedReader;
        SSLHandshakeException e;
        UnknownHostException e2;
        StringBuilder sb;
        if (TextUtils.isEmpty(str) || bArr == 0) {
            return new C0010(null, HttpClientUtils.ResCode.REQ_PARAM_INVALID);
        }
        URLConnection m35 = m35(str, i, i2, "POST");
        if (m35 == null) {
            return new C0010(null, HttpClientUtils.ResCode.EXCEPTION);
        }
        new C0010(null, -1);
        long currentTimeMillis = System.currentTimeMillis();
        try {
            try {
                if (map != 0) {
                    try {
                        for (String str3 : map.keySet()) {
                            m35.setRequestProperty(str3, (String) map.get(str3));
                        }
                    } catch (UnknownHostException e3) {
                        e2 = e3;
                        outputStream = null;
                        bufferedReader = null;
                        i3 = 0;
                        i4 = 0;
                        c0010 = new C0010(null, HttpClientUtils.ResCode.CUSTOM_UNKONW_HOST, e2);
                        if (outputStream != null) {
                        }
                        if (bufferedReader != null) {
                        }
                        m37(m35);
                        long currentTimeMillis2 = System.currentTimeMillis();
                        if (C0020.m70()) {
                        }
                        if (Build.VERSION.SDK_INT >= 14) {
                        }
                        return c0010;
                    } catch (SSLHandshakeException e4) {
                        e = e4;
                        outputStream = null;
                        bufferedReader = null;
                        i3 = 0;
                        i4 = 0;
                        c0010 = new C0010(null, HttpClientUtils.ResCode.CUSTOM_SSL_HANDSHAKE_ERROR, e);
                        if (outputStream != null) {
                        }
                        if (bufferedReader != null) {
                        }
                        m37(m35);
                        long currentTimeMillis3 = System.currentTimeMillis();
                        if (C0020.m70()) {
                        }
                        if (Build.VERSION.SDK_INT >= 14) {
                        }
                        return c0010;
                    } catch (Throwable th2) {
                        th = th2;
                        outputStream = null;
                        bufferedReader = null;
                        i3 = 0;
                        i4 = 0;
                        c0010 = new C0010(null, HttpClientUtils.ResCode.EXCEPTION, th);
                        if (outputStream != null) {
                        }
                        if (bufferedReader != null) {
                        }
                        m37(m35);
                        long currentTimeMillis4 = System.currentTimeMillis();
                        if (C0020.m70()) {
                        }
                        if (Build.VERSION.SDK_INT >= 14) {
                        }
                        return c0010;
                    }
                }
                try {
                    if (Build.VERSION.SDK_INT >= 14 && f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet")) {
                        TrafficStats.setThreadStatsTag(61453);
                    }
                } catch (Throwable unused) {
                }
                m35.setDoInput(true);
                if (str2 != null && str2.length() > 0) {
                    m35.setRequestProperty("Content-Type", str2);
                }
                m35.connect();
                outputStream = m35.getOutputStream();
            } catch (Throwable unused2) {
            }
            try {
                outputStream.write(bArr);
                outputStream.flush();
                i3 = m38(m35);
                try {
                } catch (UnknownHostException e5) {
                    e2 = e5;
                    bufferedReader = null;
                } catch (SSLHandshakeException e6) {
                    e = e6;
                    bufferedReader = null;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = null;
                }
            } catch (UnknownHostException e7) {
                e2 = e7;
                bufferedReader = null;
                i3 = 0;
                i4 = 0;
                c0010 = new C0010(null, HttpClientUtils.ResCode.CUSTOM_UNKONW_HOST, e2);
                if (outputStream != null) {
                }
                if (bufferedReader != null) {
                }
                m37(m35);
                long currentTimeMillis22 = System.currentTimeMillis();
                if (C0020.m70()) {
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            } catch (SSLHandshakeException e8) {
                e = e8;
                bufferedReader = null;
                i3 = 0;
                i4 = 0;
                c0010 = new C0010(null, HttpClientUtils.ResCode.CUSTOM_SSL_HANDSHAKE_ERROR, e);
                if (outputStream != null) {
                }
                if (bufferedReader != null) {
                }
                m37(m35);
                long currentTimeMillis32 = System.currentTimeMillis();
                if (C0020.m70()) {
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            } catch (Throwable th4) {
                th = th4;
                bufferedReader = null;
                i3 = 0;
                i4 = 0;
                c0010 = new C0010(null, HttpClientUtils.ResCode.EXCEPTION, th);
                if (outputStream != null) {
                }
                if (bufferedReader != null) {
                }
                m37(m35);
                long currentTimeMillis42 = System.currentTimeMillis();
                if (C0020.m70()) {
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            }
            if (i3 != 200) {
                C0010 c00102 = new C0010(null, i3);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable unused3) {
                    }
                }
                m37(m35);
                long currentTimeMillis5 = System.currentTimeMillis();
                if (C0020.m70()) {
                    C0020.C0023 c0023 = new C0020.C0023();
                    c0023.m74(str);
                    c0023.m72(0);
                    c0023.m76(i3);
                    c0023.m79(0);
                    c0023.m73(currentTimeMillis5 - currentTimeMillis);
                    c0023.m77(currentTimeMillis);
                    C0020.m67(c0023);
                }
                try {
                    if (Build.VERSION.SDK_INT >= 14 && f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet")) {
                        TrafficStats.clearThreadStatsTag();
                    }
                } catch (Throwable unused4) {
                }
                return c00102;
            }
            InputStream inputStream = m35.getInputStream();
            if (inputStream == null) {
                C0010 c00103 = new C0010(null, HttpClientUtils.ResCode.HTTP_RESPONSE_NULL);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable unused5) {
                    }
                }
                m37(m35);
                long currentTimeMillis6 = System.currentTimeMillis();
                if (C0020.m70()) {
                    C0020.C0023 c00232 = new C0020.C0023();
                    c00232.m74(str);
                    c00232.m72(0);
                    c00232.m76(i3);
                    c00232.m79(0);
                    c00232.m73(currentTimeMillis6 - currentTimeMillis);
                    c00232.m77(currentTimeMillis);
                    C0020.m67(c00232);
                }
                try {
                    if (Build.VERSION.SDK_INT >= 14 && f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet")) {
                        TrafficStats.clearThreadStatsTag();
                    }
                } catch (Throwable unused6) {
                }
                return c00103;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            try {
                sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                }
                i4 = sb.length();
            } catch (UnknownHostException e9) {
                e2 = e9;
                i4 = 0;
                c0010 = new C0010(null, HttpClientUtils.ResCode.CUSTOM_UNKONW_HOST, e2);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable unused7) {
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable unused8) {
                    }
                }
                m37(m35);
                long currentTimeMillis222 = System.currentTimeMillis();
                if (C0020.m70()) {
                    C0020.C0023 c00233 = new C0020.C0023();
                    c00233.m74(str);
                    c00233.m72(0);
                    c00233.m76(i3);
                    c00233.m79(i4);
                    c00233.m73(currentTimeMillis222 - currentTimeMillis);
                    c00233.m77(currentTimeMillis);
                    C0020.m67(c00233);
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            } catch (SSLHandshakeException e10) {
                e = e10;
                i4 = 0;
                c0010 = new C0010(null, HttpClientUtils.ResCode.CUSTOM_SSL_HANDSHAKE_ERROR, e);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable unused9) {
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable unused10) {
                    }
                }
                m37(m35);
                long currentTimeMillis322 = System.currentTimeMillis();
                if (C0020.m70()) {
                    C0020.C0023 c00234 = new C0020.C0023();
                    c00234.m74(str);
                    c00234.m72(0);
                    c00234.m76(i3);
                    c00234.m79(i4);
                    c00234.m73(currentTimeMillis322 - currentTimeMillis);
                    c00234.m77(currentTimeMillis);
                    C0020.m67(c00234);
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            } catch (Throwable th5) {
                th = th5;
                i4 = 0;
                c0010 = new C0010(null, HttpClientUtils.ResCode.EXCEPTION, th);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable unused11) {
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable unused12) {
                    }
                }
                m37(m35);
                long currentTimeMillis422 = System.currentTimeMillis();
                if (C0020.m70()) {
                    C0020.C0023 c00235 = new C0020.C0023();
                    c00235.m74(str);
                    c00235.m72(0);
                    c00235.m76(i3);
                    c00235.m79(i4);
                    c00235.m73(currentTimeMillis422 - currentTimeMillis);
                    c00235.m77(currentTimeMillis);
                    C0020.m67(c00235);
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            }
            try {
                c0010 = new C0010(sb.toString(), HttpClientUtils.ResCode.STATUS_OK);
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable unused13) {
                    }
                }
                try {
                    bufferedReader.close();
                } catch (Throwable unused14) {
                }
                m37(m35);
                long currentTimeMillis7 = System.currentTimeMillis();
                if (C0020.m70()) {
                    C0020.C0023 c00236 = new C0020.C0023();
                    c00236.m74(str);
                    c00236.m72(0);
                    c00236.m76(i3);
                    c00236.m79(i4);
                    c00236.m73(currentTimeMillis7 - currentTimeMillis);
                    c00236.m77(currentTimeMillis);
                    C0020.m67(c00236);
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
            } catch (UnknownHostException e11) {
                e2 = e11;
                c0010 = new C0010(null, HttpClientUtils.ResCode.CUSTOM_UNKONW_HOST, e2);
                if (outputStream != null) {
                }
                if (bufferedReader != null) {
                }
                m37(m35);
                long currentTimeMillis2222 = System.currentTimeMillis();
                if (C0020.m70()) {
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            } catch (SSLHandshakeException e12) {
                e = e12;
                c0010 = new C0010(null, HttpClientUtils.ResCode.CUSTOM_SSL_HANDSHAKE_ERROR, e);
                if (outputStream != null) {
                }
                if (bufferedReader != null) {
                }
                m37(m35);
                long currentTimeMillis3222 = System.currentTimeMillis();
                if (C0020.m70()) {
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            } catch (Throwable th6) {
                th = th6;
                c0010 = new C0010(null, HttpClientUtils.ResCode.EXCEPTION, th);
                if (outputStream != null) {
                }
                if (bufferedReader != null) {
                }
                m37(m35);
                long currentTimeMillis4222 = System.currentTimeMillis();
                if (C0020.m70()) {
                }
                if (Build.VERSION.SDK_INT >= 14) {
                }
                return c0010;
            }
            return c0010;
        } finally {
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:111:0x0385, code lost:
    
        if (com.taobao.wireless.security.adapter.common.C0011.f29.getPackageName().equalsIgnoreCase(r29) != false) goto L182;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0387, code lost:
    
        android.net.TrafficStats.clearThreadStatsTag();
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x02f7, code lost:
    
        if (com.taobao.wireless.security.adapter.common.C0011.f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet") != false) goto L182;
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x025d, code lost:
    
        if (com.taobao.wireless.security.adapter.common.C0011.f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet") != false) goto L182;
     */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0379 A[Catch: all -> 0x038a, TryCatch #30 {all -> 0x038a, blocks: (B:199:0x024d, B:201:0x0253, B:112:0x0387, B:169:0x02e5, B:171:0x02eb, B:108:0x0373, B:110:0x0379), top: B:12:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0342 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x033b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:131:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x03d3 A[Catch: all -> 0x03e2, TryCatch #3 {all -> 0x03e2, blocks: (B:133:0x03cf, B:135:0x03d3, B:137:0x03df), top: B:132:0x03cf }] */
    /* JADX WARN: Removed duplicated region for block: B:141:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x03a2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x039b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x02eb A[Catch: all -> 0x038a, TRY_LEAVE, TryCatch #30 {all -> 0x038a, blocks: (B:199:0x024d, B:201:0x0253, B:112:0x0387, B:169:0x02e5, B:171:0x02eb, B:108:0x0373, B:110:0x0379), top: B:12:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x02b4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02ad A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0253 A[Catch: all -> 0x038a, TRY_LEAVE, TryCatch #30 {all -> 0x038a, blocks: (B:199:0x024d, B:201:0x0253, B:112:0x0387, B:169:0x02e5, B:171:0x02eb, B:108:0x0373, B:110:0x0379), top: B:12:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x021b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:207:0x0214 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: а, reason: contains not printable characters */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String m34(String str, String str2) {
        long j;
        int i;
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        String str3;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        long j2;
        String str4;
        int i7;
        if (f29 == null || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return null;
        }
        URLConnection m35 = m35(str, HttpClientUtils.TIMEOUT_DEFAULT, HttpClientUtils.TIMEOUT_DEFAULT, "GET");
        if (m35 == null) {
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int i8 = 0;
        try {
            try {
                try {
                    m35.setDoInput(true);
                    try {
                        m35.connect();
                        int m38 = m38(m35);
                        if (m38 != 200) {
                            m37(m35);
                            long currentTimeMillis2 = System.currentTimeMillis();
                            if (C0020.m70()) {
                                C0020.C0023 c0023 = new C0020.C0023();
                                c0023.m74(str);
                                c0023.m72(0);
                                c0023.m76(m38);
                                c0023.m79(0);
                                c0023.m73(currentTimeMillis2 - currentTimeMillis);
                                c0023.m77(currentTimeMillis);
                                C0020.m67(c0023);
                            }
                            try {
                                if (Build.VERSION.SDK_INT >= 14 && f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet")) {
                                    TrafficStats.clearThreadStatsTag();
                                }
                            } catch (Throwable unused) {
                            }
                            return null;
                        }
                        try {
                            File file = new File(str2);
                            if (file.exists()) {
                                file.delete();
                            }
                            file.createNewFile();
                            try {
                                if (Build.VERSION.SDK_INT >= 14 && f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet")) {
                                    TrafficStats.setThreadStatsTag(61453);
                                }
                            } catch (Throwable unused2) {
                            }
                            InputStream inputStream2 = m35.getInputStream();
                            if (inputStream2 == null) {
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (Exception unused3) {
                                    }
                                }
                                m37(m35);
                                long currentTimeMillis3 = System.currentTimeMillis();
                                if (C0020.m70()) {
                                    C0020.C0023 c00232 = new C0020.C0023();
                                    c00232.m74(str);
                                    c00232.m72(0);
                                    c00232.m76(m38);
                                    c00232.m79(0);
                                    c00232.m73(currentTimeMillis3 - currentTimeMillis);
                                    c00232.m77(currentTimeMillis);
                                    C0020.m67(c00232);
                                }
                                try {
                                    if (Build.VERSION.SDK_INT >= 14 && f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet")) {
                                        TrafficStats.clearThreadStatsTag();
                                    }
                                } catch (Throwable unused4) {
                                }
                                return null;
                            }
                            try {
                                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                                try {
                                    byte[] bArr = new byte[1024];
                                    i3 = 0;
                                    while (true) {
                                        try {
                                            int read = inputStream2.read(bArr);
                                            if (read == -1) {
                                                break;
                                            }
                                            fileOutputStream2.write(bArr, 0, read);
                                            i3 += read;
                                        } catch (UnknownHostException e) {
                                            e = e;
                                            i = m38;
                                            inputStream = inputStream2;
                                            fileOutputStream = fileOutputStream2;
                                            i8 = i3;
                                            str3 = "com.alibaba.android.rimet";
                                            i2 = 0;
                                            try {
                                                String str5 = str3;
                                                int i9 = i8;
                                                try {
                                                    UserTrackMethodJniBridge.addUtRecord("100168", 3, 0, "", 0L, e.getMessage(), str, "", "", "");
                                                    if (inputStream != null) {
                                                        try {
                                                            inputStream.close();
                                                        } catch (Exception unused5) {
                                                        }
                                                    }
                                                    if (fileOutputStream != null) {
                                                        try {
                                                            fileOutputStream.close();
                                                        } catch (Exception unused6) {
                                                        }
                                                    }
                                                    m37(m35);
                                                    long currentTimeMillis4 = System.currentTimeMillis();
                                                    if (C0020.m70()) {
                                                        C0020.C0023 c00233 = new C0020.C0023();
                                                        c00233.m74(str);
                                                        c00233.m72(i2);
                                                        c00233.m76(i);
                                                        c00233.m79(i9);
                                                        c00233.m73(currentTimeMillis4 - currentTimeMillis);
                                                        c00233.m77(currentTimeMillis);
                                                        C0020.m67(c00233);
                                                    }
                                                    if (Build.VERSION.SDK_INT >= 14) {
                                                    }
                                                    return null;
                                                } catch (Throwable th) {
                                                    th = th;
                                                    str4 = str5;
                                                    i8 = i9;
                                                    j2 = currentTimeMillis;
                                                    i7 = 14;
                                                    if (inputStream != null) {
                                                    }
                                                    if (fileOutputStream != null) {
                                                    }
                                                    m37(m35);
                                                    long currentTimeMillis5 = System.currentTimeMillis();
                                                    if (C0020.m70()) {
                                                    }
                                                    try {
                                                        if (Build.VERSION.SDK_INT < i7) {
                                                        }
                                                    } catch (Throwable unused7) {
                                                        throw th;
                                                    }
                                                }
                                            } catch (Throwable th2) {
                                                th = th2;
                                                str4 = str3;
                                                j2 = currentTimeMillis;
                                                i7 = 14;
                                                if (inputStream != null) {
                                                }
                                                if (fileOutputStream != null) {
                                                }
                                                m37(m35);
                                                long currentTimeMillis52 = System.currentTimeMillis();
                                                if (C0020.m70()) {
                                                }
                                                if (Build.VERSION.SDK_INT < i7) {
                                                }
                                            }
                                        } catch (SSLHandshakeException e2) {
                                            e = e2;
                                            i = m38;
                                            inputStream = inputStream2;
                                            fileOutputStream = fileOutputStream2;
                                            i8 = i3;
                                            j = currentTimeMillis;
                                            try {
                                                int i10 = i8;
                                                long j3 = j;
                                                i2 = 0;
                                                try {
                                                    UserTrackMethodJniBridge.addUtRecord("100168", 4, 0, "", 0L, e.getMessage(), str, "", "", "");
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
                                                    m37(m35);
                                                    long currentTimeMillis6 = System.currentTimeMillis();
                                                    if (C0020.m70()) {
                                                        C0020.C0023 c00234 = new C0020.C0023();
                                                        c00234.m74(str);
                                                        c00234.m72(0);
                                                        c00234.m76(i);
                                                        c00234.m79(i10);
                                                        c00234.m73(currentTimeMillis6 - j3);
                                                        c00234.m77(j3);
                                                        C0020.m67(c00234);
                                                    }
                                                    if (Build.VERSION.SDK_INT >= 14) {
                                                    }
                                                    return null;
                                                } catch (Throwable th3) {
                                                    th = th3;
                                                    i8 = i10;
                                                    str4 = "com.alibaba.android.rimet";
                                                    j2 = j3;
                                                    i7 = 14;
                                                    if (inputStream != null) {
                                                    }
                                                    if (fileOutputStream != null) {
                                                    }
                                                    m37(m35);
                                                    long currentTimeMillis522 = System.currentTimeMillis();
                                                    if (C0020.m70()) {
                                                    }
                                                    if (Build.VERSION.SDK_INT < i7) {
                                                    }
                                                }
                                            } catch (Throwable th4) {
                                                th = th4;
                                                currentTimeMillis = j;
                                                str3 = "com.alibaba.android.rimet";
                                                i2 = 0;
                                                str4 = str3;
                                                j2 = currentTimeMillis;
                                                i7 = 14;
                                                if (inputStream != null) {
                                                }
                                                if (fileOutputStream != null) {
                                                }
                                                m37(m35);
                                                long currentTimeMillis5222 = System.currentTimeMillis();
                                                if (C0020.m70()) {
                                                }
                                                if (Build.VERSION.SDK_INT < i7) {
                                                }
                                            }
                                        } catch (Throwable th5) {
                                            th = th5;
                                            i4 = m38;
                                            inputStream = inputStream2;
                                            fileOutputStream = fileOutputStream2;
                                        }
                                    }
                                    inputStream2.close();
                                } catch (UnknownHostException e3) {
                                    e = e3;
                                    i = m38;
                                    inputStream = inputStream2;
                                    fileOutputStream = fileOutputStream2;
                                } catch (SSLHandshakeException e4) {
                                    e = e4;
                                    i = m38;
                                    inputStream = inputStream2;
                                    fileOutputStream = fileOutputStream2;
                                } catch (Throwable th6) {
                                    th = th6;
                                    i4 = m38;
                                    inputStream = inputStream2;
                                    fileOutputStream = fileOutputStream2;
                                    i3 = 0;
                                    try {
                                        i5 = i3;
                                        int i11 = i4;
                                        try {
                                            UserTrackMethodJniBridge.addUtRecord("100168", 5, 0, "", 0L, th.getMessage(), str, "", "", "");
                                            if (inputStream != null) {
                                                try {
                                                    inputStream.close();
                                                } catch (Exception unused10) {
                                                }
                                            }
                                            if (fileOutputStream != null) {
                                                try {
                                                    fileOutputStream.close();
                                                } catch (Exception unused11) {
                                                }
                                            }
                                            m37(m35);
                                            long currentTimeMillis7 = System.currentTimeMillis();
                                            if (C0020.m70()) {
                                                C0020.C0023 c00235 = new C0020.C0023();
                                                c00235.m74(str);
                                                c00235.m72(0);
                                                c00235.m76(i11);
                                                c00235.m79(i5);
                                                c00235.m73(currentTimeMillis7 - currentTimeMillis);
                                                c00235.m77(currentTimeMillis);
                                                C0020.m67(c00235);
                                            }
                                            if (Build.VERSION.SDK_INT >= 14) {
                                            }
                                            return null;
                                        } catch (Throwable th7) {
                                            th = th7;
                                            i6 = i11;
                                            str4 = "com.alibaba.android.rimet";
                                            j2 = currentTimeMillis;
                                            i7 = 14;
                                            i2 = 0;
                                            int i12 = i5;
                                            i = i6;
                                            i8 = i12;
                                            if (inputStream != null) {
                                                try {
                                                    inputStream.close();
                                                } catch (Exception unused12) {
                                                }
                                            }
                                            if (fileOutputStream != null) {
                                                try {
                                                    fileOutputStream.close();
                                                } catch (Exception unused13) {
                                                }
                                            }
                                            m37(m35);
                                            long currentTimeMillis52222 = System.currentTimeMillis();
                                            if (C0020.m70()) {
                                                C0020.C0023 c00236 = new C0020.C0023();
                                                c00236.m74(str);
                                                c00236.m72(i2);
                                                c00236.m76(i);
                                                c00236.m79(i8);
                                                c00236.m73(currentTimeMillis52222 - j2);
                                                c00236.m77(j2);
                                                C0020.m67(c00236);
                                            }
                                            if (Build.VERSION.SDK_INT < i7) {
                                                throw th;
                                            }
                                            if (!f29.getPackageName().equalsIgnoreCase(str4)) {
                                                throw th;
                                            }
                                            TrafficStats.clearThreadStatsTag();
                                            throw th;
                                        }
                                    } catch (Throwable th8) {
                                        th = th8;
                                        i5 = i3;
                                        i6 = i4;
                                        j2 = currentTimeMillis;
                                        str4 = "com.alibaba.android.rimet";
                                    }
                                }
                                try {
                                    fileOutputStream2.close();
                                } catch (UnknownHostException e5) {
                                    e = e5;
                                    fileOutputStream = fileOutputStream2;
                                    i8 = i3;
                                    str3 = "com.alibaba.android.rimet";
                                    inputStream = null;
                                    i2 = 0;
                                    i = m38;
                                    String str52 = str3;
                                    int i92 = i8;
                                    UserTrackMethodJniBridge.addUtRecord("100168", 3, 0, "", 0L, e.getMessage(), str, "", "", "");
                                    if (inputStream != null) {
                                    }
                                    if (fileOutputStream != null) {
                                    }
                                    m37(m35);
                                    long currentTimeMillis42 = System.currentTimeMillis();
                                    if (C0020.m70()) {
                                    }
                                    if (Build.VERSION.SDK_INT >= 14) {
                                    }
                                    return null;
                                } catch (SSLHandshakeException e6) {
                                    e = e6;
                                    fileOutputStream = fileOutputStream2;
                                    i8 = i3;
                                    j = currentTimeMillis;
                                    inputStream = null;
                                    i = m38;
                                    int i102 = i8;
                                    long j32 = j;
                                    i2 = 0;
                                    UserTrackMethodJniBridge.addUtRecord("100168", 4, 0, "", 0L, e.getMessage(), str, "", "", "");
                                    if (inputStream != null) {
                                    }
                                    if (fileOutputStream != null) {
                                    }
                                    m37(m35);
                                    long currentTimeMillis62 = System.currentTimeMillis();
                                    if (C0020.m70()) {
                                    }
                                    if (Build.VERSION.SDK_INT >= 14) {
                                    }
                                    return null;
                                } catch (Throwable th9) {
                                    th = th9;
                                    i4 = m38;
                                    fileOutputStream = fileOutputStream2;
                                    inputStream = null;
                                }
                                try {
                                    String absolutePath = file.getAbsolutePath();
                                    m37(m35);
                                    long currentTimeMillis8 = System.currentTimeMillis();
                                    if (C0020.m70()) {
                                        C0020.C0023 c00237 = new C0020.C0023();
                                        c00237.m74(str);
                                        c00237.m72(0);
                                        c00237.m76(m38);
                                        c00237.m79(i3);
                                        c00237.m73(currentTimeMillis8 - currentTimeMillis);
                                        c00237.m77(currentTimeMillis);
                                        C0020.m67(c00237);
                                    }
                                    try {
                                        if (Build.VERSION.SDK_INT >= 14 && f29.getPackageName().equalsIgnoreCase("com.alibaba.android.rimet")) {
                                            TrafficStats.clearThreadStatsTag();
                                        }
                                    } catch (Throwable unused14) {
                                    }
                                    return absolutePath;
                                } catch (UnknownHostException e7) {
                                    e = e7;
                                    i8 = i3;
                                    str3 = "com.alibaba.android.rimet";
                                    inputStream = null;
                                    fileOutputStream = null;
                                    i2 = 0;
                                    i = m38;
                                    String str522 = str3;
                                    int i922 = i8;
                                    UserTrackMethodJniBridge.addUtRecord("100168", 3, 0, "", 0L, e.getMessage(), str, "", "", "");
                                    if (inputStream != null) {
                                    }
                                    if (fileOutputStream != null) {
                                    }
                                    m37(m35);
                                    long currentTimeMillis422 = System.currentTimeMillis();
                                    if (C0020.m70()) {
                                    }
                                    if (Build.VERSION.SDK_INT >= 14) {
                                    }
                                    return null;
                                } catch (SSLHandshakeException e8) {
                                    e = e8;
                                    i8 = i3;
                                    j = currentTimeMillis;
                                    inputStream = null;
                                    fileOutputStream = null;
                                    i = m38;
                                    int i1022 = i8;
                                    long j322 = j;
                                    i2 = 0;
                                    UserTrackMethodJniBridge.addUtRecord("100168", 4, 0, "", 0L, e.getMessage(), str, "", "", "");
                                    if (inputStream != null) {
                                    }
                                    if (fileOutputStream != null) {
                                    }
                                    m37(m35);
                                    long currentTimeMillis622 = System.currentTimeMillis();
                                    if (C0020.m70()) {
                                    }
                                    if (Build.VERSION.SDK_INT >= 14) {
                                    }
                                    return null;
                                } catch (Throwable th10) {
                                    th = th10;
                                    i4 = m38;
                                    inputStream = null;
                                    fileOutputStream = null;
                                    i5 = i3;
                                    int i112 = i4;
                                    UserTrackMethodJniBridge.addUtRecord("100168", 5, 0, "", 0L, th.getMessage(), str, "", "", "");
                                    if (inputStream != null) {
                                    }
                                    if (fileOutputStream != null) {
                                    }
                                    m37(m35);
                                    long currentTimeMillis72 = System.currentTimeMillis();
                                    if (C0020.m70()) {
                                    }
                                    if (Build.VERSION.SDK_INT >= 14) {
                                    }
                                    return null;
                                }
                            } catch (UnknownHostException e9) {
                                e = e9;
                                inputStream = inputStream2;
                                str3 = "com.alibaba.android.rimet";
                                fileOutputStream = null;
                            } catch (SSLHandshakeException e10) {
                                e = e10;
                                inputStream = inputStream2;
                                j = currentTimeMillis;
                                fileOutputStream = null;
                            } catch (Throwable th11) {
                                th = th11;
                                i4 = m38;
                                inputStream = inputStream2;
                                fileOutputStream = null;
                            }
                        } catch (UnknownHostException e11) {
                            e = e11;
                        } catch (SSLHandshakeException e12) {
                            e = e12;
                        } catch (Throwable th12) {
                            th = th12;
                            i4 = m38;
                            inputStream = null;
                            fileOutputStream = null;
                        }
                    } catch (UnknownHostException e13) {
                        e = e13;
                        str3 = "com.alibaba.android.rimet";
                        inputStream = null;
                        fileOutputStream = null;
                        i2 = 0;
                        i = 0;
                    } catch (SSLHandshakeException e14) {
                        e = e14;
                        j = currentTimeMillis;
                        inputStream = null;
                        fileOutputStream = null;
                        i = 0;
                    }
                } catch (Throwable th13) {
                    th = th13;
                    inputStream = null;
                    fileOutputStream = null;
                    i3 = 0;
                    i4 = 0;
                }
            } catch (UnknownHostException e15) {
                e = e15;
                str3 = "com.alibaba.android.rimet";
                i2 = 0;
                i8 = 0;
                i = 0;
                inputStream = null;
                fileOutputStream = null;
            } catch (SSLHandshakeException e16) {
                e = e16;
                j = currentTimeMillis;
                i8 = 0;
                i = 0;
                inputStream = null;
                fileOutputStream = null;
            }
        } catch (Throwable unused15) {
        }
    }

    /* renamed from: а, reason: contains not printable characters */
    private static URLConnection m35(String str, int i, int i2, String str2) {
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
            i = HttpClientUtils.TIMEOUT_DEFAULT;
        }
        if (i2 <= 0) {
            i2 = HttpClientUtils.TIMEOUT_DEFAULT;
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
        uRLConnection.setUseCaches(false);
        if ("POST".equals(str2)) {
            uRLConnection.setRequestProperty("Content-Type", "text/plain;charset=UTF-8");
            uRLConnection.setDoOutput(true);
        }
        uRLConnection.setRequestProperty("Accept-Charset", "UTF-8");
        return uRLConnection;
    }

    /* renamed from: а, reason: contains not printable characters */
    public static void m36(Context context) {
        if (context != null) {
            f29 = context;
        }
    }

    /* renamed from: а, reason: contains not printable characters */
    private static void m37(URLConnection uRLConnection) {
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

    /* renamed from: б, reason: contains not printable characters */
    private static int m38(URLConnection uRLConnection) throws IOException {
        return uRLConnection instanceof HttpsURLConnection ? ((HttpsURLConnection) uRLConnection).getResponseCode() : ((HttpURLConnection) uRLConnection).getResponseCode();
    }
}
