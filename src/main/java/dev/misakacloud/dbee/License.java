package dev.misakacloud.dbee;

import dev.misakacloud.dbee.enums.LMLicenseFormat;
import dev.misakacloud.dbee.enums.LMLicenseType;
import dev.misakacloud.dbee.enums.LMStatusDetails;
import dev.misakacloud.dbee.utils.Convert;
import dev.misakacloud.dbee.utils.OldCryptKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

public class License {
    private byte[] decrypted;
    private String licenseId;
    private dev.misakacloud.dbee.enums.LMLicenseType licenseType;
    private Date licenseIssueTime;
    private Date licenseStartTime;
    private Date licenseEndTime;
    private long flags;
    private String productId;
    private String productVersion;
    private String ownerId;
    private String ownerCompany;
    private String ownerName;
    private String ownerEmail;
    private byte yearsNumber;
    private byte reserved1;
    private short usersNumber;
    private LMLicenseFormat licenseFormat;
    private LMStatusDetails remoteStatus;


    public License(String licenseString, Key key) throws Exception {
        byte[] data = getDecryptedLicense(licenseString, key);
        int offset = 0;
        this.licenseFormat = LMLicenseFormat.STANDARD;

        try {
            this.licenseFormat = LMLicenseFormat.valueOf(data[offset]);
        } catch (Exception var5) {
//            log.warning("Unsupported license format: " + data[offset]);
        }

        offset += 1;
        if (data.length != this.licenseFormat.getEncryptedLength()) {
            throw new Exception("Bad " + this.licenseFormat + " license length (" + data.length + ")");
        } else {
            this.licenseId = (new String(data, offset, 16)).trim();
            offset += 16;
            this.licenseType = LMLicenseType.valueOf(data[offset]);
            offset += 1;
            this.licenseIssueTime = Convert.getDateFromBytes(data, offset);
            offset += 8;
            this.licenseStartTime = Convert.getDateFromBytes(data, offset);
            offset += 8;
            this.licenseEndTime = Convert.getDateFromBytes(data, offset);
            offset += 8;
            this.flags = Convert.bytesToLong(data, offset);
            offset += 8;
            this.productId = (new String(data, offset, 16)).trim();
            offset += 16;
            this.productVersion = (new String(data, offset, 8)).trim();
            offset += 8;
            this.ownerId = (new String(data, offset, 16)).trim();
            offset += 16;
            this.ownerCompany = (new String(data, offset, 64)).trim();
            offset += 64;
            if (this.licenseFormat == LMLicenseFormat.STANDARD) {
                this.ownerName = (new String(data, offset, 64)).trim();
                offset += 64;
            } else {
                this.ownerName = (new String(data, offset, 32)).trim();
                offset += 32;
                this.ownerEmail = (new String(data, offset, 48)).trim();
                offset += 48;
                this.yearsNumber = data[offset++];
                if (this.yearsNumber <= 0) {
                    this.yearsNumber = 1;
                }

                this.reserved1 = data[offset++];
                this.usersNumber = Convert.bytesToShort(data, offset);
                if (this.usersNumber <= 0) {
                    this.usersNumber = 1;
                }

                offset += 2;
            }

        }
    }

    public License(String licenseId, LMLicenseType licenseType, Date licenseIssueTime, Date licenseStartTime, Date licenseEndTime, long flags, String productId, String productVersion, String ownerId, String ownerCompany, String ownerName, String ownerEmail) {
        this.licenseFormat = LMLicenseFormat.EXTENDED;
        this.licenseId = licenseId;
        this.licenseType = licenseType;
        this.licenseIssueTime = licenseIssueTime;
        this.licenseStartTime = licenseStartTime;
        this.licenseEndTime = licenseEndTime;
        this.flags = flags;
        this.productId = productId;
        this.productVersion = productVersion;
        this.ownerId = ownerId;
        this.ownerCompany = ownerCompany;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.yearsNumber = 1;
        this.reserved1 = 0;
        this.usersNumber = 1;
    }

