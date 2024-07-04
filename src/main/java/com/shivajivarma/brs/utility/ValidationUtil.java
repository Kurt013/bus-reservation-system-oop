package com.shivajivarma.brs.utility;

import java.util.regex.Pattern;

import javax.swing.JTextField;

public class ValidationUtil {
    
    public static String validateField(JTextField field, String[] options){
        
        String value = field.getText();
        
        for (String option : options) {
            switch(option){
                case "required" :
                    if(value.equals("")){
                        field.setText(null);
                        return "'" + field.getName() + "' is a required field.";
                    }
                    break;
                    
                case "alphabetsOnly" :
                    if(!value.equals("") && !Pattern.matches("[A-Za-z ]+", value)){
                        field.setText(null);
                        return "'" + field.getName() + "' field supports only alphabets.";
                    }
                    break;
                    
                case "username" :
                    if(!value.equals("") && !Pattern.matches("[A-Za-z1-9._]+", value)){
                        field.setText(null);
                        return "'" + field.getName() + "' field supports only alphabets, numbers, underscore and full stop.";
                    }
                    break;
                case "noSpaces" :
                    if(!value.equals("") && !Pattern.matches("[^ ]+", value)){
                        field.setText(null);
                        return "'" + field.getName() + "' field doesn't support spaces.";
                    }
                    break;
                case "numeric" :
                    if(!value.equals("") && !Pattern.matches("[0-9]+", value)){
                        field.setText(null);
                        return "'" + field.getName() + "' field supports only digits.";
                    }
                    break;
                case "mobile" :
                    if(!value.equals("") && (!Pattern.matches("[0-9]+", value) || value.length()!=10)){
                        field.setText(null);
                        return "'" + field.getName() + "' field supports only 10 digits.";
                    }
                    break;
                case "email" :
                    if(!value.equals("") && !Pattern.matches("[A-Za-z]+@[A-Za-z]+[.][A-Za-z]+", value)){
                        field.setText(null);
                        return "'" + field.getName() + "' field is not a valid email address.";
                    }
                    break;
                case "password" :
                    if(!value.equals("") && !Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", value)){
                        field.setText(null);
                        return "'" + field.getName() + "' field must be at least 8 characters long and include numbers, special characters, and letters.";
                    }
                    break;
            }
        }
        return null;
    }
}
