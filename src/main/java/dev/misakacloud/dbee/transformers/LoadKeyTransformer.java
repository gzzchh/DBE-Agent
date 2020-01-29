package dev.misakacloud.dbee.transformers;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class LoadKeyTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer)  {
        if (className.contains("LicenseKeyProvider")) {
            System.out.println("正在加载的类:" + className);
        }
//        System.out.println("正在加载的类:" + className);
        return null;
    }
}
