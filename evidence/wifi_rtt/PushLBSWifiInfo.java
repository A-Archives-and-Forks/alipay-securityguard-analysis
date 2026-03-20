package com.alipay.pushsdk.push.lbs;

import com.alipay.instantrun.ChangeQuickRedirect;
import com.alipay.instantrun.ConstructorCode;
import com.alipay.instantrun.PatchProxy;
import com.alipay.instantrun.PatchProxyResult;
import com.alipay.mobile.framework.MpaasClassInfo;
import com.vivo.push.util.NotifyAdapterUtil;

@MpaasClassInfo(BundleName = "android-phone-wallet-pushsdk", ExportJarName = "api", Level = "base-component", Product = NotifyAdapterUtil.PUSH_EN)
/* loaded from: classes16.dex */
public class PushLBSWifiInfo {

    /* renamed from: 支, reason: contains not printable characters */
    public static ChangeQuickRedirect f120704;

    /* renamed from: a, reason: collision with root package name */
    public String f209433a;
    public int b;

    public PushLBSWifiInfo() {
        ConstructorCode proxy;
        ChangeQuickRedirect changeQuickRedirect = f120704;
        if (changeQuickRedirect == null || (proxy = PatchProxy.proxy(changeQuickRedirect, "0")) == null) {
            return;
        }
        proxy.afterSuper(this);
    }

    public String toString() {
        ChangeQuickRedirect changeQuickRedirect = f120704;
        if (changeQuickRedirect != null) {
            PatchProxyResult proxy = PatchProxy.proxy(this, changeQuickRedirect, "1", String.class);
            if (proxy.isSupported) {
                return (String) proxy.result;
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(this.f209433a);
        stringBuffer.append("|");
        stringBuffer.append(this.b);
        return stringBuffer.toString();
    }
}
