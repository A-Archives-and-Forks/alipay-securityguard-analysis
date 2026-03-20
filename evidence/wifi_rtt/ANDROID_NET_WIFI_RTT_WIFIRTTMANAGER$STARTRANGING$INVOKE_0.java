package com.alipay.dexaop.invokers;

import android.net.wifi.rtt.RangingRequest;
import android.net.wifi.rtt.RangingResultCallback;
import android.net.wifi.rtt.WifiRttManager;
import com.alipay.android.phone.compliance.config.ConfigConstants;
import com.alipay.dexaop.DexAOPPoints;
import com.alipay.dexaop.proxy.PointInterceptor;
import com.alipay.fusion.intercept.manager.config.ConfigItem;
import com.alipay.mobile.framework.MpaasClassInfo;
import java.util.concurrent.Executor;

@MpaasClassInfo(BundleName = "android-phone-mobilesdk-dexaop", ExportJarName = "unknown", Level = ConfigConstants.CONTROLLER_FRAMEWORK, Product = "Native框架")
/* loaded from: classes.dex */
public class ANDROID_NET_WIFI_RTT_WIFIRTTMANAGER$STARTRANGING$INVOKE_0 {
    public static final PointInterceptor.Invoker<Object> INVOKER;

    static {
        String str = ConfigItem.RV_VOID;
        INVOKER = new PointInterceptor.Invoker<Object>(DexAOPPoints.INVOKE_android_net_wifi_rtt_WifiRttManager_startRanging_proxy, new String[]{"android.net.wifi.rtt.RangingRequest", "java.util.concurrent.Executor", "android.net.wifi.rtt.RangingResultCallback"}, str) { // from class: com.alipay.dexaop.invokers.ANDROID_NET_WIFI_RTT_WIFIRTTMANAGER$STARTRANGING$INVOKE_0.1
            @Override // com.alipay.dexaop.proxy.PointInterceptor.Invoker
            public final Object invokeMethod(Object obj, Object[] objArr) throws Throwable {
                ((WifiRttManager) obj).startRanging((RangingRequest) objArr[0], (Executor) objArr[1], (RangingResultCallback) objArr[2]);
                return null;
            }
        };
    }
}
