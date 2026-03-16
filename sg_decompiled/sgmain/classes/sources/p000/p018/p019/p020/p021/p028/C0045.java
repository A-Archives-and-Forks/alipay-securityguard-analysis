package p000.p018.p019.p020.p021.p028;

import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.taobao.wireless.security.sdk.staticdatastore.IStaticDataStoreComponent;

/* renamed from: а.б.а.а.а.ж.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0045 implements IStaticDataStoreComponent {

    /* renamed from: а, reason: contains not printable characters */
    private com.alibaba.wireless.security.open.staticdatastore.IStaticDataStoreComponent f100;

    public C0045(ISecurityGuardPlugin iSecurityGuardPlugin) {
        this.f100 = (com.alibaba.wireless.security.open.staticdatastore.IStaticDataStoreComponent) iSecurityGuardPlugin.getInterface(com.alibaba.wireless.security.open.staticdatastore.IStaticDataStoreComponent.class);
    }

    public String getAppKeyByIndex(int i) {
        try {
            return this.f100.getAppKeyByIndex(i, "");
        } catch (SecException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getExtraData(String str) {
        try {
            return this.f100.getExtraData(str, "");
        } catch (SecException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getKeyType(String str) {
        try {
            return this.f100.getKeyType(str, "");
        } catch (SecException e) {
            e.printStackTrace();
            return 4;
        }
    }
}
