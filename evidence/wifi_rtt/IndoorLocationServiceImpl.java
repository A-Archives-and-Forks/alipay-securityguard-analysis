package com.alipay.mobilelbs.biz.impl;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.alipay.dexaop.DexAOPCenter;
import com.alipay.dexaop.DexAOPEntry;
import com.alipay.dexaop.stub.android.os.Handler_handleMessage_androidosMessage_stub;
import com.alipay.instantrun.ChangeQuickRedirect;
import com.alipay.instantrun.ConstructorCode;
import com.alipay.instantrun.PatchProxy;
import com.alipay.instantrun.PatchProxyResult;
import com.alipay.mobile.common.lbs.BuildConfig;
import com.alipay.mobile.common.lbs.LBSLogger;
import com.alipay.mobile.framework.MpaasClassInfo;
import com.alipay.mobile.framework.region.RegionChangeParam;
import com.alipay.mobile.framework.service.IndoorLocationService;

@MpaasClassInfo(BundleName = BuildConfig.BUNDLE_NAME, ExportJarName = "unknown", Level = "product", Product = ":android-phone-mobilecommon-lbs")
/* loaded from: classes14.dex */
public class IndoorLocationServiceImpl extends IndoorLocationService {
    private static final String TAG = "IndoorLocationServiceImpl";

    /* renamed from: 支, reason: contains not printable characters */
    public static ChangeQuickRedirect f101792;
    private Context mContext;
    private IndoorLocationService.IndoorLocationListener mIndoorLocationListener;
    private InnerHandler mInnerHandler;
    private SDKInitHandler mSDKInitHandler;

    @MpaasClassInfo(BundleName = BuildConfig.BUNDLE_NAME, ExportJarName = "unknown", Level = "product", Product = ":android-phone-mobilecommon-lbs")
    public class InnerHandler extends Handler implements Handler_handleMessage_androidosMessage_stub {

        /* renamed from: 支, reason: contains not printable characters */
        public static ChangeQuickRedirect f101793;
        private IndoorLocationService.IndoorLocationListener mlistener;
        final /* synthetic */ IndoorLocationServiceImpl this$0;

        public InnerHandler(IndoorLocationServiceImpl indoorLocationServiceImpl, Context context, IndoorLocationService.IndoorLocationListener indoorLocationListener) {
            ConstructorCode proxy;
            ChangeQuickRedirect changeQuickRedirect = f101793;
            if (changeQuickRedirect != null && (proxy = PatchProxy.proxy(changeQuickRedirect, "0", new Object[]{indoorLocationServiceImpl, context, indoorLocationListener})) != null) {
                this.this$0 = (IndoorLocationServiceImpl) proxy.getFieldValue(0);
                proxy.afterSuper(this);
            } else {
                this.this$0 = indoorLocationServiceImpl;
                this.mlistener = indoorLocationListener;
                LBSLogger.info(IndoorLocationServiceImpl.TAG, "InnerHandler");
            }
        }

        private /* synthetic */ void __handleMessage_stub_private(Message message) {
            Message message2;
            ChangeQuickRedirect changeQuickRedirect = f101793;
            if (changeQuickRedirect != null) {
                message2 = message;
                if (PatchProxy.proxy(message2, this, changeQuickRedirect, "2", Message.class, Void.TYPE).isSupported) {
                    return;
                }
            } else {
                message2 = message;
            }
            LBSLogger.info(IndoorLocationServiceImpl.TAG, "InnerHandler.handleMessage Message = " + message2.what);
            LBSLogger.info(IndoorLocationServiceImpl.TAG, "InnerHandler.handleMessage msg = " + message2.toString());
        }

        @Override // com.alipay.dexaop.stub.android.os.Handler_handleMessage_androidosMessage_stub
        public /* synthetic */ void __handleMessage_stub(Message message) {
            Message message2;
            ChangeQuickRedirect changeQuickRedirect = f101793;
            if (changeQuickRedirect != null) {
                message2 = message;
                if (PatchProxy.proxy(message2, this, changeQuickRedirect, "1", Message.class, Void.TYPE).isSupported) {
                    return;
                }
            } else {
                message2 = message;
            }
            __handleMessage_stub_private(message2);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Message message2;
            ChangeQuickRedirect changeQuickRedirect = f101793;
            if (changeQuickRedirect != null) {
                message2 = message;
                if (PatchProxy.proxy(message2, this, changeQuickRedirect, "3", Message.class, Void.TYPE).isSupported) {
                    return;
                }
            } else {
                message2 = message;
            }
            if ((DexAOPCenter.sFlag & 2) == 0 || getClass() != InnerHandler.class) {
                __handleMessage_stub_private(message2);
            } else {
                DexAOPEntry.android_os_Handler_handleMessage_proxy(InnerHandler.class, this, message2);
            }
        }
    }

