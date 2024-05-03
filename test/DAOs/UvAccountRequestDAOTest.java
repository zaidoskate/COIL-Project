/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import logic.LogicException;
import logic.DAOs.UvAccountRequestDAO;
import logic.domain.UvAccountRequest;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author zaido
 */
public class UvAccountRequestDAOTest {
    
    @Test
    public void testInsertUvAccountRequestSuccess() {
        UvAccountRequest uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setName("Juan Carlos");
        uvAccountRequest.setLastName("Perez Arriaga");
        uvAccountRequest.setEmail("elrevo@gmail.com");
        uvAccountRequest.setPersonalNumber("12335");
        uvAccountRequest.setIdDepartment("FEIX");
        
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        try {
            int result = uvAccountRequestDAO.insertUvAccountRequest(uvAccountRequest);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha creado la solicitud de cuenta UV" + logicException.getMessage());
        }
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
    public void testDeleteUvAccountRequestSuccess() {
        UvAccountRequest uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setIdRequest(1);
        uvAccountRequest.setName("Juan Carlos");
        uvAccountRequest.setLastName("Perez Arriaga");
        uvAccountRequest.setEmail("elrevo@gmail.com");
        uvAccountRequest.setPersonalNumber("12345");
        uvAccountRequest.setIdDepartment("FEIX");
        
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        try {
            int result = uvAccountRequestDAO.deleteUvAccountRequest(uvAccountRequest);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha podido eliminar la solicitud de cuenta UV" + logicException.getMessage());
        }
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
