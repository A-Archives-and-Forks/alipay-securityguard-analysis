package com.alibaba.wireless.security.middletierplugin.open.fc.strategy;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public class StrategyCenter {
    private static HashMap<Long, com.alibaba.wireless.security.middletierplugin.open.fc.strategy.c> g = null;
    private static Object h = new Object();
    private static Handler i = null;
    static volatile int j = 0;
    static volatile int k = 0;
    private static boolean l = false;
    private Context a;
    private com.alibaba.wireless.security.middletierplugin.open.fc.strategy.d b;
    private String c = "key_login_module";
    private volatile int d = 0;
    private volatile long e;
    private volatile int f;

    class a implements Runnable {
        a(StrategyCenter strategyCenter) {
        }

        @Override // java.lang.Runnable
        public void run() {
            int d = com.alibaba.wireless.security.middletierplugin.open.fc.a.d();
            if ((d != 6 && d != 7) || StrategyCenter.j >= 10) {
                com.alibaba.wireless.security.middletierplugin.open.fc.a.a();
            } else {
                StrategyCenter.i.postDelayed(this, 30000L);
                StrategyCenter.j++;
            }
        }
    }

    class b implements Runnable {
        final /* synthetic */ long a;

        b(long j) {
            this.a = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                int c = com.alibaba.wireless.security.middletierplugin.open.fc.a.c();
                if (c == 2) {
                    StrategyCenter.i.postDelayed(this, 3000L);
                } else {
                    String str = "[StrategyCenter][processFCContent] start activity-timeout-check: backgroudState=" + c + " app not in background we start check now";
                    StrategyCenter.this.b.clearTimeoutItem(this.a);
                }
            } catch (Exception unused) {
            }
        }
    }

    class c implements Runnable {
        final /* synthetic */ long a;
        final /* synthetic */ boolean b;
        final /* synthetic */ String c;
        final /* synthetic */ boolean d;
        final /* synthetic */ int e;

        c(StrategyCenter strategyCenter, long j, boolean z, String str, boolean z2, int i) {
            this.a = j;
            this.b = z;
            this.c = str;
            this.d = z2;
            this.e = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (StrategyCenter.h) {
                String str = "[StrategyCenter][processFCContent] api-timeout-thread: check enter and sessionId=" + this.a;
                if (StrategyCenter.g != null && StrategyCenter.g.containsKey(Long.valueOf(this.a))) {
                    com.alibaba.wireless.security.middletierplugin.open.fc.a.a(this.b, 4, this.c);
                    StrategyCenter.b(this.a, 8, this.d, null);
                    com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100155, 0, 0L, "", "Apitimeout", "" + this.e, "" + this.a, this.c, this.b ? 7 : 1, false);
                    String str2 = "[StrategyCenter][processFCContent] api-timeout-thread: SesseionID: " + this.a + "   BXUserReport: Apitimeout=";
                }
                com.alibaba.wireless.security.middletierplugin.open.fc.a.a(this.b, 0, this.c);
                int d = com.alibaba.wireless.security.middletierplugin.open.fc.a.d();
                if ((d == 6 || d == 7) && StrategyCenter.k < 10) {
                    StrategyCenter.i.postDelayed(this, 3000L);
                    StrategyCenter.k++;
                }
            }
        }
    }

    static class d implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ long b;
        final /* synthetic */ boolean c;
        final /* synthetic */ HashMap d;

        d(int i, long j, boolean z, HashMap hashMap) {
            this.a = i;
            this.b = j;
            this.c = z;
            this.d = hashMap;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (StrategyCenter.h) {
                try {
                    if (this.a == 1) {
                        if (Build.VERSION.SDK_INT < 21) {
                            CookieSyncManager.getInstance().sync();
                        } else {
                            CookieManager.getInstance().flush();
                        }
                    }
                } catch (Exception e) {
                    String str = "[StrategyCenter][processStrategyList] CookieManager : " + e.getMessage();
                }
                StrategyCenter.b(this.b, this.a, this.c, this.d);
            }
        }
    }

    public StrategyCenter(Context context, HashMap hashMap) {
        this.a = null;
        this.b = null;
        this.e = 0L;
        this.f = 0;
        this.a = context;
        try {
            g = new HashMap<>();
            this.b = com.alibaba.wireless.security.middletierplugin.open.fc.strategy.d.a(context.getApplicationContext());
            HandlerThread handlerThread = new HandlerThread("FC-StrategyCenter");
            handlerThread.start();
            i = new Handler(handlerThread.getLooper());
            if (hashMap != null && hashMap.containsKey(this.c)) {
                com.alibaba.wireless.security.middletierplugin.open.fc.c.a(((Boolean) hashMap.get(this.c)).booleanValue());
            }
            i.postDelayed(new a(this), 10000L);
        } catch (Exception e) {
            this.f = 2303;
            String message = e.getMessage();
            StringBuilder sb = new StringBuilder();
            sb.append("mUIBrige=");
            sb.append(this.b == null ? "0" : "1");
            sb.append(",mHandler=");
            sb.append(i != null ? "1" : "0");
            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100104, 0, 7L, message, "Strategycenter", sb.toString(), com.alibaba.wireless.security.middletierplugin.open.fc.c.a() ? "true" : "false", "", 1, false);
        }
        this.e = System.currentTimeMillis();
    }

    static void a(long j2, int i2, boolean z, HashMap hashMap) {
        d dVar = new d(i2, j2, z, hashMap);
        Handler handler = i;
        if (handler != null) {
            handler.post(dVar);
        }
    }

    static void b(long j2, int i2, boolean z, HashMap hashMap) {
        StringBuilder sb;
        com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a aVar;
        try {
            String str = "[StrategyCenter][processStrategyListLocked] enter sessionid=" + j2 + " and uiResult=" + i2 + " processAll=" + z + " extraInfo=" + hashMap;
            int i3 = 100174;
            boolean z2 = true;
            if (!z) {
                com.alibaba.wireless.security.middletierplugin.open.fc.strategy.c cVar = g.get(Long.valueOf(j2));
                if (cVar != null && !cVar.a(true)) {
                    String str2 = "[StrategyCenter][processStrategyListLocked] find the sessionId=" + j2 + " and process single one";
                    com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a aVar2 = com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a.FAIL;
                    if (i2 == 1) {
                        com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100174);
                        aVar = cVar.d();
                    } else if (i2 == 4) {
                        com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100175);
                        aVar = com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a.CANCEL;
                    } else if (i2 == 8) {
                        com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100177);
                        aVar = com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a.TIMEOUT;
                    } else {
                        com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100176);
                        aVar = com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a.FAIL;
                    }
                    if (hashMap != null) {
                        cVar.f().put("result_extra", hashMap);
                    }
                    String str3 = "[StrategyCenter][processStrategyListLocked] onStrategy3 sessionId=" + j2 + " mAction=" + cVar.d() + " subAction=" + cVar.e() + " extra=" + cVar.f();
                    cVar.i().onStrategy(j2, aVar, cVar.e(), cVar.f());
                    if (cVar.q()) {
                        com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100157, 0, 0L, "", "Processstrategylistlockedonatrategysingleself", "" + aVar.a() + "|" + cVar.e() + "|" + (cVar.f() == null ? "" : cVar.f().toString()), "" + j2, cVar.b(), cVar.o() ? 7 : 1, true);
                        String str4 = "[StrategyCenter][processStrategyListLocked] SesseionID: " + j2 + "   BXUserReport: Processstrategylistlockedonatrategysingleself";
                    }
                    com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100157);
                    g.remove(Long.valueOf(j2));
                    return;
                }
                sb = new StringBuilder();
                sb.append("[StrategyCenter][processStrategyListLocked] Error: not find the sessionId=");
                sb.append(j2);
                sb.append(" to process single one");
            } else {
                if (g.get(Long.valueOf(j2)) != null) {
                    String str5 = "[StrategyCenter][processStrategyListLocked] find the sessionId=" + j2 + " and process all session";
                    for (Long l2 : g.keySet()) {
                        com.alibaba.wireless.security.middletierplugin.open.fc.strategy.c cVar2 = g.get(l2);
                        String str6 = "[StrategyCenter][processStrategyListLocked] key=" + l2;
                        if (!cVar2.a(z2)) {
                            com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a aVar3 = com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a.FAIL;
                            if (i2 == z2) {
                                com.alibaba.wireless.security.middletierplugin.open.fc.d.a(i3);
                                aVar3 = cVar2.d();
                            } else if (i2 == 4) {
                                com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100175);
                                aVar3 = com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a.CANCEL;
                            } else if (i2 == 8) {
                                com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100177);
                                aVar3 = com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a.TIMEOUT;
                            } else {
                                com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100176);
                            }
                            if (hashMap != null) {
                                cVar2.f().put("result_extra", hashMap);
                            }
                            if (i2 != z2) {
                                com.alibaba.wireless.security.middletierplugin.open.fc.c.a(cVar2);
                            }
                            String str7 = "[StrategyCenter][processStrategyListLocked] onStrategy1 key=" + l2 + " mAction=" + aVar3 + " item=" + cVar2.e() + " extra=" + cVar2.f();
                            cVar2.i().onStrategy(l2.longValue(), aVar3, cVar2.e(), cVar2.f());
                            if (cVar2.q()) {
                                String hashMap2 = cVar2.f() == null ? "" : cVar2.f().toString();
                                com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100157, 0, 0L, "", l2.longValue() == j2 ? "Processstrategylistlockedonstrategyallself" : "Processstrategylistlockedonstrategyallother", "" + aVar3.a() + "|" + cVar2.e() + "|" + hashMap2, "" + j2, cVar2.b(), cVar2.o() ? 7 : 1, true);
                                String str8 = "[StrategyCenter][processStrategyListLocked] SesseionID: " + j2 + "   BXUserReport: Processstrategylistlockedonstrategyallself";
                            }
                            com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100157);
                        }
                        z2 = true;
                        i3 = 100174;
                    }
                    g.clear();
                    return;
                }
                com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100108);
                sb = new StringBuilder();
                sb.append("[StrategyCenter][processStrategyListLocked] Error: not find the sessionId=");
                sb.append(j2);
                sb.append(" to process all");
            }
            sb.toString();
        } catch (Exception e) {
            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100108, 2303, 0L, "" + e.getMessage(), "Processstrategylistlockedexception", null, "" + j2, null, 3, false);
            String str9 = "[StrategyCenter][processStrategyListLocked] Exception SesseionID: " + j2 + "   BXUserReport: Processstrategylistlockedexception";
            String str10 = "[StrategyCenter][processStrategyListLocked] Exception=" + e.getMessage();
            com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100108);
            g.clear();
        }
    }

    public boolean needFCProcessOrNot(int i2, HashMap hashMap) {
        boolean z = i2 == com.alibaba.wireless.security.middletierplugin.open.fc.b.NC.a() || i2 == com.alibaba.wireless.security.middletierplugin.open.fc.b.FL.a() || i2 == com.alibaba.wireless.security.middletierplugin.open.fc.b.SG.a();
        if (!l) {
            com.alibaba.wireless.security.middletierplugin.a.a("firstBizRequest");
            l = true;
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002b, code lost:
    
        if (r5 == com.alibaba.wireless.security.middletierplugin.open.fc.b.SG.a()) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean needFCProcessOrNotV2(int i2, String str) {
        boolean z = false;
        if (str != null && str.length() > 0) {
            try {
                int i3 = new JSONObject(str).getInt("httpCode");
                if (i3 != com.alibaba.wireless.security.middletierplugin.open.fc.b.NC.a() && i3 != com.alibaba.wireless.security.middletierplugin.open.fc.b.FL.a()) {
                }
                z = true;
            } catch (Exception e) {
                String str2 = "needFCProcessOrNot Exception: " + e.getMessage();
            }
        }
        if (!l) {
            com.alibaba.wireless.security.middletierplugin.a.a("firstBizRequest");
            l = true;
        }
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:185:0x00c8, code lost:
    
        if (com.alibaba.wireless.security.middletierplugin.open.fc.c.a() == false) goto L29;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x03ff  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x03ae  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x03b1  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0391 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void processFCContent(int i2, HashMap hashMap, IStrategyCallback iStrategyCallback, int i3) {
        String str;
        String str2;
        com.alibaba.wireless.security.middletierplugin.open.fc.strategy.c cVar;
        String str3;
        String str4;
        String str5;
        long j2;
        com.alibaba.wireless.security.middletierplugin.open.fc.strategy.c cVar2;
        boolean z;
        String str6;
        com.alibaba.wireless.security.middletierplugin.open.fc.strategy.c cVar3;
        boolean z2;
        String str7;
        boolean z3;
        String b2;
        String str8;
        long currentTimeMillis = System.currentTimeMillis();
        boolean a2 = com.alibaba.wireless.security.middletierplugin.open.fc.a.a(i3, hashMap);
        if (this.d == 0) {
            synchronized (StrategyCenter.class) {
                if (this.d == 0) {
                    int i4 = this.f;
                    str7 = "";
                    com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100152, i4, 0L, "", "ProcessfccontentStrategycenter", com.alibaba.wireless.security.middletierplugin.open.fc.c.a() ? "true" : "false", "" + this.e, com.alibaba.wireless.security.middletierplugin.open.fc.c.a(i3, hashMap, null, "bx-uuid", "uuid="), 1, false);
                    this.d = 1;
                }
            }
        }
        String str9 = "Processfccontent";
        String str10 = "";
        try {
            String str11 = "[StrategyCenter][processFCContent] enter content=" + hashMap;
            cVar = new com.alibaba.wireless.security.middletierplugin.open.fc.strategy.c(i2, hashMap, iStrategyCallback, i3);
            str9 = "Processfccontent|Strategyitem";
            str3 = "" + cVar.r();
            try {
                str4 = "" + cVar.h();
                try {
                } catch (Exception unused) {
                    str10 = str4;
                }
            } catch (Exception unused2) {
            }
        } catch (Exception unused3) {
            str = "";
            str2 = "";
        }
        try {
            try {
                try {
                    try {
                        if (cVar.n()) {
                            try {
                                if (cVar.m()) {
                                    try {
                                    } catch (Exception unused4) {
                                        str10 = str4;
                                        str2 = "";
                                        str = str3;
                                        com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100106, 0, System.currentTimeMillis() - currentTimeMillis, "", str9, str, str10, str2, a2 ? 7 : 1, false);
                                        String str12 = "[StrategyCenter][processFCContent] SesseionID: " + str10 + "   BXUserReport: " + str9;
                                        return;
                                    }
                                }
                                str9 = str9 + "|Showui";
                                if (iStrategyCallback != null) {
                                    String str13 = str9 + "|Onprestrategybegin" + System.currentTimeMillis();
                                    iStrategyCallback.onPreStrategy(cVar.h(), true);
                                    str9 = str13 + "|Onprestrategyend" + System.currentTimeMillis();
                                }
                                long h2 = cVar.h();
                                try {
                                    if (g != null) {
                                        try {
                                            synchronized (h) {
                                                try {
                                                    if (com.alibaba.wireless.security.middletierplugin.open.fc.c.a(cVar, g.size() >= 1)) {
                                                        g.put(Long.valueOf(h2), cVar);
                                                        try {
                                                            if (g.size() == 1) {
                                                                this.b.a(this.a, cVar);
                                                                b bVar = new b(h2);
                                                                if (i != null) {
                                                                    str7 = str4;
                                                                    i.postDelayed(bVar, 3000L);
                                                                } else {
                                                                    str7 = str4;
                                                                }
                                                                str9 = str9 + "|Startactivity";
                                                            } else {
                                                                str7 = str4;
                                                                String str14 = "[StrategyCenter][processFCContent] mStrategyItemList.size() != 1 equal=" + g.size();
                                                                if (!cVar.p()) {
                                                                    str9 = str9 + "|Removeitem";
                                                                    g.remove(Long.valueOf(h2));
                                                                    z3 = false;
                                                                    if (z3 || cVar.a() <= 0) {
                                                                        j2 = h2;
                                                                        str5 = str7;
                                                                        cVar2 = cVar;
                                                                        z2 = z3;
                                                                    } else {
                                                                        String str15 = str9 + "|Startapitimeoutmonitor";
                                                                        try {
                                                                            boolean z4 = g.size() == 1;
                                                                            String b3 = cVar.b();
                                                                            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(a2, 2, cVar.b());
                                                                            j2 = h2;
                                                                            str5 = str7;
                                                                            cVar2 = cVar;
                                                                            try {
                                                                                c cVar4 = new c(this, j2, a2, b3, z4, i2);
                                                                                if (i != null) {
                                                                                    i.postDelayed(cVar4, cVar2.a());
                                                                                    String str16 = "[StrategyCenter][processFCContent] timeoutRunable postDelay=" + cVar2.a();
                                                                                }
                                                                                z2 = z3;
                                                                                str9 = str15;
                                                                            } catch (Throwable th) {
                                                                                th = th;
                                                                                throw th;
                                                                            }
                                                                        } catch (Throwable th2) {
                                                                            th = th2;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            z3 = true;
                                                            if (z3) {
                                                            }
                                                            j2 = h2;
                                                            str5 = str7;
                                                            cVar2 = cVar;
                                                            z2 = z3;
                                                        } catch (Throwable th3) {
                                                            th = th3;
                                                        }
                                                    } else {
                                                        j2 = h2;
                                                        str5 = str4;
                                                        cVar2 = cVar;
                                                        z2 = false;
                                                    }
                                                    z = z2;
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                }
                                            }
                                        } catch (Throwable th5) {
                                            th = th5;
                                        }
                                    } else {
                                        j2 = h2;
                                        str5 = str4;
                                        cVar2 = cVar;
                                        z = false;
                                    }
                                } catch (Exception unused5) {
                                }
                            } catch (Exception unused6) {
                                str5 = str4;
                            }
                            try {
                                if (z && cVar2.p()) {
                                    cVar3 = cVar2;
                                    b2 = cVar3.b();
                                    if (!com.alibaba.wireless.security.middletierplugin.open.fc.a.a(i3, hashMap)) {
                                        try {
                                            if (!cVar3.q()) {
                                                return;
                                            }
                                        } catch (Exception unused7) {
                                            str2 = b2;
                                            str = str3;
                                            str10 = str5;
                                            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100106, 0, System.currentTimeMillis() - currentTimeMillis, "", str9, str, str10, str2, a2 ? 7 : 1, false);
                                            String str122 = "[StrategyCenter][processFCContent] SesseionID: " + str10 + "   BXUserReport: " + str9;
                                            return;
                                        }
                                    }
                                    com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100155, 0, System.currentTimeMillis() - currentTimeMillis, "", str9, str3, str5, b2, a2 ? 7 : 1, false);
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("[StrategyCenter][processFCContent] SesseionID: ");
                                    str10 = str5;
                                    sb.append(str10);
                                    sb.append("   BXUserReport: ");
                                    sb.append(str9);
                                    sb.toString();
                                    return;
                                }
                                if (cVar3.a(true) || iStrategyCallback == null) {
                                    str9 = str6;
                                } else {
                                    String str17 = "[StrategyCenter][processFCContent] Faildirect and sessionId=" + cVar3.h() + " mainAction=" + cVar3.d() + " subAction=" + cVar3.e();
                                    iStrategyCallback.onStrategy(j2, com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a.FAIL, cVar3.e(), cVar3.f());
                                    str9 = str6 + "Onstrategy";
                                }
                                b2 = cVar3.b();
                                if (!com.alibaba.wireless.security.middletierplugin.open.fc.a.a(i3, hashMap)) {
                                }
                                com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100155, 0, System.currentTimeMillis() - currentTimeMillis, "", str9, str3, str5, b2, a2 ? 7 : 1, false);
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("[StrategyCenter][processFCContent] SesseionID: ");
                                str10 = str5;
                                sb2.append(str10);
                                sb2.append("   BXUserReport: ");
                                sb2.append(str9);
                                sb2.toString();
                                return;
                            } catch (Exception unused8) {
                                str9 = str6;
                                str2 = "";
                                str = str3;
                                str10 = str5;
                                com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100106, 0, System.currentTimeMillis() - currentTimeMillis, "", str9, str, str10, str2, a2 ? 7 : 1, false);
                                String str1222 = "[StrategyCenter][processFCContent] SesseionID: " + str10 + "   BXUserReport: " + str9;
                                return;
                            }
                            str6 = str9 + "Faildirect";
                            cVar3 = cVar2;
                        }
                        sb2.append(str10);
                        sb2.append("   BXUserReport: ");
                        sb2.append(str9);
                        sb2.toString();
                        return;
                    } catch (Exception unused9) {
                        str2 = b2;
                        str = str3;
                        com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100106, 0, System.currentTimeMillis() - currentTimeMillis, "", str9, str, str10, str2, a2 ? 7 : 1, false);
                        String str12222 = "[StrategyCenter][processFCContent] SesseionID: " + str10 + "   BXUserReport: " + str9;
                        return;
                    }
                    if (!com.alibaba.wireless.security.middletierplugin.open.fc.a.a(i3, hashMap)) {
                    }
                    com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100155, 0, System.currentTimeMillis() - currentTimeMillis, "", str9, str3, str5, b2, a2 ? 7 : 1, false);
                    StringBuilder sb22 = new StringBuilder();
                    sb22.append("[StrategyCenter][processFCContent] SesseionID: ");
                    str10 = str5;
                } catch (Exception unused10) {
                    str10 = str5;
                }
                if (!cVar3.a(true) && iStrategyCallback != null) {
                    try {
                        String str18 = "[StrategyCenter][processFCContent] not bxui directcallback and sessionId=" + cVar3.h() + " mainAction=" + cVar3.d() + " subAction=" + cVar3.e();
                        str9 = str9 + "Onprestrategybegin" + System.currentTimeMillis();
                        iStrategyCallback.onPreStrategy(cVar3.h(), false);
                        String str19 = str9 + "Onprestrategyend" + System.currentTimeMillis();
                        try {
                            str8 = str19;
                            try {
                                iStrategyCallback.onStrategy(cVar3.h(), cVar3.d(), cVar3.e(), cVar3.f());
                                str9 = str8 + "Onstrategy";
                            } catch (Exception unused11) {
                                str9 = str8;
                                str2 = "";
                                str = str3;
                                str10 = str5;
                                com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100106, 0, System.currentTimeMillis() - currentTimeMillis, "", str9, str, str10, str2, a2 ? 7 : 1, false);
                                String str122222 = "[StrategyCenter][processFCContent] SesseionID: " + str10 + "   BXUserReport: " + str9;
                                return;
                            }
                        } catch (Exception unused12) {
                            str8 = str19;
                        }
                    } catch (Exception unused13) {
                    }
                }
                b2 = cVar3.b();
            } catch (Exception unused14) {
                str10 = str5;
                str2 = "";
                str = str3;
                com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100106, 0, System.currentTimeMillis() - currentTimeMillis, "", str9, str, str10, str2, a2 ? 7 : 1, false);
                String str1222222 = "[StrategyCenter][processFCContent] SesseionID: " + str10 + "   BXUserReport: " + str9;
                return;
            }
            str9 = str9 + "Directcallback";
        } catch (Exception unused15) {
            str10 = str5;
            str2 = "";
            str = str3;
            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100106, 0, System.currentTimeMillis() - currentTimeMillis, "", str9, str, str10, str2, a2 ? 7 : 1, false);
            String str12222222 = "[StrategyCenter][processFCContent] SesseionID: " + str10 + "   BXUserReport: " + str9;
            return;
        }
        str5 = str4;
        cVar3 = cVar;
    }

    public void processFCContentV2(String str, IStrategyCallback iStrategyCallback) {
        if (str == null || str.length() <= 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int i2 = jSONObject.getInt("httpCode");
            String string = jSONObject.getString("header");
            if (string != null) {
                processFCContent(i2, com.alibaba.wireless.security.middletierplugin.open.fc.c.a(new JSONObject(string)), iStrategyCallback, 0);
            }
        } catch (Exception e) {
            String str2 = "[StrategyCenter][processFCContentV2] Exception: " + e.getMessage();
        }
    }
}
