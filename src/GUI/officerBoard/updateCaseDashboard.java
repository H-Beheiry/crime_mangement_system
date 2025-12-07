package GUI.officerBoard;

import core_classes.policeOfficer;
import system_mangement.crimeSystem;

import javax.swing.*;

public class updateCaseDashboard extends JFrame {
    protected crimeSystem sys;
    protected policeOfficer officer;

    public updateCaseDashboard(crimeSystem sys, policeOfficer officer){
        this.sys = sys;
        this.officer = officer;
    }
}
