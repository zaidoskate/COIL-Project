package DAOs.getTests;

import java.util.ArrayList;
import logic.DAOs.UvAccountRequestDAO;
import logic.LogicException;
import logic.domain.UvAccountRequest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class UvAccountRequestDAOTest {
    
    @Test
    public void testGetUvAccountRequestsSuccess() throws LogicException {
        ArrayList<UvAccountRequest> expectedResult = new ArrayList<>();
        UvAccountRequest uvAccountRequest;
        
        uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setIdDepartment("FEX");
        uvAccountRequest.setEmail("jesustlapahernandez@gmail.com");
        uvAccountRequest.setIdRequest(1);
        uvAccountRequest.setLastName("Tlapa Hernández");
        uvAccountRequest.setName("Frida");
        uvAccountRequest.setPersonalNumber("09652");
        expectedResult.add(uvAccountRequest);
        
        uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setIdDepartment("FEIX");
        uvAccountRequest.setEmail("mzelox@gmail.com");
        uvAccountRequest.setIdRequest(2);
        uvAccountRequest.setLastName("Zapata Elox");
        uvAccountRequest.setName("Martin");
        uvAccountRequest.setPersonalNumber("09122");
        expectedResult.add(uvAccountRequest);   
        
        uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setIdDepartment("FEX");
        uvAccountRequest.setEmail("aguslvaz@gmail.com");
        uvAccountRequest.setIdRequest(3);
        uvAccountRequest.setLastName("Lopez Vazquez");
        uvAccountRequest.setName("Agustin");
        uvAccountRequest.setPersonalNumber("93150");
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
