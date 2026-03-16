package com.alibaba.wireless.security.middletierplugin.open.fc.strategy;

import java.util.HashMap;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmiddletier/classes.dex */
public interface IStrategyCallback {
    void onPreStrategy(long j, boolean z);

    void onStrategy(long j, a aVar, long j2, HashMap hashMap);
}
