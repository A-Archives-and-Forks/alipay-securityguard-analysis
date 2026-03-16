package com.alibaba.wireless.security.middletierplugin.open.fc.strategy;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public enum a {
    SUCCESS(0),
    FAIL(1),
    CANCEL(2),
    RETRY(4),
    TIMEOUT(8);

    private int a;

    a(int i) {
        this.a = 0;
        this.a = i;
    }

    public int a() {
        return this.a;
    }
}
