package core_classes;
import java.util.ArrayList;
import java.util.Arrays;

public class criminal extends person {
    protected String currentLocation;
    protected ArrayList<Case> casesInvolved;
    protected String dangerLevel;

    public criminal (int age, String name, String ID, String currentLocation, String dangerLevel) {
        super(name,ID,age);
        this.currentLocation = currentLocation;
        this.casesInvolved = new ArrayList<>();
        this.dangerLevel = dangerLevel;
    }

    public ArrayList<Case> getCasesInvolved() {
        return casesInvolved;
    }
    public String getID(){
        return ID;
    }
    public void setLocation(String location){
        this.currentLocation = location;
    }
    public void setDangerLevel(String level){
        this.dangerLevel = level;
    }

    @Override
    public void get_person_info(){
        System.out.println("Name: "+ name);
        System.out.println("ID: "+ ID);
        System.out.println("Age: "+ age);
        System.out.println("Current Location: "+currentLocation);
        System.out.println("Commited Crimes: "+ Arrays.toString(casesInvolved.toArray()));
        System.out.println("Danger Level: "+dangerLevel);
    }
    public void addCase(Case newCase){
        if (!casesInvolved.contains(newCase)){
            casesInvolved.add(newCase);
            newCase.assignCriminal(this);
        }
    }


}
