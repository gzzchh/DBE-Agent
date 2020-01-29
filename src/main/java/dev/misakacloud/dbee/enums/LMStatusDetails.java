//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package dev.misakacloud.dbee.enums;

public class LMStatusDetails {
    private String message;
    private String details;
    private boolean valid;
    private LMLicenseStatus remoteStatus;

    public LMStatusDetails(String message, String details, boolean valid) {
        this(message, details, valid, (LMLicenseStatus)null);
    }

    public LMStatusDetails(String message, String details, boolean valid, LMLicenseStatus remoteStatus) {
        this.message = message;
        this.details = details;
        this.valid = valid;
        this.remoteStatus = remoteStatus;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDetails() {
        return this.details;
    }

    public boolean isValid() {
        return this.valid;
    }

    public LMLicenseStatus getRemoteStatus() {
        return this.remoteStatus;
    }
}
