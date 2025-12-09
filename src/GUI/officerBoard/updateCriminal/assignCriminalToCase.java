package GUI.officerBoard.updateCriminal;
import core_classes.Case;
import core_classes.criminal;
import core_classes.policeOfficer;
import system_mangement.accessValidator;
import system_mangement.crimeSystem;
import system_mangement.inputValidator;
import javax.swing.*;
import java.awt.*;

public class assignCriminalToCase extends JFrame {
    protected crimeSystem sys;
    protected policeOfficer officer;
    protected JTextField criminalIdField, CaseIdField;

    public assignCriminalToCase(crimeSystem sys, policeOfficer officer) {
        this.sys = sys;
        this.officer = officer;

        setSize(400, 300);
        setTitle("Assign Criminal to Case Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        add(new JLabel("  Criminal ID:"));
        criminalIdField= new JTextField();
        add(criminalIdField);
        add(new JLabel("  Case ID:"));
        CaseIdField= new JTextField();
        add(CaseIdField);

        JButton addCaseBtn= new JButton("Assign Case");
        add(addCaseBtn);
        addCaseBtn.addActionListener(e -> assignCase(sys,officer));
        JButton backBtn= new JButton("Back to Menu");
        add(backBtn);
        backBtn.addActionListener(e -> backToMenu(sys,officer));
    }
    private void backToMenu(crimeSystem sys, policeOfficer officer) {
        new updateCriminalDashboard(sys,officer).setVisible(true);
        this.dispose();
    }
    private void assignCase(crimeSystem sys, policeOfficer officer) {
        try{
            String criminalId= inputValidator.validateBlank(criminalIdField.getText());
            String CaseId= inputValidator.validateBlank(CaseIdField.getText());
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
            criminal criminal= null;
            for (criminal c : sys.allCriminals) {
                if (c.getID().equals(criminalId)) {
                    criminal= c;
                    break;
                }
            }
            if (officer == null) {
                throw new IllegalArgumentException("Criminal ID " + criminalId + " does not exist.");
            }
            if(! accessValidator.canAccessCriminal(officer,criminal)){
                throw new IllegalArgumentException("Cannot Access This Criminal");
            }
            criminal.addCase(Case);
            JOptionPane.showMessageDialog(this, "Case Assigned Successfully");
            backToMenu(sys,officer);
        } catch (IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
