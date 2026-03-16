package com.alibaba.wireless.security.open.umid;

import android.content.Intent;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.alibaba.wireless.security.securitybody.ApmSecurityBodyPluginAdapter;
import com.alibaba.wireless.security.securitybody.SecurityGuardSecurityBodyPlugin;
import com.taobao.dp.DeviceSecuritySDKImpl;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class UMIDComponent implements IUMIDComponent, IUMIDInitListenerEx {
    private static final short e = -1;
    private static final short f = 0;
    private static final short g = 1;
    private static final Object h = new Object();
    private static SecurityGuardSecurityBodyPlugin i;

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f21a;
    private short b;
    private ArrayList<IUMIDInitListener> c;
    private DeviceSecuritySDKImpl d;

    public UMIDComponent(SecurityGuardSecurityBodyPlugin securityGuardSecurityBodyPlugin) {
        init(securityGuardSecurityBodyPlugin, new Object[0]);
    }

    public static void a(SecurityGuardSecurityBodyPlugin securityGuardSecurityBodyPlugin) {
        i = securityGuardSecurityBodyPlugin;
    }

    public static SecurityGuardSecurityBodyPlugin b() {
        return i;
    }

    public static void sendUmidChangedNotification(String str, int i2) {
        Intent intent = new Intent("NotificationUmidDidChanged");
        intent.setPackage(i.getContext().getPackageName());
        intent.putExtra("token", str);
        intent.putExtra("env", i2);
        i.getContext().sendBroadcast(intent);
    }

    public void a() {
        this.f21a = true;
        this.d.registerListener(this);
    }

    public String getSecurityToken() {
        return this.d.getSecurityToken(0, false);
    }

    public String getSecurityToken(int i2) {
        ApmSecurityBodyPluginAdapter.monitorStart("getSecurityToken");
        if (i2 < 0 || i2 > 2) {
            throw new SecException(901);
        }
        String securityToken = this.d.getSecurityToken(i2, true);
        ApmSecurityBodyPluginAdapter.monitorEnd("getSecurityToken");
        return securityToken;
    }

    public int init(ISecurityGuardPlugin iSecurityGuardPlugin, Object... objArr) {
        this.f21a = false;
        this.b = e;
        this.c = new ArrayList<>();
        this.d = new DeviceSecuritySDKImpl(iSecurityGuardPlugin);
        return 0;
    }

    public void initUMID() {
        this.d.init(null);
    }

    public void initUMID(int i2, IUMIDInitListenerEx iUMIDInitListenerEx) {
        this.d.initAsync(i2, iUMIDInitListenerEx);
    }

    public void initUMID(String str, int i2, String str2, IUMIDInitListenerEx iUMIDInitListenerEx) {
        initUMID(i2, iUMIDInitListenerEx);
    }

    public int initUMIDSync(int i2) {
        return this.d.initSync(i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c A[Catch: all -> 0x0017, TryCatch #0 {all -> 0x0017, blocks: (B:29:0x0007, B:31:0x000d, B:8:0x001e, B:9:0x0026, B:11:0x002c, B:14:0x0034, B:19:0x0038), top: B:28:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x001d  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onUMIDInitFinishedEx(String str, int i2) {
        boolean z;
        Iterator<IUMIDInitListener> it;
        synchronized (h) {
            short s = g;
            if (str != null) {
                try {
                    if (str.length() > 0 && !"000000000000000000000000".equals(str)) {
                        z = true;
                        if (z) {
                            s = f;
                        }
                        this.b = s;
                        it = this.c.iterator();
                        while (it.hasNext()) {
                            IUMIDInitListener next = it.next();
                            if (next != null) {
                                next.onUMIDInitFinished(z);
                            }
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            z = false;
            if (z) {
            }
            this.b = s;
            it = this.c.iterator();
            while (it.hasNext()) {
            }
        }
    }

    public void registerInitListener(IUMIDInitListener iUMIDInitListener) {
        if (!this.f21a) {
            throw new SecException(907);
        }
        synchronized (h) {
            if (this.b == -1) {
                if (iUMIDInitListener != null) {
                    this.c.add(iUMIDInitListener);
                }
            } else if (iUMIDInitListener != null) {
                iUMIDInitListener.onUMIDInitFinished(this.b == 1);
            }
        }
    }

    public void setEnvironment(int i2) {
        this.d.setEnvironment(i2);
    }

    public void setOnlineHost(String str) {
        try {
            this.d.setOnlineHost(str);
        } catch (IllegalArgumentException unused) {
            throw new SecException(901);
        }
    }
}
