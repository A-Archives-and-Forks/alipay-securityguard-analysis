package com.alibaba.wireless.security.middletierplugin.open.es;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.view.View;
import android.view.WindowManager;
import com.alibaba.wireless.security.framework.utils.UserTrackMethodJniBridge;
import com.alibaba.wireless.security.middletierplugin.open.fc.FCComponent;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import java.lang.reflect.Field;
import java.util.ArrayList;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public class SGWindowManager {
    private static ISecurityGuardPlugin a;
    private static Handler b;

    public static class a extends ArrayList {

        /* renamed from: com.alibaba.wireless.security.middletierplugin.open.es.SGWindowManager$a$a, reason: collision with other inner class name */
        class RunnableC0006a implements Runnable {
            final /* synthetic */ String a;

            RunnableC0006a(a aVar, String str) {
                this.a = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    SGWindowManager.a.getRouter().doCommand(70602, new Object[]{this.a});
                } catch (Exception unused) {
                }
            }
        }

        @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
        public int indexOf(Object obj) {
            try {
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(SystemClock.elapsedRealtime());
                if (stackTrace.length >= 5) {
                    for (int i = 5; i < 8; i++) {
                        stringBuffer.append("@" + i + "^" + stackTrace[i].getClassName() + "^" + stackTrace[i].getMethodName());
                    }
                }
                String stringBuffer2 = stringBuffer.toString();
                if (SGWindowManager.b == null) {
                    HandlerThread handlerThread = new HandlerThread("SWM");
                    handlerThread.start();
                    Handler unused = SGWindowManager.b = new Handler(handlerThread.getLooper());
                }
                if (SGWindowManager.b != null) {
                    SGWindowManager.b.post(new RunnableC0006a(this, stringBuffer2));
                }
            } catch (Throwable th) {
                SGWindowManager.a("ERROR:" + th.toString());
                UserTrackMethodJniBridge.addUtRecord("125", 0, 7, FCComponent.getPluginVersion(), 0L, "SGWindowManagerError IndexOf:" + th.toString(), th.getMessage(), th.getStackTrace().toString(), (String) null, (String) null);
            }
            return super.indexOf(obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(String str) {
    }

    public static void init(ISecurityGuardPlugin iSecurityGuardPlugin) {
        a = iSecurityGuardPlugin;
    }

    public static boolean windowMangerInit(Context context) {
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            if (windowManager == null) {
                return false;
            }
            Field declaredField = Class.forName("android.view.WindowManagerGlobal").getDeclaredField("mViews");
            declaredField.setAccessible(true);
            Field declaredField2 = Class.forName("android.view.WindowManagerImpl").getDeclaredField("mGlobal");
            declaredField2.setAccessible(true);
            Object obj = declaredField2.get(windowManager);
            ArrayList arrayList = (ArrayList) declaredField.get(obj);
            a aVar = new a();
            for (int i = 0; arrayList != null && i < arrayList.size(); i++) {
                aVar.add((View) arrayList.get(i));
            }
            declaredField.set(obj, aVar);
            a("init SGWindowManger succeed");
            return true;
        } catch (Throwable th) {
            a("ERROR:" + th.toString());
            UserTrackMethodJniBridge.addUtRecord("125", 0, 7, FCComponent.getPluginVersion(), 0L, "SGWindowManagerError init:" + th.toString(), th.getMessage(), th.getStackTrace().toString(), (String) null, (String) null);
            return false;
        }
    }
}
