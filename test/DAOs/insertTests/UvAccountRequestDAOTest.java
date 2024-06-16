package DAOs.insertTests;

import logic.DAOs.UvAccountRequestDAO;
import logic.LogicException;
import logic.domain.UvAccountRequest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UvAccountRequestDAOTest {
    @Test
    public void testInsertUvAccountRequestSuccess() throws LogicException {
        UvAccountRequest uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setName("Juan Carlos");
        uvAccountRequest.setLastName("Perez Arriaga");
        uvAccountRequest.setEmail("elrevo@gmail.com");
        uvAccountRequest.setPersonalNumber("12335");
        uvAccountRequest.setIdDepartment("FEIX");
        
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        int result = uvAccountRequestDAO.insertUvAccountRequest(uvAccountRequest);
        assertEquals(1, result);
    }
    
    @Test(expected = LogicException.class)
    public void testInsertUvAccountRequestFailed() throws LogicException {
        UvAccountRequest uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setName("Juan Carlosdfhsdgnsdgndgsbdsfsssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        uvAccountRequest.setLastName("Perez Arrdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfdfiaga");
        uvAccountRequest.setEmail("elrevo@gmail.com");
        uvAccountRequest.setPersonalNumber("");
        
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        uvAccountRequestDAO.insertUvAccountRequest(uvAccountRequest);
    }
}
