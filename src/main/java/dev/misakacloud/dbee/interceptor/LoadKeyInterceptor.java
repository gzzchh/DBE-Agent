package dev.misakacloud.dbee.interceptor;

import com.sun.org.apache.bcel.internal.classfile.Method;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.concurrent.Callable;

public class LoadKeyInterceptor {
    @RuntimeType
    public static Object intercept(@Advice.Origin Method method, @SuperCall Callable<?> callable) throws Exception {
        long start = System.currentTimeMillis();
        try {
            // 先调用所有
            return callable.call();
        } finally {
            System.out.println(method + " took " + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
