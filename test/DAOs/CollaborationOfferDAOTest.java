package DAOs;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.CollaborationOffer;
import logic.DAOs.CollaborationOfferDAO;
import logic.LogicException;

public class CollaborationOfferDAOTest {
    
    public CollaborationOfferDAOTest() {
    }
    
    @Test
    public void testInsertCollaborationOfferSuccess() {
        CollaborationOffer collaborationOffer = new CollaborationOffer();
        collaborationOffer.setIdUser(6);
        collaborationOffer.setObjective("Aprendizaje");
        collaborationOffer.setTopicsOfInterest("Mecánica");
        collaborationOffer.setNumberOfStudents(30);
        collaborationOffer.setProfile("Mecánica");
        collaborationOffer.setLanguage("Español");
        collaborationOffer.setPeriod("Agosto - Enero 2025");

        CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
        try {
            int result = collaborationOfferDAO.insertColaborationOffer(collaborationOffer);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha insertado la oferta de colaboracion");
        }
        
    }
    
    @Test
    public void testEvaluateCollaborationOfferSuccess() {
        int expectedResult = 1;
        CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
        try {
            int result = collaborationOfferDAO.evaluateCollaborationOffer(2, "Aprobada");
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("No se pudo evaluar la oferta");
        }
    }
    
    @Test
    public void testGetApprovedCollaborationOffer() {
        CollaborationOffer offerExpected = new CollaborationOffer();
        offerExpected.setIdCollaboration(1);
        offerExpected.setIdUser(4);
        offerExpected.setObjective("Aprendizaje de Spring framework");
        offerExpected.setTopicsOfInterest("Informática, Computación");
        offerExpected.setNumberOfStudents(45);
        offerExpected.setProfile("Ingeniero en sistemas");
        offerExpected.setLanguage("Español");
        offerExpected.setPeriod("Agosto - Enero 2025");
        
        CollaborationOffer offerExpected2 = new CollaborationOffer();
        offerExpected2.setIdCollaboration(3);
        offerExpected2.setIdUser(3);
        offerExpected2.setObjective("Aprendizaje");
        offerExpected2.setTopicsOfInterest("Mecánica");
        offerExpected2.setNumberOfStudents(30);
        offerExpected2.setProfile("Mecánica");
        offerExpected2.setLanguage("Español");
        offerExpected2.setPeriod("Agosto - Enero 2025");
        
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
    public void testGetUnapprovedCollaborationOffer() {
        CollaborationOffer offerExpected = new CollaborationOffer();
        offerExpected.setIdCollaboration(4);
        offerExpected.setIdUser(3);
        offerExpected.setObjective("Curso de derecho constitucional");
        offerExpected.setTopicsOfInterest("Derecho constitucional");
        offerExpected.setNumberOfStudents(50);
        offerExpected.setProfile("Abogado");
        offerExpected.setLanguage("Español");
        offerExpected.setPeriod("Agosto - Enero 2025");
        
        ArrayList<CollaborationOffer> offersExpected = new ArrayList<>();
        offersExpected.add(offerExpected);
        
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
        offerExpected.setIdUser(4);
        offerExpected.setObjective("Aprendizaje de Spring framework");
        offerExpected.setTopicsOfInterest("Informática, Computación");
        offerExpected.setNumberOfStudents(45);
        offerExpected.setProfile("Ingeniero en sistemas");
        offerExpected.setLanguage("Español");
        offerExpected.setPeriod("Agosto - Enero 2025");
        
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
            int result = collaborationOfferDAO.deleteCollaborationOffer(5);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha podido eliminar la oferta de colaboracion");
        }
    }
    
    @Test
    public void testProfessorHasOfferSuccess() {
        boolean expectedResult = true;
        CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
        try {
            boolean obtainedResult = collaborationOfferDAO.professorHasOffer(4);
            assertEquals(expectedResult, obtainedResult);
        } catch(LogicException logicException) {
            fail("No se ha podido verificar si el profesor tiene una oferta publicada");
        }
    }
}
