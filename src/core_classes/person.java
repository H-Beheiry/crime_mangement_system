package core_classes;

import java.io.Serializable;

public abstract class person implements Serializable {
    protected String name;
    protected String ID;
    protected int age;

    public person(String name,String ID,int age){
        this.name=name;
        this.ID=ID;
        this.age=age;
    }

    public abstract void get_person_info();
}
