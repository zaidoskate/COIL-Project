package DAOs.deleteTests;

import logic.DAOs.CollaborationOfferDAO;
import logic.LogicException;
import logic.domain.CollaborationOffer;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class CollaborationOfferDAOTest {
    
    @Before
    public void setUp() {
        CollaborationOffer collaborationOffer = new CollaborationOffer();
        collaborationOffer.setIdUser(3);
        collaborationOffer.setObjective("Investigación");
        collaborationOffer.setTopicsOfInterest("Inteligencia Artificial");
        collaborationOffer.setNumberOfStudents(20);
        collaborationOffer.setProfile("Computación");
        collaborationOffer.setLanguage("Inglés");
        collaborationOffer.setPeriod("Enero - Junio 2024");

        CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
        try {
            int result = collaborationOfferDAO.insertColaborationOffer(collaborationOffer);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            logicException.printStackTrace();
            fail("No se ha insertado la oferta de colaboración: " + logicException.getMessage());
        }
    }
    
    @Test
    public void testDeleteCollaborationOffer() {
        CollaborationOfferDAO collaborationOfferDAO = new CollaborationOfferDAO();
        try {
            int result = collaborationOfferDAO.deleteCollaborationOffer(5);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha podido eliminar la oferta de colaboración: " + logicException.getMessage());
        }
    }
}
