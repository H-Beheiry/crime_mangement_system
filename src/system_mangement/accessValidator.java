package system_mangement;
import core_classes.*;

public class accessValidator {
    public static boolean validateAdminLogin(String ID, String password, crimeSystem sys) {
        for (systemAdmin admin : sys.allAdmins) {
            if (admin.getID().equals(ID) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateOfficerLogin(String ID, String password, crimeSystem sys) {
        for (policeOfficer officer : sys.allOfficers) {
            if (officer.getID().equals(ID) && officer.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static policeOfficer getOfficer(String ID,crimeSystem sys) {
        for (policeOfficer officer : sys.allOfficers) {
            if (officer.getID().equals(ID)) {
                return officer;
            }
        }
        return null;
    }

    public static boolean canAccessCase(policeOfficer officer, Case c) {
        if (officer == null || c == null) {
            return false;
        }
        String officerDeptID = officer.getDepartment().getDepartmentID();
        String caseDeptID = c.getDepartment().getDepartmentID();
        return officerDeptID.equals(caseDeptID);
    }

    public static boolean canAccessCriminal(policeOfficer officer, criminal crim) {
        if (officer == null || crim == null) {
            return false;
        }
        String officerDeptID = officer.getDepartment().getDepartmentID();
        for (Case c : crim.getCasesInvolved()) {
            if (c.getDepartment().getDepartmentID().equals(officerDeptID)) {
                return true;
            }
        }
        return false;
    }
}