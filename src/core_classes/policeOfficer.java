package core_classes;
import java.util.ArrayList;

public class policeOfficer extends person {
    private int salary;
    protected String phoneNumber;
    protected department department;
    protected ArrayList<Case> handledCases;
    protected String password;

    public policeOfficer(int salary, String phoneNumber, department department, int age, String name, String ID, String password) {
        super(name,ID,age);
        this.salary= salary;
        this.phoneNumber= phoneNumber;
        this.department= department;
        this.handledCases= new ArrayList<>();
        this.password= password;
    }
    public int getSalary() {
        return salary;
    }
    public String getID() {
        return ID;
    }
    public String getPassword() {
        return password;
    }
    public department getDepartment() {
        return department;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public ArrayList<Case> getCases() {
        return handledCases;
    }

    @Override
    public void get_person_info(){
        System.out.println("Name: "+ name);
        System.out.println("ID: "+ ID);
        System.out.println("Age: "+ age);
        System.out.println("Department: "+ department.name + "ID: " + department.departmentID);
        System.out.println("Handled cases: "+ handledCases);
        System.out.println("Phone number: "+ phoneNumber);
    }

    public void assignCase(Case newCase){
        if (!handledCases.contains(newCase)){
            handledCases.add(newCase);
            newCase.assignOfficers(this);
        }
    }
    @Override
    public String toString() {
        return String.format("Officer Name: %s\n Handled Cases: %s", name, handledCases);
    }
}

