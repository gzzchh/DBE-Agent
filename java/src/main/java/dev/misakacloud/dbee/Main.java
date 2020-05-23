package dev.misakacloud.dbee;

import java.awt.*;
import java.security.Key;
import java.util.Date;

import dev.misakacloud.dbee.enums.LMLicenseType;
import dev.misakacloud.dbee.gui.MainForm;
import dev.misakacloud.dbee.utils.MyCryptKey;

import javax.swing.*;

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
        licObj.setLicenseType(LMLicenseType.ULTIMATE);
        licObj.setLicenseIssueTime(new Date());
        licObj.setLicenseStartTime(new Date());
        licObj.setLicenseId("DB-随便啦");
        licObj.setLicenseEndTime(new Date(1893427200000L));
        licObj.setYearsNumber((byte) 10);
        licObj.setOwnerName("是你的名字");
        licObj.setOwnerEmail("你看不到我~");
        System.out.println(licInfo);
        System.out.println("-- DBeaver EE LICENSE - " + licObj.getLicenseId());
        System.out.println("-- Issued at " + licObj.getLicenseIssueTime().toString() + " to " + licObj.getOwnerName() + " //");
        System.out.println(licObj.getEncryptLicense(new MyCryptKey().getPrivateKey()));

//        JFrame.setDefaultLookAndFeelDecorated(true);
//        JFrame mainFrame = new JFrame("DBeaver 许可生成");
//        Container c = mainFrame.getContentPane();
//        mainFrame.setContentPane(new MainForm().mainPanel);
//        Toolkit toolkit = Toolkit.getDefaultToolkit();
//        int x = (int)(toolkit.getScreenSize().getWidth() - (double)mainFrame.getWidth()) / 2;
//        int y = (int)(toolkit.getScreenSize().getHeight() - (double)mainFrame.getHeight()) / 2;
//        mainFrame.setLocation(x, y);
//        mainFrame.setResizable(true);
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        c.add(new MainForm().mainPanel);
//        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
//        mainFrame.setSize(600, 600);
//        mainFrame.pack();
//        mainFrame.setVisible(true);



    }
}
