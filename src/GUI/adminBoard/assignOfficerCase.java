package GUI.adminBoard;
import GUI.adminDashboard;
import core_classes.Case;
import core_classes.policeOfficer;
import system_mangement.crimeSystem;
import system_mangement.inputValidator;
import javax.swing.*;
import java.awt.*;

public class assignOfficerCase extends JFrame {
    protected crimeSystem sys;
    protected policeOfficer officer;
    protected JTextField officerIdField, CaseIdField;

    public assignOfficerCase(crimeSystem sys){
        this.sys= sys;

        setSize(400, 200);
        setTitle("Assigning Case Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        add(new JLabel("  Officer ID:"));
        officerIdField= new JTextField();
        add(officerIdField);
        add(new JLabel("  Case ID:"));
        CaseIdField= new JTextField();
        add(CaseIdField);

        JButton addCaseBtn= new JButton("Assign Case");
        add(addCaseBtn);
        addCaseBtn.addActionListener(e -> assignCase(sys));
        JButton backBtn= new JButton("Back to Menu");
        add(backBtn);
        backBtn.addActionListener(e -> backToMenu(sys));
    }

    private void assignCase(crimeSystem sys){
        try {
            String officerId = inputValidator.validateBlank(officerIdField.getText());
            String CaseId = inputValidator.validateBlank(CaseIdField.getText());

            policeOfficer officer = null;
            for (policeOfficer p : sys.allOfficers) {
                if (p.getID().equals(officerId)) {
                    officer = p;
                    break;
                }
            }
            if (officer == null) {
                throw new IllegalArgumentException("Officer ID " + officerId + " does not exist.");
            }

            Case Case = null;
            for (Case c : sys.allCases) {
                if (c.getCaseID().equals(CaseId)) {
                    Case = c;
                    break;
                }
            }
            if (Case == null) {
                throw new IllegalArgumentException("Case ID " + CaseId + " does not exist.");
            }

            officer.assignCase(Case);
            JOptionPane.showMessageDialog(this, "Case Assigned Successfully");
            backToMenu(sys);

        } catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void backToMenu(crimeSystem sys) {
        new adminDashboard(sys).setVisible(true);
        this.dispose();
    }
}
