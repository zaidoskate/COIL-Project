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
public class MailSenderTest {
    
    public MailSenderTest() {
    }

    @Test
    public void testSendEmail() throws LogicException {
        String emailBody = "Hola esto es una prueba";
        String recipient = "chuchitotlapa@hotmail.com";
        boolean expResult = true;
        boolean result = MailSender.sendEmail(emailBody, recipient);
        assertEquals(expResult, result);
    }
    
}
