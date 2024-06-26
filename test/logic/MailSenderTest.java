package logic;

import org.junit.Test;
import static org.junit.Assert.*;

public class MailSenderTest {
    
    public MailSenderTest() {
    }

    @Test
    public void testSendEmail() {
        String emailBody = "Hola esto es una prueba";
        String recipient = "zs22013659@estudiantes.uv.mx";
        boolean expResult = true;
        try {
            boolean result = MailSender.sendEmail(emailBody, recipient);
            assertEquals(expResult, result);
        } catch(LogicException logicException) {
            fail("Error al enviar el correo");
        }
    }
    
}
