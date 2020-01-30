package dev.misakacloud.dbee;

import dev.misakacloud.dbee.transformers.LoadKeyTransformer;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class Agent {

    public static void premain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException {
        System.out.println("Agent已加载");
        new AgentBuilder.Default()
                // 类名匹配
                .type(ElementMatchers.named("org.jkiss.lm.LMEncryption"))
                .transform((builder, type, classLoader, module) ->
                        builder.method(ElementMatchers.nameMatches("generatePublicKey"))
                                .intercept(MethodDelegation.to(LoadKeyTransformer.class)))
                .installOn(inst);



    }

}
