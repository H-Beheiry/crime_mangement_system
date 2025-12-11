package GUI.adminBoard;
import GUI.adminDashboard;
import core_classes.department;
import system_mangement.crimeSystem;
import system_mangement.inputValidator;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addDepartmentDashboard extends JFrame {
    protected crimeSystem sys;
    protected JTextField nameField, idField;
    protected JSpinner activationDateField;

    public addDepartmentDashboard(crimeSystem sys){
        this.sys= sys;

        setSize(400, 300);
        setTitle("Adding Department Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        add(new JLabel("  Department Name:"));
        nameField = new JTextField();
        add(nameField);
        add(new JLabel("  Department ID:"));
        idField= new JTextField();
        add(idField);

        SpinnerDateModel model= new SpinnerDateModel();
        add(new JLabel("  Activation Date:"));
        activationDateField= new JSpinner(model);
        JSpinner.DateEditor editor= new JSpinner.DateEditor(activationDateField, "dd-MM-yyyy");
        activationDateField.setEditor(editor);
        add(activationDateField);

        JButton addDepartmentBtn= new JButton("Add Department");
        add(addDepartmentBtn);
        addDepartmentBtn.addActionListener(e -> addDepartment(sys));
        JButton BackBtn= new JButton("Back To Menu");
        add(BackBtn);
        BackBtn.addActionListener(e -> backToMenu(sys));
    }

    private void backToMenu(crimeSystem sys){
        new adminDashboard(sys).setVisible(true);
        this.dispose();
    }

    private void addDepartment(crimeSystem sys){
        try {
            String name= inputValidator.validateBlank(nameField.getText());
            String id= inputValidator.validateBlank(idField.getText());
            Date rawDate= (Date) activationDateField.getValue();
            SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
            String dateString= formatter.format(rawDate);
            for (department d : sys.allDepartments) {
                if (d.getDepartmentID().equals(id)) {
                    throw new IllegalArgumentException("Department ID " + id + " already exists.");
                }
            }

            department newDept= new department(name, id, dateString);
            sys.allDepartments.add(newDept);
            JOptionPane.showMessageDialog(this, "Department Added Successfully");
            backToMenu(sys);

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}