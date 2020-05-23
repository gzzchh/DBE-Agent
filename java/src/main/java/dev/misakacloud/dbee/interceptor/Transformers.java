package dev.misakacloud.dbee.interceptor;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class Transformers {
    public static AgentBuilder.Transformer loadKeyTransformer = (builder, typeDescription, classLoader, module) -> {
        return builder
                .method(ElementMatchers.named("getDecryptionKey")) // 拦截任意方法
                .intercept(MethodDelegation.to(LoadKeyInterceptor.class)); // 委托
    };
    public static AgentBuilder.Transformer networkCheckTransformer = (builder, typeDescription, classLoader, module) -> {
        return builder
                // 拦截 Ping 方法
                .method(ElementMatchers.named("ping"))
                .intercept(MethodDelegation.to(PingCheckInterceptor.class))
                // 拦截 checkCustomerEmail 方法
                .method(ElementMatchers.named("checkCustomerEmail"))
                .intercept(MethodDelegation.to(CheckCustomerInterceptor.class))
                // 拦截 checkLicenseStatus 方法
                .method(ElementMatchers.named("checkLicenseStatus"))
                .intercept(MethodDelegation.to(CheckLicenseInterceptor.class))
                ;
    };
}
