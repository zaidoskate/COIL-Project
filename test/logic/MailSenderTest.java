package logic;

import org.junit.Test;
import static org.junit.Assert.*;

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
