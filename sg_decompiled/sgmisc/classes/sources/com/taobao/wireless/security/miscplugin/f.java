package com.taobao.wireless.security.miscplugin;

import android.content.Context;
import com.alibaba.wireless.security.misc.MiscPlugin;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.sdk.pkgvaliditycheck.IPkgValidityCheckComponent;
import com.taobao.wireless.security.sdk.pkgvaliditycheck.PkgValidityCheckDefine;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmisc/classes.dex */
public class f implements IPkgValidityCheckComponent {
    private e a;

    public f(MiscPlugin miscPlugin, Context context) {
        this.a = new e(miscPlugin);
    }

    public int checkEnvAndFiles(String... strArr) {
        try {
            return this.a.a(strArr);
        } catch (SecException unused) {
            return -1;
        }
    }

    public boolean generateValidPackage(String str, String str2, String str3) {
        return this.a.a(str, str2, str3);
    }

    public String getDexHash(String str, String str2, int i) {
        if (PkgValidityCheckDefine.FLAG_DEX_FILE == i || PkgValidityCheckDefine.FLAG_DEX_MANIFEST == i) {
            return this.a.a(str, str2, i);
        }
        return null;
    }

    public boolean isPackageValid(String str) {
        return isPackageValidEx(str, null, 0);
    }

    public boolean isPackageValidEx(String str, String str2, int i) {
        if (i != 0 && i != 1) {
            return false;
        }
        if (i == 1 && (str2 == null || "".equals(str2))) {
            return false;
        }
        return this.a.b(str, str2, i);
    }
}