    @MpaasClassInfo(BundleName = BuildConfig.BUNDLE_NAME, ExportJarName = "unknown", Level = "product", Product = ":android-phone-mobilecommon-lbs")
    public class SDKInitHandler extends Handler implements Handler_handleMessage_androidosMessage_stub {

        /* renamed from: 支, reason: contains not printable characters */
        public static ChangeQuickRedirect f101794;
        final /* synthetic */ IndoorLocationServiceImpl this$0;

        public SDKInitHandler(IndoorLocationServiceImpl indoorLocationServiceImpl) {
            ConstructorCode proxy;
            ChangeQuickRedirect changeQuickRedirect = f101794;
            if (changeQuickRedirect == null || (proxy = PatchProxy.proxy(changeQuickRedirect, "0", new Object[]{indoorLocationServiceImpl})) == null) {
                this.this$0 = indoorLocationServiceImpl;
                LBSLogger.info(IndoorLocationServiceImpl.TAG, "SDKInitHandler");
            } else {
                this.this$0 = (IndoorLocationServiceImpl) proxy.getFieldValue(0);
                proxy.afterSuper(this);
            }
        }

        private /* synthetic */ void __handleMessage_stub_private(Message message) {
            SDKInitHandler sDKInitHandler;
            Message message2;
            ChangeQuickRedirect changeQuickRedirect = f101794;
            if (changeQuickRedirect != null) {
                sDKInitHandler = this;
                message2 = message;
                if (PatchProxy.proxy(message2, sDKInitHandler, changeQuickRedirect, "2", Message.class, Void.TYPE).isSupported) {
                    return;
                }
            } else {
                sDKInitHandler = this;
                message2 = message;
            }
            LBSLogger.info(IndoorLocationServiceImpl.TAG, "SDKInitHandler.handleMessage");
            IndoorLocationServiceImpl.access$000(sDKInitHandler.this$0, message2);
        }

        @Override // com.alipay.dexaop.stub.android.os.Handler_handleMessage_androidosMessage_stub
        public /* synthetic */ void __handleMessage_stub(Message message) {
            Message message2;
            ChangeQuickRedirect changeQuickRedirect = f101794;
            if (changeQuickRedirect != null) {
                message2 = message;
                if (PatchProxy.proxy(message2, this, changeQuickRedirect, "1", Message.class, Void.TYPE).isSupported) {
                    return;
                }
            } else {
                message2 = message;
            }
            __handleMessage_stub_private(message2);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Message message2;
            ChangeQuickRedirect changeQuickRedirect = f101794;
            if (changeQuickRedirect != null) {
                message2 = message;
                if (PatchProxy.proxy(message2, this, changeQuickRedirect, "3", Message.class, Void.TYPE).isSupported) {
                    return;
                }
            } else {
                message2 = message;
            }
            if ((DexAOPCenter.sFlag & 2) == 0 || getClass() != SDKInitHandler.class) {
                __handleMessage_stub_private(message2);
            } else {
                DexAOPEntry.android_os_Handler_handleMessage_proxy(SDKInitHandler.class, this, message2);
            }
        }
    }

    public IndoorLocationServiceImpl() {
        ConstructorCode proxy;
        ChangeQuickRedirect changeQuickRedirect = f101792;
        if (changeQuickRedirect == null || (proxy = PatchProxy.proxy(changeQuickRedirect, "0")) == null) {
            return;
        }
        proxy.afterSuper(this);
    }

    public static /* synthetic */ void access$000(IndoorLocationServiceImpl indoorLocationServiceImpl, Message message) {
        ChangeQuickRedirect changeQuickRedirect = f101792;
        if (changeQuickRedirect == null || !PatchProxy.proxy(new Object[]{indoorLocationServiceImpl, message, IndoorLocationServiceImpl.class, Message.class, Void.TYPE}, (Object) null, changeQuickRedirect, "1").isSupported) {
            indoorLocationServiceImpl.onLocated(message);
        }
    }

    @Override // com.alipay.mobile.framework.service.IndoorLocationService
    public boolean attach(Context context, IndoorLocationService.IndoorLocationListener indoorLocationListener, String str) {
        ChangeQuickRedirect changeQuickRedirect = f101792;
        if (changeQuickRedirect != null) {
            PatchProxyResult proxy = PatchProxy.proxy(new Object[]{context, indoorLocationListener, str, Context.class, IndoorLocationService.IndoorLocationListener.class, String.class, Boolean.TYPE}, this, changeQuickRedirect, "2");
            if (proxy.isSupported) {
                return ((Boolean) proxy.result).booleanValue();
            }
        }
        return attach(context, indoorLocationListener, str, null);
    }

