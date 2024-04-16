/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package gui;

import gui.DataValidation;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zaido
 */
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
        String name = "Zaid Alexis Vázquez Ramírez";
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
        String email = "zaidskate@hotmail.com";
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
}
