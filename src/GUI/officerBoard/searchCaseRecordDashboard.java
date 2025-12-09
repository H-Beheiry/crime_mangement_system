package GUI.officerBoard;

import GUI.officerDashboard;
import core_classes.Case;
import core_classes.criminal;
import core_classes.policeOfficer;
import system_mangement.accessValidator;
import system_mangement.crimeSystem;
import system_mangement.inputValidator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class searchCaseRecordDashboard extends JFrame {
    protected crimeSystem sys;
    protected policeOfficer officer;
    protected JTextField idField;

    public searchCaseRecordDashboard(crimeSystem sys, policeOfficer officer){
        this.sys = sys;
        this.officer = officer;

        setSize(400, 300);
        setTitle("Search Case Description Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        add(new JLabel("  Case ID:"));
        idField = new JTextField();
        add(idField);
        JButton searchBtn = new JButton("Search ");
        add(searchBtn);
        searchBtn.addActionListener(e -> searchCase(sys, officer));
        JButton backBtn = new JButton("Back to Menu");
        add(backBtn);
        backBtn.addActionListener(e -> backToMenu(sys, officer));
    }
    private void backToMenu(crimeSystem sys, policeOfficer officer) {
        new officerDashboard(sys, officer).setVisible(true);
        this.dispose();
    }
    private void searchCase(crimeSystem sys, policeOfficer officer) {
        try {
            String id = inputValidator.validateBlank(idField.getText());
            Case searchedCase = null;
            for (Case c : sys.allCases) {
                if (c.getID().equals(id)) {
                    searchedCase = c;
                    break;
                }
            }
            if (searchedCase == null) {
                throw new IllegalArgumentException("Case ID " + id + " does not exist.");
            }
            if (!accessValidator.canAccessCase(officer, searchedCase)) {
                throw new IllegalArgumentException("Cannot Access This Criminal");
            }
            showCaseDetails(searchedCase);

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void showCaseDetails(Case searchedCase) {
        JFrame detailsFrame = new JFrame("Case Record: " + searchedCase.getCaseID());
        detailsFrame.setSize(500, 400);
        detailsFrame.setLayout(new GridLayout(0, 2, 10, 10)); // 2 columns: Label | Value
        detailsFrame.setLocationRelativeTo(null);
        detailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ((JPanel)detailsFrame.getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        detailsFrame.add(new JLabel("Case Name:"));
        detailsFrame.add(new JLabel(searchedCase.getName()));
        detailsFrame.add(new JLabel("Case ID:"));
        detailsFrame.add(new JLabel(searchedCase.getCaseID()));
        detailsFrame.add(new JLabel("Crime Type:"));
        detailsFrame.add(new JLabel(searchedCase.getCrimeType()));
        detailsFrame.add(new JLabel("Start Date:"));
        detailsFrame.add(new JLabel(searchedCase.getStartDate()));
        detailsFrame.add(new JLabel("Description:"));
        JTextArea descArea = new JTextArea(searchedCase.getDescription());
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);
        descArea.setOpaque(false); // Make it look like a label
        detailsFrame.add(new JScrollPane(descArea));
        String lastUpdateDate = "No updates recorded";
        String lastUpdateDesc = "N/A";
        ArrayList<String> dates = searchedCase.getUpdateDates();
        ArrayList<String> descs = searchedCase.getUpdateDescriptions();
        if (dates != null && !dates.isEmpty()) {
            int lastIndex = dates.size() - 1;
            lastUpdateDate = dates.get(lastIndex);
            lastUpdateDesc = descs.get(lastIndex);
        }
        detailsFrame.add(new JLabel("Last Update Date:"));
        detailsFrame.add(new JLabel(lastUpdateDate));
        detailsFrame.add(new JLabel("Last Update Info:"));
        JTextArea updateArea = new JTextArea(lastUpdateDesc);
        updateArea.setLineWrap(true);
        updateArea.setWrapStyleWord(true);
        updateArea.setEditable(false);
        updateArea.setOpaque(false);
        detailsFrame.add(new JScrollPane(updateArea));

        detailsFrame.setVisible(true);
    }
}
