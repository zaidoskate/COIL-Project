package DAOs;

import java.util.ArrayList;
import logic.DAOs.CollaborationDAO;
import logic.LogicException;
import logic.domain.Collaboration;
import org.junit.Test;
import static org.junit.Assert.*;

public class CollaborationDAOTest {
    public CollaborationDAOTest() {
        
    }
    
    @Test
    public void testAddCollaborationSuccess() throws LogicException{
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        Collaboration collaboration = new Collaboration();
        collaboration.setColaborationName("Colaboracion5");
        collaboration.setStartDate("2024-12-15");
        
        int expectedResult = 1;
        int currentResult = collaborationDAO.addColaboration(collaboration);
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetCollaborationByIdSuccess() throws LogicException{
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        Collaboration currentResultCollaboration = collaborationDAO.getColaborationById(2);
        Collaboration collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion");
        collaborationExpected.setEndDate("2024-12-01");
        collaborationExpected.setIdColaboration(2);
        
        assertEquals(collaborationExpected, currentResultCollaboration);
    }
    
    @Test
    public void testUpdateEndDateByIdCollaboration() throws LogicException{
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        
        int currentResult = collaborationDAO.updateEndDateByIdCollaboration(1, "2025-01-01");
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetAllCollaborations() throws LogicException{
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> collaborationsResult = collaborationDAO.getAllCollaborations();
        
        ArrayList<Collaboration> collaborationsExpected = new ArrayList();
        
        Collaboration collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion4");
        collaborationExpected.setEndDate("2024-04-12");
        collaborationExpected.setIdColaboration(1);
        collaborationsExpected.add(collaborationExpected);
        
        collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion");
        collaborationExpected.setEndDate("2024-12-01");
        collaborationExpected.setIdColaboration(2);
        collaborationsExpected.add(collaborationExpected);
        
        
        assertEquals(collaborationsExpected, collaborationsResult);
    }
    
    @Test
    public void testGetActiveCollaborations() throws LogicException{
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> collaborationsResult = collaborationDAO.getActiveCollaborations();
        
        ArrayList<Collaboration> collaborationsExpected = new ArrayList();
        
        Collaboration collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion2");
        collaborationExpected.setEndDate("2024-04-12");
        collaborationExpected.setIdColaboration(2);
        collaborationsExpected.add(collaborationExpected);
        
        
        assertEquals(collaborationsExpected, collaborationsResult);
    }
}
