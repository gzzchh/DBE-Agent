package dev.misakacloud.dbee.enums;

public enum LMLicenseStatus {
    VALID,
    INVALID,
    EXPIRED,
    CANCELED,
    COMPROMISED,
    BAD_CODE;

    private LMLicenseStatus() {
    }
}
