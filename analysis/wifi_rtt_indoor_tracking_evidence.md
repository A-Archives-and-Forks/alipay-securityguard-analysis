# 支付宝WiFi RTT室内定位 — 完整代码证据包

## 1. WiFi RTT测距 (WifiRttManager.startRanging)

### 1.1 DexAOP拦截点注册 (InterferePointInitHelper.java:1129)
```java
hashMap.put(DexAOPPoints.INVOKE_android_net_wifi_rtt_WifiRttManager_startRanging_proxy,
    new DefaultInterferePointProperty(
        DexAOPPoints.INVOKE_android_net_wifi_rtt_WifiRttManager_startRanging_proxy,
        7L,  // API level 28+ (Android 9)
        "allOf(android.permission.ACCESS_FINE_LOCATION:android.permission.ACCESS_WIFI_STATE:android.permission.CHANGE_WIFI_STATE)",
        "位置获取|WiFi控制",
        PointCategory.ACCESS));
```

### 1.2 代理方法 (DexAOPEntry2.java:3056-3068)
```java
public static final void android_net_wifi_rtt_WifiRttManager_startRanging_proxy(
    Object obj, RangingRequest rangingRequest, Executor executor,
    RangingResultCallback rangingResultCallback) throws Throwable {
    objArr[0] = rangingRequest;
    objArr[1] = executor;
    objArr[2] = rangingResultCallback;
    if (DexAOPCenter.sEnable) {
        DexAOPCenter.processInvoke(DexAOPPoints.INVOKE_android_net_wifi_rtt_WifiRttManager_startRanging_proxy,
            (WifiRttManager) obj, INVOKER, objArr);
    } else {
        INVOKER.invokeMethod((WifiRttManager) obj, objArr);
    }
}
```

### 1.3 Invoker (ANDROID_NET_WIFI_RTT_WIFIRTTMANAGER$STARTRANGING$INVOKE_0.java)
```java
INVOKER = new PointInterceptor.Invoker<Object>(...) {
    public final Object invokeMethod(Object obj, Object[] objArr) {
        ((WifiRttManager) obj).startRanging(
            (RangingRequest) objArr[0], (Executor) objArr[1],
            (RangingResultCallback) objArr[2]);
        return null;
    }
};
```

## 2. WiFi Aware (设备发现+定位)

### 2.1 WiFi Aware Manager (InterferePointInitHelper.java:1101-1104)
```java
// WiFi Aware身份变更监听
hashMap.put("android_net_wifi_aware_IdentityChangedListener_onIdentityChanged_proxy", ...);
// WiFi Aware设备附着 — 标注"位置获取"
hashMap.put("android_net_wifi_aware_WifiAwareManager_attach_proxy", ... "位置获取" ...);
// WiFi Aware发布/订阅
hashMap.put("android_net_wifi_aware_WifiAwareSession_publish_proxy", ...);
hashMap.put("android_net_wifi_aware_WifiAwareSession_subscribe_proxy", ...);
```

## 3. WiFi P2P (对等设备发现)

### 3.1 P2P事件监听 (InterferePointInitHelper.java:56-58)
```java
hashMap.put("RegisterReceiver__WIFI_P2P_CONNECTION_CHANGED_ACTION", ...
    "allOf(ACCESS_FINE_LOCATION:ACCESS_WIFI_STATE)", PointCategory.ACCESS);
hashMap.put("RegisterReceiver__WIFI_P2P_PEERS_CHANGED_ACTION", ...);
hashMap.put("RegisterReceiver__WIFI_P2P_THIS_DEVICE_CHANGED_ACTION", ...);
```

### 3.2 P2P设备操作 (共28个拦截点, line 1105-1127)
标注"位置获取"的: addLocalService, connect, createGroup, discoverPeers,
discoverServices, requestDeviceInfo, requestGroupInfo, requestPeers

## 4. WiFi扫描与BSSID采集

### 4.1 WiFi操作拦截 (60个WifiManager相关拦截点)
```java
// 获取扫描结果 — 所有可见WiFi AP
hashMap.put(DexAOPPoints.INVOKE_android_net_wifi_WifiManager_getScanResults_proxy, ...
    "anyOf(ACCESS_COARSE_LOCATION:ACCESS_FINE_LOCATION:ACCESS_WIFI_STATE)",
    "位置获取|WiFi控制", PointCategory.ACCESS);
// 触发WiFi扫描
hashMap.put(DexAOPPoints.INVOKE_android_net_wifi_WifiManager_startScan_proxy, ...
    "anyOf(ACCESS_FINE_LOCATION:CHANGE_WIFI_STATE)", "位置获取|WiFi控制");
// 获取连接信息
hashMap.put(DexAOPPoints.INVOKE_android_net_wifi_WifiManager_getConnectionInfo_proxy, ...
    "anyOf(ACCESS_FINE_LOCATION:ACCESS_WIFI_STATE)", "位置获取|WiFi控制");
// 获取已配置网络
hashMap.put(DexAOPPoints.INVOKE_android_net_wifi_WifiManager_getConfiguredNetworks_proxy, ...
    "allOf(ACCESS_FINE_LOCATION:ACCESS_WIFI_STATE)", "位置获取|WiFi控制");
```

