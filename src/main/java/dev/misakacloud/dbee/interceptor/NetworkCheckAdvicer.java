package dev.misakacloud.dbee.interceptor;

import net.bytebuddy.asm.Advice;

public class NetworkCheckAdvicer {

    @Advice.OnMethodExit
    static void giveMeMoreLife(@Advice.FieldValue(value = "PUBLIC_SERVICE_URL", readOnly = false) String serviceUrl)
            throws Exception {
        System.out.println("New life set to 1000");
        serviceUrl = "http://localhost:3265/";
    }
}
