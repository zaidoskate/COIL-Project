package DAOs.getTests;

import java.util.ArrayList;
import logic.DAOs.ExternalAccountRequestDAO;
import logic.LogicException;
import logic.domain.ExternalAccountRequest;
import logic.domain.ExternalAccountRequestData;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class ExternalAccountRequestDAOTest {
    
    @Test
    public void testGetExternalAccountRequestsDataSuccess() throws LogicException {
        ArrayList<ExternalAccountRequestData> externalAccountRequestsExpected = new ArrayList<>();
        ExternalAccountRequestData externalAccountRequest;
        
        externalAccountRequest = new ExternalAccountRequestData();
        externalAccountRequest.setIdRequest(1);
        externalAccountRequest.setName("Cristy");
        externalAccountRequest.setLastName("Rodríguez");
        externalAccountRequest.setEmail("cristyzarg@gmail.com");
        externalAccountRequest.setIdUniversity(1);
        externalAccountRequestsExpected.add(externalAccountRequest);
        
        externalAccountRequest = new ExternalAccountRequestData();
        externalAccountRequest.setIdRequest(2);
        externalAccountRequest.setName("Rosalia");
        externalAccountRequest.setLastName("Rodríguez");
        externalAccountRequest.setEmail("rositaz@gmail.com");
        externalAccountRequest.setIdUniversity(3);
        externalAccountRequestsExpected.add(externalAccountRequest);
        
        externalAccountRequest = new ExternalAccountRequestData();
        externalAccountRequest.setIdRequest(3);
        externalAccountRequest.setName("Magdalena");
        externalAccountRequest.setLastName("Gonzalez Pérez");
        externalAccountRequest.setEmail("magperez@gmail.com");
        externalAccountRequest.setIdUniversity(5);
        externalAccountRequestsExpected.add(externalAccountRequest);
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        ArrayList<ExternalAccountRequestData> externalAccountRequestsCurrentResult = externalAccountRequestDAO.getExternalAccountRequestsData();
        
        assertEquals(externalAccountRequestsExpected, externalAccountRequestsCurrentResult);
    }
    
    @Test
    public void testGetExternalAccountRequestByIdSuccess() throws LogicException {
        ExternalAccountRequest externalAccountRequestExpected = new ExternalAccountRequest();
        externalAccountRequestExpected.setIdRequest(2);
        externalAccountRequestExpected.setName("Rosalia");
        externalAccountRequestExpected.setLastName("Rodríguez");
        externalAccountRequestExpected.setEmail("rositaz@gmail.com");
        externalAccountRequestExpected.setIdUniversity(3);
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        ExternalAccountRequest currentExternalAccountRequest = externalAccountRequestDAO.getExternalAccountRequestById(2);
        
        assertEquals(externalAccountRequestExpected, currentExternalAccountRequest);
    }
    
    @Test
    public void testGetExternalAccountRequestByIdFailed() throws LogicException {
        ExternalAccountRequest externalAccountRequestExpected = new ExternalAccountRequest();
        externalAccountRequestExpected.setIdRequest(100);
        externalAccountRequestExpected.setName("Jesus Lorenzo");
        externalAccountRequestExpected.setLastName("Tlapa Hdez");
        externalAccountRequestExpected.setEmail("jesus@hotmail.com");
        externalAccountRequestExpected.setIdUniversity(1);
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        ExternalAccountRequest currentExternalAccountRequest = externalAccountRequestDAO.getExternalAccountRequestById(100);
        
        assertNotEquals(externalAccountRequestExpected, currentExternalAccountRequest);
    }
}
