package dev.misakacloud.dbee.transformers;

import com.sun.org.apache.bcel.internal.Repository;
import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.bcel.internal.classfile.Method;
import com.sun.org.apache.bcel.internal.generic.*;
import dev.misakacloud.dbee.utils.CryptKey;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class LoadKeyTransformer implements ClassFileTransformer {
    public final String injectedClassName = "com/dbeaver/ee/runtime/lm/DBeaverEnterpriseLM$LicenseKeyProvider";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        className = className.replace("/", ".");

        if (className.contains("DBeaverEnterpriseLM") && className.contains("LicenseKeyProvider")) {
            JavaClass clazz = Repository.lookupClass(className);
            ClassGen cg = new ClassGen(clazz);
            ConstantPoolGen cp = cg.getConstantPool();
            for (Method method : clazz.getMethods()) {
                if (method.getName().equals("getDecryptionKey")) {
                    MethodGen mg = new MethodGen(method, cg.getClassName(), cp);
                    InstructionList il = new InstructionList();
//                    il.append(new PUSH(cp, this.agentArgs));
//                    il.append(new )
                    il.append(InstructionFactory.createReturn(Type.getType(CryptKey.class)));
                    mg.setInstructionList(il);
                    mg.setMaxStack();
                    mg.setMaxLocals();
                    cg.replaceMethod(method, mg.getMethod());
                }
            }
            return cg.getJavaClass().getBytes();
//            return null;
//            return handleKeySpec();
        }
        return null;
    }


}
