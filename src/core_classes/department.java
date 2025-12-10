package core_classes;
import java.io.Serializable;
import java.util.ArrayList;

public class department implements Serializable {
    private ArrayList<policeOfficer> officers;
    private final String name;
    private final String departmentID;
    private final String activationDate;

    public department(String name, String departmentID, String activationDate) {
        this.name= name;
        this.departmentID= departmentID;
        this.officers= new ArrayList<>();
        this.activationDate= activationDate;
    }
    public String getDepartmentID() {
        return departmentID;
    }
    public String getName() {
        return name;
    }
    public void addOfficer(policeOfficer p){
        officers.add(p);
    }
}
