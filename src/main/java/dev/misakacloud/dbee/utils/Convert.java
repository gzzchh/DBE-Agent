package dev.misakacloud.dbee.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convert {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    public static byte[] longToBytes(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(x);
        return buffer.array();
    }

    public static byte[] intToBytes(int x) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(x);
        return buffer.array();
    }

    public static byte[] shortToBytes(short x) {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort(x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes, int offset) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(bytes, offset, 8);
        buffer.flip();
        return buffer.getLong();
    }

    public static int bytesToInt(byte[] bytes, int offset) {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(bytes, offset, 4);
        buffer.flip();
        return buffer.getInt();
    }

    public static short bytesToShort(byte[] bytes, int offset) {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.put(bytes, offset, 2);
        buffer.flip();
        return buffer.getShort();
    }

    public static Date dateFromString(String s) {
        if (s != null && !s.isEmpty()) {
            try {
                return DATE_FORMAT.parse(s);
            } catch (ParseException var2) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static void copyStringBytes(byte[] data, int offset, String string, int length) {
        try {
            byte[] strData = string == null ? new byte[0] : string.getBytes("utf-8");
            System.arraycopy(strData, 0, data, offset, strData.length);

            for (int i = strData.length; i < length; ++i) {
                data[offset + i] = 32;
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public static void copyLongBytes(byte[] data, int offset, Long inputLong, int length) {
        try {
            byte[] longData = inputLong == null ? new byte[0] : longToBytes(inputLong);
            System.arraycopy(longData, 0, data, offset, longData.length);

            for (int i = longData.length; i < length; ++i) {
                data[offset + i] = 32;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyDateBytes(byte[] data, int offset, Date date) {
        byte[] dateBytes = date == null ? new byte[8] : longToBytes(date.getTime());
        System.arraycopy(dateBytes, 0, data, offset, dateBytes.length);
    }

    public static Date getDateFromBytes(byte[] data, int offset) {
        long time = bytesToLong(data, offset);
        return time == 0L ? null : new Date(time);
    }

}
