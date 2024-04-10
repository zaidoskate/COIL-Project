/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package logic;

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
        String input = "Palabra de prueba";
        boolean expectedResult = true;
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
    public void testValidateEmailSuccess() {
        String email = "zaidskate@hotmail.com";
        boolean expectedResult = true;
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
    public void testValidateWordFail() {
        String input = "H0L4 3570 35 un test";
        boolean expectedResult = false;
        boolean result = DataValidation.validateWord(input);
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
    public void testValidateEmailFail() {
        String email = "prueba.com";
        boolean expectedResult = false;
        boolean result = DataValidation.validateEmail(email);
        assertEquals(expectedResult, result);
    }
    
    @Test
    public void testValidateDateFail() {
        String date = "19-12-2004";
        boolean expectedResult = false;
        boolean result = DataValidation.validateDate(date);
        assertEquals(expectedResult, result);
    }
    
}
