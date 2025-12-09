package GUI.officerBoard.updateCriminal;

import core_classes.criminal;
import core_classes.policeOfficer;
import system_mangement.accessValidator;
import system_mangement.crimeSystem;
import system_mangement.inputValidator;

import javax.swing.*;
import java.awt.*;

public class updateCriminalDescription extends JFrame {
    protected crimeSystem sys;
    protected policeOfficer officer;
    protected JTextField  idField,currentLocationField,dangerLevelField;

    public updateCriminalDescription(crimeSystem sys, policeOfficer officer) {
        this.sys = sys;
        this.officer = officer;

        setSize(400, 300);
        setTitle("Update Criminal Description Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 3, 10, 10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        add(new JLabel("  Criminal ID:"));
        idField= new JTextField();
        add(idField);
        add(new JLabel("  Current Location:"));
        currentLocationField= new JTextField();
        add(currentLocationField);
        add(new JLabel("  Danger Level:"));
        dangerLevelField= new JTextField();
        add(dangerLevelField);

        JButton UpdateBtn= new JButton("Update");
        add(UpdateBtn);
        UpdateBtn.addActionListener(e -> updateDescription(sys,officer));
        JButton backBtn= new JButton("Back to Menu");
        add(backBtn);
        backBtn.addActionListener(e -> backToMenu(sys,officer));
    }
    private void backToMenu(crimeSystem sys, policeOfficer officer) {
        new updateCriminalDashboard(sys,officer).setVisible(true);
        this.dispose();
    }
    private void updateDescription(crimeSystem  sys, policeOfficer officer) {
        try {
            String currentLocation = inputValidator.validateBlank(currentLocationField.getText());
            String id = inputValidator.validateBlank(idField.getText());
            String dangerLevel = inputValidator.validateBlank(dangerLevelField.getText());
            dangerLevel = inputValidator.validateDangerLevel(dangerLevel);

            criminal criminal = null;
            for (criminal c : sys.allCriminals) {
                if (c.getID().equals(id)) {
                    criminal = c;
                    break;
                }
            }
            if (criminal == null) {
                throw new IllegalArgumentException("Criminal ID " + id + " does not exist.");
            }
            if (!accessValidator.canAccessCriminal(officer, criminal)) {
                throw new IllegalArgumentException("Cannot Access This Criminal");
            }
            criminal.setDangerLevel(dangerLevel);
            criminal.setLocation(currentLocation);
            JOptionPane.showMessageDialog(this, "Updated criminal successfully");
            backToMenu(sys, officer);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
