/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import java.util.regex.*;

/**
 *
 * @author zaido
 */
public class DataValidation {
    
    public static boolean validateWord(String input) {
        String validWordForm = "^[a-zA-ZáéíóúÁÉÍÓüÜ ]+$";
        return input.matches(validWordForm);
    }
    
    public static boolean validateName(String name) {
        String validNameForm = "^[A-ZÀ-ÿ][a-zA-ZÀ-ÿ.,' ]+$";
        return name.matches(validNameForm);
    }
    
    public static boolean validateEmail(String email) {
        String validEmailForm = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(validEmailForm);
    }

    public static boolean validateDate(String date) {
        /* Format YYYY/MM/DD */
        String validDateForm = "[1-9][0-9][0-9]{2}-([0][1-9]|[1][0-2])-([1-2][0-9]|[0][1-9]|[3][0-1])";
        return date.matches(validDateForm);
    }
    
    public static boolean validateNumber(String number) {
        String validNumberForm = "";
        return number.matches(validNumberForm);
    }
}