    @Override // com.alipay.mobile.framework.service.IndoorLocationService
    public boolean attach(Context context, IndoorLocationService.IndoorLocationListener indoorLocationListener, String str, String str2) {
        ChangeQuickRedirect changeQuickRedirect = f101792;
        if (changeQuickRedirect != null) {
            PatchProxyResult proxy = PatchProxy.proxy(new Object[]{context, indoorLocationListener, str, str2, Context.class, IndoorLocationService.IndoorLocationListener.class, String.class, String.class, Boolean.TYPE}, this, changeQuickRedirect, "3");
            if (proxy.isSupported) {
                return ((Boolean) proxy.result).booleanValue();
            }
        }
        LBSLogger.info(TAG, "attach type = " + str2);
        return false;
    }

    @Override // com.alipay.mobile.framework.service.IndoorLocationService
    public void detach(IndoorLocationService.IndoorLocationListener indoorLocationListener) {
        IndoorLocationServiceImpl indoorLocationServiceImpl;
        ChangeQuickRedirect changeQuickRedirect = f101792;
        if (changeQuickRedirect != null) {
            indoorLocationServiceImpl = this;
            if (PatchProxy.proxy(indoorLocationListener, indoorLocationServiceImpl, changeQuickRedirect, "4", IndoorLocationService.IndoorLocationListener.class, Void.TYPE).isSupported) {
                return;
            }
        } else {
            indoorLocationServiceImpl = this;
        }
        LBSLogger.info(TAG, "detach");
        indoorLocationServiceImpl.mIndoorLocationListener = null;
        indoorLocationServiceImpl.mSDKInitHandler = null;
        indoorLocationServiceImpl.mInnerHandler = null;
    }

    @Override // com.alipay.mobile.framework.service.MicroService
    public void onCreate(Bundle bundle) {
        ChangeQuickRedirect changeQuickRedirect = f101792;
        if (changeQuickRedirect != null) {
            boolean z = PatchProxy.proxy(bundle, this, changeQuickRedirect, "5", Bundle.class, Void.TYPE).isSupported;
        }
    }

    @Override // com.alipay.mobile.framework.service.MicroService
    public void onDestroy(Bundle bundle) {
        ChangeQuickRedirect changeQuickRedirect = f101792;
        if (changeQuickRedirect != null && PatchProxy.proxy(bundle, this, changeQuickRedirect, "6", Bundle.class, Void.TYPE).isSupported) {
            return;
        }
        detach(null);
    }

    public void onLocated(Message message) {
        IndoorLocationServiceImpl indoorLocationServiceImpl;
        Message message2;
        ChangeQuickRedirect changeQuickRedirect = f101792;
        if (changeQuickRedirect != null) {
            indoorLocationServiceImpl = this;
            message2 = message;
            if (PatchProxy.proxy(message2, indoorLocationServiceImpl, changeQuickRedirect, "7", Message.class, Void.TYPE).isSupported) {
                return;
            }
        } else {
            indoorLocationServiceImpl = this;
            message2 = message;
        }
        if (indoorLocationServiceImpl.mIndoorLocationListener != null) {
            LBSLogger.info(TAG, "onLocated mlistener.onLocationFail(msg.what) msg.what = " + message2.what);
            indoorLocationServiceImpl.mIndoorLocationListener.onLocationFail(message2.what);
        }
    }

    @Override // com.alipay.mobile.framework.service.ext.ExternalService, com.alipay.mobile.framework.region.MultiRegionAware
    public void onRegionChangeEvent(int i2, RegionChangeParam regionChangeParam) {
        ChangeQuickRedirect changeQuickRedirect = f101792;
        if (changeQuickRedirect == null || !PatchProxy.proxy(new Object[]{Integer.valueOf(i2), regionChangeParam, Integer.TYPE, RegionChangeParam.class, Void.TYPE}, this, changeQuickRedirect, "8").isSupported) {
            super.onRegionChangeEvent(i2, regionChangeParam);
        }
    }

    @Override // com.alipay.mobile.framework.service.ext.ExternalService, com.alipay.mobile.framework.region.MultiRegionAware
    public boolean surviveRegionChange(String str, String str2) {
        ChangeQuickRedirect changeQuickRedirect = f101792;
        if (changeQuickRedirect != null) {
            PatchProxyResult proxy = PatchProxy.proxy(new Object[]{str, str2, String.class, String.class, Boolean.TYPE}, this, changeQuickRedirect, "9");
            if (proxy.isSupported) {
                return ((Boolean) proxy.result).booleanValue();
            }
        }
        return super.surviveRegionChange(str, str2);
    }
}
