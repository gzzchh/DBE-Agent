package dev.misakacloud.dbee.transformers;

import jdk.internal.org.objectweb.asm.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class ValidateClientTransformer implements ClassFileTransformer {
    private static final String TRANSFORM_CLASS = "Multiplier";
    private static final String TRANSFORM_METHOD_NAME = "main";
    private static final String TRANSFORM_METHOD_DESC = "([Ljava/lang/String;)V";

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!TRANSFORM_CLASS.equals(className)) {
            return null;
        }

        ClassReader cr = new ClassReader(classfileBuffer);
        ClassWriter cw = new ClassWriter(cr, 0);
        cr.accept(new ClassVisitor(Opcodes.ASM5, cw) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc,
                                             String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(
                        access, name, desc, signature, exceptions);
                if (name.equals(TRANSFORM_METHOD_NAME)
                        && desc.equals(TRANSFORM_METHOD_DESC)) {
                    return new MethodVisitor(Opcodes.ASM5, mv) {
                        @Override
                        public void visitIntInsn(int opcode, int operand) {
                            if (opcode == Opcodes.BIPUSH && operand == 10) operand = 5;
                            super.visitIntInsn(opcode, operand);
                        }
                    };
                }
                return mv;
            }
        }, 0);
        return cw.toByteArray();
    }
}
