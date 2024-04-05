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
        collaborationOffer.setIdCollaboration(101010);
        collaborationOffer.setObjective("Aprendizaje");
        collaborationOffer.setTopicsOfInterest("Mecánica");
        collaborationOffer.setNumberOfStudents(30);
        collaborationOffer.setProfile("Mecánica");
        collaborationOffer.setLanguage("Español");
        collaborationOffer.setPeriod("FEBRERO-JULIO 2024");
        collaborationOffer.setAditionalInfo("Nada");

        CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
        int result = collaborationOfferDAO.insertColaborationOffer(collaborationOffer);
        
        assertNotEquals(0, result);
    }
    
}
