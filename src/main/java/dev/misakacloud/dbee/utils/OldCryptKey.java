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
        String keyJarPath = findJarPath("com.dbeaver.ee.runtime_");
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

    public static String findJarPath(String prefix) {
        File dir = new File("plugins/");
        String[] fileNameList = dir.list();
        if (fileNameList != null) {
            for (String filename : fileNameList) {
                if (filename.startsWith(prefix)) {
                    return "plugins/" + filename;
                } else {
                }
            }
        } else {
            return null;
        }
        return null;
    }
}
