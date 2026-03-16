package com.taobao.wireless.security.miscplugin;

import android.content.Context;
import com.alibaba.wireless.security.misc.MiscPlugin;
import com.taobao.wireless.security.sdk.SecurityGuardParamContext;
import java.util.Map;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmisc/classes.dex */
public class b {
    MiscPlugin a;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a = new int[c.values().length];

        static {
            try {
                a[c.INDIE_KIT_TOP_TOKEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[c.INDIE_KIT_SECURITY_CHECK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[c.INDIE_KIT_VALIDATE_FILE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public b(MiscPlugin miscPlugin, Context context) {
        this.a = miscPlugin;
    }

    private String a(String[] strArr, String str, int i) {
        return (String) this.a.getRouter().doCommand(50101, new Object[]{strArr, str, Integer.valueOf(i)});
    }

    private String[] a(Map<String, String> map) {
        if (map != null && map.size() != 1) {
            String.format("Input map size invalid : required size is \"%d\" and actual size is \"%d\"", 1, Integer.valueOf(map.size()));
            return null;
        }
        String str = map.get("timestamp");
        if (str != null && !"".equals(str)) {
            return new String[]{str};
        }
        String.format("Input map value invalid : key \"%1s\" not exits or the relative value is empty", "timestamp");
        return null;
    }

    private String[] b(Map<String, String> map) {
        if (map != null && map.size() != 2) {
            String.format("Input map size invalid : required size is \"%d\" and actual size is \"%d\"", 2, Integer.valueOf(map.size()));
            return null;
        }
        String str = map.get("username");
        String str2 = map.get("timestamp");
        if (!com.taobao.wireless.security.miscplugin.a.a(str, str2)) {
            return new String[]{str, str2};
        }
        String.format("Input map value invalid : some key not exits or the relative value is empty", new Object[0]);
        return null;
    }

    private String[] c(Map<String, String> map) {
        if (map != null && map.size() != 2) {
            String.format("Input map size invalid : required size is \"%d\" and actual size is \"%d\"", 2, Integer.valueOf(map.size()));
            return null;
        }
        String str = map.get("filesignature");
        String str2 = map.get("filehash");
        if (!com.taobao.wireless.security.miscplugin.a.a(str, str2)) {
            return new String[]{str, str2};
        }
        String.format("Input map value invalid : some key not exits or the relative value is empty", new Object[0]);
        return null;
    }

    public String a(SecurityGuardParamContext securityGuardParamContext) {
        Map<String, String> map = securityGuardParamContext.paramMap;
        c cVar = c.e[securityGuardParamContext.requestType];
        String str = securityGuardParamContext.appKey;
        int i = a.a[cVar.ordinal()];
        String[] c = i != 1 ? i != 2 ? i != 3 ? null : c(map) : a(map) : b(map);
        if (c != null) {
            return a(c, str, cVar.ordinal());
        }
        return null;
    }
}
