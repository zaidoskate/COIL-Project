package DAOs.deleteTests;

import logic.DAOs.ExternalAccountRequestDAO;
import logic.domain.ExternalAccountRequest;
import logic.LogicException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ExternalAccountRequestDAOTest {
    
    @Test
    public void testDeleteExternalAccountRequestSuccess() throws LogicException {
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setIdRequest(3);
        externalAccountRequest.setName("Magdalena");
        externalAccountRequest.setLastName("Gonzalez Pérez");
        externalAccountRequest.setEmail("magperez@gmail.com");
        externalAccountRequest.setIdUniversity(5);
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        int result = externalAccountRequestDAO.deleteExternalAccountRequest(externalAccountRequest);
        assertEquals(1, result);
    }
}
