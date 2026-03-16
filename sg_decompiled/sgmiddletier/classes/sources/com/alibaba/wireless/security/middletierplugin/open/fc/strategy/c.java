package com.alibaba.wireless.security.middletierplugin.open.fc.strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public class c {
    private static Random w = new Random();
    private int a;
    private long e;
    private IStrategyCallback h;
    private HashMap l;
    private long b = 0;
    private boolean c = false;
    private boolean d = false;
    private String f = "";
    private long g = 10000;
    private boolean i = false;
    private a j = a.FAIL;
    private long k = 0;
    private String m = null;
    private AtomicBoolean n = new AtomicBoolean(false);
    private int o = 0;
    private String p = "";
    private boolean q = false;
    private boolean r = false;
    private boolean s = false;
    private float t = 1.0f;
    private String u = "";
    private int v = 0;

    public c(int i, HashMap hashMap, IStrategyCallback iStrategyCallback, int i2) {
        this.a = -1;
        this.e = 0L;
        this.h = null;
        this.l = null;
        this.a = i;
        this.l = new HashMap();
        this.h = iStrategyCallback;
        try {
            this.e = w.nextLong();
            String str = "[StrategyItem][Init] content : " + hashMap.toString();
            a(hashMap, i2);
        } catch (Exception e) {
            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100106, 2303, 0L, "" + e.getMessage(), "Strategyitem", null, "" + this.e, com.alibaba.wireless.security.middletierplugin.open.fc.c.a(i2, hashMap, this.f, "bx-uuid", "uuid="), (com.alibaba.wireless.security.middletierplugin.open.fc.a.a(i2, hashMap) ? 6 : 0) + 1, false);
            String str2 = "[StrategyItem][Init] SesseionID: " + this.e + "   BXUserReport: Strategyitem exception = " + e.getMessage();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void a(HashMap hashMap, int i) {
        String str;
        boolean z;
        long j;
        Object obj;
        String str2;
        String str3;
        List list;
        String str4;
        if (this.a == com.alibaba.wireless.security.middletierplugin.open.fc.b.NC.a() || this.a == com.alibaba.wireless.security.middletierplugin.open.fc.b.FL.a() || this.a == com.alibaba.wireless.security.middletierplugin.open.fc.b.SG.a()) {
            if (hashMap.containsKey("bx-action")) {
                Object obj2 = hashMap.get("bx-action");
                if (i != 0) {
                    if (i == 1) {
                        obj2 = ((List) obj2).get(0);
                    }
                }
                str = (String) obj2;
                if ("sample".equals(str)) {
                    if (hashMap.containsKey("bx-log-ratio")) {
                        Object obj3 = hashMap.get("bx-log-ratio");
                        if (i == 0) {
                            j = this.e;
                        } else if (i == 1) {
                            j = this.e;
                            obj3 = ((List) obj3).get(0);
                        }
                        z = com.alibaba.wireless.security.middletierplugin.open.fc.c.a(j, (String) obj3);
                    } else {
                        z = this.e % ((long) com.alibaba.wireless.security.middletierplugin.open.fc.c.b) == 0;
                    }
                    this.q = z;
                } else {
                    this.q = true;
                    this.r = true;
                }
                if (hashMap.containsKey("bx-retry")) {
                    Object obj4 = hashMap.get("bx-retry");
                    if (i != 0) {
                        if (i == 1) {
                            obj4 = ((List) obj4).get(0);
                        }
                    }
                    this.i = "true".equals((String) obj4);
                }
                this.j = !this.i ? a.RETRY : a.FAIL;
                if (this.a == com.alibaba.wireless.security.middletierplugin.open.fc.b.FL.a()) {
                    this.k |= b.FL.a();
                }
                if (hashMap.containsKey("bx-subcode")) {
                    Object obj5 = hashMap.get("bx-subcode");
                    if (i != 0) {
                        if (i == 1) {
                            obj5 = ((List) obj5).get(0);
                        }
                        this.k |= this.b;
                    }
                    this.b = Long.parseLong((String) obj5);
                    this.k |= this.b;
                }
                this.c = "login".equalsIgnoreCase(str);
                if (this.c) {
                    this.k |= b.LOGIN.a();
                }
                if (hashMap.containsKey("bx-close-btn")) {
                    Object obj6 = hashMap.get("bx-close-btn");
                    if (i != 0) {
                        if (i == 1) {
                            obj6 = ((List) obj6).get(0);
                        }
                    }
                    this.s = "false".equals((String) obj6);
                }
                if (hashMap.containsKey("bx-show")) {
                    Object obj7 = hashMap.get("bx-show");
                    if (i != 0) {
                        if (i == 1) {
                            obj7 = ((List) obj7).get(0);
                        }
                    }
                    this.t = Float.parseFloat((String) obj7);
                }
                if (!hashMap.containsKey("Location") || hashMap.containsKey("location")) {
                    obj = hashMap.get("Location");
                    if (obj == null) {
                        obj = hashMap.get("location");
                    }
                    if (obj != null) {
                        if (i != 0) {
                            if (i == 1) {
                                obj = ((List) obj).get(0);
                            }
                        }
                        this.f = (String) obj;
                    }
                }
                str2 = this.f;
                if (str2 != null && !str2.isEmpty()) {
                    this.d = true;
                }
                if (hashMap.containsKey("bx-sleep")) {
                    Object obj8 = hashMap.get("bx-sleep");
                    if (i != 0) {
                        if (i == 1) {
                            obj8 = ((List) obj8).get(0);
                        }
                    }
                    this.g = Integer.parseInt((String) obj8);
                }
                this.l.put("bx-sleep", Long.valueOf(this.g));
                if (hashMap.containsKey("bx-view-timeout")) {
                    Object obj9 = hashMap.get("bx-view-timeout");
                    if (i != 0) {
                        if (i == 1) {
                            obj9 = ((List) obj9).get(0);
                        }
                    }
                    this.o = Integer.parseInt((String) obj9);
                }
                if (hashMap.containsKey("bx-view-interval")) {
                    Object obj10 = hashMap.get("bx-view-interval");
                    if (i != 0) {
                        if (i == 1) {
                            obj10 = ((List) obj10).get(0);
                        }
                    }
                    this.v = Integer.parseInt((String) obj10);
                }
                if (hashMap.containsKey("bx-extra-info")) {
                    String str5 = null;
                    Object obj11 = hashMap.get("bx-extra-info");
                    if (i == 0) {
                        str5 = (String) obj11;
                    } else if (i == 1) {
                        str5 = (String) ((List) obj11).get(0);
                    }
                    if (str5 != null) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("bx-extra-info", str5);
                            this.m = jSONObject.toString();
                        } catch (Exception e) {
                            String str6 = "[StrategyItem][parseContent] Exception: " + e.getMessage();
                        }
                    }
                }
                if (hashMap.containsKey("bx-download-config")) {
                    if (i == 0) {
                        str4 = String.valueOf(hashMap.get("bx-download-config"));
                    } else if (i == 1 && (list = (List) hashMap.get("bx-download-config")) != null && list.size() != 0) {
                        str4 = (String) list.get(0);
                    }
                    this.u = str4;
                }
                str3 = this.m;
                if (str3 != null) {
                    this.l.put("x-bx-resend", str3);
                }
            }
            str = "";
            if ("sample".equals(str)) {
            }
            if (hashMap.containsKey("bx-retry")) {
            }
            this.j = !this.i ? a.RETRY : a.FAIL;
            if (this.a == com.alibaba.wireless.security.middletierplugin.open.fc.b.FL.a()) {
            }
            if (hashMap.containsKey("bx-subcode")) {
            }
            this.c = "login".equalsIgnoreCase(str);
            if (this.c) {
            }
            if (hashMap.containsKey("bx-close-btn")) {
            }
            if (hashMap.containsKey("bx-show")) {
            }
            if (!hashMap.containsKey("Location")) {
            }
            obj = hashMap.get("Location");
            if (obj == null) {
            }
            if (obj != null) {
            }
            str2 = this.f;
            if (str2 != null) {
                this.d = true;
            }
            if (hashMap.containsKey("bx-sleep")) {
            }
            this.l.put("bx-sleep", Long.valueOf(this.g));
            if (hashMap.containsKey("bx-view-timeout")) {
            }
            if (hashMap.containsKey("bx-view-interval")) {
            }
            if (hashMap.containsKey("bx-extra-info")) {
            }
            if (hashMap.containsKey("bx-download-config")) {
            }
            str3 = this.m;
            if (str3 != null) {
            }
        } else {
            this.j = a.SUCCESS;
        }
        this.p = com.alibaba.wireless.security.middletierplugin.open.fc.c.a(i, hashMap, this.f, "bx-uuid", "uuid=");
    }

    public int a() {
        return this.o;
    }

    public boolean a(boolean z) {
        return this.n.getAndSet(z);
    }

    public String b() {
        return this.p;
    }

    public int c() {
        return this.v;
    }

    public a d() {
        return this.j;
    }

    public long e() {
        return this.k;
    }

    public HashMap f() {
        return this.l;
    }

    public String g() {
        return this.f;
    }

    public long h() {
        return this.e;
    }

    public IStrategyCallback i() {
        return this.h;
    }

    public float j() {
        return this.t;
    }

    public String k() {
        return this.u;
    }

    public boolean l() {
        return this.s;
    }

    public boolean m() {
        return this.c;
    }

    public boolean n() {
        return this.d;
    }

    public boolean o() {
        return this.r;
    }

    public boolean p() {
        return this.i;
    }

    public boolean q() {
        return this.q;
    }

    String r() {
        StringBuilder sb = new StringBuilder();
        sb.append("mBXLogin = ");
        sb.append(this.c);
        sb.append(", mBXUI = ");
        sb.append(this.d);
        sb.append(", mNeedRetry = ");
        sb.append(this.i);
        sb.append(", mBXMainCode = ");
        sb.append(this.a);
        sb.append(", mSessionId = ");
        sb.append(this.e);
        sb.append(", mLocation = ");
        sb.append(this.f.length() > 1000 ? this.f.substring(0, 1000) : this.f);
        sb.append(",  mExtraInfo = ");
        HashMap hashMap = this.l;
        sb.append(hashMap == null ? "" : hashMap.toString());
        sb.append(", mExpectSubAction = ");
        sb.append(this.k);
        sb.append(", mExpectMainAction = ");
        sb.append(this.j);
        sb.append(", mApiTimeout = ");
        sb.append(this.o);
        sb.append(", mBXUUID = ");
        sb.append(this.p);
        sb.append(", mHideCloseBtn = ");
        sb.append(this.s);
        return sb.toString();
    }
}
