package dev.misakacloud.dbee.interceptor;


import dev.misakacloud.dbee.utils.MyCryptKey;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.security.PublicKey;
import java.util.concurrent.Callable;

public class LoadKeyInterceptor {
    @RuntimeType
    public static Object intercept(@Origin Method method, @SuperCall Callable<?> callable) throws Exception {
//        long start = System.currentTimeMillis();
        return (PublicKey) new MyCryptKey().getPublicKey();

            // 原有函数执行
//            return (PublicKey) new MyCryptKey().getPublicKey();
//            return callable.call();
//            System.out.println(method + ": took " + (System.currentTimeMillis() - start) + "ms");
    }
}
