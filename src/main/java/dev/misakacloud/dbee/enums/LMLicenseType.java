//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dev.misakacloud.dbee.enums;

public enum LMLicenseType {
    YEAR_UPDATE('Y', "Standard license"),
    YEAR_CORPORATE('C', "Corporate license"),
    ULTIMATE('U', "Ultimate license"),
    LIMITED('L', "Limited license"),
    PARTNER('P', "Technical partner license"),
    TRIAL('T', "Trial license"),
    ACADEMIC('A', "Academic license"),
    CUSTOM('X', "Custom license");

    private final char id;
    private final String displayName;

    private LMLicenseType(char id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public byte getId() {
        return (byte)this.id;
    }

    public static LMLicenseType valueOf(byte id) {
        LMLicenseType[] var4;
        int var3 = (var4 = values()).length;

        for(int var2 = 0; var2 < var3; ++var2) {
            LMLicenseType format = var4[var2];
            if (format.id == id) {
                return format;
            }
        }

        throw new IllegalArgumentException(String.valueOf(id));
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
