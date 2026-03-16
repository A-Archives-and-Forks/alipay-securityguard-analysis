package com.taobao.wireless.security.miscplugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmisc/classes.dex */
public class PkgValidUtils {
    private static String a = "res/drawable/abc_wb_textfield_exf.jpg";

    /* JADX WARN: Code restructure failed: missing block: B:72:0x0120, code lost:
    
        if (r10 == false) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0122, code lost:
    
        r8.delete();
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0125, code lost:
    
        r9.printStackTrace();
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0128, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x00fa, code lost:
    
        if (r10 == false) goto L94;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0134 A[Catch: IOException -> 0x0130, TRY_LEAVE, TryCatch #7 {IOException -> 0x0130, blocks: (B:112:0x012c, B:102:0x0134), top: B:111:0x012c }] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x012c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0108 A[Catch: all -> 0x0129, TryCatch #6 {all -> 0x0129, blocks: (B:22:0x005f, B:23:0x0061, B:25:0x0067, B:27:0x0071, B:30:0x007e, B:31:0x0086, B:33:0x008d, B:35:0x0091, B:43:0x0095, B:81:0x00dc, B:83:0x00e2, B:84:0x00e5, B:61:0x0102, B:63:0x0108, B:64:0x010b), top: B:6:0x001b }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0118 A[Catch: IOException -> 0x0114, TRY_LEAVE, TryCatch #2 {IOException -> 0x0114, blocks: (B:79:0x0110, B:67:0x0118), top: B:78:0x0110 }] */
    /* JADX WARN: Removed duplicated region for block: B:77:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0110 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00e2 A[Catch: all -> 0x0129, TryCatch #6 {all -> 0x0129, blocks: (B:22:0x005f, B:23:0x0061, B:25:0x0067, B:27:0x0071, B:30:0x007e, B:31:0x0086, B:33:0x008d, B:35:0x0091, B:43:0x0095, B:81:0x00dc, B:83:0x00e2, B:84:0x00e5, B:61:0x0102, B:63:0x0108, B:64:0x010b), top: B:6:0x001b }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f2 A[Catch: IOException -> 0x00ee, TRY_LEAVE, TryCatch #8 {IOException -> 0x00ee, blocks: (B:96:0x00ea, B:87:0x00f2), top: B:95:0x00ea }] */
    /* JADX WARN: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x00ea A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v21 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v7, types: [java.util.zip.ZipInputStream] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r8v11, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r8v8, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r9v10, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r9v12, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r9v16, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r9v22, types: [java.util.zip.ZipOutputStream] */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean genPkgWithCfgJpg(String str, String str2, String str3) {
        ZipInputStream zipInputStream;
        IOException e;
        FileNotFoundException e2;
        File file;
        File file2;
        if (a.a(str, str2, str3) || a.a(str, str2)) {
            return false;
        }
        try {
            try {
                file2 = new File(str);
                str = new File(str2);
            } catch (Throwable th) {
                th = th;
            }
        } catch (FileNotFoundException e3) {
            e = e3;
            str = 0;
            zipInputStream = null;
        } catch (IOException e4) {
            e = e4;
            str = 0;
            zipInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            str = 0;
            str3 = 0;
        }
        try {
            if (str.exists()) {
                if (str.isDirectory()) {
                    return false;
                }
                str.delete();
            }
            str2 = new ZipOutputStream(new FileOutputStream((File) str));
            try {
                try {
                    str2.putNextEntry(new ZipEntry(a));
                    str2.write(str3.getBytes());
                    str2.closeEntry();
                    zipInputStream = new ZipInputStream(new FileInputStream(file2));
                    try {
                        byte[] bArr = new byte[4096];
                        while (true) {
                            ZipEntry nextEntry = zipInputStream.getNextEntry();
                            if (nextEntry == null) {
                                try {
                                    break;
                                } catch (Exception unused) {
                                }
                            } else if (!isPathSecurityValid(nextEntry.getName()) || !nextEntry.getName().equals(a)) {
                                str2.putNextEntry(new ZipEntry(nextEntry));
                                while (true) {
                                    int read = zipInputStream.read(bArr);
                                    if (read == -1) {
                                        break;
                                    }
                                    str2.write(bArr, 0, read);
                                }
                                str2.closeEntry();
                            }
                        }
                        zipInputStream.close();
                        try {
                            str2.close();
                        } catch (Exception unused2) {
                        }
                        if (str.exists() && str.length() > 0) {
                            return true;
                        }
                        str.delete();
                        return false;
                    } catch (FileNotFoundException e5) {
                        e2 = e5;
                        if (str.exists()) {
                            str.delete();
                        }
                        e2.printStackTrace();
                        if (zipInputStream != null) {
                            try {
                                zipInputStream.close();
                            } catch (IOException e6) {
                                e = e6;
                                boolean exists = str.exists();
                                file = str;
                            }
                        }
                        if (str2 != 0) {
                            return false;
                        }
                        str2.close();
                        return false;
                    } catch (IOException e7) {
                        e = e7;
                        if (str.exists()) {
                            str.delete();
                        }
                        e.printStackTrace();
                        if (zipInputStream != null) {
                            try {
                                zipInputStream.close();
                            } catch (IOException e8) {
                                e = e8;
                                boolean exists2 = str.exists();
                                file = str;
                            }
                        }
                        if (str2 != 0) {
                            return false;
                        }
                        str2.close();
                        return false;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    str3 = 0;
                    if (str3 != 0) {
                        try {
                            str3.close();
                        } catch (IOException e9) {
                            if (str.exists()) {
                                str.delete();
                            }
                            e9.printStackTrace();
                            throw th;
                        }
                    }
                    if (str2 != 0) {
                        str2.close();
                    }
                    throw th;
                }
            } catch (FileNotFoundException e10) {
                e2 = e10;
                zipInputStream = null;
            } catch (IOException e11) {
                e = e11;
                zipInputStream = null;
            }
        } catch (FileNotFoundException e12) {
            e = e12;
            zipInputStream = null;
            str = str;
            e2 = e;
            str2 = zipInputStream;
            if (str.exists()) {
            }
            e2.printStackTrace();
            if (zipInputStream != null) {
            }
            if (str2 != 0) {
            }
        } catch (IOException e13) {
            e = e13;
            zipInputStream = null;
            str = str;
            e = e;
            str2 = zipInputStream;
            if (str.exists()) {
            }
            e.printStackTrace();
            if (zipInputStream != null) {
            }
            if (str2 != 0) {
            }
        } catch (Throwable th4) {
            th = th4;
            str3 = 0;
            str = str;
            th = th;
            str2 = str3;
            if (str3 != 0) {
            }
            if (str2 != 0) {
            }
            throw th;
        }
    }

    public static boolean isPathSecurityValid(String str) {
        return (str.contains("..") || str.contains("\\") || str.contains("%")) ? false : true;
    }
}
