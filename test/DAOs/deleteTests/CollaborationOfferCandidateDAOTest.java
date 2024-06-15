package DAOs.deleteTests;

import logic.DAOs.CollaborationOfferCandidateDAO;
import logic.LogicException;
import static org.junit.Assert.fail;
import org.junit.Test;

public class CollaborationOfferCandidateDAOTest {
    
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
