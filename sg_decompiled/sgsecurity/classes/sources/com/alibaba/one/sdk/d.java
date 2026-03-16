package com.alibaba.one.sdk;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.IBinder;
import android.os.Parcel;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.one.android.inner.DeviceInfoCapturer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public final class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Context f9a;

    public class a implements ServiceConnection {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ LinkedBlockingQueue f10a;

        public a(d dVar, LinkedBlockingQueue linkedBlockingQueue) {
            this.f10a = linkedBlockingQueue;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f10a.put(iBinder);
                DeviceInfoCapturer.a();
            } catch (Exception e) {
                Log.e("bindOppoService", "onServiceConnected", e);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            DeviceInfoCapturer.a();
            this.f10a.clear();
        }
    }

    public d(Context context) {
        this.f9a = context;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:3|4|5|6|(4:10|11|12|(11:14|(1:16)|17|18|19|20|21|22|(1:24)|25|26))|35|19|20|21|22|(0)|25|26) */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a8, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a9, code lost:
    
        r7.recycle();
        r6.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00af, code lost:
    
        throw r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00bc  */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void run() {
        Signature[] signatureArr;
        String str;
        MessageDigest messageDigest;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(1);
        a aVar = new a(this, linkedBlockingQueue);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
        intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
        if (!this.f9a.bindService(intent, aVar, 1)) {
            DeviceInfoCapturer.a();
            return;
        }
        String packageName = this.f9a.getPackageName();
        DeviceInfoCapturer.a();
        String str2 = null;
        try {
            signatureArr = this.f9a.getPackageManager().getPackageInfo(packageName, 64).signatures;
        } catch (PackageManager.NameNotFoundException unused) {
            signatureArr = null;
        }
        if (signatureArr != null && signatureArr.length > 0) {
            byte[] byteArray = signatureArr[0].toByteArray();
            try {
                messageDigest = MessageDigest.getInstance("SHA1");
            } catch (NoSuchAlgorithmException unused2) {
            }
            if (messageDigest != null) {
                byte[] digest = messageDigest.digest(byteArray);
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
                }
                str = sb.toString();
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                obtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                obtain.writeString(packageName);
                obtain.writeString(str);
                obtain.writeString("OUID");
                ((IBinder) linkedBlockingQueue.take()).transact(1, obtain, obtain2, 0);
                obtain2.readException();
                str2 = obtain2.readString();
                obtain2.recycle();
                obtain.recycle();
                if (TextUtils.isEmpty(str2)) {
                    str2 = "";
                }
                DeviceInfoCapturer.c = str2;
            }
        }
        str = null;
        Parcel obtain3 = Parcel.obtain();
        Parcel obtain22 = Parcel.obtain();
        obtain3.writeInterfaceToken("com.heytap.openid.IOpenID");
        obtain3.writeString(packageName);
        obtain3.writeString(str);
        obtain3.writeString("OUID");
        ((IBinder) linkedBlockingQueue.take()).transact(1, obtain3, obtain22, 0);
        obtain22.readException();
        str2 = obtain22.readString();
        obtain22.recycle();
        obtain3.recycle();
        if (TextUtils.isEmpty(str2)) {
        }
        DeviceInfoCapturer.c = str2;
    }
}
