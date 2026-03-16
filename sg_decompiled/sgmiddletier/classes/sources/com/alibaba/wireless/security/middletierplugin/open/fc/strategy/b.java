package com.alibaba.wireless.security.middletierplugin.open.fc.strategy;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
enum b {
    WUA(1),
    NC(2),
    FL(4),
    LOGIN(8),
    DENY(16);

    private long a;

    b(long j) {
        this.a = 0L;
        this.a = j;
    }

    public long a() {
        return this.a;
    }
}
