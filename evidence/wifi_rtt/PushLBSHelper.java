package com.alipay.pushsdk.push.lbs;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import ap.sps.k.a;
import com.alipay.dexaop.DexAOPEntry;
import com.alipay.instantrun.ChangeQuickRedirect;
import com.alipay.instantrun.ConstructorCode;
import com.alipay.instantrun.PatchProxy;
import com.alipay.instantrun.PatchProxyResult;
import com.alipay.mobile.common.fgbg.FgBgMonitor;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.framework.MpaasClassInfo;
import com.alipay.pushsdk.util.PushUtil;
import com.alipay.pushsdk.util.log.LogUtil;
import com.vivo.push.util.NotifyAdapterUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@MpaasClassInfo(BundleName = "android-phone-wallet-pushsdk", ExportJarName = "api", Level = "base-component", Product = NotifyAdapterUtil.PUSH_EN)
/* loaded from: classes16.dex */
public class PushLBSHelper {

    /* renamed from: 支, reason: contains not printable characters */
    public static ChangeQuickRedirect f120703;

    /* renamed from: a, reason: collision with root package name */
    public final Context f209432a;
    public WifiManager b;

    public PushLBSHelper(Context context) {
        ConstructorCode proxy;
        ChangeQuickRedirect changeQuickRedirect = f120703;
        if (changeQuickRedirect == null || (proxy = PatchProxy.proxy(changeQuickRedirect, "0", new Object[]{context})) == null) {
            this.f209432a = context;
        } else {
            proxy.afterSuper(this);
        }
    }

