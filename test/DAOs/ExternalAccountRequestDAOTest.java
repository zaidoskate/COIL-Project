/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import logic.DAOs.ExternalAccountRequestDAO;
import logic.LogicException;
import logic.domain.ExternalAccountRequest;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author zaido
 */
public class ExternalAccountRequestDAOTest {
    public ExternalAccountRequestDAOTest() {
    }
    
    @Test
    public void testInsertExternalAccountRequestSuccess() {
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setName("Luis");
        externalAccountRequest.setLastName("Vargas");
        externalAccountRequest.setEmail("luis33@gmail.com");
        externalAccountRequest.setIdUniversity(1);
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        try{
            int result = externalAccountRequestDAO.insertExternalAccountRequest(externalAccountRequest);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha dado de alta la universidad" + logicException.getMessage());
        }
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
    public void testDeleteExternalAccountRequestSuccess() {
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setName("Luis");
        externalAccountRequest.setLastName("Vargas");
        externalAccountRequest.setEmail("luis33@gmail.com");
        externalAccountRequest.setIdUniversity(1);
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        try{
            int result = externalAccountRequestDAO.deleteExternalAccountRequest(externalAccountRequest);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha eliminado la universidad" + logicException.getMessage());
        }
    }
}
