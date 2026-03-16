package com.alibaba.one.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import android.util.Log;
import com.alibaba.one.android.inner.DeviceInfoCapturer;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public final class b implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Context f7a;

    public class a implements ServiceConnection {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ LinkedBlockingQueue f8a;

        public a(b bVar, LinkedBlockingQueue linkedBlockingQueue) {
            this.f8a = linkedBlockingQueue;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f8a.put(iBinder);
                DeviceInfoCapturer.a();
            } catch (Exception e) {
                Log.e("bindHwService", "onServiceConnected", e);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            DeviceInfoCapturer.a();
            this.f8a.clear();
        }
    }

    public b(Context context) {
        this.f7a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(1);
        a aVar = new a(this, linkedBlockingQueue);
        Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
        intent.setPackage("com.huawei.hwid");
        if (!this.f7a.bindService(intent, aVar, 1)) {
            DeviceInfoCapturer.a();
            return;
        }
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        String str = null;
        try {
            try {
                obtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                ((IBinder) linkedBlockingQueue.take()).transact(1, obtain, obtain2, 0);
                obtain2.readException();
                str = obtain2.readString();
            } catch (Exception unused) {
                DeviceInfoCapturer.a();
            }
            obtain2.recycle();
            obtain.recycle();
            DeviceInfoCapturer.b = str;
        } catch (Throwable th) {
            obtain2.recycle();
            obtain.recycle();
            throw th;
        }
    }
}
