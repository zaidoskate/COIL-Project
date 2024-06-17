package gui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import logic.DAOs.UvProfessorDAO;
import logic.LogicException;

public class DataValidation {
    
    public static boolean validateWord(String input) {
        String validWordForm = "^[a-zA-Z0-9. ,\\-ÑñáéíóúÁÉÍÓÚ]*$";
        if (input.matches(validWordForm)) {
            String onlyNumbersForm = "^\\d+$";
            return !input.matches(onlyNumbersForm);
        }
        return false;
    }
    
    public static boolean validateName(String name) {
        String validNameForm = "^[A-ZÀ-ÿ][a-zA-ZÀ-ÿ.,' ]+$";
        return name.matches(validNameForm);
    }
    
    public static boolean validateEmail(String email) {
        String validEmailForm = "^[a-z-0-9._%+-]+@[a-z-0-9.-]+\\.[a-zA-Z]{2,}$";
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
            if (uvProfessorDAO.countUvProfessorByPersonalNumber(personalNumber) == 0) {
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
    
    public static boolean validateFileExtension(String fileName, String extension) {
        if(fileName == null || extension == null || extension.isBlank()) {
            return false;
        }
        
        return fileName.toLowerCase().endsWith("." + extension.toLowerCase());
    }
    
    public static boolean validateYear(String year) {
        String validYearForm = "^\\d{4}$";
        if (!year.matches(validYearForm)) {
            return false;
        }

        int yearInt = Integer.parseInt(year);
        int currentYear = LocalDate.now().getYear();
        return yearInt >= currentYear;
    }
    
    public static boolean validatePeriodValidity(String period, String year) {
        String validPeriod1 = "Febrero - Julio";
        String validPeriod2 = "Agosto - Enero";

        int yearInt = Integer.parseInt(year);

        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();
        int currentMonth = now.getMonthValue();

        if (period.equals(validPeriod1)) {
            if (currentMonth >= 2 && currentMonth <= 7) {
                return yearInt > currentYear;
            } else {
                return yearInt > currentYear;
            }
        } else if (period.equals(validPeriod2)) {
            if (currentMonth >= 8 || currentMonth == 1) {
                return yearInt > currentYear;
            } else {
                return yearInt >= currentYear;
            }
        }
        return false;
    }
    
    public static boolean validateDateRange(String startDate, String endDate) throws LogicException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        boolean validRange = false;
        try {
            LocalDate start = LocalDate.parse(startDate, dateFormatter);
            LocalDate end = LocalDate.parse(endDate, dateFormatter);
            validRange = !end.isBefore(start);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new LogicException("Fecha recibida no tiene el formato válido", dateTimeParseException);
        }
        return validRange;
    }
    
    public static boolean validateNumberStudents(String numberStudents) {
        try {
            int num = Integer.parseInt(numberStudents);
            return num >= 10 && num <= 500;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean validateOnlyLetters(String text) {
        String validLettersForm = "^[a-zA-ZÀ-ÿ\\s.]+$";
        return text.matches(validLettersForm);
    }

    
    public static String trimUnnecesaryBlanks(String input) {
        return input.trim();
    }
}
