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
}
