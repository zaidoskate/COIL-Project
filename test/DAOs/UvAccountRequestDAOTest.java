/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import logic.DAOs.UvAccountRequestDAO;
import logic.domain.UvAccountRequest;
import static org.junit.Assert.assertNotEquals;
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
        uvAccountRequest.setPersonalNumber(23);
        
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        int result = uvAccountRequestDAO.insertUvAccountRequest(uvAccountRequest);
        assertNotEquals(0, result);
    }
    
    @Test
    public void testDeleteUvAccountRequestSuccess() {
        UvAccountRequest uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setName("Juan Carlos");
        uvAccountRequest.setLastName("Perez Arriaga");
        uvAccountRequest.setEmail("elrevo@gmail.com");
        uvAccountRequest.setPersonalNumber(23);
        
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        int result = uvAccountRequestDAO.deleteUvAccountRequest(uvAccountRequest.getPersonalNumber());
        assertNotEquals(0, result);
    }
}