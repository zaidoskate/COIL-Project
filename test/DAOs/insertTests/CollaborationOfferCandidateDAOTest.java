package DAOs.insertTests;

import logic.DAOs.CollaborationOfferCandidateDAO;
import logic.LogicException;
import logic.domain.CollaborationOfferCandidate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class CollaborationOfferCandidateDAOTest {
    
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
}
