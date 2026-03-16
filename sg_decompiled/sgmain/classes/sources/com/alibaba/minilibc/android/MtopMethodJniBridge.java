package com.alibaba.minilibc.android;

import android.content.Context;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class MtopMethodJniBridge {
    private static final boolean DEBUG = false;
    private static final String TAG = "MtopMethodJniBridge";
    private static Class gEnvModeEnumClass = null;
    private static Class gMethodEnumClass = null;
    private static volatile int gMtopAvaiableCache = 0;
    private static volatile int gMtopAvaiableCacheChecked = 0;
    private static Method gMtopBuildMethod = null;
    private static Class gMtopBuilderClass = null;
    private static Method gMtopBuilderHeadersMethod = null;
    private static Method gMtopBuilderReqMethodMethod = null;
    private static Method gMtopBuilderSetConnectionTimeoutMilliSecondMethod = null;
    private static Method gMtopBuilderSetCustomDomainMethod = null;
    private static Method gMtopBuilderSetSocketTimeoutMilliSecondMethod = null;
    private static Method gMtopBuilderSyncRequestMethod = null;
    private static Method gMtopBusinessBuildMethod = null;
    private static Class gMtopClass = null;
    private static Method gMtopInstanceMethod = null;
    private static Class gMtopRequestClass = null;
    private static Method gMtopRequestSetApiNameMethod = null;
    private static Method gMtopRequestSetApiVersionMethod = null;
    private static Method gMtopRequestSetDataMethod = null;
    private static Class gMtopResponseClass = null;
    private static Method gMtopResponseGetDataJsonObjectMethod = null;
    private static Method gMtopResponseGetRetCodeMethod = null;
    private static Method gMtopResponseGetRetMsgMethod = null;
    private static Method gMtopResponseIsApiSuccessMethod = null;
    private static Class gReflectUtilClass = null;
    private static Method gReflectUtilConvertMapToDataStrMethod = null;
    private static boolean hasMtopBusiness = true;

    public static int mtopAvaiable() {
        if (gMtopAvaiableCacheChecked == 0) {
            synchronized (MtopMethodJniBridge.class) {
                if (gMtopAvaiableCacheChecked == 0) {
                    try {
                        gMtopBuilderClass = Class.forName("com.taobao.tao.remotebusiness.MtopBusiness");
                    } catch (ClassNotFoundException unused) {
                        hasMtopBusiness = DEBUG;
                    }
                    try {
                        gMtopClass = Class.forName("mtopsdk.mtop.intf.Mtop");
                        gMethodEnumClass = Class.forName("mtopsdk.mtop.domain.MethodEnum");
                        gEnvModeEnumClass = Class.forName("mtopsdk.mtop.domain.EnvModeEnum");
                        gMtopRequestClass = Class.forName("mtopsdk.mtop.domain.MtopRequest");
                        gMtopResponseClass = Class.forName("mtopsdk.mtop.domain.MtopResponse");
                        if (!hasMtopBusiness) {
                            gMtopBuilderClass = Class.forName("mtopsdk.mtop.intf.MtopBuilder");
                        }
                        gReflectUtilClass = Class.forName("mtopsdk.mtop.util.ReflectUtil");
                        gMtopRequestSetApiNameMethod = gMtopRequestClass.getMethod("setApiName", String.class);
                        gMtopRequestSetApiVersionMethod = gMtopRequestClass.getMethod("setVersion", String.class);
                        gMtopRequestSetDataMethod = gMtopRequestClass.getMethod("setData", String.class);
                        gMtopInstanceMethod = gMtopClass.getMethod("instance", Context.class);
                        gMtopBuildMethod = gMtopClass.getMethod("build", gMtopRequestClass, String.class);
                        gMtopBuilderReqMethodMethod = gMtopBuilderClass.getMethod("reqMethod", gMethodEnumClass);
                        gMtopBuilderSyncRequestMethod = gMtopBuilderClass.getMethod("syncRequest", new Class[0]);
                        gMtopBuilderSetCustomDomainMethod = gMtopBuilderClass.getMethod("setCustomDomain", String.class);
                        gMtopBuilderHeadersMethod = gMtopBuilderClass.getMethod("headers", Map.class);
                        if (hasMtopBusiness) {
                            gMtopBusinessBuildMethod = gMtopBuilderClass.getMethod("build", gMtopClass, gMtopRequestClass, String.class);
                        }
                        gMtopBuilderSetConnectionTimeoutMilliSecondMethod = gMtopBuilderClass.getMethod("setConnectionTimeoutMilliSecond", Integer.TYPE);
                        gMtopBuilderSetSocketTimeoutMilliSecondMethod = gMtopBuilderClass.getMethod("setSocketTimeoutMilliSecond", Integer.TYPE);
                        gMtopResponseIsApiSuccessMethod = gMtopResponseClass.getMethod("isApiSuccess", new Class[0]);
                        gMtopResponseGetDataJsonObjectMethod = gMtopResponseClass.getMethod("getDataJsonObject", new Class[0]);
                        gMtopResponseGetRetCodeMethod = gMtopResponseClass.getMethod("getRetCode", new Class[0]);
                        gMtopResponseGetRetMsgMethod = gMtopResponseClass.getMethod("getRetMsg", new Class[0]);
                        gReflectUtilConvertMapToDataStrMethod = gReflectUtilClass.getMethod("convertMapToDataStr", Map.class);
                        gMtopAvaiableCache = 1;
                    } catch (ClassNotFoundException | NoSuchMethodException unused2) {
                    }
                    gMtopAvaiableCacheChecked = 1;
                }
            }
        }
        return gMtopAvaiableCache;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x008a A[Catch: Exception -> 0x0170, TryCatch #0 {Exception -> 0x0170, blocks: (B:6:0x0009, B:8:0x0025, B:9:0x002a, B:12:0x0034, B:15:0x003d, B:16:0x0044, B:17:0x0050, B:19:0x008a, B:21:0x00a8, B:23:0x00ae, B:24:0x00b7, B:26:0x00ce, B:27:0x00d7, B:30:0x00fe, B:33:0x0124, B:35:0x0138, B:37:0x0144, B:39:0x015c, B:41:0x009a, B:42:0x0048), top: B:5:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ce A[Catch: Exception -> 0x0170, TryCatch #0 {Exception -> 0x0170, blocks: (B:6:0x0009, B:8:0x0025, B:9:0x002a, B:12:0x0034, B:15:0x003d, B:16:0x0044, B:17:0x0050, B:19:0x008a, B:21:0x00a8, B:23:0x00ae, B:24:0x00b7, B:26:0x00ce, B:27:0x00d7, B:30:0x00fe, B:33:0x0124, B:35:0x0138, B:37:0x0144, B:39:0x015c, B:41:0x009a, B:42:0x0048), top: B:5:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00fe A[Catch: Exception -> 0x0170, TRY_LEAVE, TryCatch #0 {Exception -> 0x0170, blocks: (B:6:0x0009, B:8:0x0025, B:9:0x002a, B:12:0x0034, B:15:0x003d, B:16:0x0044, B:17:0x0050, B:19:0x008a, B:21:0x00a8, B:23:0x00ae, B:24:0x00b7, B:26:0x00ce, B:27:0x00d7, B:30:0x00fe, B:33:0x0124, B:35:0x0138, B:37:0x0144, B:39:0x015c, B:41:0x009a, B:42:0x0048), top: B:5:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009a A[Catch: Exception -> 0x0170, TryCatch #0 {Exception -> 0x0170, blocks: (B:6:0x0009, B:8:0x0025, B:9:0x002a, B:12:0x0034, B:15:0x003d, B:16:0x0044, B:17:0x0050, B:19:0x008a, B:21:0x00a8, B:23:0x00ae, B:24:0x00b7, B:26:0x00ce, B:27:0x00d7, B:30:0x00fe, B:33:0x0124, B:35:0x0138, B:37:0x0144, B:39:0x015c, B:41:0x009a, B:42:0x0048), top: B:5:0x0009 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String postRequest(Context context, String str, String str2, String str3, Map<String, String> map, Map<String, String> map2, byte[] bArr) {
        String str4;
        String str5;
        Object invoke;
        Object invoke2;
        if (mtopAvaiable() != 0) {
            try {
                Object newInstance = gMtopRequestClass.newInstance();
                gMtopRequestSetApiNameMethod.invoke(newInstance, str);
                gMtopRequestSetApiVersionMethod.invoke(newInstance, str2);
                if (map == null) {
                    map = new HashMap<>();
                }
                if (!str.contains("networksdk.savewb") && !str.contains("networksdk.data")) {
                    str4 = "body";
                    str5 = new String(bArr, "UTF-8");
                    map.put(str4, str5);
                    String str6 = (String) gReflectUtilConvertMapToDataStrMethod.invoke(null, map);
                    HashMap hashMap = new HashMap();
                    hashMap.put("request", str6);
                    gMtopRequestSetDataMethod.invoke(newInstance, (String) gReflectUtilConvertMapToDataStrMethod.invoke(null, hashMap));
                    Object invoke3 = gMtopInstanceMethod.invoke(null, context);
                    invoke = !hasMtopBusiness ? gMtopBusinessBuildMethod.invoke(null, invoke3, newInstance, null) : gMtopBuildMethod.invoke(invoke3, newInstance, null);
                    if (map2 != null && !map2.isEmpty()) {
                        gMtopBuilderHeadersMethod.invoke(invoke, map2);
                    }
                    gMtopBuilderReqMethodMethod.invoke(invoke, Enum.valueOf(gMethodEnumClass, "POST"));
                    if (!TextUtils.isEmpty(str3)) {
                        gMtopBuilderSetCustomDomainMethod.invoke(invoke, str3);
                    }
                    gMtopBuilderSetConnectionTimeoutMilliSecondMethod.invoke(invoke, 5000);
                    gMtopBuilderSetSocketTimeoutMilliSecondMethod.invoke(invoke, 5000);
                    invoke2 = gMtopBuilderSyncRequestMethod.invoke(invoke, new Object[0]);
                    if (invoke2 == null) {
                        String str7 = (String) gMtopResponseGetRetCodeMethod.invoke(invoke2, new Object[0]);
                        String str8 = (String) gMtopResponseGetRetMsgMethod.invoke(invoke2, new Object[0]);
                        if (!((Boolean) gMtopResponseIsApiSuccessMethod.invoke(invoke2, new Object[0])).booleanValue()) {
                            return str7.concat("|") + str8;
                        }
                        JSONObject jSONObject = (JSONObject) gMtopResponseGetDataJsonObjectMethod.invoke(invoke2, new Object[0]);
                        if (jSONObject != null) {
                            return str7.concat("|") + jSONObject.toString();
                        }
                        return str7.concat("|") + str8;
                    }
                }
                str4 = "payload";
                str5 = new String(bArr, "UTF-8");
                map.put(str4, str5);
                String str62 = (String) gReflectUtilConvertMapToDataStrMethod.invoke(null, map);
                HashMap hashMap2 = new HashMap();
                hashMap2.put("request", str62);
                gMtopRequestSetDataMethod.invoke(newInstance, (String) gReflectUtilConvertMapToDataStrMethod.invoke(null, hashMap2));
                Object invoke32 = gMtopInstanceMethod.invoke(null, context);
                if (!hasMtopBusiness) {
                }
                if (map2 != null) {
                    gMtopBuilderHeadersMethod.invoke(invoke, map2);
                }
                gMtopBuilderReqMethodMethod.invoke(invoke, Enum.valueOf(gMethodEnumClass, "POST"));
                if (!TextUtils.isEmpty(str3)) {
                }
                gMtopBuilderSetConnectionTimeoutMilliSecondMethod.invoke(invoke, 5000);
                gMtopBuilderSetSocketTimeoutMilliSecondMethod.invoke(invoke, 5000);
                invoke2 = gMtopBuilderSyncRequestMethod.invoke(invoke, new Object[0]);
                if (invoke2 == null) {
                }
            } catch (Exception unused) {
            }
        }
        return null;
    }
}
