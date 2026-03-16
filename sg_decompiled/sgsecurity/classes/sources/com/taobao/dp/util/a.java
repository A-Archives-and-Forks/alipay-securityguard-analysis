package com.taobao.dp.util;

import com.alibaba.wireless.security.open.umid.IUMIDInitListenerEx;
import java.util.ArrayList;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private Object f42a = new Object();
    private ArrayList<IUMIDInitListenerEx> b = new ArrayList<>();
    private ArrayList<IUMIDInitListenerEx> c = new ArrayList<>();
    private ArrayList<IUMIDInitListenerEx> d = new ArrayList<>();

    public ArrayList<IUMIDInitListenerEx> a(int i) {
        ArrayList<IUMIDInitListenerEx> arrayList;
        synchronized (this.f42a) {
            if (i == 0) {
                arrayList = this.b;
                this.b = new ArrayList<>();
            } else if (i == 1) {
                arrayList = this.c;
                this.c = new ArrayList<>();
            } else if (i != 2) {
                arrayList = null;
            } else {
                arrayList = this.d;
                this.d = new ArrayList<>();
            }
        }
        return arrayList;
    }

    public void a(int i, IUMIDInitListenerEx iUMIDInitListenerEx) {
        synchronized (this.f42a) {
            ArrayList<IUMIDInitListenerEx> arrayList = i != 0 ? i != 1 ? i != 2 ? null : this.d : this.c : this.b;
            if (arrayList != null && iUMIDInitListenerEx != null) {
                arrayList.add(iUMIDInitListenerEx);
            }
        }
    }
}
