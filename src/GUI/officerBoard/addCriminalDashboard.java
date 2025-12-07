package GUI.officerBoard;

import core_classes.policeOfficer;
import system_mangement.crimeSystem;

import javax.swing.*;

public class addCriminalDashboard extends JFrame {
    protected crimeSystem sys;
    protected policeOfficer officer;

    public addCriminalDashboard(crimeSystem sys, policeOfficer officer){
        this.sys = sys;
        this.officer = officer;
    }
}
