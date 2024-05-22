package DAOs;

import java.util.ArrayList;
import logic.DAOs.CollaborationDAO;
import logic.LogicException;
import logic.domain.Collaboration;
import logic.domain.ProfessorBelongsToCollaboration;
import org.junit.Test;
import static org.junit.Assert.*;

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
        
        int unexpectedResult = 0;
        int currentResult = collaborationDAO.addColaboration(collaboration);
        
        assertNotEquals(unexpectedResult, currentResult);
    }
    
    @Test
    public void testStartCollaborationSuccess() {
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        try {
            int result = collaborationDAO.startCollaboration(1);
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
        
        int currentResult = collaborationDAO.updateEndDateByIdCollaboration(1);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetAllCollaborationsSuccess() throws LogicException{
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
    public void testGetActiveCollaborationsSuccess() throws LogicException{
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
    
    @Test
    public void testGetProfessorConcludedCollaborationsSuccess() {
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<ProfessorBelongsToCollaboration> professorBelongs = new ArrayList<>();
        
        ProfessorBelongsToCollaboration professorBelongsToCollaboration = new ProfessorBelongsToCollaboration();
        professorBelongsToCollaboration.setIdColaboration(1);
        
        ArrayList<Collaboration> collaborationsExpected = new ArrayList<>();
        Collaboration collaborationExpected = new Collaboration();
        
        collaborationExpected.setIdColaboration(1);
        collaborationExpected.setColaborationName("Colaboración Tlapa-Zaid");
        collaborationExpected.setEndDate("2024-05-17");
        collaborationExpected.setStartDate("2024-05-17");
        collaborationExpected.setLanguage("Español");
        collaborationExpected.setInterestTopic("Ciencias de la computación, JavaScript");
        
        try {
            ArrayList<Collaboration> collaborationsObtained = collaborationDAO.getProfessorConcludedCollaborations(professorBelongs);
            assertEquals(collaborationsExpected, collaborationsObtained);
        } catch(LogicException logicException) {
            fail("Error al obtener las colaboraciones concluidas del profesor");
        }
    }
}
