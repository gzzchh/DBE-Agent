package dev.misakacloud.dbee.transformers;

import com.sun.org.apache.bcel.internal.Repository;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.generic.*;
import dev.misakacloud.dbee.utils.MyCryptKey;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.CodeAttribute;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class LoadKeyTransformer implements ClassFileTransformer {
    public final String injectedClassName = "org.jkiss.lm.LMEncryption";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replace("/", ".");

        if (className.contains("LMEncryption")) {

            System.out.println("正在加载的类:" + className);
            CtClass ctclass = null;
            try {
                ctclass = ClassPool.getDefault().get(className);// 使用全称,用于取得字节码类<使用javassist>
                CtMethod[] ctmethods = ctclass.getMethods();
                for (CtMethod ctMethod : ctmethods) {
//                    CodeAttribute ca = ctMethod.getMethodInfo2().getCodeAttribute();
                    if (ctMethod.getName().equals("generatePublicKey")){
                        System.out.println("检测到读取解密Key的行为");
                        ctMethod.insertBefore(" return (PublicKey) new MyCryptKey().getPublicKey();");
                    }
                }
                return ctclass.toBytecode();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

        }
        return null;
    }


}
