package system_mangement;
import java.util.Arrays;
import java.util.List;

public class inputValidator {
    public static String validateBlank(String input){
        if (input.trim().isEmpty()){
            throw  new  IllegalArgumentException("Cannot have input");
        }
        return input;
    }

    public static int validateInteger(String input){
        validateBlank(input);
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(" must be a valid number.");
        }
    }

    public static int validateAge(String input){
       int age = validateInteger(input);
        if  (age<18 || age >100 ){
            throw  new  IllegalArgumentException("Age must be between 18 and 100");
        }
        return age;
    }

    public static int validateSalary(String input){
        int salary = validateInteger(input);
        if (salary<0){
            throw  new  IllegalArgumentException("Salary must be a positive integer");
        }
        return salary;
    }
    public static String validatePhone(String input){
        String phone = validateBlank(input);
        if (!phone.matches("\\d+")) {
            throw new IllegalArgumentException("Phone Number must contain digits only.");
        }
        return phone;
    }
    public static String validateDangerLevel(String input){
        String dangerLevel = validateBlank(input).toLowerCase();
        List<String> valid= Arrays.asList("low","moderate","high","very high");
        if (!valid.contains(dangerLevel)){
            throw new IllegalArgumentException("Danger Level must be \"low\",\"moderate\",\"high\",\"very high\".");
        }
        return dangerLevel;
    }
}
