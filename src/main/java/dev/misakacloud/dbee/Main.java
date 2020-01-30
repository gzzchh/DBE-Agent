package dev.misakacloud.dbee;

import java.security.Key;
import java.util.Date;

import dev.misakacloud.dbee.enums.LMLicenseType;
import dev.misakacloud.dbee.utils.MyCryptKey;

import static dev.misakacloud.dbee.utils.OldCryptKey.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String licenseString = "GcEVPtVH+fzyCX3Jw/b2iDGHIYe20IwwGGzvCaSvgN+SOLyeOfmhTgIXkhhuJsCi7Ov/7Sy2Hpk3\n" +
                "VdHjehLS727GlKOKKKkZ6s9C8bt+Aw4WEhDsivOJpQt5eLUjvDLhZC0ols4R9kIXHRo1KcS5AaHy\n" +
                "8EWhdiuxPOJdHTR01waJUvb4RdH8Ldi2m2CNB93sv1OTMvzoDX1oWUnWGN8mL7K0UU+3ksy06a0O\n" +
                "/AU8wueD1yaXHQp9OML5WmBDZapiuSKoQUH/dPhu6C7XRj1EAiTueNibb9rSfbhlUYKgA/1is4nW\n" +
                "42xwiN3+jzQrBYO1NQIYAlGHxlsJ0+IxqVLHCw==";
        licenseString = licenseString.replaceAll("\\n", "").trim();
        Key publicKey = null;
        try {
            publicKey = getKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
        License licObj = new License(licenseString, publicKey);
        String licInfo = licObj.toString();
//        System.out.println(licInfo);
        licObj.setLicenseType(LMLicenseType.ULTIMATE);
        licObj.setLicenseEndTime(new Date(1893427200000L));
        licObj.setYearsNumber((byte) 10);
        licObj.setOwnerName("MisakaCloud");
        licObj.setOwnerEmail("xjdzch@126.com");
        System.out.println(licObj.getEncryptLicense(new MyCryptKey().getPrivateKey()));
//        licObj.setFlags(1);
//        new MyCryptKey().getPublicKey();
//        String newLic = licObj.getEncryptLicense(new MyCryptKey().getPrivateKey());
//        License licObjNew = new License(newLic, new MyCryptKey().getPublicKey());
//        System.out.println(licObjNew.toString());
    }
}
