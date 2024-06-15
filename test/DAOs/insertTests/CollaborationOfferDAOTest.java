package DAOs.insertTests;

import logic.DAOs.CollaborationOfferDAO;
import logic.LogicException;
import logic.domain.CollaborationOffer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class CollaborationOfferDAOTest {
    
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
}
