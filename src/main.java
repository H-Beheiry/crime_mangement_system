import core_classes.*;
import GUI.*;
import system_mangement.*;

public class main {
    public static void main(String[] args) {
            crimeSystem sys = fileProcesser.loadSystem();

            if (sys.allAdmins.isEmpty()) {
                System.out.println("WARNING: System is empty. Please check console to add an Admin manually if needed.");
                systemAdmin admin= new systemAdmin("admin","a001",20, "password");
                sys.allAdmins.add(admin);
            }

            java.awt.EventQueue.invokeLater(() -> {
                new loginFrame(sys).setVisible(true);
            });

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                fileProcesser.saveSystem(sys);
                System.out.println("System Saved.");
            }));
        }
}

// TODO: add system admin dashboard
// TODO: add officer dashboard
// TODO: add signup
// TODO: customize th gui

