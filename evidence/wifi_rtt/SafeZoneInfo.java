package com.alipay.mobile.verifyidentity.module.safezone.entity;

import com.alipay.android.phone.mobilecommon.verifyidentity.BuildConfig;
import com.alipay.instantrun.ChangeQuickRedirect;
import com.alipay.instantrun.ConstructorCode;
import com.alipay.instantrun.PatchProxy;
import com.alipay.mobile.framework.MpaasClassInfo;

@MpaasClassInfo(BundleName = BuildConfig.BUNDLE_NAME, ExportJarName = "unknown", Level = "base-component", Product = "核身")
/* loaded from: classes14.dex */
public class SafeZoneInfo {
    public static final String key_cellInfo = "cellInfo";
    public static final String key_cellInfoKey = "cellInfoKey";
    public static final String key_crossLocation = "crossLocation";
    public static final String key_crossLocationKey = "crossLocationKey";
    public static final String key_device = "device";
    public static final String key_fineLocation = "fineLocation";
    public static final String key_fineLocationKey = "fineLocationKey";
    public static final String key_network = "network";
    public static final String key_position = "position";
    public static final String key_wifiInfo = "wifiInfo";
    public static final String key_wifiInfoKey = "wifiInfoKey";

    /* renamed from: 支, reason: contains not printable characters */
    public static ChangeQuickRedirect f97035;
    public String cellInfo;
    public String cellInfoKey;
    public String crossLocation;
    public String crossLocationKey;
    public String fineLocation;
    public String fineLocationKey;
    public LocationOverallEntity location;
    public String wifiInfo;
    public String wifiInfoKey;

    public SafeZoneInfo() {
        ConstructorCode proxy;
        ChangeQuickRedirect changeQuickRedirect = f97035;
        if (changeQuickRedirect != null && (proxy = PatchProxy.proxy(changeQuickRedirect, "0")) != null) {
            proxy.afterSuper(this);
            return;
        }
        this.location = new LocationOverallEntity();
        this.fineLocation = "";
        this.crossLocation = "";
        this.wifiInfo = "";
        this.cellInfo = "";
        this.fineLocationKey = "";
        this.crossLocationKey = "";
        this.wifiInfoKey = "";
        this.cellInfoKey = "";
    }
}
