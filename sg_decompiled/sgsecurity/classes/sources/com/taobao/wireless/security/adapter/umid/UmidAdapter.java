package com.taobao.wireless.security.adapter.umid;

import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.umid.IUMIDComponent;
import com.alibaba.wireless.security.open.umid.UMIDComponent;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class UmidAdapter {

    /* renamed from: a, reason: collision with root package name */
    private static IUMIDComponent f44a;

    public static int umidInitAdapter(int i) {
        try {
            f44a = (IUMIDComponent) UMIDComponent.b().getInterface(IUMIDComponent.class);
            if (f44a == null) {
                return 0;
            }
            f44a.initUMIDSync(i);
            return 1;
        } catch (SecException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
