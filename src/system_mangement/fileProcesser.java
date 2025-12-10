package system_mangement;

import java.io.*;

public class fileProcesser {
    private static final String FILE_PATH= "crime_system_data.ser";

    public static crimeSystem loadSystem() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Starting new system.");
            return new crimeSystem();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            crimeSystem sys = (crimeSystem) ois.readObject();
            System.out.println("loaded successfully");
            return sys;

        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("error: Could not load data. " + e.getMessage());
            return new crimeSystem();
        }
    }

    public static void saveSystem(crimeSystem sys) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(sys);
        }
        catch (IOException e) {
            System.out.println("error: Could not save data. " + e.getMessage());
        }
    }
}