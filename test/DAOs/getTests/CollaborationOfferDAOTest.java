package DAOs.getTests;

import java.util.ArrayList;
import logic.DAOs.CollaborationOfferDAO;
import logic.LogicException;
import logic.domain.CollaborationOffer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class CollaborationOfferDAOTest {
    
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
    public void testGetUnapprovedCollaborationOffer() {
        CollaborationOffer offerExpected = new CollaborationOffer();
        offerExpected.setIdCollaboration(2);
        offerExpected.setIdUser(5);
        offerExpected.setObjective("Machine learning using Python");
        offerExpected.setTopicsOfInterest("Data Science");
        offerExpected.setNumberOfStudents(80);
        offerExpected.setProfile("Software Engineer");
        offerExpected.setLanguage("English");
        offerExpected.setPeriod("Agosto - Enero 2025");
        
        CollaborationOffer offerExpected2 = new CollaborationOffer();
        offerExpected2.setIdCollaboration(4);
        offerExpected2.setIdUser(3);
        offerExpected2.setObjective("Curso de derecho constitucional");
        offerExpected2.setTopicsOfInterest("Derecho constitucional");
        offerExpected2.setNumberOfStudents(50);
        offerExpected2.setProfile("Abogado");
        offerExpected2.setLanguage("Español");
        offerExpected2.setPeriod("Agosto - Enero 2025");
        
        ArrayList<CollaborationOffer> offersExpected = new ArrayList<>();
        offersExpected.add(offerExpected);
        offersExpected.add(offerExpected2);
        
        CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
        try {
            ArrayList<CollaborationOffer> offersObtained = collaborationOfferDAO.getUnapprovedCollaborationOffer();
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
            CollaborationOffer offerObtained = collaborationOfferDAO.getProfessorApprovedOffer(4);
            assertEquals(offerExpected, offerObtained);
        } catch(LogicException logicException) {
            fail("No se ha podido obtener la oferta del profesor");
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
