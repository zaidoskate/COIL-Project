package DAOs.insertTests;

import java.io.File;
import logic.DAOs.ConcludedCollaborationDAO;
import logic.LogicException;
import logic.domain.ConcludedCollaboration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ConcludedCollaborationDAOTest {
    
    @Test
    public void testAddConcludedCollaborationSuccess() throws LogicException{
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(3);
        concludedCollaboration.setIdUser(6);
        
        ConcludedCollaborationDAO concludedColaborationDAO = new ConcludedCollaborationDAO();
        int currentResult = concludedColaborationDAO.addConcludedCollaboration(concludedCollaboration);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testUpdateVisibilitySuccess() throws LogicException {
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(4);
        concludedCollaboration.setVisibility("Visible");
        
        ConcludedCollaborationDAO concludedColaborationDAO = new ConcludedCollaborationDAO();
        int currentResult = concludedColaborationDAO.updateVisibility(concludedCollaboration);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testUpdateRatingSuccess() throws LogicException {
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(4);
        concludedCollaboration.setRating(5);
        
        ConcludedCollaborationDAO concludedColaborationDAO = new ConcludedCollaborationDAO();
        int currentResult = concludedColaborationDAO.updateRating(concludedCollaboration);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testUpdateCertificatesSuccess() {
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(4);
        concludedCollaboration.setCertificatesFile(new File("../../constancias.zip"));
        
        ConcludedCollaborationDAO concludedColaborationDAO = new ConcludedCollaborationDAO();
        try {
            int currentResult = concludedColaborationDAO.uploadCertificates(concludedCollaboration);
            int expectedResult = 1;
            assertEquals(expectedResult, currentResult);
        } catch(LogicException logicException) {
            fail("no se han podido cargar las constancias");
        }
    }
}
