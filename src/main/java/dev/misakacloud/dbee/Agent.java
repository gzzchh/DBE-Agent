package dev.misakacloud.dbee;

import dev.misakacloud.dbee.transformers.LoadKeyTransformer;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

import static net.bytebuddy.matcher.ElementMatchers.is;

public class Agent {

    public static void premain(String agentArgs, Instrumentation inst) throws UnmodifiableClassException {
        System.out.println("Agent已加载");
        new AgentBuilder.Default()
                // 类名匹配
                .type(ElementMatchers.nameContains("com.dbeaver.ee.runtime.lm"))
                .transform((builder, type, classLoader, module) ->
                        builder.method(ElementMatchers.nameContains("getDecryptionKey"))
                                .intercept(MethodDelegation.to(LoadKeyTransformer.class)))
                .installOn(inst);



    }

}