    public String toString() {
        return "licenseId=" + this.licenseId + "\nlicenseType=" + this.licenseType + "\nlicenseIssueTime=" + this.licenseIssueTime + "\nlicenseStartTime=" + this.licenseStartTime + "\nlicenseEndTime=" + this.licenseEndTime + "\nflags=" + this.flags + "\nproductId=" + this.productId + "\nproductVersion=" + this.productVersion + "\nownerId=" + this.ownerId + "\nownerCompany=" + this.ownerCompany + "\nownerName=" + this.ownerName + "\nownerEmail=" + this.ownerEmail + "\nyearsNumber=" + this.yearsNumber + "\nusersNumber=" + this.usersNumber;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public LMLicenseType getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(LMLicenseType licenseType) {
        this.licenseType = licenseType;
    }

    public Date getLicenseIssueTime() {
        return licenseIssueTime;
    }

    public void setLicenseIssueTime(Date licenseIssueTime) {
        this.licenseIssueTime = licenseIssueTime;
    }

    public Date getLicenseStartTime() {
        return licenseStartTime;
    }

    public void setLicenseStartTime(Date licenseStartTime) {
        this.licenseStartTime = licenseStartTime;
    }

    public Date getLicenseEndTime() {
        return licenseEndTime;
    }

    public void setLicenseEndTime(Date licenseEndTime) {
        this.licenseEndTime = licenseEndTime;
    }

    public long getFlags() {
        return flags;
    }

    public void setFlags(long flags) {
        this.flags = flags;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerCompany() {
        return ownerCompany;
    }

    public void setOwnerCompany(String ownerCompany) {
        this.ownerCompany = ownerCompany;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public byte getYearsNumber() {
        return yearsNumber;
    }

    public void setYearsNumber(byte yearsNumber) {
        this.yearsNumber = yearsNumber;
    }

    public byte getReserved1() {
        return reserved1;
    }

    public void setReserved1(byte reserved1) {
        this.reserved1 = reserved1;
    }

    public short getUsersNumber() {
        return usersNumber;
    }

    public void setUsersNumber(short usersNumber) {
        this.usersNumber = usersNumber;
    }

    public LMLicenseFormat getLicenseFormat() {
        return licenseFormat;
    }

    public void setLicenseFormat(LMLicenseFormat licenseFormat) {
        this.licenseFormat = licenseFormat;
    }

    public String getEncryptLicense(Key key) throws Exception {
        byte[] licenseData = new byte[this.licenseFormat.getEncryptedLength()];

        int offset = 0;
        // 计算许可类型
        licenseData[offset] = this.licenseFormat.getId();
        // 移动偏移
        offset += 1;
        //计算许可ID
        Convert.copyStringBytes(licenseData, offset, this.licenseId, 16);
        // 移动偏移
        offset += 16;
        // 计算许可类型
        licenseData[offset] = (byte) this.licenseType.getId();
        // 移动偏移
        ++offset;
        //计算签发时间
        Convert.copyDateBytes(licenseData, offset, this.licenseIssueTime);
        // 移动偏移
        offset += 8;
        // 计算起始时间
        Convert.copyDateBytes(licenseData, offset, this.licenseStartTime);
        // 移动偏移
        offset += 8;
        Convert.copyDateBytes(licenseData, offset, this.licenseEndTime);
        // 移动偏移
        offset += 8;
        // 计算Flag
        Convert.copyLongBytes(licenseData, offset, this.flags, 8);
        // 移动偏移
        offset += 8;
        // 计算产品ID
        Convert.copyStringBytes(licenseData, offset, this.productId, 16);
        offset += 16;
        Convert.copyStringBytes(licenseData, offset, this.productVersion, 8);
        offset += 8;
        Convert.copyStringBytes(licenseData, offset, this.ownerId, 16);
        offset += 16;
        Convert.copyStringBytes(licenseData, offset, this.ownerCompany, 64);
        offset += 64;
        if (this.licenseFormat == LMLicenseFormat.STANDARD) {
            Convert.copyStringBytes(licenseData, offset, this.ownerName, 64);
            offset += 64;
        } else {
            Convert.copyStringBytes(licenseData, offset, this.ownerName, 32);
            offset += 32;
            Convert.copyStringBytes(licenseData, offset, this.ownerEmail, 32);
            offset += 48;
            licenseData[offset++] = this.yearsNumber;
            licenseData[offset++] = this.reserved1;
            offset += 2;
        }

        byte[] encodedLicenseBytes = null;
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encodedLicenseBytes = cipher.doFinal(licenseData);
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        String encodedStr = Base64.getEncoder().encodeToString(encodedLicenseBytes);
        return encodedStr;


    }

    public byte[] getDecryptedLicense(String licenseString, Key key) {
        byte[] decodedBytes = null;
        licenseString = licenseString.replaceAll("\\n", "");
        byte[] base64DecodedBytes = Base64.getDecoder().decode(licenseString.trim());
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            decodedBytes = cipher.doFinal(base64DecodedBytes);
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return decodedBytes;
    }
}
