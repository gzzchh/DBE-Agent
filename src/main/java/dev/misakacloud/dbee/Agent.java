package dev.misakacloud.dbee;

import dev.misakacloud.dbee.transformers.LoadKeyTransformer;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("Agent已加载");
        inst.addTransformer(new LoadKeyTransformer(), true);
//        inst.redefineClasses();

    }

}
