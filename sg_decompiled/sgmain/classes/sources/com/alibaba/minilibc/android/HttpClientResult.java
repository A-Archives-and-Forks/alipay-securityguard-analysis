package com.alibaba.minilibc.android;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class HttpClientResult {
    public String mExceptionString;
    public int mResponseCode;
    public String mString;

    public HttpClientResult(String str, int i) {
        this.mString = str;
        this.mResponseCode = i;
    }

    public HttpClientResult(String str, int i, Throwable th) {
        this.mString = str;
        this.mResponseCode = i;
        if (th != null) {
            this.mExceptionString = th.toString();
        }
    }
}
