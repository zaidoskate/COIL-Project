package DAOs;

import java.util.ArrayList;
import logic.DAOs.PendingMailDAO;
import logic.LogicException;
import logic.domain.PendingMail;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PendingMailDAOTest {
    
    @Test
    public void testInsertPendingMailSuccess() throws LogicException {
        PendingMail pendingMail = new PendingMail();
        pendingMail.setIdUser(1);
        pendingMail.setDestinationEmail("destino@hotmail.com");
        pendingMail.setContent("Hola bienvendio a los coils");
        pendingMail.setSubject("Cuenta de acceso");
        
        PendingMailDAO pendingMailDAO = new PendingMailDAO();
        int currentResult = pendingMailDAO.insertPendingMail(pendingMail);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetPendingMailsByIdSuccess() throws LogicException {
        PendingMail pendingMail = new PendingMail();
        pendingMail.setIdUser(1);
        pendingMail.setIdEmail(1);
        pendingMail.setDestinationEmail("destino@hotmail.com");
        pendingMail.setContent("Hola bienvendio a los coils");
        pendingMail.setSubject("Cuenta de acceso");
        
        ArrayList<PendingMail> expectedResult = new ArrayList();
        expectedResult.add(pendingMail);
        
        PendingMailDAO pendingMailDAO = new PendingMailDAO();
        ArrayList<PendingMail> currentResult = pendingMailDAO.getPendingMailsByIdUser(1);
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testDeletePendingMailSuccess() throws LogicException {
        PendingMail pendingMail = new PendingMail();
        pendingMail.setIdUser(1);
        pendingMail.setIdEmail(1);
        pendingMail.setDestinationEmail("destino@hotmail.com");
        pendingMail.setContent("Hola bienvendio a los coils");
        pendingMail.setSubject("Cuenta de acceso");
        
        PendingMailDAO pendingMailDAO = new PendingMailDAO();
        int currentResult = pendingMailDAO.deletePendingMail(pendingMail);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
}
