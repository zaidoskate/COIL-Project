package DAOs.deleteTests;

import logic.DAOs.PendingMailDAO;
import logic.LogicException;
import logic.domain.PendingMail;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PendingMailDAOTest {
    @Test
    public void testDeletePendingMailSuccess() throws LogicException {
        PendingMail pendingMail = new PendingMail();
        pendingMail.setIdUser(1);
        pendingMail.setIdEmail(2);
        pendingMail.setDestinationEmail("anacristinazarg@gmail.com");
        pendingMail.setContent("Hola bienvendio a los coils");
        pendingMail.setSubject("Cuenta de acceso");
        
        PendingMailDAO pendingMailDAO = new PendingMailDAO();
        int currentResult = pendingMailDAO.deletePendingMail(pendingMail);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
}
