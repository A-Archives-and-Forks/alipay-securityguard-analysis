    public static final void android_net_wifi_p2p_WifiP2pManager_requestPeers_proxy(Object obj, WifiP2pManager.Channel channel, WifiP2pManager.PeerListListener peerListListener) throws Throwable {
        ObjectArrayPool.Item obtain = ObjectArrayPool2.sInstance.obtain();
        try {
            Object[] objArr = obtain.data;
            objArr[0] = channel;
            objArr[1] = peerListListener;
            if (DexAOPCenter.sEnable) {
                DexAOPCenter.processInvoke(DexAOPPoints.INVOKE_android_net_wifi_p2p_WifiP2pManager_requestPeers_proxy, (WifiP2pManager) obj, (PointInterceptor.Invoker<WifiP2pManager>) ANDROID_NET_WIFI_P2P_WIFIP2PMANAGER$REQUESTPEERS$INVOKE_0.INVOKER, objArr);
            } else {
                ANDROID_NET_WIFI_P2P_WIFIP2PMANAGER$REQUESTPEERS$INVOKE_0.INVOKER.invokeMethod((WifiP2pManager) obj, objArr);
            }
        } finally {
            obtain.recycle();
        }
    }

    public static final void android_net_wifi_rtt_WifiRttManager_startRanging_proxy(Object obj, RangingRequest rangingRequest, Executor executor, RangingResultCallback rangingResultCallback) throws Throwable {
        ObjectArrayPool.Item obtain = ObjectArrayPool3.sInstance.obtain();
        try {
            Object[] objArr = obtain.data;
            objArr[0] = rangingRequest;
            objArr[1] = executor;
            objArr[2] = rangingResultCallback;
            if (DexAOPCenter.sEnable) {
                DexAOPCenter.processInvoke(DexAOPPoints.INVOKE_android_net_wifi_rtt_WifiRttManager_startRanging_proxy, (WifiRttManager) obj, (PointInterceptor.Invoker<WifiRttManager>) ANDROID_NET_WIFI_RTT_WIFIRTTMANAGER$STARTRANGING$INVOKE_0.INVOKER, objArr);
            } else {
                ANDROID_NET_WIFI_RTT_WIFIRTTMANAGER$STARTRANGING$INVOKE_0.INVOKER.invokeMethod((WifiRttManager) obj, objArr);
            }
        } finally {
            obtain.recycle();
        }
    }

    public static final String android_os_Build_getSerial_proxy() throws Throwable {
        return !DexAOPCenter.sEnable ? (String) ANDROID_OS_BUILD$GETSERIAL$INVOKE_0.INVOKER.invokeMethod(null, f172803a) : (String) DexAOPCenter.processInvoke(DexAOPPoints.INVOKE_android_os_Build_getSerial_proxy, (Object) null, ANDROID_OS_BUILD$GETSERIAL$INVOKE_0.INVOKER, f172803a);
    }

    public static final void android_telecom_TelecomManager_acceptRingingCall_proxy(Object obj) throws Throwable {
        if (DexAOPCenter.sEnable) {
            DexAOPCenter.processInvoke(DexAOPPoints.INVOKE_android_telecom_TelecomManager_acceptRingingCall_proxy, (TelecomManager) obj, (PointInterceptor.Invoker<TelecomManager>) ANDROID_TELECOM_TELECOMMANAGER$ACCEPTRINGINGCALL$INVOKE_1.INVOKER, f172803a);
        } else {
