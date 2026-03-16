package com.taobao.wireless.security.miscplugin;

import android.content.Context;
import com.alibaba.wireless.security.misc.MiscPlugin;
import com.taobao.wireless.security.sdk.SecurityGuardParamContext;
import com.taobao.wireless.security.sdk.indiekit.IIndieKitComponent;
import java.util.HashMap;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmisc/classes.dex */
public class d implements IIndieKitComponent {
    private b a;

    public d(MiscPlugin miscPlugin, Context context) {
        this.a = new b(miscPlugin, context);
    }

    public String indieKitRequest(SecurityGuardParamContext securityGuardParamContext) {
        if (securityGuardParamContext == null || securityGuardParamContext.paramMap == null) {
            return null;
        }
        return this.a.a(securityGuardParamContext);
    }

    public int reportSusText(String str, String str2) {
        return 0;
    }

    public int validateFileSignature(String str, String str2, String str3) {
        if (str != null && str2 != null && str3 != null && str.length() > 0 && str2.length() > 0 && str3.length() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("filesignature", str);
            hashMap.put("filehash", str2);
            SecurityGuardParamContext securityGuardParamContext = new SecurityGuardParamContext();
            securityGuardParamContext.appKey = str3;
            securityGuardParamContext.paramMap = hashMap;
            securityGuardParamContext.requestType = 2;
            String a = this.a.a(securityGuardParamContext);
            if (a != null && a.length() > 0) {
                return Integer.parseInt(a);
            }
        }
        return -1;
    }
}
