package dev.misakacloud.dbee;

import dev.misakacloud.dbee.interceptor.NetworkCheckAdvicer;
import dev.misakacloud.dbee.interceptor.Transformers;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("===============DBeaver-EE Agent===============");
        System.out.println("Starting Local License Server");
//        LicenseServer licenseServer = new LicenseServer(3265);
//        licenseServer.addHandler();
//        try {
//            licenseServer.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println("本地许可服务器启动成功");
        System.out.println("开始进行类替换");

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {

            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

            @Override
            public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {

            }

            @Override
            public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {

            }

        };
        System.out.println("准备劫持解密密钥获取");
        new AgentBuilder
                .Default()
                .type(ElementMatchers.nameContains("com.dbeaver.ee.runtime.lm.DBeaverEnterpriseLM")) // 指定需要拦截的类
                .transform(Transformers.loadKeyTransformer)
                .with(listener)
                .installOn(inst);
        System.out.println("解密密钥获取已经劫持");
        System.out.println("准备修改验证地址");
        // ElementMatchers.nameContains("com.dbeaver.lm.validate.PublicServiceClient")
        new AgentBuilder.Default()
                .with(new AgentBuilder.InitializationStrategy.SelfInjection.Eager())
                .with(AgentBuilder.InstallationListener.StreamWriting.toSystemOut())
                .type(ElementMatchers.nameContains("com.dbeaver.lm.validate.PublicServiceClient"))
                .transform((builder, typeDescription, classLoader, module) ->
                        builder.visit(Advice.to(NetworkCheckAdvicer.class)
                                .on(ElementMatchers.isConstructor())))
                .installOn(inst);
    }


}
