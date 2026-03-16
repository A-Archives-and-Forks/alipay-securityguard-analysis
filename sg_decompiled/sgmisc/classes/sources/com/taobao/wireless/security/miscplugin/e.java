package com.taobao.wireless.security.miscplugin;

import android.content.Context;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import java.util.HashSet;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmisc/classes.dex */
public class e {
    private Context a;
    private ISecurityGuardPlugin b;

    public e(ISecurityGuardPlugin iSecurityGuardPlugin) {
        this.b = iSecurityGuardPlugin;
        this.a = iSecurityGuardPlugin.getContext();
    }

    public int a(String... strArr) throws SecException {
        HashSet hashSet = new HashSet();
        if (strArr != null) {
            for (String str : strArr) {
                if (!a.a(str)) {
                    hashSet.add(str);
                }
            }
        }
        String[] strArr2 = new String[hashSet.size()];
        hashSet.toArray(strArr2);
        return ((Integer) this.b.getRouter().doCommand(50201, new Object[]{strArr2, this.a})).intValue();
    }

    public String a(String str, String str2, int i) {
        if (a.a(str, str2)) {
            return null;
        }
        try {
            byte[] bArr = (byte[]) this.b.getRouter().doCommand(50202, new Object[]{str, str2, Integer.valueOf(i)});
            if (bArr != null) {
                return new String(bArr, "UTF-8");
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public boolean a(String str, String str2, String str3) {
        if (a.a(str, str2, str3)) {
            return false;
        }
        try {
            return ((Boolean) this.b.getRouter().doCommand(50204, new Object[]{str, str2, str3})).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean b(String str, String str2, int i) {
        if (a.a(str)) {
            return false;
        }
        try {
            return ((Boolean) this.b.getRouter().doCommand(50203, new Object[]{str, str2, Integer.valueOf(i)})).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
