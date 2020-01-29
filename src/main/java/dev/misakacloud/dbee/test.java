package dev.misakacloud.dbee;


import dev.misakacloud.dbee.utils.CryptKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Optional;
import java.util.stream.Stream;

public class test {
    public static Key getPublicKey(String key) throws Exception {
        return new CryptKey().getPublicKey();
    }

    public static void main(String[] args) throws Exception {
//        String lic = "kKGIrBS2fhcXMeMnqblGBdTmnFeMnD9hTIwGAGhJf6myjyNivoKFz1npmv0udI2oIMxdPWdfHMW0P+9+TzYzruK9huqiZmr8WVI91WHt8FnMzV4Sx1BfN1R+DeYdbQEHD+VrGPSvyczCZRtIPLIec3Bk+ELty8qZbjxODNLX3sfGMvUvANjrjydjlOorbmNIAvlw55Iepwuh0EM0RAllkYe6ymE8TjvLQEOzCFhUlSpNkghtgaMGx0hVrYEV0/YVR67ZnCLM1Dzf+CvQTrLgiNqCJ3xyN32DEyLASbVjYdP4YVMp8NVKnsdVorPU5mBuwlwylGwMU/WETka4252bhw==";
        String lic = "GcEVPtVH+fzyCX3Jw/b2iDGHIYe20IwwGGzvCaSvgN+SOLyeOfmhTgIXkhhuJsCi7Ov/7Sy2Hpk3\n" +
                "VdHjehLS727GlKOKKKkZ6s9C8bt+Aw4WEhDsivOJpQt5eLUjvDLhZC0ols4R9kIXHRo1KcS5AaHy\n" +
                "8EWhdiuxPOJdHTR01waJUvb4RdH8Ldi2m2CNB93sv1OTMvzoDX1oWUnWGN8mL7K0UU+3ksy06a0O\n" +
                "/AU8wueD1yaXHQp9OML5WmBDZapiuSKoQUH/dPhu6C7XRj1EAiTueNibb9rSfbhlUYKgA/1is4nW\n" +
                "42xwiN3+jzQrBYO1NQIYAlGHxlsJ0+IxqVLHCw==";
        lic = lic.replaceAll("\\n", "");
        String publicKeyStr =
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAk7ciFU/aUCIgH5flBbGD0t7B3KOmfL0l\n" +
                        "BMf2ENuLA0w/T8A1RvteUYk2EQo3UrZ7kMZ8rK93nmDjituN7jlv/bsxGyAox87BbKYSs9oH5f9P\n" +
                        "hYHAiTE0PxoMODnl4NgR+Bpc+Ath8wDLHMC+BzYkOy4JQo8EX/ff58TT9UYP8eoDeGdSxQmW3FJC\n" +
                        "i82UiC5zIk75dx20Al9ql0fdxnzo31q/2MbnNCAfSchsqrKtzBtheex4JvvqZjxn98wk5Te1QgZz\n" +
                        "Caz4ay9dkLVjSt79QYm5hKb8Jt3O5SxSUsrjmYVeG+k2bQlidw8dENwLZmvJkIJi8kb94yEwY/dq\n" +
                        "lENDkQIDAQAB";
        publicKeyStr = publicKeyStr.replaceAll("\\n", "");
        byte[] decodedBytes = null;
//        System.out.println(lic.trim());
        byte[] base64DecodedBytes = Base64.getDecoder().decode(lic.trim());
        Key publicKey = getPublicKey(publicKeyStr.trim());
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            decodedBytes = cipher.doFinal(base64DecodedBytes);
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        String res = new String(decodedBytes);
//        System.out.println(res);


    }
}
