package gui;

import logic.LogicException;
import org.junit.Test;
import static org.junit.Assert.*;

public class DataValidationTest {
    
    public DataValidationTest() {
    }

    @Test
    public void testValidateWordSuccess() {
        String input = "El motivo de mi oferta es hacer colaborar a mis 24 estudiantes";
        boolean expectedResult = true;
        boolean result = DataValidation.validateWord(input);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testValidateWordFail() {
        String input = "El motivo de mi oferta es colaborar?";
        boolean expectedResult = false;
        boolean result = DataValidation.validateWord(input);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testValidateNameSuccess() {
        String name = "ZAid Alexis Vázquez Ramírez";
        boolean expectedResult = true;
        boolean result = DataValidation.validateName(name);
        assertEquals(expectedResult, result);
    }

    @Test
    public void testValidateNameFail() {
        String name = "Marci0 Migu3l_Gonz@lez? Chimal";
        boolean expectedResult = false;
        boolean result = DataValidation.validateName(name);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testValidateEmailSuccess() {
        String email = "elrevo@gmail.com";
        boolean expectedResult = true;
        boolean result = DataValidation.validateEmail(email);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testValidateEmailFail() {
        String email = "prueba.com";
        boolean expectedResult = false;
        boolean result = DataValidation.validateEmail(email);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testValidateDateSuccess(){
        String date = "2004-12-19";
        boolean expectedResult = true;
        boolean result = DataValidation.validateDate(date);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testValidateDateFail() {
        String date = "19-12-2004";
        boolean expectedResult = false;
        boolean result = DataValidation.validateDate(date);
        assertEquals(expectedResult, result);
    }
    
    @Test 
    public void testValidateNotBlanksSuccess() {
        String text = "Hola como estas";
        boolean expectedResult = true;
        boolean result = DataValidation.validateNotBlanks(text);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testValidateNotBlanksFail() {
        String text = "      ";
        boolean expectedResult = false;
        boolean result = DataValidation.validateNotBlanks(text);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testValidateFieldLengthSuccess() {
        String text = "Lorenzo Tlapa";
        boolean expectedResult = true;
        boolean result = DataValidation.validateLengthField(text, 25);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testValidateFieldLengthFail() {
        String text = "Lorenzo Tlapa";
        boolean expectedResult = false;
        boolean result = DataValidation.validateLengthField(text, 5);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testValidateFileExtensionSuccess() {
        String fileName = "Syllabus.pdf";
        String extension = "pdf";
        boolean expectedResult = true;
        boolean result = DataValidation.validateFileExtension(fileName, extension);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testValidateYearSuccess() {
        
    }
    
    @Test
    public void testValidatePeriodValiditySuccess() {
        String period = "Agosto - Enero";
        String year = "2026";
        boolean expectedResult = true;
        boolean result = DataValidation.validatePeriodValidity(period, year);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testValidateDateRangeSuccess() {
        boolean expectedResult = true;
        String startDate = "2024-12-19";
        String endDate = "2025-01-17";
        try {
            boolean result = DataValidation.validateDateRange(startDate, endDate);
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("Error al validar el rango de fechas");
        }
    }
    
    @Test
    public void testValidateNumberStudentsSuccess() {
        boolean expectedResult = true;
        String numberStudents = "125";
        boolean result = DataValidation.validateNumberStudents(numberStudents);
        assertEquals(expectedResult, result);
    }
}
