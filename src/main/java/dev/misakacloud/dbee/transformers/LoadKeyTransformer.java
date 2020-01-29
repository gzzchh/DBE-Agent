package dev.misakacloud.dbee.transformers;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Arrays;

public class LoadKeyTransformer implements ClassFileTransformer {
    public final String injectedClassName = "com/dbeaver/ee/runtime/lm/DBeaverEnterpriseLM$LicenseKeyProvider";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replace("/", ".");

        if (className.contains("DBeaverEnterpriseLM") && className.contains("LicenseKeyProvider")) {
//            CtClass ctclass = null;
//            try {
//                ctclass = ClassPool.getDefault().get(className);// 使用全称,用于取得字节码类<使用javassist>
//                System.out.println(ctclass);
//                System.out.println(Arrays.toString(ctclass.getDeclaredMethods()));
//                CtMethod ctmethod = ctclass.getDeclaredMethod("getDecryptionKey");// 得到这方法实例
//                ctmethod.insertBefore("System.out.println(11111111);");
//                // getKeyMethod.insertBefore("return new CryptKey().getPublicKey();");
//                return ctclass.toBytecode();
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                e.printStackTrace();
//            }
            return handleKeySpec();
        }
        return null;
    }

    private byte[] handleKeySpec() throws IllegalClassFormatException {
        try {
            String getPubKeyCodeStr = null;
            ClassPool cp = ClassPool.getDefault();
            CtClass cc = cp.get(injectedClassName.replace('/', '.'));

            cp.importPackage("dev.misakacloud.dbee.transformers.CryptKey");
            try {
                Class.forName("dev.misakacloud.dbee.transformers.CryptKey");
//                cp.importPackage("java.util.Base64");
                getPubKeyCodeStr = "return new CryptKey().getPublicKey();";
            } catch (ClassNotFoundException e)  {

            }

//            cc.addField(CtField.make("private static final byte[] __h_ok=" + b64f + "(\"MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBAIvfweZvmGo5otwawI3no7Udanxal3hX2haw962KL/nHQrnC4FG2PvUFf34OecSK1KtHDPQoSQ+DHrfdf6vKUJphw0Kn3gXm4LS8VK/LrY7on/wh2iUobS2XlhuIqEc5mLAUu9Hd+1qxsQkQ50d0lzKrnDqPsM0WA9htkdJJw2nS\");", cc));
//            cc.addField(CtField.make("private static final byte[] __h_nk=" + b64f + "(\"MIIBuDCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYUAAoGBAO0DidNibJHhtgxAnM9NszURYU25CVLAlwFdOWhiUkjrjOY459ObRZDVd35hQmN/cCLkDox7y2InJE6PDWfbx9BsgPmPvH75yKgPs3B8pClQVkgIpJp08R59hoZabYuvm7mxCyDGTl2lbrOi0a3j4vM5OoCWKQjIEZ28OpjTyCr3\");", cc));
            cc.addField(CtField.make("",cc));
            CtConstructor cm = cc.getConstructor("([B)V");
            cm.insertBefore(getPubKeyCodeStr);


            return cc.toBytecode();
        } catch (Exception e) {
            throw new IllegalClassFormatException(e.getMessage());
        }
    }
}
