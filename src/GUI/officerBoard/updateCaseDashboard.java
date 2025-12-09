package GUI.officerBoard;
import GUI.officerBoard.updateCriminal.updateCriminalDashboard;
import GUI.officerDashboard;
import core_classes.Case;
import core_classes.policeOfficer;
import system_mangement.accessValidator;
import system_mangement.crimeSystem;
import system_mangement.inputValidator;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class updateCaseDashboard extends JFrame {
    protected crimeSystem sys;
    protected policeOfficer officer;
    protected JTextField  idField,updateField;
    protected JSpinner startDateField;

    public updateCaseDashboard(crimeSystem sys, policeOfficer officer) {
        this.sys = sys;
        this.officer = officer;

        setSize(400, 300);
        setTitle("Update Case Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 3, 10, 10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        add(new JLabel("  Case ID:"));
        idField= new JTextField();
        add(idField);
        add(new JLabel("  Update Description:"));
        updateField= new JTextField();
        add(updateField);

        SpinnerDateModel model= new SpinnerDateModel();
        add(new JLabel("  Case Start Date:"));
        startDateField= new JSpinner(model);
        JSpinner.DateEditor editor= new JSpinner.DateEditor(startDateField, "dd-MM-yyyy");
        startDateField.setEditor(editor);
        add(startDateField);

        JButton UpdateBtn= new JButton("Update");
        add(UpdateBtn);
        UpdateBtn.addActionListener(e -> updateDescription(sys,officer));
        JButton backBtn= new JButton("Back to Menu");
        add(backBtn);
        backBtn.addActionListener(e -> backToMenu(sys,officer));
    }
    private void backToMenu(crimeSystem sys, policeOfficer officer) {
        new officerDashboard(sys,officer).setVisible(true);
        this.dispose();
    }
    private void updateDescription(crimeSystem  sys, policeOfficer officer){
        try{
            String id= inputValidator.validateBlank(idField.getText());
            String desc= inputValidator.validateBlank(updateField.getText());
            Date rawDate = (Date) startDateField.getValue();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String dateString = formatter.format(rawDate);

            Case updatedCase= null;
            for (Case c: sys.allCases){
                if (c.getID().equals(id)) {
                    updatedCase = c;
                    break;
                }
            }
            if (updatedCase == null) {
                throw new IllegalArgumentException("Case ID " + id + " does not exist.");
            }
            if (!accessValidator.canAccessCase(officer, updatedCase)) {
                throw new IllegalArgumentException("Cannot Access This Criminal");
            }
            updatedCase.addUpdate(dateString,desc);
            JOptionPane.showMessageDialog(this, "Case Updated Successfully");
            backToMenu(sys,officer);
        }catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
