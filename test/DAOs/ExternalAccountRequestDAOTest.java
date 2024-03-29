/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import logic.DAOs.ExternalAccountRequestDAO;
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
        int result = externalAccountRequestDAO.insertExternalAccountRequest(externalAccountRequest);
        assertNotEquals(0, result);
    }
    
    @Test
    public void testDeleteExternalAccountRequestSuccess() {
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setName("Luis");
        externalAccountRequest.setLastName("Vargas");
        externalAccountRequest.setEmail("luis33@gmail.com");
        externalAccountRequest.setIdUniversity(1);
        
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        int result = externalAccountRequestDAO.deleteExternalAccountRequest(externalAccountRequest.getEmail());
        assertNotEquals(0, result);
    }
}
