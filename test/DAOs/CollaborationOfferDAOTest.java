/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOs;

import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.CollaborationOffer;
import logic.DAOs.CollaborationOfferDAO;

/**
 *
 * @author chima
 */
public class CollaborationOfferDAOTest {
    
    public CollaborationOfferDAOTest() {
    }

    @Test
    public void testInsertCollaborationOfferSuccess() {
        CollaborationOffer collaborationOffer = new CollaborationOffer();
        collaborationOffer.setIdCollaboration(12345);
        collaborationOffer.setOfferStatus("Abierta");
        collaborationOffer.setIdUser(54321);

        CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
        int result = collaborationOfferDAO.insertColaborationOffer(collaborationOffer);
        
        assertNotEquals(0, result);
    }
    
}
