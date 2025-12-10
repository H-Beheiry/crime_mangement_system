package core_classes;
import java.io.Serializable;
import java.util.Arrays;
import java.util.ArrayList;

public class Case implements Serializable {
    private final String caseID;
    private final String name;
    private final String description;
    private final String crimeType;
    private ArrayList<criminal> criminalsInvolved;
    private ArrayList<policeOfficer> officersInvolved;
    private final String startDate;
    private ArrayList<String> updatesdates;
    private ArrayList<String> updateDescriptions;
    private final department department;

    public Case(String caseID, String crimeType, String description, String name, String startDate, department department) {
        this.caseID= caseID;
        this.officersInvolved= new  ArrayList<>();
        this.criminalsInvolved= new  ArrayList<>();
        this.crimeType= crimeType;
        this.description= description;
        this.name= name;
        this.startDate= startDate;
        this.updatesdates= new  ArrayList<>();
        this.updateDescriptions= new  ArrayList<>();
        this.department= department;
    }

    public void addUpdate(String updatedate, String updateDescription) {
        updatesdates.add(updatedate);
        updateDescriptions.add(updateDescription);
    }

    public void assignOfficers(policeOfficer officer) {
       if (!officersInvolved.contains(officer)) {
           officersInvolved.add(officer);
           officer.assignCase(this);
       }
    }

    public void assignCriminal(criminal criminal){
        if (!criminalsInvolved.contains(criminal)){
            criminalsInvolved.add(criminal);
            criminal.addCase(this);
        }
    }

    @Override
    public String toString() {
        return name;
    }
    public String getCaseID() {
        return caseID;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getCrimeType() {
        return crimeType;
    }
    public String getStartDate() {
        return startDate;
    }
    public department getDepartment() {
        return department;
    }
    public String getID() {
        return caseID;
    }
    public ArrayList<String> getUpdateDates() {
        return updatesdates;
    }
    public ArrayList<String> getUpdateDescriptions() {
        return updateDescriptions;
    }
}
