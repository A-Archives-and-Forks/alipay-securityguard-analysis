package p000.p018.p019.p020.p021.p023;

import com.alibaba.wireless.security.mainplugin.SecurityGuardMainPlugin;
import com.taobao.wireless.security.adapter.datacollection.C0013;
import com.taobao.wireless.security.sdk.datacollection.IDataCollectionComponent;

/* renamed from: а.б.а.а.а.б.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0040 implements IDataCollectionComponent {

    /* renamed from: а, reason: contains not printable characters */
    private C0013 f95;

    public C0040(SecurityGuardMainPlugin securityGuardMainPlugin) {
        this.f95 = C0013.m44(securityGuardMainPlugin);
    }

    public String getEncryptedDevAndEnvInfo(int i, String str) {
        return null;
    }

    public String getNick() {
        try {
            return this.f95.m48();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getNickEx(int i) {
        try {
            return this.f95.m49(i);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean setNick(String str) {
        try {
            return this.f95.m51(str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setNickEx(int i, String str) {
        try {
            return this.f95.m50(i, str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
