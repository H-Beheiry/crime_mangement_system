package GUI;
import GUI.adminBoard.addCase;
import GUI.adminBoard.addDepartmentDashboard;
import GUI.adminBoard.addOfficerDashboard;
import GUI.adminBoard.assignOfficerCase;
import system_mangement.crimeSystem;
import javax.swing.*;
import java.awt.*;

public class adminDashboard extends JFrame{
    protected crimeSystem sys;

    public adminDashboard(crimeSystem sys) {
        this.sys = sys;

        setSize(400, 300);
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        JButton officerBtn = new JButton("Add Officer");
        JButton departmentBtn = new JButton("Add Department");
        JButton exitBtn = new JButton("Exit");
        JButton addCaseBtn = new JButton("Add Case");
        JButton assignCaseBtn = new JButton("Assign Case to Officer");
        add(officerBtn);
        add(departmentBtn);
        add(addCaseBtn);
        add(assignCaseBtn);
        add(exitBtn);
        officerBtn.addActionListener(e -> addOfficer());
        departmentBtn.addActionListener(e -> addDepartment());
        addCaseBtn.addActionListener(e -> addCase());
        assignCaseBtn.addActionListener(e -> assignCase());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void addOfficer(){
        new addOfficerDashboard(sys).setVisible(true);
        this.dispose();
    }
    private void addDepartment(){
        new addDepartmentDashboard(sys).setVisible(true);
        this.dispose();
    }
    private void addCase(){
        new addCase(sys).setVisible(true);
        this.dispose();
    }
    private void assignCase(){
        new assignOfficerCase(sys).setVisible(true);
        this.dispose();
    }
}
