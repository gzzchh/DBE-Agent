package dev.misakacloud.dbee.utils;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class MyCryptKey {
    public byte[] localKeyBytes;


    public MyCryptKey() throws Exception {
        String resourceName = "keys/FakeLicense.key";
        URL keyURL = this.getClass().getClassLoader().getResource(resourceName);
        try {
            InputStream keyStream = keyURL.openStream();
            this.localKeyBytes = KeyLoader.loadKeyBytesFromStream(keyStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Key getPublicKey() {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(localKeyBytes);
            return keyFactory.generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Key getPrivateKey() {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec privateKeySpec = new X509EncodedKeySpec(localKeyBytes);
            return keyFactory.generatePublic(privateKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
}
