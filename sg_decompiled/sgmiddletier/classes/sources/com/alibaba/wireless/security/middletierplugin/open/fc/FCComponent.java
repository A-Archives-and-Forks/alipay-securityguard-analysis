package com.alibaba.wireless.security.middletierplugin.open.fc;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.alibaba.wireless.security.middletierplugin.SecurityGuardMiddleTierPlugin;
import com.alibaba.wireless.security.middletierplugin.open.fc.strategy.IStrategyCallback;
import com.alibaba.wireless.security.middletierplugin.open.fc.strategy.StrategyCenter;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.alibaba.wireless.security.open.middletier.fc.FCAction;
import com.alibaba.wireless.security.open.middletier.fc.IFCActionCallback;
import com.alibaba.wireless.security.open.middletier.fc.IFCComponent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public class FCComponent {
    private static Object a;
    private static ISecurityGuardPlugin b;
    private static FCComponent c;
    private static StrategyCenter d;
    private static Handler e;

    static class a implements InvocationHandler {
        a() {
        }

        private boolean a(Object obj, Object obj2) {
            if (obj2 != null && obj2.getClass() == obj.getClass()) {
                try {
                    return equals(Proxy.getInvocationHandler(obj2));
                } catch (Exception unused) {
                }
            }
            return false;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            char c;
            String name = method.getName();
            switch (name.hashCode()) {
                case -1776922004:
                    if (name.equals("toString")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -1295482945:
                    if (name.equals("equals")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case -674205102:
                    if (name.equals("getFCPluginVersion")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case -557739155:
                    if (name.equals("processFCContent")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 109328029:
                    if (name.equals("setUp")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 147696667:
                    if (name.equals("hashCode")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 278841396:
                    if (name.equals("needFCProcessOrNot")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 883586761:
                    if (name.equals("processFCContentV2")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1678611920:
                    if (name.equals("needFCProcessOrNotV2")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    FCComponent.c.setUp((Context) objArr[0], (HashMap) objArr[1]);
                    return null;
                case 1:
                    return Boolean.valueOf(FCComponent.c.needFCProcessOrNot(((Integer) objArr[0]).intValue(), (HashMap) objArr[1], (IFCComponent.ResponseHeaderType) objArr[2]));
                case 2:
                    return Boolean.valueOf(FCComponent.c.needFCProcessOrNotV2(((Integer) objArr[0]).intValue(), (String) objArr[1]));
                case 3:
                    FCComponent.c.processFCContent(((Integer) objArr[0]).intValue(), (HashMap) objArr[1], (IFCActionCallback) objArr[2], (IFCComponent.ResponseHeaderType) objArr[3]);
                    return null;
                case 4:
                    FCComponent.c.processFCContentV2((String) objArr[0], (IFCActionCallback) objArr[1]);
                    return null;
                case 5:
                    return FCComponent.c.getFCPluginVersion();
                case 6:
                    return obj.getClass().getName() + "&ID=" + hashCode();
                case 7:
                    return Boolean.valueOf(a(obj, objArr[0]));
                case '\b':
                    return Integer.valueOf(hashCode());
                default:
                    return null;
            }
        }
    }

    class b implements IStrategyCallback {
        final /* synthetic */ IFCActionCallback a;

        b(IFCActionCallback iFCActionCallback) {
            this.a = iFCActionCallback;
        }

        @Override // com.alibaba.wireless.security.middletierplugin.open.fc.strategy.IStrategyCallback
        public void onPreStrategy(long j, boolean z) {
            String str = "[processFCContent][processFCContent] onPreStrategy : " + j + " , hasUI = " + z;
            this.a.onPreAction(j, z);
        }

        @Override // com.alibaba.wireless.security.middletierplugin.open.fc.strategy.IStrategyCallback
        public void onStrategy(long j, com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a aVar, long j2, HashMap hashMap) {
            StringBuilder sb = new StringBuilder();
            sb.append("[processFCContent][processFCContent] onStrategy : ");
            sb.append(j);
            sb.append(" , action = ");
            sb.append(aVar);
            sb.append(" ,subAction = ");
            sb.append(j2);
            sb.append(" ,actionInfo = ");
            sb.append(hashMap == null ? "null" : hashMap.toString());
            sb.toString();
            this.a.onAction(j, FCComponent.this.a(aVar), j2, hashMap);
        }
    }

    class c implements IStrategyCallback {
        final /* synthetic */ IFCActionCallback a;

        c(IFCActionCallback iFCActionCallback) {
            this.a = iFCActionCallback;
        }

        @Override // com.alibaba.wireless.security.middletierplugin.open.fc.strategy.IStrategyCallback
        public void onPreStrategy(long j, boolean z) {
            String str = "[FCComponent][processFCContentV2] onPreStrategy : " + j + " , hasUI = " + z;
            this.a.onPreAction(j, z);
        }

        @Override // com.alibaba.wireless.security.middletierplugin.open.fc.strategy.IStrategyCallback
        public void onStrategy(long j, com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a aVar, long j2, HashMap hashMap) {
            StringBuilder sb = new StringBuilder();
            sb.append("[FCComponent][processFCContentV2] onStrategy : ");
            sb.append(j);
            sb.append(" , action = ");
            sb.append(aVar);
            sb.append(" ,subAction = ");
            sb.append(j2);
            sb.append(" ,actionInfo = ");
            sb.append(hashMap == null ? "null" : hashMap.toString());
            sb.toString();
            this.a.onAction(j, FCComponent.this.a(aVar), j2, hashMap);
        }
    }

    class d implements Runnable {
        final /* synthetic */ HashMap a;
        final /* synthetic */ int b;

        d(FCComponent fCComponent, HashMap hashMap, int i) {
            this.a = hashMap;
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                HashMap hashMap = new HashMap();
                for (String str : this.a.keySet()) {
                    hashMap.put(str, this.a.get(str) instanceof List ? ((List) this.a.get(str)).get(0) : this.a.get(str));
                }
                hashMap.put("resCode", Integer.valueOf(this.b));
                FCComponent.b.getRouter().doCommand(70504, new Object[]{Integer.valueOf(this.b), hashMap});
            } catch (Exception unused) {
            }
        }
    }

    public FCComponent(ISecurityGuardPlugin iSecurityGuardPlugin) {
        b = iSecurityGuardPlugin;
        com.alibaba.wireless.security.middletierplugin.open.fc.a.a(b);
        com.alibaba.wireless.security.middletierplugin.open.fc.d.a(b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FCAction.FCMainAction a(com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a aVar) {
        return aVar == com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a.SUCCESS ? FCAction.FCMainAction.SUCCESS : aVar == com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a.FAIL ? FCAction.FCMainAction.FAIL : aVar == com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a.CANCEL ? FCAction.FCMainAction.CANCEL : aVar == com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a.RETRY ? FCAction.FCMainAction.RETRY : aVar == com.alibaba.wireless.security.middletierplugin.open.fc.strategy.a.TIMEOUT ? FCAction.FCMainAction.TIMEOUT : FCAction.FCMainAction.FAIL;
    }

    private void a(int i, HashMap hashMap) {
        if (e == null) {
            synchronized (FCComponent.class) {
                if (e == null) {
                    HandlerThread handlerThread = new HandlerThread("FC");
                    handlerThread.start();
                    e = new Handler(handlerThread.getLooper());
                }
            }
        }
        if (e != null) {
            e.post(new d(this, hashMap, i));
        }
    }

    public static String getPluginVersion() {
        try {
            return b.getSGPluginInfo().getVersion();
        } catch (Exception e2) {
            String str = "getPluginVersion : " + e2.getMessage();
            return "";
        }
    }

    public static Object getProxyInstance(Class cls, ISecurityGuardPlugin iSecurityGuardPlugin) {
        if (a == null) {
            synchronized (FCComponent.class) {
                if (a == null && iSecurityGuardPlugin != null) {
                    c = new FCComponent(iSecurityGuardPlugin);
                    a = SecurityGuardMiddleTierPlugin.getProxyInstance(cls, new a());
                }
            }
        }
        return a;
    }

    public String getFCPluginVersion() {
        try {
            return b.getSGPluginInfo().getVersion();
        } catch (Exception e2) {
            String str = "getPluginVersion : " + e2.getMessage();
            return "";
        }
    }

    public int init(ISecurityGuardPlugin iSecurityGuardPlugin, Object... objArr) {
        b = iSecurityGuardPlugin;
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean needFCProcessOrNot(int i, HashMap hashMap, IFCComponent.ResponseHeaderType responseHeaderType) {
        int i2;
        StrategyCenter strategyCenter;
        if (i == 419 && com.alibaba.wireless.security.middletierplugin.open.fc.a.a(responseHeaderType.ordinal(), hashMap)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Strategycenter=");
            sb.append(d == null ? "0" : "1");
            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100105, 0, 7L, "", "FccomponentNeedfcprocessornot", sb.toString(), "", com.alibaba.wireless.security.middletierplugin.open.fc.c.a(responseHeaderType.ordinal(), hashMap, null, "bx-uuid", "uuid="), 7, false);
        }
        if (i == 419) {
            i2 = 100153;
        } else {
            if (i != 200) {
                if (i == 420) {
                    i2 = 100178;
                }
                strategyCenter = d;
                if (strategyCenter != null) {
                    throw new SecException("StrategyCenter not init!", 2302);
                }
                boolean needFCProcessOrNot = strategyCenter.needFCProcessOrNot(i, hashMap);
                a(i, hashMap);
                return needFCProcessOrNot;
            }
            i2 = 100154;
        }
        com.alibaba.wireless.security.middletierplugin.open.fc.d.a(i2);
        strategyCenter = d;
        if (strategyCenter != null) {
        }
    }

    public boolean needFCProcessOrNotV2(int i, String str) {
        StrategyCenter strategyCenter = d;
        if (strategyCenter == null || i == 0) {
            throw new SecException("StrategyCenter not init!", 2302);
        }
        return strategyCenter.needFCProcessOrNotV2(i, str);
    }

    public void processFCContent(int i, HashMap hashMap, IFCActionCallback iFCActionCallback, IFCComponent.ResponseHeaderType responseHeaderType) {
        if (d == null) {
            if (i == 419) {
                com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100106, 0, 0L, "", "FcomponentProcessfccontent", "Strategycenter=0", "", com.alibaba.wireless.security.middletierplugin.open.fc.c.a(responseHeaderType.ordinal(), hashMap, null, "bx-uuid", "uuid="), 7, false);
            }
            com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100106);
            throw new SecException("StrategyCenter not init!", 2302);
        }
        if (iFCActionCallback != null) {
            d.processFCContent(i, hashMap, new b(iFCActionCallback), responseHeaderType.ordinal());
            com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100155);
        } else {
            if (i == 419) {
                com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100106, 0, 0L, "", "FcomponentProcessfccontent", "Actioncallback=0", "", com.alibaba.wireless.security.middletierplugin.open.fc.c.a(responseHeaderType.ordinal(), hashMap, null, "bx-uuid", "uuid="), 7, false);
            }
            com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100106);
            throw new SecException("processFCContent no cb!", 2301);
        }
    }

    public void processFCContentV2(String str, IFCActionCallback iFCActionCallback) {
        if (d == null) {
            throw new SecException("StrategyCenter not init!", 2302);
        }
        if (iFCActionCallback == null) {
            throw new SecException("processFCContent no cb!", 2301);
        }
        d.processFCContentV2(str, new c(iFCActionCallback));
    }

    public void setUp(Context context, HashMap hashMap) {
        if (context == null || hashMap == null) {
            com.alibaba.wireless.security.middletierplugin.open.fc.a.a(100104, 2301, 0L, "", "FccomponentSetup", "", "", "", 3, false);
            com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100104);
            throw new SecException("setUp param error!", 2301);
        }
        synchronized (FCComponent.class) {
            if (d == null) {
                d = new StrategyCenter(context, hashMap);
            }
        }
        com.alibaba.wireless.security.middletierplugin.open.fc.d.a(100152);
    }
}
