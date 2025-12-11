package GUI;
import GUI.officerBoard.*;
import GUI.officerBoard.updateCriminal.updateCriminalDashboard;
import system_mangement.crimeSystem;
import core_classes.policeOfficer;
import javax.swing.*;
import java.awt.*;

public class officerDashboard extends JFrame{
    protected crimeSystem sys;
    protected policeOfficer officer;

    public officerDashboard(crimeSystem sys, policeOfficer officer) {
        this.sys= sys;
        this.officer= officer;


        setSize(500, 400);
        setTitle("Officer Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 3, 10, 10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JButton viewHandledCasesBtn= new JButton("View My Cases");
        JButton UpdateCaseBtn= new JButton("Update Case");
        JButton searchCaseRecordBtn= new JButton("Search Case Record");
        JButton addCriminalBtn= new JButton("Add Criminal");
        JButton searchCriminalBtn= new JButton("Search Criminal");
        JButton updateCriminalBtn= new JButton("Update Criminal Record");
        JButton exitBtn= new JButton("Exit");

        add(viewHandledCasesBtn);
        add(UpdateCaseBtn);
        add(searchCaseRecordBtn);
        add(addCriminalBtn);
        add(searchCriminalBtn);
        add(updateCriminalBtn);
        add(exitBtn);

        viewHandledCasesBtn.addActionListener(e -> viewHandledCases());
        UpdateCaseBtn.addActionListener(e -> updateCase());
        searchCaseRecordBtn.addActionListener(e -> searchCaseRecord());
        addCriminalBtn.addActionListener(e -> addCriminal());
        searchCriminalBtn.addActionListener(e -> searchCriminal());
        updateCriminalBtn.addActionListener(e -> updateCriminal());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void viewHandledCases() {
        new viewHandledCasesDashboard(sys,officer).setVisible(true);
        this.dispose();
    }

    private void updateCase() {
        new updateCaseDashboard(sys,officer).setVisible(true);
        this.dispose();
    }

    private void searchCaseRecord() {
        new searchCaseRecordDashboard(sys,officer).setVisible(true);
        this.dispose();
    }

    private void addCriminal() {
        new addCriminalDashboard(sys,officer).setVisible(true);
        this.dispose();
    }

    private void searchCriminal() {
        new searchCriminalDashboard(sys,officer).setVisible(true);
        this.dispose();
    }

    private void updateCriminal() {
        new updateCriminalDashboard(sys,officer).setVisible(true);
        this.dispose();
    }
}
