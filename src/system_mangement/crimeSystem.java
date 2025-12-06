package system_mangement;
import core_classes.*;
import java.io.Serializable;
import java.util.ArrayList;

public class crimeSystem implements Serializable {
    public ArrayList<systemAdmin> allAdmins;
    public ArrayList<department> allDepartments;
    public ArrayList<policeOfficer> allOfficers;
    public ArrayList<Case> allCases;
    public ArrayList<criminal> allCriminals;

    public crimeSystem(){
        this.allAdmins = new ArrayList<>();
        this.allDepartments = new ArrayList<>();
        this.allOfficers = new ArrayList<>();
        this.allCases = new ArrayList<>();
        this.allCriminals = new ArrayList<>();
    }

    public void addDepartment(department d) {
        allDepartments.add(d);
    }
    public void addAdmin(systemAdmin a) {
        allAdmins.add(a);
    }
    public void addPolice(policeOfficer p) {
        allOfficers.add(p);
    }
    public void addCase(Case c) {
        allCases.add(c);
    }
    public void addCriminals(criminal c){
        allCriminals.add(c);
    }
}
