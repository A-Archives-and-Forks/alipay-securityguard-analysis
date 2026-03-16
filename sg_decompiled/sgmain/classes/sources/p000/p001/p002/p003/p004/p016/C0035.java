package p000.p001.p002.p003.p004.p016;

import android.util.Base64;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.alibaba.wireless.security.open.statickeyencrypt.IStaticKeyEncryptComponent;
import com.taobao.wireless.security.adapter.common.SPUtility2;

/* renamed from: а.а.а.а.а.л.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0035 implements IStaticKeyEncryptComponent {

    /* renamed from: а, reason: contains not printable characters */
    private ISecurityGuardPlugin f87 = null;

    public C0035(ISecurityGuardPlugin iSecurityGuardPlugin) {
        init(iSecurityGuardPlugin, new Object[0]);
    }

    /* renamed from: а, reason: contains not printable characters */
    private int m99(String str, byte[] bArr) throws SecException {
        if (str == null || str.length() <= 0 || bArr == null || bArr.length <= 0) {
            throw new SecException(701);
        }
        byte[] m104 = m104(704, str.getBytes(), bArr);
        if (m104 != null && m104.length > 0) {
            String readFromSPUnified = SPUtility2.readFromSPUnified("StaticKey", str, null);
            if (SPUtility2.saveToSPUnified("StaticKey", str, new String(m104), true)) {
                return (readFromSPUnified == null || readFromSPUnified.length() <= 0) ? 1 : 2;
            }
        }
        return 0;
    }

    /* renamed from: а, reason: contains not printable characters */
    private boolean m100(String str) {
        String readFromSPUnified = SPUtility2.readFromSPUnified("StaticKey", str, null);
        return readFromSPUnified != null && readFromSPUnified.length() > 0;
    }

    /* renamed from: а, reason: contains not printable characters */
    private byte[] m101(int i, int i2, byte[] bArr, byte[] bArr2) {
        return (byte[]) this.f87.getRouter().doCommand(10605, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), bArr, bArr2});
    }

    /* renamed from: а, reason: contains not printable characters */
    private byte[] m102(int i, String str, String str2) throws SecException {
        String readFromSPUnified = SPUtility2.readFromSPUnified("StaticKey", str, null);
        String readFromSPUnified2 = SPUtility2.readFromSPUnified("StaticKey", str2, null);
        if (readFromSPUnified == null || readFromSPUnified.length() == 0) {
            throw new SecException(703);
        }
        if (readFromSPUnified2 == null || readFromSPUnified2.length() == 0) {
            throw new SecException(703);
        }
        byte[] m101 = m101(703, i, readFromSPUnified.getBytes(), readFromSPUnified2.getBytes());
        if (m101 == null || m101.length <= 0) {
            return m101;
        }
        try {
            return Base64.decode(m101, 0);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: а, reason: contains not printable characters */
    private byte[] m103(int i, String str, byte[] bArr) throws SecException {
        String readFromSPUnified = SPUtility2.readFromSPUnified("StaticKey", str, null);
        if (readFromSPUnified == null || readFromSPUnified.length() <= 0) {
            throw new SecException(703);
        }
        return m101(702, i, readFromSPUnified.getBytes(), Base64.encode(bArr, 2));
    }

    /* renamed from: а, reason: contains not printable characters */
    private byte[] m104(int i, byte[] bArr, byte[] bArr2) {
        return (byte[]) this.f87.getRouter().doCommand(10605, new Object[]{Integer.valueOf(i), bArr, bArr2});
    }

    /* renamed from: б, reason: contains not printable characters */
    private int m105(String str) {
        if (SPUtility2.readFromSPUnified("StaticKey", str, null) == null) {
            return 2;
        }
        return SPUtility2.removeFromSPUnified("StaticKey", str, true) ? 1 : 0;
    }

    /* renamed from: б, reason: contains not printable characters */
    private byte[] m106(int i, String str, byte[] bArr) throws SecException {
        String readFromSPUnified = SPUtility2.readFromSPUnified("StaticKey", str, null);
        if (readFromSPUnified == null || readFromSPUnified.length() <= 0) {
            throw new SecException(703);
        }
        byte[] m101 = m101(701, i, readFromSPUnified.getBytes(), bArr);
        return (m101 == null || m101.length <= 0) ? m101 : Base64.decode(m101, 0);
    }

    public byte[] decrypt(int i, String str, byte[] bArr) throws SecException {
        if (str == null || str.length() <= 0 || bArr == null || bArr.length <= 0 || i < 16 || i > 18) {
            throw new SecException(701);
        }
        return m103(i, str, bArr);
    }

    public byte[] encrypt(int i, String str, byte[] bArr) throws SecException {
        if (str == null || str.length() <= 0 || bArr == null || bArr.length <= 0 || i < 16 || i > 18) {
            throw new SecException(701);
        }
        return m106(i, str, bArr);
    }

    public byte[] encryptSecretData(int i, String str, String str2) throws SecException {
        if (str == null || str.length() <= 0 || str2 == null || str2.length() <= 0 || i < 16 || i > 18) {
            throw new SecException(701);
        }
        return m102(i, str, str2);
    }

    public int init(ISecurityGuardPlugin iSecurityGuardPlugin, Object... objArr) {
        this.f87 = iSecurityGuardPlugin;
        return 0;
    }

    public boolean isSecretExist(String str) throws SecException {
        if (str == null || str.length() <= 0) {
            throw new SecException(701);
        }
        return m100(str);
    }

    public int removeSecret(String str) throws SecException {
        if (str == null || str.length() <= 0) {
            throw new SecException(701);
        }
        return m105(str);
    }

    public int saveSecret(String str, byte[] bArr) throws SecException {
        if (str == null || str.length() <= 0 || bArr == null || bArr.length <= 0) {
            throw new SecException(701);
        }
        return m99(str, bArr);
    }
}
