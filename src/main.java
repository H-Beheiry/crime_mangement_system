import core_classes.*;
import GUI.*;
import system_mangement.*;

public class main {
    public static void main(String[] args) {
        GUI.ThemeManager.applyTheme();
        crimeSystem sys = fileProcesser.loadSystem();


            if (sys.allAdmins.isEmpty()) {
                System.out.println("WARNING: System is empty. Please check console to add an Admin manually if needed.");
                sys= populateSystem.populateSystemData();
            }

            final crimeSystem finalSys= sys;

            java.awt.EventQueue.invokeLater(() -> {
                new loginFrame(finalSys).setVisible(true);
            });

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                fileProcesser.saveSystem(finalSys);
                System.out.println("System Saved.");
            }));
        }
}

// TODO: add officer dashboard
// TODO: add the white border from the officer dashboard stuff (final polish)