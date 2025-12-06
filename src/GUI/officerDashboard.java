package GUI;
import system_mangement.crimeSystem;
import core_classes.policeOfficer;
import javax.swing.*;

public class officerDashboard extends JFrame{
    public officerDashboard(crimeSystem sys, policeOfficer officer) {
        setSize(400, 300);
        setTitle("Officer Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JLabel("Welcome Officer ",SwingConstants.CENTER));
    }
}
