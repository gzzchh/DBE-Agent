package dev.misakacloud.dbee.interceptor;


import dev.misakacloud.dbee.utils.MyCryptKey;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.concurrent.Callable;

public class LoadKeyInterceptor {


    @RuntimeType
    public static Object intercept(@Origin Method method, @SuperCall Callable<?> callable) throws Exception {
//        privateKeyStr = privateKeyStr.replaceAll("\\n", "").trim();
//        byte[] keyBytes = Base64.getDecoder().decode(privateKeyStr.getBytes());
//        KeyFactory publicKeyFactory = KeyFactory.getInstance("RSA");
//        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(keyBytes);
//        PrivateKey myPrivateKey = publicKeyFactory.generatePrivate(privateKeySpec);
//        RSAPrivateCrtKey privk = (RSAPrivateCrtKey) myPrivateKey;
//        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privk.getModulus(), privk.getPublicExponent());
//        KeyFactory privateKeyFactory = KeyFactory.getInstance("RSA");
//        return privateKeyFactory.generatePublic(publicKeySpec);
        // 原有函数执行
        return new MyCryptKey().getPublicKey();
//            return callable.call();
    }
}
