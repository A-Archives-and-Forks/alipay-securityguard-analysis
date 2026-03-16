package com.nocaptcha.sdk;

import android.os.Handler;
import android.view.MotionEvent;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.taobao.wireless.security.sdk.nocaptcha.INoCaptchaComponent;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgnocaptcha/classes.dex */
public class NoCaptchaComponent implements INoCaptchaComponent {
    private com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent a;

    public NoCaptchaComponent(ISecurityGuardPlugin iSecurityGuardPlugin) {
        this.a = (com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent) iSecurityGuardPlugin.getInterface(com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent.class);
    }

    public void initNoCaptcha(String str, String str2, int i, int i2, int i3, Handler handler) {
        try {
            this.a.initNoCaptcha(str, str2, i, i2, i3, handler, (String) null);
        } catch (SecException e) {
            e.printStackTrace();
        }
    }

    public void noCaptchaVerification(String str) {
        try {
            this.a.noCaptchaVerification(str);
        } catch (SecException e) {
            e.printStackTrace();
        }
    }

    public boolean putNoCaptchaTraceRecord(MotionEvent motionEvent) {
        try {
            return this.a.putNoCaptchaTraceRecord(motionEvent);
        } catch (SecException e) {
            e.printStackTrace();
            return false;
        }
    }
}
