package DAOs;

import java.util.ArrayList;
import logic.LogicException;
import logic.DAOs.UvAccountRequestDAO;
import logic.domain.UvAccountRequest;
import static org.junit.Assert.*;
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
        uvAccountRequest.setIdRequest(1);
        uvAccountRequest.setName("Juan Carlos");
        uvAccountRequest.setLastName("Perez Arriaga");
        uvAccountRequest.setEmail("elrevo@gmail.com");
        uvAccountRequest.setPersonalNumber("");
        
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        uvAccountRequestDAO.insertUvAccountRequest(uvAccountRequest);
    }
    
    @Test
    public void testDeleteUvAccountRequestSuccess() throws LogicException {
        UvAccountRequest uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setIdRequest(1);
        uvAccountRequest.setName("Juan Carlos");
        uvAccountRequest.setLastName("Perez Arriaga");
        uvAccountRequest.setEmail("elrevo@gmail.com");
        uvAccountRequest.setPersonalNumber("12345");
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
    
    @Test
    public void testGetUvAccountRequestsSuccess() throws LogicException {
        ArrayList<UvAccountRequest> expectedResult = new ArrayList<>();
        UvAccountRequest uvAccountRequest;
        uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setIdDepartment("FEIX");
        uvAccountRequest.setEmail("chimalg2001@outlook.com");
        uvAccountRequest.setIdRequest(9);
        uvAccountRequest.setLastName("González Chimal");
        uvAccountRequest.setName("Marcio Miguel");
        uvAccountRequest.setPersonalNumber("07061");
        expectedResult.add(uvAccountRequest);
        
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        ArrayList<UvAccountRequest> result = uvAccountRequestDAO.getUvAccountRequests();
        
        assertEquals(expectedResult, result);
    }
    @Test
    public void testGetUvAccountRequestsFailed() throws LogicException {
        ArrayList<UvAccountRequest> expectedResult = new ArrayList<>();
        UvAccountRequest uvAccountRequest;
        uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setIdDepartment("FDX");
        uvAccountRequest.setEmail("chimalg2001@outlook.com");
        uvAccountRequest.setIdRequest(1);
        uvAccountRequest.setLastName("González Chimal");
        uvAccountRequest.setName("Marcio Miguel");
        uvAccountRequest.setPersonalNumber("12345");
        expectedResult.add(uvAccountRequest);
        
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        ArrayList<UvAccountRequest> result = uvAccountRequestDAO.getUvAccountRequests();
        
        assertNotEquals(expectedResult, result);
    }
}
