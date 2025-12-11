package GUI.officerBoard.updateCriminal;
import GUI.officerDashboard;
import core_classes.policeOfficer;
import system_mangement.crimeSystem;
import javax.swing.*;
import java.awt.*;

public class updateCriminalDashboard extends JFrame {
    crimeSystem sys;
    policeOfficer officer;

    public updateCriminalDashboard(crimeSystem sys, policeOfficer officer) {
        this.sys = sys;
        this.officer = officer;

        setSize(400, 400);
        setTitle("Update Criminal Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JButton updateCriminalDescriptionBtn= new JButton("Update Description");
        JButton assignCriminalToCaseBtn= new JButton("Assign Criminal to Case");
        JButton BackBtn= new JButton("Back To Dashboard");
        add(updateCriminalDescriptionBtn);
        add(assignCriminalToCaseBtn);
        add(BackBtn);
        updateCriminalDescriptionBtn.addActionListener(e -> updateCriminalDescription());
        assignCriminalToCaseBtn.addActionListener(e-> assignCriminalToCase());
        BackBtn.addActionListener(e -> back());

    }
    private void updateCriminalDescription(){
        new updateCriminalDescription(sys,officer).setVisible(true);
        this.dispose();
    }
    private void assignCriminalToCase(){
        new assignCriminalToCase(sys,officer).setVisible(true);
        this.dispose();
    }
    private void back(){
        new officerDashboard(sys, officer).setVisible(true);
        this.dispose();
    }
}
