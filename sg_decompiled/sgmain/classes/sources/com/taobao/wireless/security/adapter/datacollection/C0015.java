package com.taobao.wireless.security.adapter.datacollection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Process;
import android.util.Log;
import com.alibaba.wireless.security.framework.utils.UserTrackMethodJniBridge;
import com.alibaba.wireless.security.open.SecurityGuardManager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.taobao.wireless.security.adapter.datacollection.в, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0015 {

    /* renamed from: а, reason: contains not printable characters */
    static List<String> f46 = new ArrayList(15);

    /* renamed from: б, reason: contains not printable characters */
    static List<C0017> f47 = new ArrayList(10);

    /* renamed from: com.taobao.wireless.security.adapter.datacollection.в$а, reason: contains not printable characters */
    static class C0016 extends BroadcastReceiver {
        C0016() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                String action = intent.getAction();
                synchronized (C0015.class) {
                    C0015.f47.add(new C0017(C0015.f46.indexOf(action), System.currentTimeMillis()));
                    if (C0015.f47.size() >= 10) {
                        C0015.m59();
                        C0015.f47.clear();
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: com.taobao.wireless.security.adapter.datacollection.в$б, reason: contains not printable characters */
    static class C0017 {

        /* renamed from: а, reason: contains not printable characters */
        int f48;

        /* renamed from: б, reason: contains not printable characters */
        long f49;

        public C0017(int i, long j) {
            this.f48 = i;
            this.f49 = j;
        }

        public String toString() {
            return this.f48 + "_" + this.f49;
        }
    }

    static {
        f46.add("android.intent.action.SCREEN_OFF");
        f46.add("android.intent.action.SCREEN_ON");
        f46.add("com.alibaba.action.ENTER_FOREGROUND");
        f46.add("com.alibaba.action.ENTER_BACKGROUND");
        f46.add("android.intent.action.AIRPLANE_MODE");
        f46.add("android.intent.action.TIME_SET");
        f46.add("com.alibaba.action.SCREEN_SHOT");
        f46.add("com.alibaba.action.SCREEN_RECORD");
        f46.add("android.bluetooth.adapter.action.STATE_CHANGED");
        f46.add("android.bluetooth.adapter.action.ACL_CONNECTED");
        f46.add("android.bluetooth.adapter.action.ACL_DISCONNECTED");
        f46.add("android.intent.action.PHONE_STATE");
        f46.add("android.intent.action.HEADSET_PLUG");
        f46.add("com.alibaba.action.PrimaryClipChanged");
        f46.add("android.net.conn.CONNECTIVITY_CHANGE");
        f46.add("com.alibaba.action.LC_ON_ACT_CREATED");
        f46.add("com.alibaba.action.LC_ON_ACT_STARTED");
        f46.add("com.alibaba.action.LC_ON_ACT_RESUMED");
        f46.add("com.alibaba.action.LC_ON_ACT_PAUSED");
        f46.add("com.alibaba.action.LC_ON_ACT_STOPPED");
        f46.add("com.alibaba.action.LC_ON_ACT_SAVE_INSTANCE_STATE");
        f46.add("com.alibaba.action.LC_ON_ACT_DESTROYED");
    }

    /* renamed from: а, reason: contains not printable characters */
    public static void m57(Context context) {
        if (context != null && m58()) {
            IntentFilter intentFilter = new IntentFilter();
            Iterator<String> it = f46.iterator();
            while (it.hasNext()) {
                intentFilter.addAction(it.next());
            }
            context.registerReceiver(new C0016(), intentFilter);
        }
    }

    /* renamed from: б, reason: contains not printable characters */
    public static boolean m58() {
        Method method;
        String str = "0";
        try {
            Class<?> cls = Class.forName("com.alibaba.wireless.security.open.securityguardaccsadapter.OrangeListener");
            if (cls != null && (method = cls.getMethod("getOrangeConfig", String.class, String.class, String.class)) != null) {
                str = (String) method.invoke(cls, "securityguard_orange_namespace", "132", "0");
            }
        } catch (Throwable th) {
            Log.e("SGNTF", "get notificationCollectionSwitch : " + th.getMessage());
        }
        return "1".equals(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: в, reason: contains not printable characters */
    public static void m59() {
        StringBuilder sb = new StringBuilder();
        Iterator<C0017> it = f47.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            sb.append("|");
        }
        String str = null;
        try {
            str = SecurityGuardManager.getInstance((Context) null).getSDKVerison();
        } catch (Throwable unused) {
        }
        sb.deleteCharAt(sb.length() - 1);
        UserTrackMethodJniBridge.addUtRecord("100184", 234, 0, "" + str, 0L, "", "" + Process.myPid(), sb.toString(), "", "");
    }
}
