package dev.misakacloud.dbee.transformers;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class LoadKeyTransformer implements ClassFileTransformer {
    public final String injectedClassName = "com/dbeaver/ee/runtime/lm/DBeaverEnterpriseLM$LicenseKeyProvider";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        className = className.replace("/", ".");
        if (className.contains("DBeaverEnterpriseLM") && className.contains("LicenseKeyProvider")) {
            System.out.println("正在加载的类:" + className);
            CtClass ctclass = null;
            try {
                ctclass = ClassPool.getDefault().get(className);// 使用全称,用于取得字节码类<使用javassist>
                CtMethod[] ctmethods = ctclass.getMethods();
                for (CtMethod ctMethod : ctmethods) {
                    CodeAttribute ca = ctMethod.getMethodInfo2().getCodeAttribute();
                    if (ctMethod.getName().equals("getDecryptionKey")){
                        System.out.println("检测到读取解密Key的行为");
                        ctMethod.
                    }
                }
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
