package dev.misakacloud.dbee;

import dev.misakacloud.dbee.utils.MyCryptKey;
import org.jkiss.lm.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;

public class UltimateLicense {
    @Test
    public void genUltimateLicense() throws Exception {
        PrivateKey privateKey = (PrivateKey) new MyCryptKey().getPrivateKey();
        // 需要注意的是,这里 id 是不一样的 终极版叫 dbeaver-ue
        LMProduct product = new LMProduct("dbeaver-ue",
                                          "DB",
                                          "DBeaver Enterprise",
                                          "DBeaver Ultimate Edition",
                                          "21",
                                          LMProductType.DESKTOP,
                                          new Date(),
                                          new String[0]);
        String licenseID = LMUtils.generateLicenseId(product);
        String productID = product.getId();
        String productVersion = product.getVersion();
        String ownerID = "114514";
        String ownerCompany = "下北泽";
        String ownerName = "MisakaCloud";
        String ownerEmail = "xjdzch@126.com";
        LMLicense license = new LMLicense(licenseID,
                                          LMLicenseType.ULTIMATE,
                                          new Date(),
                                          new Date(),
                                          // 这样子就是没有到期
                                          (Date) null,
                                          0L,
                                          productID,
                                          productVersion,
                                          ownerID,
                                          ownerCompany,
                                          ownerName,
                                          ownerEmail);
        // 反射修改 yearsNumber 用来修改支持年份
        Field yearsNumberField = license.getClass().getDeclaredField("yearsNumber");
        yearsNumberField.setAccessible(true);
        yearsNumberField.set(license, (byte) 127);
        byte[] licenseData = license.getData();
        byte[] licenseEncrypted = LMEncryption.encrypt(licenseData, privateKey);
        System.out.println("--- LICENSE ---");
        System.out.println(Base64.getEncoder().encodeToString(licenseEncrypted));
    }

    private void decryptLicense() throws Exception {
        PublicKey publicKey = (PublicKey) new MyCryptKey().getPublicKey();
        System.out.println("License:");
        byte[] encryptedLicense = LMUtils.readEncryptedString(System.in);
        LMLicense license = new LMLicense(encryptedLicense, publicKey);
        System.out.println(license);
    }
}
