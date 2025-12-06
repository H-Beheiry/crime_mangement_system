package GUI;
import system_mangement.crimeSystem;
import javax.swing.*;

public class adminDashboard extends JFrame{
    public adminDashboard(crimeSystem sys) {
        setSize(400, 300);
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JLabel("Welcome Admin", SwingConstants.CENTER));
    }
}
