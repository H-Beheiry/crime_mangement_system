package GUI.adminBoard;
import GUI.adminDashboard;
import core_classes.Case;
import core_classes.department;
import system_mangement.crimeSystem;
import system_mangement.inputValidator;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addCase extends JFrame {
    crimeSystem sys;
    protected JTextField nameField, idField, typeField,descriptionField,departmentIDField;
    protected JSpinner startDateField;
//    Case(String caseID, String crimeType, String description, String name, String startDate, department department)
    public addCase(crimeSystem sys) {
        this.sys= sys;

        setSize(400, 300);
        setTitle("Adding Case Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(8, 1, 10, 10));

        add(new JLabel("  Case Name:"));
        nameField= new JTextField();
        add(nameField);
        add(new JLabel("  Case ID:"));
        idField= new JTextField();
        add(idField);
        add(new JLabel("  Case Type:"));
        typeField= new JTextField();
        add(typeField);
        add(new JLabel("  Case Description:"));
        descriptionField= new JTextField();
        add(descriptionField);
        add(new JLabel("  Department ID:"));
        departmentIDField= new JTextField();
        add(departmentIDField);

        SpinnerDateModel model= new SpinnerDateModel();
        add(new JLabel("  Case Start Date:"));
        startDateField= new JSpinner(model);
        JSpinner.DateEditor editor= new JSpinner.DateEditor(startDateField, "dd-MM-yyyy");
        startDateField.setEditor(editor);
        add(startDateField);

        JButton addCaseBtn= new JButton("Add Case");
        add(addCaseBtn);
        addCaseBtn.addActionListener(e -> addCaseBtn(sys));

        JButton BackBtn= new JButton("Back To Menu");
        add(BackBtn);
        BackBtn.addActionListener(e -> backToMenu(sys));
    }

    private void backToMenu(crimeSystem sys) {
        new adminDashboard(sys).setVisible(true);
        this.dispose();
    }

    private void addCaseBtn(crimeSystem sys) {
        try {
            String name = inputValidator.validateBlank(nameField.getText());
            String id = inputValidator.validateBlank(idField.getText());
            String type = inputValidator.validateBlank(typeField.getText());
            String description = inputValidator.validateBlank(descriptionField.getText());
            String departmentID = inputValidator.validateBlank(departmentIDField.getText());

            Date rawDate = (Date) startDateField.getValue();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String dateString = formatter.format(rawDate);

            for (Case c : sys.allCases) {
                if (c.getCaseID().equals(id)) {
                    throw new IllegalArgumentException("Case ID " + id + " already exists.");
                }
            }
            department caseDept= null;
            for (department d : sys.allDepartments) {
                if (d.getDepartmentID().equals(departmentID)) {
                    caseDept= d;
                    break;
                }
            }
            if (caseDept == null) {
                throw new IllegalArgumentException("Department ID " + departmentID + " does not exist.");
            }

            Case newCase= new Case(id, type, description, name, dateString, caseDept);
            sys.allCases.add(newCase);
            JOptionPane.showMessageDialog(this, "Case Added Successfully");
            backToMenu(sys);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
