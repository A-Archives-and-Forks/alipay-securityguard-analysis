package p000.p001.p002.p003.p004.p017;

import com.alibaba.wireless.security.mainplugin.C0004;
import com.alibaba.wireless.security.open.SecException;
import com.alibaba.wireless.security.open.initialize.ISecurityGuardPlugin;
import com.alibaba.wireless.security.open.litevm.LiteVMInstance;
import com.alibaba.wireless.security.open.litevm.LiteVMParamType;
import com.alibaba.wireless.security.open.litevm.LiteVMParameterWrapper;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* renamed from: а.а.а.а.а.ё.а, reason: contains not printable characters */
/* loaded from: /Users/anwu/Desktop/apk_any/apk/alipay/securityguard-analysis/./sg_unpacked/modules/sgmain/classes.dex */
public class C0036 {

    /* renamed from: а, reason: contains not printable characters */
    private static ISecurityGuardPlugin f88;

    /* renamed from: б, reason: contains not printable characters */
    private static volatile Object f89;

    /* renamed from: в, reason: contains not printable characters */
    private static C0036 f90;

    /* renamed from: а.а.а.а.а.ё.а$а, reason: contains not printable characters */
    static class C0037 implements InvocationHandler {
        C0037() {
        }

        /* renamed from: а, reason: contains not printable characters */
        private boolean m120(Object obj, Object obj2) {
            if (obj2 != null && obj2.getClass() == obj.getClass()) {
                try {
                    return equals(Proxy.getInvocationHandler(obj2));
                } catch (Exception unused) {
                }
            }
            return false;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            char c;
            String name = method.getName();
            switch (name.hashCode()) {
                case -1776922004:
                    if (name.equals("toString")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case -1566653364:
                    if (name.equals("callLiteVMByteMethod")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case -1376216685:
                    if (name.equals("reloadLiteVMInstance")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -1295482945:
                    if (name.equals("equals")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case -611610698:
                    if (name.equals("createLiteVMInstance")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -514295244:
                    if (name.equals("destroyLiteVMInstance")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case -320658273:
                    if (name.equals("createLiteVMInstanceWithSymbols")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 66098133:
                    if (name.equals("callLiteVMStringMethod")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 147696667:
                    if (name.equals("hashCode")) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case 1109138392:
                    if (name.equals("callLiteVMVoidMethod")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 1565934272:
                    if (name.equals("callLiteVMLongMethod")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                    return C0036.f90.m108((String) objArr[0], (String) objArr[1], (byte[]) objArr[2], (Object[]) objArr[3]);
                case 1:
                    return C0036.f90.m107((String) objArr[0], (String) objArr[1], (byte[]) objArr[2], ((Long) objArr[3]).longValue());
                case 2:
                    C0036.f90.m116((LiteVMInstance) objArr[0], (byte[]) objArr[1]);
                    return null;
                case 3:
                    C0036.f90.m115((LiteVMInstance) objArr[0]);
                    return null;
                case 4:
                    return C0036.f90.m111((LiteVMInstance) objArr[0], ((Integer) objArr[1]).intValue(), (LiteVMParameterWrapper[]) objArr[2], 1);
                case 5:
                    return C0036.f90.m111((LiteVMInstance) objArr[0], ((Integer) objArr[1]).intValue(), (LiteVMParameterWrapper[]) objArr[2], 2);
                case 6:
                    return C0036.f90.m111((LiteVMInstance) objArr[0], ((Integer) objArr[1]).intValue(), (LiteVMParameterWrapper[]) objArr[2], 3);
                case 7:
                    C0036.f90.m111((LiteVMInstance) objArr[0], ((Integer) objArr[1]).intValue(), (LiteVMParameterWrapper[]) objArr[2], 0);
                    return null;
                case '\b':
                    return obj.getClass().getName() + "&ID=" + hashCode();
                case '\t':
                    return Boolean.valueOf(m120(obj, objArr[0]));
                case '\n':
                    return Integer.valueOf(hashCode());
                default:
                    return null;
            }
        }
    }

    private C0036(ISecurityGuardPlugin iSecurityGuardPlugin) throws ClassNotFoundException {
        f88 = iSecurityGuardPlugin;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: а, reason: contains not printable characters */
    public LiteVMInstance m107(String str, String str2, byte[] bArr, long j) throws SecException {
        return new LiteVMInstance(C0038.m121(f88, str, str2, bArr, j), str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: а, reason: contains not printable characters */
    public LiteVMInstance m108(String str, String str2, byte[] bArr, Object[] objArr) throws SecException {
        return new LiteVMInstance(C0038.m122(f88, str, str2, bArr, (Object[]) null), str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: а, reason: contains not printable characters */
    public Object m111(LiteVMInstance liteVMInstance, int i, LiteVMParameterWrapper[] liteVMParameterWrapperArr, int i2) throws SecException {
        if (liteVMInstance == null || !m119(liteVMInstance)) {
            throw new SecException("LVM instance not valid", 2101);
        }
        LiteVMParameterWrapper[] liteVMParameterWrapperArr2 = null;
        if (liteVMParameterWrapperArr != null) {
            liteVMParameterWrapperArr2 = (LiteVMParameterWrapper[]) liteVMParameterWrapperArr.clone();
            int length = liteVMParameterWrapperArr2.length;
            for (int i3 = 0; i3 < length; i3++) {
                LiteVMParameterWrapper liteVMParameterWrapper = liteVMParameterWrapperArr2[i3];
                if (liteVMParameterWrapper != null && liteVMParameterWrapper.getType() == LiteVMParamType.PARAM_TYPE.PARAM_TYPE_STRING.getValue() && liteVMParameterWrapper.getValue() != null) {
                    try {
                        byte[] bytes = ((String) liteVMParameterWrapper.getValue()).getBytes("UTF-8");
                        byte[] bArr = new byte[bytes.length + 1];
                        for (int i4 = 0; i4 < bytes.length; i4++) {
                            bArr[i4] = bytes[i4];
                        }
                        bArr[bytes.length] = 0;
                        liteVMParameterWrapperArr2[i3] = new LiteVMParameterWrapper(LiteVMParamType.PARAM_TYPE.PARAM_TYPE_STRING, bArr);
                    } catch (Exception unused) {
                        throw new SecException("LVM string param convert error", 2101);
                    }
                }
            }
        }
        return ((C0038) liteVMInstance.getImpl()).m124(i, liteVMParameterWrapperArr2, i2, 12504);
    }

    /* renamed from: а, reason: contains not printable characters */
    public static Object m112(Class cls, ISecurityGuardPlugin iSecurityGuardPlugin) throws ClassNotFoundException {
        Class.forName("com.alibaba.wireless.security.open.litevm.LiteVMInstance");
        if (f89 == null) {
            synchronized (C0036.class) {
                if (f89 == null && iSecurityGuardPlugin != null) {
                    f90 = new C0036(iSecurityGuardPlugin);
                    f89 = C0004.m5(cls, new C0037());
                }
            }
        }
        return f89;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: а, reason: contains not printable characters */
    public void m115(LiteVMInstance liteVMInstance) throws SecException {
        if (liteVMInstance == null || !m119(liteVMInstance)) {
            throw new SecException("LVM instance not valid", 2101);
        }
        ((C0038) liteVMInstance.getImpl()).m125();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: а, reason: contains not printable characters */
    public void m116(LiteVMInstance liteVMInstance, byte[] bArr) throws SecException {
        if (liteVMInstance == null || !m119(liteVMInstance)) {
            throw new SecException("lvm instance not valid", 2101);
        }
        ((C0038) liteVMInstance.getImpl()).m126(bArr);
    }

    /* renamed from: б, reason: contains not printable characters */
    private boolean m119(LiteVMInstance liteVMInstance) {
        Object impl;
        if (liteVMInstance == null || (impl = liteVMInstance.getImpl()) == null || !(impl instanceof C0038)) {
            return false;
        }
        return ((C0038) impl).m127();
    }
}
