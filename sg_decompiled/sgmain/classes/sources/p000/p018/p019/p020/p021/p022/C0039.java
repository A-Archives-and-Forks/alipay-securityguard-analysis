package p000.p018.p019.p020.p021.p022;

import com.alibaba.wireless.security.mainplugin.SecurityGuardMainPlugin;
import com.alibaba.wireless.security.open.SecException;
import com.taobao.wireless.security.sdk.atlasencrypt.IAtlasEncryptComponent;

/* renamed from: а.б.а.а.а.а.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0039 implements IAtlasEncryptComponent {

    /* renamed from: а, reason: contains not printable characters */
    private com.alibaba.wireless.security.open.atlasencrypt.IAtlasEncryptComponent f94;

    public C0039(SecurityGuardMainPlugin securityGuardMainPlugin) {
        this.f94 = (com.alibaba.wireless.security.open.atlasencrypt.IAtlasEncryptComponent) securityGuardMainPlugin.getInterface(com.alibaba.wireless.security.open.atlasencrypt.IAtlasEncryptComponent.class);
    }

    public String atlasSafeEncrypt(String str) {
        try {
            return this.f94.atlasSafeEncrypt(str, "");
        } catch (SecException e) {
            e.printStackTrace();
            return null;
        }
    }
}
