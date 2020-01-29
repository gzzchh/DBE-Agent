package dev.misakacloud.dbee.transformers;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;
import java.util.Arrays;

public class LoadKeyTransformer implements ClassFileTransformer {
    public final String injectedClassName = "com/dbeaver/ee/runtime/lm/DBeaverEnterpriseLM$LicenseKeyProvider";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        className = className.replace("/", ".");
// && className.contains("LicenseKeyProvider")
        if (className.contains("DBeaverEnterpriseLM")) {
            CtClass ctclass = null;
            try {
                ctclass = ClassPool.getDefault().get(className);// 使用全称,用于取得字节码类<使用javassist>
                System.out.println(ctclass);
                System.out.println(Arrays.toString(ctclass.getDeclaredMethods()));
                CtMethod ctmethod = ctclass.getDeclaredMethod("getDecryptionKey");// 得到这方法实例
                ctmethod.insertBefore("System.out.println(11111111);");
                // getKeyMethod.insertBefore("return new CryptKey().getPublicKey();");
                return ctclass.toBytecode();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

//        System.out.println("正在加载的类:" + className);
        return null;
    }
}
