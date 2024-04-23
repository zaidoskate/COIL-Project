package DAOs;

import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.LogicException;
import logic.domain.ProfessorBelongsToCollaboration;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProfessorBelongsToCollaborationTest {
    public ProfessorBelongsToCollaborationTest() {
        
    }
    
    @Test
    public void testAddProfessorBelongsToCollaborationSuccess()  throws LogicException {
        ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
        ProfessorBelongsToCollaboration professorBelongsToCollaboration = new ProfessorBelongsToCollaboration();
        professorBelongsToCollaboration.setIdColaboration(1);
        professorBelongsToCollaboration.setIdUser(1);
        professorBelongsToCollaboration.setIdUserMirrorClass(2);
        professorBelongsToCollaboration.setColaborationStatus("Terminada");
        
        int expectedResult = 1;
        int currentResult = professorBelongsToCollaborationDAO.addProfessorBelongsToCollaboration(professorBelongsToCollaboration);
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testDeleteProfessorBelongsToCollaborationSuccess()  throws LogicException {
        ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
        
        int expectedResult = 1;
        int currentResult = professorBelongsToCollaborationDAO.deleteProfessorBelongsToCollaborationByIdCollaboration(133);
        
        assertEquals(expectedResult, currentResult);
    }
}
