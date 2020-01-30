package dev.misakacloud.dbee.utils;

import java.io.*;
import java.util.Base64;

public class KeyLoader {
    public static byte[] loadKeyBytesFromStream(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder(4000);
        Reader reader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(reader);
        try {
            while (true) {
                String line = br.readLine();
                if (line == null || line.isEmpty()) {
                    // 读取结束以后进行B64解码
                    byte[] keyBytes = Base64.getDecoder().decode(result.toString());
                    return keyBytes;
                }

                if (!line.startsWith("-") && !line.startsWith("#")) {
                    result.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            br.close();
        }
    }
}
