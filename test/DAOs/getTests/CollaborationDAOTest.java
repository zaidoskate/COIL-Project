package DAOs.getTests;

import java.util.ArrayList;
import logic.DAOs.CollaborationDAO;
import logic.LogicException;
import logic.domain.Collaboration;
import logic.domain.ProfessorBelongsToCollaboration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class CollaborationDAOTest {
    
    @Test
    public void testGetCollaborationByIdSuccess() throws LogicException{
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        Collaboration currentResultCollaboration = collaborationDAO.getColaborationById(3);
        Collaboration collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion Veracruzana");
        collaborationExpected.setStartDate("2023-08-10");
        collaborationExpected.setIdColaboration(3);
        
        assertEquals(collaborationExpected, currentResultCollaboration);
    }
    
    @Test
    public void testGetAllCollaborationsSuccess() throws LogicException{
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> collaborationsResult = collaborationDAO.getAllCollaborations();
        
        ArrayList<Collaboration> collaborationsExpected = new ArrayList();
        
        Collaboration collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion FEI-UNAM");
        collaborationExpected.setStartDate("2024-06-07");
        collaborationExpected.setIdColaboration(1);
        collaborationsExpected.add(collaborationExpected);
        
        collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion computacional");
        collaborationExpected.setStartDate("2024-01-01");
        collaborationExpected.setIdColaboration(2);
        collaborationsExpected.add(collaborationExpected);
        
        collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion Veracruzana");
        collaborationExpected.setStartDate("2023-08-10");
        collaborationExpected.setIdColaboration(3);
        collaborationsExpected.add(collaborationExpected);
        
        collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("La segunda guerra mundial");
        collaborationExpected.setStartDate("2023-02-10");
        collaborationExpected.setIdColaboration(4);
        collaborationsExpected.add(collaborationExpected);
        
        collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Conquista de Brasil");
        collaborationExpected.setStartDate("Null");
        collaborationExpected.setIdColaboration(5);
        collaborationsExpected.add(collaborationExpected);
        
        
        assertEquals(collaborationsExpected, collaborationsResult);
    }
    
    @Test
    public void testGetActiveCollaborationsSuccess() throws LogicException{
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<Collaboration> collaborationsResult = collaborationDAO.getActiveCollaborations();
        
        ArrayList<Collaboration> collaborationsExpected = new ArrayList();
        
        Collaboration collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion FEI-UNAM");
        collaborationExpected.setStartDate("2024-06-07");
        collaborationExpected.setIdColaboration(1);
        collaborationsExpected.add(collaborationExpected);
        
        collaborationExpected = new Collaboration();
        collaborationExpected.setColaborationName("Colaboracion computacional");
        collaborationExpected.setStartDate("2024-01-01");
        collaborationExpected.setIdColaboration(2);
        collaborationsExpected.add(collaborationExpected);
        
        assertEquals(collaborationsExpected, collaborationsResult);
    }
    
    @Test
    public void testGetProfessorConcludedCollaborationsSuccess() {
        CollaborationDAO collaborationDAO = new CollaborationDAO();
        ArrayList<ProfessorBelongsToCollaboration> professorBelongs = new ArrayList<>();
        
        ProfessorBelongsToCollaboration professorBelongsToCollaboration = new ProfessorBelongsToCollaboration();
        professorBelongsToCollaboration.setIdColaboration(4);
        professorBelongs.add(professorBelongsToCollaboration);
        
        ArrayList<Collaboration> collaborationsExpected = new ArrayList<>();
        Collaboration collaborationExpected = new Collaboration();
        
        collaborationExpected.setIdColaboration(4);
        collaborationExpected.setColaborationName("La segunda guerra mundial");
        collaborationExpected.setEndDate("2023-15-07");
        collaborationExpected.setStartDate("2023-02-10");
        collaborationExpected.setLanguage("Alemán");
        collaborationExpected.setInterestTopic("Historia universal");
        collaborationsExpected.add(collaborationExpected);
        
        try {
            ArrayList<Collaboration> collaborationsObtained = collaborationDAO.getProfessorConcludedCollaborations(professorBelongs);
            assertEquals(collaborationsExpected, collaborationsObtained);
        } catch(LogicException logicException) {
            fail("Error al obtener las colaboraciones concluidas del profesor");
        }
    }
}
