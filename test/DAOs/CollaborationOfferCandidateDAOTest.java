package DAOs;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;
import logic.domain.CollaborationOfferCandidate;
import logic.DAOs.CollaborationOfferCandidateDAO;
import logic.LogicException;

public class CollaborationOfferCandidateDAOTest {
    
    public CollaborationOfferCandidateDAOTest() {
    }

    @Test
    public void testInsertCollaborationOfferCandidateSuccess() {
        CollaborationOfferCandidateDAO collaborationOfferCandidateDAO = new CollaborationOfferCandidateDAO();
        CollaborationOfferCandidate collaborationOfferCandidate = new CollaborationOfferCandidate();
        collaborationOfferCandidate.setIdCollaboration(1);
        collaborationOfferCandidate.setIdUser(3);
        
        try {
            int currentResult = collaborationOfferCandidateDAO.InsertCollaborationOfferCandidate(collaborationOfferCandidate);
            int expectedResult = 1;
            assertEquals(expectedResult, currentResult);
        } catch(LogicException logicException) {
            fail("Error al insertar el candidato");
        }
    }
    
    @Test
    public void testProfessorHasAppliedForOfferSuccess() {
        CollaborationOfferCandidateDAO collaborationOfferCandidateDAO = new CollaborationOfferCandidateDAO();
        try {
            boolean result = collaborationOfferCandidateDAO.professorHasAppliedForOffer(2, 3);
            assertTrue(result);
        } catch (LogicException logicException) {
            fail("Error al saber si un profesor ya se postulo a esa oferta");
        }
    }
    
    @Test
    public void testGetCollaborationOfferCandidateByIdCollaborationOfferSuccess() {
        CollaborationOfferCandidate expectedCollaborationOfferCandidate = new CollaborationOfferCandidate();
        expectedCollaborationOfferCandidate.setIdCollaboration(3);
        expectedCollaborationOfferCandidate.setIdUser(1);
        ArrayList<CollaborationOfferCandidate> candidatesExpected = new ArrayList<>();
        candidatesExpected.add(expectedCollaborationOfferCandidate);
        
        CollaborationOfferCandidateDAO collaborationOfferCandidateDAO = new CollaborationOfferCandidateDAO();
        try {
            ArrayList<CollaborationOfferCandidate> candidatesObtained = collaborationOfferCandidateDAO.GetCollaborationOfferCandidateByIdCollaborationOffer(3);
            assertEquals(candidatesExpected, candidatesObtained);
        } catch(LogicException logicException) {
            fail("Error al recuperar los candidatos de la oferta");
        }
    }
    
    @Test
    public void testDeleteCollaborationOfferSuccess() {
        CollaborationOfferCandidateDAO collaborationOfferCandidateDAO = new CollaborationOfferCandidateDAO();
        try  {
            collaborationOfferCandidateDAO.deleteCollaborationOffer(7);
        } catch(LogicException logicException) {
            fail("Error al eliminar la oferta de colaboración: " + logicException.getMessage());
        }
    }

}
