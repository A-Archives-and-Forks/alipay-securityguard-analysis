package com.alibaba.wireless.security.middletierplugin.open.fc.strategy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.alibaba.wireless.security.open.middletier.fc.ui.ContainerActivity;
import com.alibaba.wireless.security.open.middletier.fc.ui.IUIBridge;
import java.util.HashMap;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public class d extends BroadcastReceiver implements IUIBridge {
    private static d b;
    private HashMap a = new HashMap();

    class a implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;

        a(d dVar, String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            String str = "==============content================" + this.a;
            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(this.a, this.b);
        }
    }

    private d() {
    }

    public static d a(Context context) {
        d dVar;
        if (b == null) {
            synchronized (d.class) {
                if (b == null) {
                    b = new d();
                    IntentFilter intentFilter = new IntentFilter("com.alibaba.wireless.security.open.middletier.fc.ui.BXIntentCreate4323");
                    intentFilter.addAction("com.alibaba.wireless.security.open.middletier.fc.ui.BXIntentResult4323");
                    intentFilter.addAction("com.alibaba.wireless.security.open.middletier.fc.ui.BXIntentLog4323");
                    try {
                        Class<?> cls = Class.forName("com.alibaba.wireless.security.open.middletier.fc.ui.LocalBroadcastManager");
                        cls.getMethod("registerReceiver", BroadcastReceiver.class, IntentFilter.class).invoke(cls.getMethod("getInstance", Context.class).invoke(null, context), b, intentFilter);
                    } catch (Throwable unused) {
                        try {
                            Class<?> cls2 = Class.forName("android.support.v4.content.LocalBroadcastManager");
                            cls2.getMethod("registerReceiver", BroadcastReceiver.class, IntentFilter.class).invoke(cls2.getMethod("getInstance", Context.class).invoke(null, context), b, intentFilter);
                            dVar = b;
                        } catch (Throwable unused2) {
                            dVar = b;
                        }
                        context.registerReceiver(dVar, intentFilter);
                    }
                }
            }
        }
        return b;
    }

    private void a(boolean z, long j) {
        c cVar = (c) this.a.get("" + j);
        com.alibaba.wireless.security.middletierplugin.open.fc.a.a(z, 0, cVar != null ? cVar.b() : null);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void a(Context context, c cVar) {
        String str;
        String str2;
        Intent intent;
        try {
            float j = cVar.j();
            if (j <= 0.0f || j >= 1.0f) {
                intent = new Intent(context, (Class<?>) ContainerActivity.class);
            } else {
                try {
                    intent = new Intent(context, Class.forName("com.alibaba.wireless.security.open.middletier.fc.ui.ExtContainerActivity"));
                } catch (Throwable unused) {
                    intent = new Intent(context, (Class<?>) ContainerActivity.class);
                }
            }
            intent.putExtra("location", cVar.g());
            intent.putExtra("sessionId", cVar.h());
            intent.putExtra("bxUUID", cVar.b());
            intent.putExtra("needUT", cVar.q());
            intent.putExtra("hideCloseBtn", cVar.l());
            intent.putExtra("isSample", cVar.o());
            intent.putExtra("apiTimeOut", cVar.a());
            intent.putExtra("downloadConfig", cVar.k());
            intent.putExtra("windowHeight", cVar.j());
            intent.setFlags(268435456);
        } catch (Exception e) {
            e = e;
            boolean z = false;
            if (cVar == null) {
                String str3 = "" + cVar.h();
                str2 = cVar.b();
                str = str3;
                z = cVar.o();
            } else {
                str = "";
                str2 = str;
            }
            e.printStackTrace();
            e.getMessage();
            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100107, 2303, 0L, e.getMessage(), "Startcontaineractivity", "", str, str2, !z ? 7 : 1, false);
            StrategyCenter.a(cVar.h(), 2, true, null);
            com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100107);
        }
        try {
            addUIItem(cVar.h(), cVar);
            context.startActivity(intent);
            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(cVar.o(), 1, cVar.b());
            com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100156);
        } catch (Exception e2) {
            e = e2;
            boolean z2 = false;
            if (cVar == null) {
            }
            e.printStackTrace();
            e.getMessage();
            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100107, 2303, 0L, e.getMessage(), "Startcontaineractivity", "", str, str2, !z2 ? 7 : 1, false);
            StrategyCenter.a(cVar.h(), 2, true, null);
            com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100107);
        }
    }

    public void addUIItem(long j, Object obj) {
        this.a.put("" + j, obj);
    }

    public void clearTimeoutItem(long j) {
        String str = "" + j;
        if (this.a.containsKey(str)) {
            String str2 = "[UIBridge][clearTimeoutItem] start activity-timeout: SessionID: " + j + " not start the activity !!!";
            c cVar = (c) this.a.get(str);
            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100156, 0, 0L, "", "Clearactivitystarttimeoutitem", "", "" + j, cVar.b(), cVar.o() ? 7 : 1, false);
            String str3 = "[UIBridge][clearTimeoutItem] start activity-timeout: SessionID: " + j + "   BXUserReport: Clearactivitystarttimeoutitem";
            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(cVar.o(), 3, cVar.b());
            this.a.clear();
            StrategyCenter.a(j, 2, true, null);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        StringBuilder sb;
        String str;
        String str2 = "[UIBridge][onReceive] SessionID: " + intent.getLongExtra("BXExtraInfoSession4323", 0L) + "BXUserReport: onReceive BRCAST : " + intent.getAction();
        if (intent != null) {
            String action = intent.getAction();
            boolean booleanExtra = intent.getBooleanExtra("BXExtraInfoUIIsSample4323", false);
            String stringExtra = intent.getStringExtra("BXExtraInfoUILog4323");
            if (stringExtra != null) {
                com.alibaba.wireless.security.middletierplugin.open.fc.a.b(stringExtra);
            }
            String str3 = "[UIBridge] @@@@@ isSample=" + booleanExtra + " and intent=" + intent.toString();
            if ("com.alibaba.wireless.security.open.middletier.fc.ui.BXIntentCreate4323".equals(action)) {
                long longExtra = intent.getLongExtra("BXExtraInfoSession4323", 0L);
                removeUIItem(longExtra);
                a(booleanExtra, longExtra);
                sb = new StringBuilder();
                sb.append("[UIBridge][onReceive] SessionID: ");
                sb.append(intent.getLongExtra("BXExtraInfoSession4323", 0L));
                str = " create";
            } else {
                if (!"com.alibaba.wireless.security.open.middletier.fc.ui.BXIntentResult4323".equals(action)) {
                    if ("com.alibaba.wireless.security.open.middletier.fc.ui.BXIntentLog4323".equals(action)) {
                        String stringExtra2 = intent.getStringExtra("BXExtraInfoUILog4323");
                        int intExtra = intent.getIntExtra("BXExtraInfoUILogWay4323", 0);
                        if ((intExtra & 2) == 2) {
                            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(stringExtra2);
                        }
                        String str4 = "[UIBridge][onReceive] SessionID: " + intent.getLongExtra("BXExtraInfoSession4323", 0L) + " send log";
                        if ((intExtra & 4) == 4 && com.alibaba.wireless.security.middletierplugin.open.fc.a.c != null) {
                            com.alibaba.wireless.security.middletierplugin.open.fc.a.c.post(new a(this, stringExtra2, intent.getBooleanExtra("BXExtraInfoUILogSend4323", false)));
                        }
                        String stringExtra3 = intent.getStringExtra("BXExtraInfoUIInfo4323");
                        String str5 = "[UIBridge][onReceive] sendlog =" + stringExtra3;
                        if (stringExtra3 != null) {
                            String[] split = stringExtra3.split("&");
                            if (split == null || split.length < 2) {
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append(" list length=");
                                sb2.append(split != null ? split.length : 0);
                                sb2.toString();
                                return;
                            }
                            int parseInt = Integer.parseInt(split[0]);
                            String str6 = split[1];
                            String str7 = " status=" + parseInt + " bxuuid=" + str6;
                            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(booleanExtra, parseInt, str6);
                            return;
                        }
                        return;
                    }
                    return;
                }
                long longExtra2 = intent.getLongExtra("BXExtraInfoSession4323", 0L);
                StrategyCenter.a(longExtra2, intent.getIntExtra("BXExtraInfoUIResult4323", 0), true, null);
                a(booleanExtra, longExtra2);
                sb = new StringBuilder();
                sb.append("[UIBridge][onReceive] SessionID: ");
                sb.append(intent.getLongExtra("BXExtraInfoSession4323", 0L));
                str = " activity_result";
            }
            sb.append(str);
            sb.toString();
        }
    }

    public void removeUIItem(long j) {
        String str = "[UIBridge][removeUIItem] SessionID: " + j + "   BXUserReport: removeactivitystartitem";
        if (this.a.containsKey("" + j)) {
            this.a.clear();
        }
    }
}
