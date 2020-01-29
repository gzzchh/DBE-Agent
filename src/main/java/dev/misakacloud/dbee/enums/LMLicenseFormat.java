//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dev.misakacloud.dbee.enums;

public enum LMLicenseFormat implements LMSerializeFormat {
    STANDARD((byte)0, 218, "Initial basic license format"),
    EXTENDED((byte)1, 238, "Extended format with owner email and corporate license info");

    private final byte id;
    private final int encryptedLength;
    private final String description;

    private LMLicenseFormat(byte id, int encryptedLength, String description) {
        this.id = id;
        this.encryptedLength = encryptedLength;
        this.description = description;
    }

    public byte getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public static LMLicenseFormat valueOf(byte id) {
        LMLicenseFormat[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            LMLicenseFormat format = var4[var2];
            if (format.id == id) {
                return format;
            }
        }

        throw new IllegalArgumentException(String.valueOf(id));
    }

    public int getEncryptedLength() {
        return this.encryptedLength;
    }
}
