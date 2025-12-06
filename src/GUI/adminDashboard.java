package GUI;
import GUI.adminBoard.addOfficerDashboard;
import system_mangement.crimeSystem;
import javax.swing.*;
import java.awt.*;

public class adminDashboard extends JFrame{
    private crimeSystem sys;

    public adminDashboard(crimeSystem sys) {
        this.sys = sys;

        setSize(400, 300);
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 3, 10, 10));

        JButton officerBtn = new JButton("Add Officer");
        JButton departmentBtn = new JButton("Add Department");
        JButton exitBtn = new JButton("Exit");
        add(officerBtn);
        add(departmentBtn);
        add(exitBtn);
        officerBtn.addActionListener(e -> addOfficer());
        departmentBtn.addActionListener(e -> addDepartment());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void addOfficer(){
        new addOfficerDashboard(sys).setVisible(true);
        this.dispose();
    }

    public static void addDepartment(){
        System.out.println("lol");
    }
}
