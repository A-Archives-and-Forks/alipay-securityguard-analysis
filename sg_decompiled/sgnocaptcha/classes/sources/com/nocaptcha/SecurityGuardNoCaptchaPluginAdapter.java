package com.nocaptcha;

import com.alibaba.wireless.security.nocaptchaplugin.SecurityGuardNoCaptchaPlugin;
import com.alibaba.wireless.security.open.nocaptcha.INoCaptchaComponent;
import com.nocaptcha.open.NoCaptchaComponent;
import java.util.HashMap;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgnocaptcha/classes.dex */
public class SecurityGuardNoCaptchaPluginAdapter {
    public void a(HashMap<Class, Object> hashMap, SecurityGuardNoCaptchaPlugin securityGuardNoCaptchaPlugin) {
        hashMap.put(INoCaptchaComponent.class, new NoCaptchaComponent(securityGuardNoCaptchaPlugin));
        hashMap.put(com.taobao.wireless.security.sdk.nocaptcha.INoCaptchaComponent.class, new com.nocaptcha.sdk.NoCaptchaComponent(securityGuardNoCaptchaPlugin));
    }
}
