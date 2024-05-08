package DAOs;

import java.util.ArrayList;
import logic.DAOs.ExternalAccountRequestDAO;
import logic.LogicException;
import logic.domain.ExternalAccountRequest;
import logic.domain.ExternalAccountRequestData;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExternalAccountRequestDAOTest {
    public ExternalAccountRequestDAOTest() {
    }
    
    @Test
    public void testInsertExternalAccountRequestSuccess() throws LogicException {
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setName("Luis");
        externalAccountRequest.setLastName("Vargas");
        externalAccountRequest.setEmail("luis33@gmail.com");
        externalAccountRequest.setIdUniversity(2);
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        
        int result = externalAccountRequestDAO.insertExternalAccountRequest(externalAccountRequest);
        assertEquals(1, result);
        
    }
    
    @Test(expected = LogicException.class)
    public void testInsertExternalAccountRequestFailed() throws LogicException {
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setName("Luis");
        externalAccountRequest.setLastName("Vargas");
        externalAccountRequest.setEmail("luis33@gmail.com");
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        int result = externalAccountRequestDAO.insertExternalAccountRequest(externalAccountRequest);
        assertEquals(0, result);
    }
    
    @Test
    public void testDeleteExternalAccountRequestSuccess() throws LogicException {
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setIdRequest(4);
        externalAccountRequest.setName("Luis");
        externalAccountRequest.setLastName("Vargas");
        externalAccountRequest.setEmail("luis33@gmail.com");
        externalAccountRequest.setIdUniversity(2);
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        int result = externalAccountRequestDAO.deleteExternalAccountRequest(externalAccountRequest);
        assertEquals(1, result);
    }
    
    @Test
    public void testGetExternalAccountRequestsDataSuccess() throws LogicException {
        ArrayList<ExternalAccountRequestData> externalAccountRequestsExpected = new ArrayList<>();
        ExternalAccountRequestData externalAccountRequest;
        externalAccountRequest = new ExternalAccountRequestData();
        externalAccountRequest.setIdRequest(1);
        externalAccountRequest.setName("Jesus Lorenzo");
        externalAccountRequest.setLastName("Tlapa Hdez");
        externalAccountRequest.setEmail("jesus@hotmail.com");
        externalAccountRequest.setIdUniversity(1);
        externalAccountRequestsExpected.add(externalAccountRequest);
        externalAccountRequest = new ExternalAccountRequestData();
        externalAccountRequest.setIdRequest(2);
        externalAccountRequest.setName("Jesus");
        externalAccountRequest.setLastName("Tlapa");
        externalAccountRequest.setEmail("jesus@hotmail.com");
        externalAccountRequest.setIdUniversity(1);
        externalAccountRequestsExpected.add(externalAccountRequest);
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        ArrayList<ExternalAccountRequestData> externalAccountRequestsCurrentResult = externalAccountRequestDAO.getExternalAccountRequestsData();
        
        assertEquals(externalAccountRequestsExpected, externalAccountRequestsCurrentResult);
    }
    
    @Test
    public void testGetExternalAccountRequestByIdSuccess() throws LogicException {
        ExternalAccountRequest externalAccountRequestExpected = new ExternalAccountRequest();
        externalAccountRequestExpected.setIdRequest(1);
        externalAccountRequestExpected.setName("Jesus Lorenzo");
        externalAccountRequestExpected.setLastName("Tlapa Hdez");
        externalAccountRequestExpected.setEmail("jesus@hotmail.com");
        externalAccountRequestExpected.setIdUniversity(1);
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        ExternalAccountRequest currentExternalAccountRequest = externalAccountRequestDAO.getExternalAccountRequestById(1);
        
        assertEquals(externalAccountRequestExpected, currentExternalAccountRequest);
    }
    
    @Test
    public void testGetExternalAccountRequestByIdFailed() throws LogicException {
        ExternalAccountRequest externalAccountRequestExpected = new ExternalAccountRequest();
        externalAccountRequestExpected.setIdRequest(2);
        externalAccountRequestExpected.setName("Jesus Lorenzo");
        externalAccountRequestExpected.setLastName("Tlapa Hdez");
        externalAccountRequestExpected.setEmail("jesus@hotmail.com");
        externalAccountRequestExpected.setIdUniversity(1);
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        ExternalAccountRequest currentExternalAccountRequest = externalAccountRequestDAO.getExternalAccountRequestById(2);
        
        assertNotEquals(externalAccountRequestExpected, currentExternalAccountRequest);
    }

}
