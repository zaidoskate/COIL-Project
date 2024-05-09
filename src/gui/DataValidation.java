package gui;

import logic.DAOs.UvProfessorDAO;
import logic.LogicException;

public class DataValidation {
    
    public static boolean validateWord(String input) {
        String validWordForm = "^[a-zA-Z0-9. ,\\-ÑñáéíóúÁÉÍÓÚ]*$";
        return input.matches(validWordForm);
    }
    
    public static boolean validateName(String name) {
        String validNameForm = "^[A-ZÀ-ÿ][a-zA-ZÀ-ÿ.,' ]+$";
        return name.matches(validNameForm);
    }
    
    public static boolean validateEmail(String email) {
        String validEmailForm = "^[a-zA-Z0-9._%+-]+@(hotmail|outlook)\\.(com|es|mx|co|ar)$";
        return email.matches(validEmailForm);
    }

    public static boolean validateDate(String date) {
        /* Format YYYY/MM/DD */
        String validDateForm = "[1-9][0-9][0-9]{2}-([0][1-9]|[1][0-2])-([1-2][0-9]|[0][1-9]|[3][0-1])";
        return date.matches(validDateForm);
    }
    
    public static boolean validateNotBlanks(String text) {
        if(text.isBlank()) {
            return false;
        }
        return true;
    }

    public static boolean validatePersonalNumberExists(String personalNumber) {
        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        try {
            if(uvProfessorDAO.countUvProfessorByPersonalNumber(personalNumber) == 0) {
                return true;
            }
        } catch(LogicException logicException) {
            return false;
        }
        return false;
    } 
    public static boolean validatePersonalNumberFormat(String personalNumber) {
        String validNameForm = "^\\d{5}$";
        return personalNumber.matches(validNameForm);
    }
    public static boolean validateLengthField(String field, int length) {
        return field.length() <= length;
    }
    
}
