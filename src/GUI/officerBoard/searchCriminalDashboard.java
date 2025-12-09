package GUI.officerBoard;

import GUI.officerDashboard;
import core_classes.criminal;
import core_classes.policeOfficer;
import system_mangement.accessValidator;
import system_mangement.crimeSystem;
import system_mangement.inputValidator;

import javax.swing.*;
import java.awt.*;

public class searchCriminalDashboard extends JFrame {
    protected crimeSystem sys;
    protected policeOfficer officer;
    protected JTextField idField;

    public searchCriminalDashboard(crimeSystem sys, policeOfficer officer) {
        this.sys = sys;
        this.officer = officer;

        setSize(400, 300);
        setTitle("Search Criminal Description Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        add(new JLabel("  Criminal ID:"));
        idField = new JTextField();
        add(idField);
        JButton searchBtn = new JButton("Search ");
        add(searchBtn);
        searchBtn.addActionListener(e -> searchCriminal(sys, officer));
        JButton backBtn = new JButton("Back to Menu");
        add(backBtn);
        backBtn.addActionListener(e -> backToMenu(sys, officer));
    }

    private void backToMenu(crimeSystem sys, policeOfficer officer) {
        new officerDashboard(sys, officer).setVisible(true);
        this.dispose();
    }

    private void searchCriminal(crimeSystem sys, policeOfficer officer) {
        try {
            String id = inputValidator.validateBlank(idField.getText());
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
            showCriminalDetails(criminal);

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void showCriminalDetails(criminal c) {
        JFrame detailsFrame = new JFrame("Criminal Record: " + c.getID());
        detailsFrame.setSize(400, 400);
        detailsFrame.setLayout(new GridLayout(0, 2, 10, 10));
        detailsFrame.setLocationRelativeTo(null);
        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ((JPanel)detailsFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        detailsFrame.add(new JLabel("Name:"));
        detailsFrame.add(new JLabel(c.getName())); // Assuming getName() exists in person class
        detailsFrame.add(new JLabel("ID:"));
        detailsFrame.add(new JLabel(c.getID()));
        detailsFrame.add(new JLabel("Age:"));
        detailsFrame.add(new JLabel(String.valueOf(c.getAge()))); // Assuming getAge() exists
        detailsFrame.add(new JLabel("Danger Level:"));
        detailsFrame.add(new JLabel(c.getDangerLevel()));
        detailsFrame.add(new JLabel("Current Location:"));
        detailsFrame.add(new JLabel(c.getCurrentLocation()));
        detailsFrame.add(new JLabel("Cases Involved:"));
        JTextArea caseList= new JTextArea(c.getCasesInvolved().toString());
        caseList.setLineWrap(true);
        caseList.setWrapStyleWord(true);
        caseList.setEditable(false);
        detailsFrame.add(new JScrollPane(caseList));
        detailsFrame.setVisible(true);
    }
}
