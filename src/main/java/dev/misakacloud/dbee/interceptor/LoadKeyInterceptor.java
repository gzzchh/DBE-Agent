package dev.misakacloud.dbee.interceptor;

//import com.sun.org.apache.bcel.internal.classfile.Method;
import javassist.compiler.MemberResolver;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.concurrent.Callable;

public class LoadKeyInterceptor {
    @RuntimeType
    public static Object intercept(@Advice.Origin MemberResolver.Method method, @SuperCall Callable<?> callable) throws Exception {
        System.out.println("检测到调用读取解密Key");
        long start = System.currentTimeMillis();
        try {
            // 先调用所有
            return callable.call();
        } finally {
            System.out.println("检测到调用读取解密Key");
            System.out.println(method + " took " + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
