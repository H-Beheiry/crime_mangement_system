package core_classes;

public class systemAdmin extends person {
    private final String password;
    public systemAdmin(String name,String id,int age, String password) {
        super(name,id,age);
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public String getID() {
        return ID;
    }

    @Override
    public void get_person_info() {
        System.out.println("--System Admin--");
        System.out.println("Name: "+ name);
        System.out.println("Age: "+ age);
        System.out.println("ID: "+ ID);
    }
}