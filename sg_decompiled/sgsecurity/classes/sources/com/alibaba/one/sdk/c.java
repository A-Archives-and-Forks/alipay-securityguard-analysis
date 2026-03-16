package com.alibaba.one.sdk;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgsecurity/classes.dex */
public class c implements FileFilter {
    @Override // java.io.FileFilter
    public boolean accept(File file) {
        return Pattern.matches("cpu[0-9]+", file.getName());
    }
}