    public static void a(StringBuffer stringBuffer, ArrayList arrayList, List list) {
        ChangeQuickRedirect changeQuickRedirect = f120703;
        if (changeQuickRedirect == null || !PatchProxy.proxy(new Object[]{stringBuffer, arrayList, list, StringBuffer.class, ArrayList.class, List.class, Void.TYPE}, (Object) null, changeQuickRedirect, "1").isSupported) {
            if (list != null) {
                LogUtil.d("getWifiInfo lastScanResult size=" + list.size());
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ScanResult scanResult = (ScanResult) it.next();
                    PushLBSWifiInfo pushLBSWifiInfo = new PushLBSWifiInfo();
                    String str = scanResult.SSID;
                    pushLBSWifiInfo.f209433a = scanResult.BSSID;
                    pushLBSWifiInfo.b = scanResult.level;
                    arrayList.add(pushLBSWifiInfo);
                }
            }
            Collections.sort(arrayList, new a());
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(23:0|1|(2:3|(2:5|6))|8|(3:9|10|(1:12)(1:83))|(18:17|(15:24|(1:80)|28|29|30|(1:32)(1:76)|33|(1:35)|36|(1:38)|(1:40)(10:46|47|48|(1:50)|(5:58|59|(3:61|(3:63|64|(2:66|67)(1:69))(1:72)|68)|73|70)|74|59|(0)|73|70)|41|(1:43)|44|45)|81|(1:26)|80|28|29|30|(0)(0)|33|(0)|36|(0)|(0)(0)|41|(0)|44|45)|82|81|(0)|80|28|29|30|(0)(0)|33|(0)|36|(0)|(0)(0)|41|(0)|44|45) */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00dc, code lost:
    
        r2 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01b7, code lost:
    
        com.alipay.pushsdk.util.log.LogUtil.e("getWifiInfo error");
        com.alipay.pushsdk.util.log.LogUtil.printErr(r2);
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00d5 A[Catch: all -> 0x00dc, TryCatch #1 {all -> 0x00dc, blocks: (B:30:0x00c6, B:32:0x00d5, B:33:0x00e0, B:35:0x00e8, B:36:0x00ee, B:38:0x00f7, B:46:0x0101, B:53:0x013c, B:55:0x0146, B:58:0x0151, B:59:0x0166, B:61:0x017d, B:64:0x018f, B:66:0x019b, B:70:0x019e, B:74:0x0161, B:75:0x0135, B:48:0x010f, B:50:0x0130), top: B:29:0x00c6, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00e8 A[Catch: all -> 0x00dc, TryCatch #1 {all -> 0x00dc, blocks: (B:30:0x00c6, B:32:0x00d5, B:33:0x00e0, B:35:0x00e8, B:36:0x00ee, B:38:0x00f7, B:46:0x0101, B:53:0x013c, B:55:0x0146, B:58:0x0151, B:59:0x0166, B:61:0x017d, B:64:0x018f, B:66:0x019b, B:70:0x019e, B:74:0x0161, B:75:0x0135, B:48:0x010f, B:50:0x0130), top: B:29:0x00c6, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f7 A[Catch: all -> 0x00dc, TryCatch #1 {all -> 0x00dc, blocks: (B:30:0x00c6, B:32:0x00d5, B:33:0x00e0, B:35:0x00e8, B:36:0x00ee, B:38:0x00f7, B:46:0x0101, B:53:0x013c, B:55:0x0146, B:58:0x0151, B:59:0x0166, B:61:0x017d, B:64:0x018f, B:66:0x019b, B:70:0x019e, B:74:0x0161, B:75:0x0135, B:48:0x010f, B:50:0x0130), top: B:29:0x00c6, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0101 A[Catch: all -> 0x00dc, TRY_LEAVE, TryCatch #1 {all -> 0x00dc, blocks: (B:30:0x00c6, B:32:0x00d5, B:33:0x00e0, B:35:0x00e8, B:36:0x00ee, B:38:0x00f7, B:46:0x0101, B:53:0x013c, B:55:0x0146, B:58:0x0151, B:59:0x0166, B:61:0x017d, B:64:0x018f, B:66:0x019b, B:70:0x019e, B:74:0x0161, B:75:0x0135, B:48:0x010f, B:50:0x0130), top: B:29:0x00c6, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x017d A[Catch: all -> 0x00dc, TryCatch #1 {all -> 0x00dc, blocks: (B:30:0x00c6, B:32:0x00d5, B:33:0x00e0, B:35:0x00e8, B:36:0x00ee, B:38:0x00f7, B:46:0x0101, B:53:0x013c, B:55:0x0146, B:58:0x0151, B:59:0x0166, B:61:0x017d, B:64:0x018f, B:66:0x019b, B:70:0x019e, B:74:0x0161, B:75:0x0135, B:48:0x010f, B:50:0x0130), top: B:29:0x00c6, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String b() {
        /*
            Method dump skipped, instructions count: 482
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.pushsdk.push.lbs.PushLBSHelper.b():java.lang.String");
    }

    public List c(WifiManager wifiManager) {
        PushLBSHelper pushLBSHelper;
        WifiManager wifiManager2;
        ChangeQuickRedirect changeQuickRedirect = f120703;
        if (changeQuickRedirect != null) {
            pushLBSHelper = this;
            wifiManager2 = wifiManager;
            PatchProxyResult proxy = PatchProxy.proxy(wifiManager2, pushLBSHelper, changeQuickRedirect, "3", WifiManager.class, List.class);
            if (proxy.isSupported) {
                return (List) proxy.result;
            }
        } else {
            pushLBSHelper = this;
            wifiManager2 = wifiManager;
        }
        if (wifiManager2 == null) {
            return null;
        }
        try {
            if (!PushUtil.canScanWifiRetAtNetCon()) {
                return null;
            }
            boolean isInBackground = FgBgMonitor.getInstance(LoggerFactory.getLogContext().getApplicationContext()).isInBackground();
            LoggerFactory.getTraceLogger().info("lgkwl_push_lbs", "getWifiScanResults,flag=" + isInBackground);
            if (isInBackground) {
                return null;
            }
            return DexAOPEntry.android_net_wifi_WifiManager_freq_getScanResults_proxy(pushLBSHelper.b);
        } catch (Throwable th) {
            LogUtil.e("getWifiScanResults,err=" + th);
            return null;
        }
    }
}
