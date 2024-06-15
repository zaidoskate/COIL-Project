package DAOs.getTests;

import java.util.ArrayList;
import logic.DAOs.CollaborationOfferCandidateDAO;
import logic.LogicException;
import logic.domain.CollaborationOfferCandidate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CollaborationOfferCandidateDAOTest {
    
    @Before
    public void setUp() {
        CollaborationOfferCandidateDAO collaborationOfferCandidateDAO = new CollaborationOfferCandidateDAO();
        
        CollaborationOfferCandidate candidate1 = new CollaborationOfferCandidate();
        candidate1.setIdCollaboration(3);
        candidate1.setIdUser(3);
        
        CollaborationOfferCandidate candidate2 = new CollaborationOfferCandidate();
        candidate2.setIdCollaboration(3);
        candidate2.setIdUser(4);
        
        try {
            collaborationOfferCandidateDAO.InsertCollaborationOfferCandidate(candidate1);
            collaborationOfferCandidateDAO.InsertCollaborationOfferCandidate(candidate2);
        } catch(LogicException logicException) {
            fail("Error al insertar los candidatos: " + logicException.getMessage());
        }
    }
    
    @After
    public void tearDown() {
        CollaborationOfferCandidateDAO collaborationOfferCandidateDAO = new CollaborationOfferCandidateDAO();
        
        try {
            collaborationOfferCandidateDAO.deleteCollaborationOffer(3);
        } catch(LogicException logicException) {
            fail("Error al eliminar la oferta de colaboración: " + logicException.getMessage());
        }
    }
    
    @Test
    public void testGetCollaborationOfferCandidateByIdCollaborationOfferSuccess() {
        CollaborationOfferCandidate expectedCollaborationOfferCandidate1 = new CollaborationOfferCandidate();
        expectedCollaborationOfferCandidate1.setIdCollaboration(3);
        expectedCollaborationOfferCandidate1.setIdUser(3);
        
        CollaborationOfferCandidate expectedCollaborationOfferCandidate2 = new CollaborationOfferCandidate();
        expectedCollaborationOfferCandidate2.setIdCollaboration(3);
        expectedCollaborationOfferCandidate2.setIdUser(4);
        
        ArrayList<CollaborationOfferCandidate> candidatesExpected = new ArrayList<>();
        candidatesExpected.add(expectedCollaborationOfferCandidate1);
        candidatesExpected.add(expectedCollaborationOfferCandidate2);
        
        CollaborationOfferCandidateDAO collaborationOfferCandidateDAO = new CollaborationOfferCandidateDAO();
        try {
            ArrayList<CollaborationOfferCandidate> candidatesObtained = collaborationOfferCandidateDAO.GetCollaborationOfferCandidateByIdCollaborationOffer(3);
            assertEquals(candidatesExpected, candidatesObtained);
        } catch(LogicException logicException) {
            fail("Error al recuperar los candidatos de la oferta: " + logicException.getMessage());
        }
    }
    
    @Test
    public void testProfessorHasAppliedForOfferSuccess() {
        CollaborationOfferCandidateDAO collaborationOfferCandidateDAO = new CollaborationOfferCandidateDAO();
        try {
            boolean result = collaborationOfferCandidateDAO.professorHasAppliedForOffer(4, 3);
            assertTrue(result);
        } catch (LogicException logicException) {
            fail("Error al saber si un profesor ya se postuló a esa oferta: " + logicException.getMessage());
        }
    }
}
