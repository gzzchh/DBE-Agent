package dev.misakacloud.dbee.gui;

import javax.swing.*;

public class MainForm   {

    private JLabel licenseIdLabel;
    private JTextField licenseIdField;
    private JButton licenseIdBtn;
    private JComboBox licenseTypeComboBox;
    private JLabel licenseTypeField;
    private JTextField licenseIssueTimeField;
    private JButton licenseIssueTimeBtn;
    private JLabel licenseIssueTimeLabel;
    private JLabel licenseStartTimeLabel;
    private JTextField licenseStartTimeField;
    private JTextField yearsField;
    private JLabel yearsLabel;
    private JTextField flagsField;
    private JLabel flagsLabel;
    private JLabel flagsDescriptionLabel;
    private JComboBox productIdComboBox;
    private JLabel productIdLabel;
    private JLabel productVersionLabel;
    private JTextField productVersionField;
    private JLabel ownerIdLabel;
    private JTextField ownerIdField;
    private JButton ownerIdBtn;
    private JTextField ownerCompanyField;
    private JLabel ownerCompanyLabel;
    private JLabel ownerCompanyDescriptionLabel;
    private JTextField ownerNameField;
    private JTextField ownerEmailField;
    private JTextField usersNumberField;
    private JLabel ownerNameLabel;
    private JLabel ownerEmailLabel;
    private JLabel usersNumberLabel;
    private JLabel showLicenseLabel;
    private JTextArea showLicenseArea;
    public JPanel mainPanel;
    private JButton generateLicenseBtn;

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    public void showGUI(){

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
