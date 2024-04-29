/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOs;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.CollaborationOffer;
import logic.DAOs.CollaborationOfferDAO;
import logic.LogicException;

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
        collaborationOffer.setIdUser(4);
        collaborationOffer.setObjective("Aprendizaje");
        collaborationOffer.setTopicsOfInterest("Mecánica");
        collaborationOffer.setNumberOfStudents(30);
        collaborationOffer.setProfile("Mecánica");
        collaborationOffer.setLanguage("Español");
        collaborationOffer.setPeriod("FEBRERO-JULIO 2024");

        CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
        try {
            int result = collaborationOfferDAO.insertColaborationOffer(collaborationOffer);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha insertado la oferta de colaboracion");
        }
        
    }
    
    @Test
    public void testGetApprovedCollaborationOffer() {
        CollaborationOffer offerExpected = new CollaborationOffer();
        offerExpected.setIdCollaboration(1);
        offerExpected.setIdUser(1);
        offerExpected.setObjective("Promover las ciencias de la computacion");
        offerExpected.setTopicsOfInterest("informatica");
        offerExpected.setNumberOfStudents(28);
        offerExpected.setProfile("Informatica");
        offerExpected.setLanguage("English");
        offerExpected.setPeriod("Agosto-Enero 2025");
        offerExpected.setAditionalInfo("Parte del curso UV-GER");
        
        CollaborationOffer offerExpected2 = new CollaborationOffer();
        offerExpected2.setIdCollaboration(3);
        offerExpected2.setIdUser(2);
        offerExpected2.setObjective("Share knowledge");
        offerExpected2.setTopicsOfInterest("Linux");
        offerExpected2.setNumberOfStudents(150);
        offerExpected2.setProfile("Computer Science");
        offerExpected2.setLanguage("English");
        offerExpected2.setPeriod("Febrero - Julio 2024");
        
        ArrayList<CollaborationOffer> offersExpected = new ArrayList<>();
        offersExpected.add(offerExpected);
        offersExpected.add(offerExpected2);
        
        CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
        try {
            ArrayList<CollaborationOffer> offersObtained = collaborationOfferDAO.getApprovedCollaborationOffer();
            assertEquals(offersExpected, offersObtained);
        } catch(LogicException logicException) {
            fail("No se ha podido obtener las ofertas vigentes");
        }
    }
    
    @Test
    public void testGetProfessorApprovedOffer() {
        CollaborationOffer offerExpected = new CollaborationOffer();
        offerExpected.setIdCollaboration(1);
        offerExpected.setIdUser(1);
        offerExpected.setObjective("Promover las ciencias de la computacion");
        offerExpected.setTopicsOfInterest("informatica");
        offerExpected.setNumberOfStudents(28);
        offerExpected.setProfile("Informatica");
        offerExpected.setLanguage("English");
        offerExpected.setPeriod("Agosto-Enero 2025");
        offerExpected.setAditionalInfo("Parte del curso UV-GER");
        
        CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
        try {
            CollaborationOffer offerObtained = collaborationOfferDAO.getProfessorApprovedOffer(1);
            assertEquals(offerExpected, offerObtained);
        } catch(LogicException logicException) {
            fail("No se ha podido obtener la oferta del profesor");
        }
    }
    
    @Test
    public void testDeleteCollaborationOffer() {
        CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
        try {
            int result = collaborationOfferDAO.deleteCollaborationOffer(3);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha podido eliminar la oferta de colaboracion");
        }
    }
}
