package GUI.officerBoard;

import GUI.adminDashboard;
import GUI.officerDashboard;
import core_classes.criminal;
import core_classes.policeOfficer;
import system_mangement.crimeSystem;
import system_mangement.inputValidator;

import javax.swing.*;
import java.awt.*;

public class addCriminalDashboard extends JFrame {
    protected crimeSystem sys;
    protected policeOfficer officer;
    protected JTextField nameField, idField, ageField, currentLocationField, dangerLevelField;

    public addCriminalDashboard(crimeSystem sys, policeOfficer officer){
        this.sys = sys;
        this.officer = officer;

        setSize(450, 300);
        setTitle("Adding Criminal Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        add(new JLabel("  Criminal Name:"));
        nameField= new JTextField();
        add(nameField);
        add(new JLabel("  Criminal ID:"));
        idField= new JTextField();
        add(idField);
        add(new JLabel("  Criminal Age:"));
        ageField= new JTextField();
        add(ageField);
        add(new JLabel("  Current Location:"));
        currentLocationField= new JTextField();
        add(currentLocationField);
        add(new JLabel("  Danger Level:"));
        dangerLevelField= new JTextField();
        add(dangerLevelField);

        JButton addCriminalBtn= new JButton("Add Criminal");
        add(addCriminalBtn);
        addCriminalBtn.addActionListener(e -> addCriminal(sys));
        JButton BackBtn= new JButton("Back To Menu");
        add(BackBtn);
        BackBtn.addActionListener(e -> backToMenu(sys));
    }
    private void backToMenu(crimeSystem sys){
        new officerDashboard(sys,officer).setVisible(true);
        this.dispose();
    }
    private void addCriminal (crimeSystem sys){
        try {
            String name= inputValidator.validateBlank(nameField.getText());
            String id= inputValidator.validateBlank(idField.getText());
            int age= inputValidator.validateAge(ageField.getText());
            String currentLocation= inputValidator.validateBlank(currentLocationField.getText());
            String dangerLevel= inputValidator.validateBlank(dangerLevelField.getText());
            dangerLevel= inputValidator.validateDangerLevel(dangerLevel);

            for (criminal c : sys.allCriminals){
                if (c.getID().equals(id)){
                    throw new IllegalArgumentException("Criminal ID: " + id + " already exist in the system.");
                }
            }
            //    criminal (int age, String name, String ID, String currentLocation, String dangerLevel)
            criminal criminal= new criminal(age,name,id,currentLocation,dangerLevel);
            sys.allCriminals.add(criminal);
            JOptionPane.showMessageDialog(this, "Criminal Added Successfully");
            backToMenu(sys);
        } catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
