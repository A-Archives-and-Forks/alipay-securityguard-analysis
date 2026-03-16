package com.alibaba.wireless.security.middletierplugin;

import android.content.Context;
import com.alibaba.wireless.security.middletierplugin.d.a.c;
import com.alibaba.wireless.security.middletierplugin.open.es.SGWindowManager;
import com.alibaba.wireless.security.middletierplugin.open.fc.FCComponent;
import com.alibaba.wireless.security.open.avmp.IAVMPGenericComponent;
import com.alibaba.wireless.security.open.avmp.IAVMPSafeTokenComponent;
import com.alibaba.wireless.security.open.avmp.IAVMPSoftCertComponent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public class b {
    public static Object a(Class cls, InvocationHandler invocationHandler) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler);
    }

    public void a(HashMap<Class, Object> hashMap, SecurityGuardMiddleTierPlugin securityGuardMiddleTierPlugin, Context context) {
        Object proxyInstance;
        try {
            Class<?> cls = Class.forName("com.alibaba.wireless.security.open.middletier.IMiddleTierGenericComponent");
            if (cls != null) {
                hashMap.put(cls, com.alibaba.wireless.security.middletierplugin.d.b.a.a(cls, securityGuardMiddleTierPlugin, context));
            }
        } catch (Exception unused) {
        }
        try {
            Class<?> cls2 = Class.forName("com.alibaba.wireless.security.open.middletier.fc.IFCComponent");
            if (cls2 != null && (proxyInstance = FCComponent.getProxyInstance(cls2, securityGuardMiddleTierPlugin)) != null) {
                hashMap.put(cls2, proxyInstance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class<?> cls3 = Class.forName("com.alibaba.wireless.security.open.middletier.IUnifiedSecurityComponent");
            if (cls3 != null) {
                hashMap.put(cls3, com.alibaba.wireless.security.middletierplugin.d.d.a.a(cls3, securityGuardMiddleTierPlugin, context));
            }
        } catch (Exception unused2) {
        }
        try {
            Class<?> cls4 = Class.forName("com.alibaba.wireless.security.open.middletier.ISensorComponent");
            if (cls4 != null) {
                hashMap.put(cls4, com.alibaba.wireless.security.middletierplugin.d.c.a.a(cls4, securityGuardMiddleTierPlugin, context));
            }
        } catch (Exception unused3) {
        }
        SGWindowManager.init(securityGuardMiddleTierPlugin);
        hashMap.put(IAVMPSafeTokenComponent.class, new com.alibaba.wireless.security.middletierplugin.d.a.b(securityGuardMiddleTierPlugin));
        hashMap.put(IAVMPSoftCertComponent.class, new c(securityGuardMiddleTierPlugin));
        hashMap.put(IAVMPGenericComponent.class, new com.alibaba.wireless.security.middletierplugin.d.a.a(securityGuardMiddleTierPlugin));
    }
}
