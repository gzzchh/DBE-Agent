package dev.misakacloud.dbee.utils;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class KeyReplacer {
    public KeyReplacer() {
    }

    public static void replacePublicKey() throws Exception {
        String jarPathOfPublicKey = OldCryptKey.findJarPath("com.dbeaver.ee.runtime_");
        ZipParameters para = new ZipParameters();
        para.setFileNameInZip("keys/dbeaver-ee-public.key");
        MyCryptKey newKey = new MyCryptKey();
        byte[] publicKeyBytes = newKey.getPublicKey().getEncoded();
        ZipFile zipFile = new ZipFile(jarPathOfPublicKey);
        zipFile.addStream(new ByteArrayInputStream(publicKeyBytes), para);
    }
}
