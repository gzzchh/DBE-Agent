package dev.misakacloud.dbee.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class OldCryptKey {
    public static Key getKey() throws Exception {
        String keyJarPath = findJarPath("com.dbeaver.ee.runtime");
        InputStream in = loadResourceFromJarFile(keyJarPath, "keys/dbeaver-ee-public.key");
        byte[] keyBytes = KeyLoader.loadKeyBytesFromStream(in);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    private static InputStream loadResourceFromJarFile(String jarPath, String resPath) {
        if (!jarPath.endsWith(".jar")) {
            return null;
        }
        try {
            JarFile jarFile = new JarFile(jarPath);
            JarEntry jarEntry = jarFile.getJarEntry(resPath);
            if (jarEntry == null) {
                return null;
            }
            return jarFile.getInputStream(jarEntry);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String findJarPath(String prefix) {
        File dir = new File("plugins/");
        String[] fileNameList = dir.list();
        if (fileNameList != null) {
            return "plugins/" + fileNameList[0];
        } else {
            return null;
        }

    }


    public static byte[] decryptLicense(String licenseStr, Key publicKey) {
        byte[] decodedBytes = null;
        licenseStr = licenseStr.replaceAll("\\n", "");
        byte[] base64DecodedBytes = Base64.getDecoder().decode(licenseStr.trim());
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            decodedBytes = cipher.doFinal(base64DecodedBytes);
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return decodedBytes;
    }

    public static String encryptLicense(byte[] licenseByte, Key publicKey) {
        byte[] encodedBytes = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encodedBytes = cipher.doFinal(licenseByte);
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        String encodedStr = Base64.getEncoder().encodeToString(encodedBytes);
        return encodedStr;
    }
}
