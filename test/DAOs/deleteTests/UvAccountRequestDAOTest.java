package DAOs.deleteTests;

import logic.LogicException;
import logic.DAOs.UvAccountRequestDAO;
import logic.domain.UvAccountRequest;
import static org.junit.Assert.*;
import org.junit.Test;

public class UvAccountRequestDAOTest {
    @Test
    public void testDeleteUvAccountRequestSuccess() throws LogicException {
        UvAccountRequest uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setIdRequest(3);
        uvAccountRequest.setIdDepartment("FEIX");
        
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        int result = uvAccountRequestDAO.deleteUvAccountRequest(uvAccountRequest);
        assertEquals(1, result);
    }
    
    @Test
    public void testDeleteUvAccountRequestFailed() throws LogicException {
        UvAccountRequest uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setName("Juan Carlos");
        uvAccountRequest.setLastName("Perez Arriaga");
        uvAccountRequest.setEmail("elrevo@gmail.com");
        uvAccountRequest.setPersonalNumber("12");
        uvAccountRequest.setIdDepartment("FEIX");
        
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        int result = uvAccountRequestDAO.deleteUvAccountRequest(uvAccountRequest);
        assertEquals(0, result);
    }
    
}
