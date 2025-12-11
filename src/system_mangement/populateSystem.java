package system_mangement;
import core_classes.*;
public class populateSystem {
    public static crimeSystem populateSystemData() {
        crimeSystem sys= new crimeSystem();

        department centralDept= new department("Central City Police", "CCPD01", "19900101");
        sys.allDepartments.add(centralDept);

        systemAdmin adminOne= new systemAdmin("Alice Johnson", "ADMN001", 45, "securePass1");
        systemAdmin adminTwo= new systemAdmin("Bob Smith", "ADMN002", 50, "securePass2");

        sys.allAdmins.add(adminOne);
        sys.allAdmins.add(adminTwo);

        policeOfficer officer1= new policeOfficer(
                60000, "55500010000", centralDept, 30, "John Doe", "OFF001", "pass001"
        );
        policeOfficer officer2= new policeOfficer(
                62000, "55500020000", centralDept, 32, "Jane Roe", "OFF002", "pass002"
        );
        policeOfficer officer3= new policeOfficer(
                64000, "55500030000", centralDept, 35, "Michael Lee", "OFF003", "pass003"
        );
        policeOfficer officer4= new policeOfficer(
                66000, "55500040000", centralDept, 38, "Sarah Davis", "OFF004", "pass004"
        );
        policeOfficer officer5= new policeOfficer(
                68000, "55500050000", centralDept, 40, "Chris Wilson", "OFF005", "pass005"
        );

        sys.allOfficers.add(officer1);
        sys.allOfficers.add(officer2);
        sys.allOfficers.add(officer3);
        sys.allOfficers.add(officer4);
        sys.allOfficers.add(officer5);

        centralDept.addOfficer(officer1);
        centralDept.addOfficer(officer2);
        centralDept.addOfficer(officer3);
        centralDept.addOfficer(officer4);
        centralDept.addOfficer(officer5);

        criminal criminal1= new criminal(25, "Rob Stark", "CRIM001", "Downtown", "Medium");
        criminal criminal2= new criminal(40, "Tony Montana", "CRIM002", "West End", "High");
        criminal criminal3= new criminal(22, "Jesse Pinkman", "CRIM003", "Suburbs", "Low");

        sys.allCriminals.add(criminal1);
        sys.allCriminals.add(criminal2);
        sys.allCriminals.add(criminal3);

        Case case1= new Case(
                "CASE202301", "Grand Theft Auto", "Stolen luxury vehicle from parking lot.",
                "Downtown Auto Theft", "20230115", centralDept
        );
        Case case2= new Case(
                "CASE202302", "Armed Robbery", "Convenience store robbery with weapon.",
                "Night Owl Robbery", "20230210", centralDept
        );
        Case case3= new Case(
                "CASE202303", "Fraud", "Credit card identity theft ring.",
                "Online Banking Fraud", "20230305", centralDept
        );
        Case case4= new Case(
                "CASE202304", "Assault", "Bar fight resulting in hospitalization.",
                "Main St. Assault", "20230420", centralDept
        );
        Case case5= new Case(
                "CASE202305", "Drug Trafficking", "Seizure of illegal substances at port.",
                "Harbor Drug Bust", "20230512", centralDept
        );
        Case case6= new Case(
                "CASE202306", "Vandalism", "Graffiti on public library walls.",
                "City Library Vandalism", "20230601", centralDept
        );

        sys.allCases.add(case1);
        sys.allCases.add(case2);
        sys.allCases.add(case3);
        sys.allCases.add(case4);
        sys.allCases.add(case5);
        sys.allCases.add(case6);

        case1.assignOfficers(officer1);
        case2.assignOfficers(officer1);
        case3.assignOfficers(officer1);

        case2.assignOfficers(officer2);
        case3.assignOfficers(officer2);
        case4.assignOfficers(officer2);

        case3.assignOfficers(officer3);
        case4.assignOfficers(officer3);
        case5.assignOfficers(officer3);

        case4.assignOfficers(officer4);
        case5.assignOfficers(officer4);
        case6.assignOfficers(officer4);

        case5.assignOfficers(officer5);
        case6.assignOfficers(officer5);
        case1.assignOfficers(officer5);

        case1.assignCriminal(criminal1);
        case5.assignCriminal(criminal2);
        case6.assignCriminal(criminal3);

        return sys;
    }
}
