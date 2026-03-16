package com.taobao.wireless.security.adapter.common;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/* renamed from: com.taobao.wireless.security.adapter.common.г, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0012 {

    /* renamed from: а, reason: contains not printable characters */
    private FileChannel f30 = null;

    /* renamed from: б, reason: contains not printable characters */
    private FileLock f31 = null;

    /* renamed from: в, reason: contains not printable characters */
    private RandomAccessFile f32 = null;

    /* renamed from: г, reason: contains not printable characters */
    private File f33 = null;

    /* renamed from: д, reason: contains not printable characters */
    private boolean f34;

    /* renamed from: е, reason: contains not printable characters */
    private String f35;

    /* renamed from: ё, reason: contains not printable characters */
    private Context f36;

    public C0012(Context context, String str) {
        this.f34 = true;
        this.f35 = null;
        this.f36 = null;
        this.f36 = context;
        this.f35 = str;
        this.f34 = m39();
    }

    /* renamed from: в, reason: contains not printable characters */
    private boolean m39() {
        try {
            File filesDir = this.f36.getFilesDir();
            if ((filesDir != null && filesDir.exists()) || ((filesDir = this.f36.getFilesDir()) != null && filesDir.exists())) {
                this.f33 = new File(filesDir.getAbsolutePath() + "/" + this.f35);
                if (!this.f33.exists()) {
                    this.f33.createNewFile();
                }
            }
        } catch (Exception unused) {
            File file = this.f33;
            if (file != null && !file.exists()) {
                try {
                    this.f33.createNewFile();
                } catch (Exception unused2) {
                }
            }
        }
        File file2 = this.f33;
        return file2 != null && file2.exists();
    }

    /* renamed from: а, reason: contains not printable characters */
    public boolean m40() {
        if (!this.f34) {
            this.f34 = m39();
            if (!this.f34) {
                return true;
            }
        }
        try {
            if (this.f33 == null) {
                return false;
            }
            this.f32 = new RandomAccessFile(this.f33, "rw");
            this.f30 = this.f32.getChannel();
            this.f31 = this.f30.lock();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: б, reason: contains not printable characters */
    public boolean m41() {
        boolean z = true;
        if (!this.f34) {
            return true;
        }
        try {
            if (this.f31 != null) {
                this.f31.release();
                this.f31 = null;
            }
        } catch (IOException unused) {
            z = false;
        }
        try {
            if (this.f30 != null) {
                this.f30.close();
                this.f30 = null;
            }
        } catch (IOException unused2) {
            z = false;
        }
        try {
            if (this.f32 == null) {
                return z;
            }
            this.f32.close();
            this.f32 = null;
            return z;
        } catch (IOException unused3) {
            return false;
        }
    }
}
