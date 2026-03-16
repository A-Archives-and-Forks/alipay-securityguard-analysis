package com.alibaba.one.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Base64;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    public static String f17a = "";

    public static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f18a;
        public final /* synthetic */ CountDownLatch b;

        public a(Context context, CountDownLatch countDownLatch) {
            this.f18a = context;
            this.b = countDownLatch;
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0068, code lost:
        
            r8.f18a.unbindService(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x0065, code lost:
        
            if (r1 == false) goto L25;
         */
        /* JADX WARN: Code restructure failed: missing block: B:5:0x0054, code lost:
        
            if (r1 != false) goto L30;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            Throwable th;
            boolean z;
            b bVar = new b();
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            try {
                z = this.f18a.bindService(intent, bVar, 1);
                if (z) {
                    try {
                        this.f18a.getPackageManager().getPackageInfo("com.android.vending", 0);
                        IBinder a2 = bVar.a();
                        Parcel obtain = Parcel.obtain();
                        Parcel obtain2 = Parcel.obtain();
                        try {
                            obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                            a2.transact(1, obtain, obtain2, 0);
                            obtain2.readException();
                            String readString = obtain2.readString();
                            obtain2.recycle();
                            obtain.recycle();
                            k.f17a = readString;
                        } catch (Throwable th2) {
                            obtain2.recycle();
                            obtain.recycle();
                            throw th2;
                        }
                    } catch (Exception unused) {
                    } catch (Throwable th3) {
                        th = th3;
                        if (z) {
                            try {
                                this.f18a.unbindService(bVar);
                            } catch (Exception unused2) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception unused3) {
                z = false;
            } catch (Throwable th4) {
                th = th4;
                z = false;
            }
            this.b.countDown();
        }
    }

    public static final class b implements ServiceConnection {

        /* renamed from: a, reason: collision with root package name */
        public boolean f19a = false;
        public final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);

        public IBinder a() {
            if (this.f19a) {
                throw new IllegalStateException();
            }
            this.f19a = true;
            return this.b.take();
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public static String a(Context context) {
        if (!TextUtils.isEmpty(f17a)) {
            return f17a;
        }
        if (TextUtils.equals(Build.FINGERPRINT, new String(Base64.decode("RE9PVi9mdWxsX2JpcmRfNjc1MF9jNjZfbS9iaXJkXzY3NTBfYzY2X206Ni4wL01SQTU4Sy8xNDk5NDI5MTMyOnVzZXIvdGVzdC1rZXlzCg==", 0), "UTF-8"))) {
            return f17a;
        }
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new a(context, countDownLatch)).start();
        try {
            countDownLatch.await(50L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
        }
        return f17a;
    }
}
