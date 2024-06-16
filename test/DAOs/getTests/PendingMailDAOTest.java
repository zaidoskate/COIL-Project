package DAOs.getTests;

import java.util.ArrayList;
import logic.DAOs.PendingMailDAO;
import logic.LogicException;
import logic.domain.PendingMail;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PendingMailDAOTest {
    @Test
    public void testGetPendingMailsByIdSuccess() throws LogicException {
        ArrayList<PendingMail> expectedResult = new ArrayList();
        
        PendingMail pendingMail = new PendingMail();
        pendingMail.setIdUser(1);
        pendingMail.setIdEmail(1);
        pendingMail.setDestinationEmail("destino@hotmail.com");
        pendingMail.setContent("Hola bienvendio a los coils");
        pendingMail.setSubject("Cuenta de acceso");
        expectedResult.add(pendingMail);
        
        pendingMail = new PendingMail();
        pendingMail.setIdUser(1);
        pendingMail.setIdEmail(2);
        pendingMail.setDestinationEmail("anacristinazarg@hotmail.com");
        pendingMail.setContent("Hola bienvendio a los coils");
        pendingMail.setSubject("Cuenta de acceso");
        expectedResult.add(pendingMail);
        
        PendingMailDAO pendingMailDAO = new PendingMailDAO();
        ArrayList<PendingMail> currentResult = pendingMailDAO.getPendingMailsByIdUser(1);
        assertEquals(expectedResult, currentResult);
    }
}
