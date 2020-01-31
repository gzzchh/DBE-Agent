package dev.misakacloud.dbee.utils;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class MyCryptKey {
    public static final String RSAKey = "MIIEowIBAAKCAQEAxvjmA+hkY3kwXR4CS3wH7wYCWhVzbnfg7z/f1LyIjLZ0yoWCWet+EA8dFYdDtZVspH6vym57sYI5k39lpdNOrBbhhdXg7sMZDwWAbqARBeCvK5gfo76t40hwfis6x4GKM1Qom9aLCiPkKdymGNAF4ijURBgfRBkkXxR/cnclSBpYLj7QHeIjINAYoM2/fkiIeoTYfmUbbYY7XXBP8E42GZx3Zc8oMazRgOpl2mUCgCENTI6rwesNoN1ia4lTlh158+04p5MAwD4Qi3y1uo/YBCxi5A3HRusTiaa5fl1827HJBNP1GfsJTmoBAfBaPO7XuPW2J1zf1NBQuws1QpCYvQIDAQABAoIBAE3p4zA+jSkCqLSd7qoeXID1qnAaHERyvusQ6RZF6Xaob91qIFwTwh6JvIrUl+gAJjXXwgCT+O3On7spVOV1dEYhA5jD4ru8PBDMx7kKBwB1NkLs497b6P2qPRq61N6Q1Ew7NVeo0HNdEJhv5b6rntIryMsNYxjoe3yb6zgofbnYnlORCuGXdaFltY2IIZnElWD5jEEFfdeEP5xgCStT0yRa0MNtvaSf3XH5KKV3omF+8ndJ0NzxyShb+KGfu1OrBc9giqeimn9sfbaRnQ7a4M4dBnsYyTj4XAspGga6VWeDJXgqaZS36D0XgSUiZ+DLkBoY6KlI4N1OGjFF/hyGWsECgYEA4iO3yo1d78gc4l6HUVa2tF/HwWo0T+O6qb0lcVOEfFqGdFgZq92DwiihkWg9bpzvqtNiFiY1kqjObHCQDcBvx5t3FY1zUxNKuCHOE1ouAxpJngk4HS1d8bCggCZ8BsmkZuQlcTMtfU2Gk9jIyz6pOsA7oCiZRWtEP0k7Cl5Syw0CgYEA4T7Vw3NssjnF056FcaMS6M7TgLF54Gglp+ezRGiG01ToX4DrnJsNBKw4zZi0+KNi+6TfsS7KW6J3wMkK/+mqcyrCqyAi6L1uHKOqjE5d/AwiG244IQJ2WSBjIiwI9nI1y0aZfVEEQz4XIHaO++Id5uSgUl1bG9yk7AhrsOL62HECgYEApJTcQXWLhWS96N5lW4Rhqnr+9bxm4DHce4amhokDJkH4Exq/bD7mktthY2NbOnLLVydK/tXGLbvfqDpwDSId+EckbzIoWbDzs3ke8ySx0h5Cr9sD1PBcq/chiML5hzMgDKZEvdNCayyOInInCQzMu26GtrR1gHlmDS0t5Ajr9s0CgYABBxNcl+s4CPFxHLkJIrBUZZUrciPXNUJuuKzltABheHKy9NieV0rA6bQ8o+S3ea9a7I6ktUzxDBW1yHUHY96GNfy7YSMnoq2c7dY6W6UsIDYLn/4bkHJPx8kL/91ummjKa6EJpFP7po3edQwD9C0kv4hHt+tRfjk2ZndvUCnK0QKBgHx+Zj4v8BBxeYpzsbfr6+oDJuXcqbJYoyfHTDwFncOIDmoB9DXdNj9kmjpuKmflnhDoaSr7oBvOMcujiniFZ9PHeo/Tm+YrsFWLO0mGSmjv/qvdQG8JXZ8IDWmJ3lwsWAI+fjEqfNof9KNnMGbTRCi/ZSICDlTYUZ7yDAy359WP";

    public byte[] localKeyBytes;


    public MyCryptKey() throws Exception {
        this.localKeyBytes = Base64.getDecoder().decode(RSAKey.getBytes());
    }


    public Key getPublicKey() {
        try {
            PrivateKey myPrivateKey = (PrivateKey) getPrivateKey();
            RSAPrivateCrtKey privk = (RSAPrivateCrtKey) myPrivateKey;
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(privk.getModulus(), privk.getPublicExponent());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Key getPrivateKey() {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(localKeyBytes);
            return keyFactory.generatePrivate(privateKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }
}
