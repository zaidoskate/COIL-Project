package DAOs;

import java.util.ArrayList;
import logic.DAOs.CollaborationDAO;
import logic.domain.Collaboration;
import org.junit.Test;
import static org.junit.Assert.*;

public class CollaborationDAOTest {
    public CollaborationDAOTest() {
        
    }
    
    @Test
    public void testAddCollaborationSuccess() {
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        Collaboration collaboration = new Collaboration();
        collaboration.setColaborationName("Colaboracion4");
        collaboration.setStartDate("2024-04-12");
        collaboration.setIdColaboration(184);
        
        int expectedResult = 1;
        int currentResult = collaborationDAO.addColaboration(collaboration);
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetCollaborationById() {
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        Collaboration currentResultCollaboration = collaborationDAO.getColaborationById(124);
        
        Collaboration collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion");
        collaborationExpected.setEndDate("2024-12-01");
        collaborationExpected.setIdColaboration(124);
        
        assertEquals(124, currentResultCollaboration.getIdColaboration());
    }
    
    @Test
    public void testUpdateEndDateByIdCollaboration() {
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        
        int currentResult = collaborationDAO.updateEndDateByIdCollaboration(124, "2025-01-01");
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetAllCollaborations() {
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> collaborationsResult = collaborationDAO.getAllCollaborations();
        
        ArrayList<Collaboration> collaborationsExpected = new ArrayList();
        
        Collaboration collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion");
        collaborationExpected.setEndDate("2024-04-12");
        collaborationExpected.setIdColaboration(124);
        collaborationsExpected.add(collaborationExpected);
        
        collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion2");
        collaborationExpected.setEndDate("2024-04-12");
        collaborationExpected.setIdColaboration(133);
        collaborationsExpected.add(collaborationExpected);
        
        collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion3");
        collaborationExpected.setEndDate("2024-04-12");
        collaborationExpected.setIdColaboration(154);
        collaborationsExpected.add(collaborationExpected);
        
        collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion4");
        collaborationExpected.setEndDate("2024-04-12");
        collaborationExpected.setIdColaboration(184);
        collaborationsExpected.add(collaborationExpected);
        
        assertEquals(collaborationsExpected, collaborationsResult);
    }
    
    @Test
    public void testGetActiveCollaborations() {
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> collaborationsResult = collaborationDAO.getAllCollaborations();
        
        ArrayList<Collaboration> collaborationsExpected = new ArrayList();
        
        Collaboration collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion2");
        collaborationExpected.setEndDate("2024-04-12");
        collaborationExpected.setIdColaboration(133);
        collaborationsExpected.add(collaborationExpected);
        
        collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion3");
        collaborationExpected.setEndDate("2024-04-12");
        collaborationExpected.setIdColaboration(154);
        collaborationsExpected.add(collaborationExpected);
        
        collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion4");
        collaborationExpected.setEndDate("2024-04-12");
        collaborationExpected.setIdColaboration(184);
        collaborationsExpected.add(collaborationExpected);
        
        assertEquals(collaborationsExpected, collaborationsResult);
    }
}
