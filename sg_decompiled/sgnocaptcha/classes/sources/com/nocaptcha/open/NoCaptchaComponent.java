package com.nocaptcha.open;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.HashMap;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgnocaptcha/classes.dex */
public class NoCaptchaComponent implements INoCaptchaComponent {
    private static WeakReference<Handler> b;
    private ISecurityGuardPlugin a;

    public NoCaptchaComponent(ISecurityGuardPlugin iSecurityGuardPlugin) {
        init(iSecurityGuardPlugin, new Object[0]);
    }

    private String a(String str) {
        if (str != null) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private String a(String str, String str2, int i, int i2, int i3, String str3) {
        return (String) this.a.getRouter().doCommand(40104, new Object[]{str, str2, str3, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    private String a(HashMap<String, String> hashMap) {
        String str;
        StringBuilder sb = new StringBuilder();
        if (hashMap != null) {
            for (String str2 : hashMap.keySet()) {
                if (str2 != null && str2.length() > 0 && (str = hashMap.get(str2)) != null && str.length() > 0) {
                    sb.append("&");
                    sb.append(str2);
                    sb.append("=");
                    sb.append(a(str));
                }
            }
        }
        return sb.toString();
    }

    private int b(HashMap<String, String> hashMap) {
        int i = 0;
        if (hashMap.containsKey("HOSTENV")) {
            String str = hashMap.get("HOSTENV");
            if (str.equals("PREONLINE")) {
                i = 1;
            } else if (str.equals("DAILY")) {
                i = 2;
            } else if (str.equals("SANDBOX")) {
                i = 3;
            }
            hashMap.remove("HOSTENV");
        }
        return i;
    }

    public static boolean postNoCaptchaDataToUIBridge(String str, String str2, String str3, int i, int i2, float f, float f2, float f3, float f4) {
        int i3;
        if (b == null) {
            return false;
        }
        if (i2 > 200) {
            i3 = i2;
            i2 = 105;
        } else {
            i3 = 0;
        }
        Bundle bundle = new Bundle();
        Handler handler = b.get();
        if (handler == null) {
            return false;
        }
        Message obtainMessage = handler.obtainMessage();
        obtainMessage.what = i;
        bundle.putInt("status", i2);
        bundle.putInt("errorCode", i3);
        bundle.putFloat("x1", f);
        bundle.putFloat("y1", f2);
        bundle.putFloat("x2", f3);
        bundle.putFloat("y2", f4);
        if (str != null && !"".equals(str) && str2 != null && !"".equals(str2) && str3 != null && !"".equals(str3)) {
            bundle.putString("token", str);
            bundle.putString("sig", str2);
            bundle.putString("sessionId", str3);
        }
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
        return obtainMessage.what != 0;
    }

    public int init(ISecurityGuardPlugin iSecurityGuardPlugin, Object... objArr) {
        this.a = iSecurityGuardPlugin;
        return 0;
    }

    public void initNoCaptcha(String str, String str2, int i, int i2, int i3, Handler handler, String str3) {
        if (handler != null) {
            b = new WeakReference<>(handler);
        }
        Handler handler2 = b.get();
        if (str != null && str.length() > 0 && str2 != null && str2.length() > 0 && i > 0 && i2 > 0 && i3 >= 0) {
            if (handler2 != null) {
                Message obtainMessage = handler2.obtainMessage();
                obtainMessage.what = 1;
                Bundle bundle = new Bundle();
                bundle.putInt("status", 100);
                obtainMessage.setData(bundle);
                obtainMessage.sendToTarget();
            }
            this.a.getRouter().doCommand(40101, new Object[]{str, str2, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), str3});
            return;
        }
        if (handler2 == null) {
            return;
        }
        Message obtainMessage2 = handler2.obtainMessage();
        obtainMessage2.what = 1;
        Bundle bundle2 = new Bundle();
        bundle2.putInt("status", 105);
        bundle2.putInt("errorCode", 1201);
        obtainMessage2.setData(bundle2);
        obtainMessage2.sendToTarget();
        throw new SecException("", 1201);
    }

    public String noCaptchaForwardAuth(String str, HashMap<String, String> hashMap, String str2, int i) {
        int i2;
        int i3 = 1201;
        String str3 = null;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            i3 = 1205;
        } else if (str != null && (hashMap == null || (!hashMap.containsKey("wsg_app_key") && !hashMap.containsKey("method")))) {
            HashMap<String, String> hashMap2 = hashMap == null ? new HashMap<>() : hashMap;
            int b2 = b(hashMap2);
            if (hashMap2.containsKey("REQUEST_TYPE")) {
                boolean equals = hashMap2.get("REQUEST_TYPE").equals("POST");
                hashMap2.remove("REQUEST_TYPE");
                i2 = equals ? 1 : 0;
            } else {
                i2 = 0;
            }
            try {
                str3 = a(a(hashMap2), str, i, b2, i2, str2);
                i3 = 1200;
            } catch (SecException e) {
                i3 = e.getErrorCode() + 1200;
            }
        }
        if (i3 <= 1200) {
            return str3;
        }
        throw new SecException(i3);
    }

    public void noCaptchaVerification(String str) {
        WeakReference<Handler> weakReference = b;
        if (weakReference == null) {
            throw new SecException("", 1203);
        }
        Handler handler = weakReference.get();
        if (str == null || str.length() <= 0) {
            if (handler != null) {
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.what = 1201;
                Bundle bundle = new Bundle();
                bundle.putInt("status", 105);
                bundle.putInt("errorCode", 1201);
                obtainMessage.setData(bundle);
                obtainMessage.sendToTarget();
            }
            throw new SecException("", 1201);
        }
        if (handler != null) {
            Message obtainMessage2 = handler.obtainMessage();
            obtainMessage2.what = 2;
            Bundle bundle2 = new Bundle();
            bundle2.putInt("status", 100);
            obtainMessage2.setData(bundle2);
            obtainMessage2.sendToTarget();
        }
        this.a.getRouter().doCommand(40103, new Object[]{str});
    }

    public boolean putNoCaptchaTraceRecord(MotionEvent motionEvent) {
        if (motionEvent == null) {
            throw new SecException("", 1201);
        }
        return ((Boolean) this.a.getRouter().doCommand(40102, new Object[]{Integer.valueOf(motionEvent.getAction()), Float.valueOf(motionEvent.getX()), Float.valueOf(motionEvent.getY())})).booleanValue();
    }
}
