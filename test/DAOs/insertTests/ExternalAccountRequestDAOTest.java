package DAOs.insertTests;

import logic.DAOs.ExternalAccountRequestDAO;
import logic.domain.ExternalAccountRequest;
import logic.LogicException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ExternalAccountRequestDAOTest {
    
    @Test
    public void testInsertExternalAccountRequestSuccess() throws LogicException {
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setName("Luis");
        externalAccountRequest.setLastName("Vargas");
        externalAccountRequest.setEmail("luis33@gmail.com");
        externalAccountRequest.setIdUniversity(3);
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        
        int result = externalAccountRequestDAO.insertExternalAccountRequest(externalAccountRequest);
        assertEquals(1, result);
        
    }
    
    @Test(expected = LogicException.class)
    public void testInsertExternalAccountRequestFailed() throws LogicException {
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setName("Luissssssssssssssssssssssssssssssssssssssssssssss");
        externalAccountRequest.setLastName("Vargsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssas");
        externalAccountRequest.setEmail("luisssssssssssssssssssssssssssssssssssssssssssssssssssssssss33@gmail.com");
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        int result = externalAccountRequestDAO.insertExternalAccountRequest(externalAccountRequest);
        assertEquals(0, result);
    }
}
