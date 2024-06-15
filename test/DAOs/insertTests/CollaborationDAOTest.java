package DAOs.insertTests;

import logic.DAOs.CollaborationDAO;
import logic.LogicException;
import logic.domain.Collaboration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class CollaborationDAOTest {
    public CollaborationDAOTest() {
        
    }
    
    @Test
    public void testAddCollaborationSuccess() throws LogicException{
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        Collaboration collaboration = new Collaboration();
        collaboration.setColaborationName("Colaboracion LATAM");
        collaboration.setLanguage("Español");
        collaboration.setInterestTopic("SOLID Principles");
        
        int expectedResult = 1;
        int currentResult = collaborationDAO.addColaboration(collaboration);
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testStartCollaborationSuccess() {
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        try {
            int result = collaborationDAO.startCollaboration(5);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha podido iniciar la colaboracion");
        }
    }
    
    @Test
    public void testConcludeCollaborationSuccess() {
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        try {
            int result = collaborationDAO.concludeCollaboration(1);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha podido concluir la colaboracion");
        }
    }
    
    @Test
    public void testUpdateEndDateByIdCollaboration() throws LogicException{
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        
        int currentResult = collaborationDAO.updateEndDateByIdCollaboration(2);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
}
