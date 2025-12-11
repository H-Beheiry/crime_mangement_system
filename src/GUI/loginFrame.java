package GUI;
import system_mangement.accessValidator;
import system_mangement.crimeSystem;
import core_classes.policeOfficer;
import javax.swing.*;
import java.awt.*;


public class loginFrame extends JFrame {
    protected crimeSystem sys;
    protected JTextField idField;
    protected JPasswordField passField;

    public loginFrame(crimeSystem sys) {
        this.sys = sys;

        setTitle("Crime Management System - Login");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        add(new JLabel("  User ID:"));
        idField = new JTextField();
        add(idField);
        add(new JLabel("  Password:"));
        passField = new JPasswordField();
        add(passField);

        JButton loginBtn = new JButton("Login");
        JButton exitBtn = new JButton("Exit");
        add(loginBtn);
        add(exitBtn);
        loginBtn.addActionListener(e -> performLogin());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void performLogin() {
        String id = idField.getText().trim();
        String pass = new String(passField.getPassword());

        if (accessValidator.validateAdminLogin(id, pass, sys)) {
            JOptionPane.showMessageDialog(this, "Login Successful! Welcome Admin.");
            new adminDashboard(sys).setVisible(true);
            this.dispose();
        }

        else if (accessValidator.validateOfficerLogin(id, pass, sys)) {
            policeOfficer officer = accessValidator.getOfficer(id, sys);
            JOptionPane.showMessageDialog(this, "Login Successful! Welcome Officer");
            new officerDashboard(sys, officer).setVisible(true);
            this.dispose();
        }

        else{
            JOptionPane.showMessageDialog(this, "Invalid ID or Password.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