### 4.2 Push注册时WiFi数据采集 (PushLBSHelper.java)
```java
// 扫描所有可见WiFi AP
ScanResult scanResult = (ScanResult) it.next();
PushLBSWifiInfo pushLBSWifiInfo = new PushLBSWifiInfo();
pushLBSWifiInfo.BSSID = scanResult.BSSID;  // MAC地址
pushLBSWifiInfo.level = scanResult.level;    // 信号强度
arrayList.add(pushLBSWifiInfo);
// → 作为lbsInfo上报,关联userId
```

### 4.3 登录时WiFi MAC上报
```java
// MiniShellLoginHelper.java:343
skyGateReqHpbPB.wifiMac = NetWorkInfo.getInstance(...).getBssid();
// FaceGuideHandler.java:180
unifyLoginRecommendReqHpbPB.wifiMac = NetWorkInfo.getInstance(...).getBssid();
// CdpRequestManager.java:336
cdpQueryRequestPB.wifimac = NetWorkInfo.getInstance(context).getBssid();
```

## 5. iBeacon室内定位

### 5.1 两个BeaconService实现
```java
// com.alibaba.ariver.commonability.bluetooth.ibeacon.MyBeaconServiceImpl
beaconManager.startRangingBeaconsInRegion(
    new Region(getApplicationContext().getPackageName(), null, null, null));

// com.alipay.android.phone.bluetoothsdk.beacon.MyBeaconServiceImpl
beaconManager.startRangingBeaconsInRegion(
    new Region(getApplicationContext().getPackageName(), null, null, null));
```

## 6. 室内定位服务 (IndoorLocationServiceImpl)

```java
public class IndoorLocationServiceImpl extends IndoorLocationService {
    // MetaInfoConfig_CN.java:722 注册
    // 接口: attach(context, listener, type) → 启动室内定位
    // 接口: detach(listener) → 停止
    // PatchProxy可远程热替换每个方法
}
```

## 7. 安全区域融合定位 (SafeZoneInfo)

```java
public class SafeZoneInfo {
    public String fineLocation;       // 精确位置(WiFi RTT/GPS)
    public String fineLocationKey;    // 精确位置加密密钥
    public String wifiInfo;           // WiFi环境指纹
    public String wifiInfoKey;        // WiFi指纹加密密钥
    public String cellInfo;           // 基站信息
    public String cellInfoKey;        // 基站加密密钥
    public String crossLocation;      // 交叉定位结果
    public String crossLocationKey;   // 交叉定位加密密钥
    public LocationOverallEntity location;  // 综合位置
}
```

## 8. 网络请求中的WiFi BSSID上报

```java
// anet/channel/strategy/dispatch/c.java:129
map2.put("bssid", NetworkStatusHelper.getWifiBSSID());
// anet/channel/statist/RequestStatistic.java:268
this.bssid = NetworkStatusHelper.getWifiBSSID();
// 每个网络请求都携带WiFi BSSID信息
```

## 9. 蓝牙定位(160个拦截点)

DexAOP统计:
- 蓝牙(Bluetooth): 160个拦截点, 标注"蓝牙操作/位置获取"
- 电话(Telephony): 169个拦截点
- WiFi: 74个拦截点
- 位置(Location): 46个拦截点

## 10. 位置服务全栈 (MetaInfoConfig_CN.java:722)

注册的位置服务:
- IndoorLocationService — 室内定位
- LBSLocationManagerService — LBS位置管理
- GeofenceService — 地理围栏(进入/离开事件)
- GeocodeService — 地理编码(经纬度→地址)

## 统计

| 类别 | 拦截/方法数 |
|------|-----------|
| WiFi RTT | 1 (startRanging) |
| WiFi Aware | 4 (attach/publish/subscribe/identity) |
| WiFi P2P | 28 (完整P2P API覆盖) |
| WiFi基础 | 27+ (scan/connect/config/info) |
| iBeacon | 2个独立实现 |
| 蓝牙 | 160个拦截点 |
| GPS | 46个拦截点 |
| 电话/基站 | 169个拦截点 |
| WiFi BSSID上报点 | 15+ (login/push/network/request) |
| 安全区域融合 | 8个字段(fine+wifi+cell+cross × 2) |
| PatchProxy热替换 | 全链路覆盖(146,173个挂载点) |
