package GUI.adminBoard;
import GUI.adminDashboard;
import core_classes.department;
import core_classes.policeOfficer;
import system_mangement.crimeSystem;
import system_mangement.inputValidator;
import javax.swing.*;
import java.awt.*;

public class addOfficerDashboard extends JFrame {
    protected crimeSystem sys;
    protected JTextField nameField, idField, ageField, departmentIDField, salaryField, phoneNumberField;
    protected JPasswordField passwordField;

    public addOfficerDashboard(crimeSystem sys) {
        this.sys = sys;

        setSize(400, 300);
        setTitle("Adding Officer Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(10, 1, 10, 10));

        add(new JLabel("  Officer Name:"));
        nameField = new JTextField();
        add(nameField);
        add(new JLabel("  Officer ID:"));
        idField = new JTextField();
        add(idField);
        add(new JLabel("  Officer Password:"));
        passwordField = new JPasswordField();
        add(passwordField);
        add(new JLabel("  Officer Age:"));
        ageField = new JTextField();
        add(ageField);
        add(new JLabel("  Officer Department:"));
        departmentIDField = new JTextField();
        add(departmentIDField);
        add(new JLabel("  Officer Salary:"));
        salaryField = new JTextField();
        add(salaryField);
        add(new JLabel("  Officer Phone:"));
        phoneNumberField = new JTextField();
        add(phoneNumberField);

        JButton addOfficerBtn = new JButton("Add Officer");
        add(addOfficerBtn);
        addOfficerBtn.addActionListener(e -> addOfficer(sys));
        JButton BackBtn = new JButton("Back To Menu");
        add(BackBtn);
        BackBtn.addActionListener(e -> backToMenu(sys));

    }

    public void backToMenu(crimeSystem sys){
        new adminDashboard(sys).setVisible(true);
        this.dispose();
    }

    public void addOfficer(crimeSystem sys) {
        try {
            String name = inputValidator.validateBlank(nameField.getText());
            String id = inputValidator.validateBlank(idField.getText());
            String pass = inputValidator.validateBlank(new String(passwordField.getPassword()));
            int age = inputValidator.validateAge(ageField.getText());
            String deptID = inputValidator.validateBlank(departmentIDField.getText());
            int salary = inputValidator.validateSalary(salaryField.getText());
            String phone = inputValidator.validatePhone(phoneNumberField.getText());

            department officerDepartment = null;
            for (department dept : sys.allDepartments) {
                if (dept.getDepartmentID().equals(deptID)) {
                    officerDepartment = dept;
                    break;
                }
            }
            if (officerDepartment == null) {
                throw new IllegalArgumentException("Department ID " + deptID + " does not exist in the system.");
            }
            policeOfficer o= new policeOfficer(salary,phone,officerDepartment,age,name,id,pass);
            officerDepartment.addOfficer(o);
            sys.allOfficers.add(o);
            JOptionPane.showMessageDialog(this, "Officer Added Successfully");
        }
        catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}