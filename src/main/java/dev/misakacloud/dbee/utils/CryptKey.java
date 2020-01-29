package dev.misakacloud.dbee.utils;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class CryptKey {
    public PrivateKey privateKeyBytes;


    public CryptKey() throws Exception {
        String resourceName = "keys/FakeLicense.key";
        URL keyURL = this.getClass().getClassLoader().getResource(resourceName);
        try {
            InputStream keyStream = keyURL.openStream();
            byte[] keyBytes = getStreamBytes(keyStream);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec privateKeySpec = new X509EncodedKeySpec(keyBytes);
            this.privateKeyBytes = keyFactory.generatePrivate(privateKeySpec);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getStreamBytes(InputStream is) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] b = baos.toByteArray();
        is.close();
        baos.close();
        return b;
    }

    public Key getPublicKey() {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(this.privateKeyBytes.getEncoded());
            Key publicKey = keyFactory.generatePublic(publicKeySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Key getPrivateKey() {
        return this.getPrivateKey();
    }
}
