package dev.misakacloud.dbee.utils;

import java.net.URL;
import java.security.Key;

public class CryptKey {
    public static Key getPublicKey(){
        String resourceName = "keys/FakeLicense.key";
        URL keyURL = this.getClass().getClassLoader().getResource(resourceName);

    }
}
