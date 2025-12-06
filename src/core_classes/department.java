package core_classes;
import java.io.Serializable;
import java.util.ArrayList;

public class department implements Serializable {
    protected ArrayList<policeOfficer> officers;
    protected String name;
    protected String departmentID;
    protected String activationDate;

    public department(String name, String departmentID, String activationDate) {
        this.name = name;
        this.departmentID = departmentID;
        this.officers = new ArrayList<>();
        this.activationDate = activationDate;
    }
    public String getDepartmentID() {
        return departmentID;
    }
    public void addOfficer(policeOfficer p){
        officers.add(p);
    }
}
