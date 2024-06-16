package DAOs.insertTests;

import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.LogicException;
import logic.domain.ProfessorBelongsToCollaboration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ProfessorBelongsToCollaborationDAOTest {
    @Test
    public void testAddProfessorBelongsToCollaborationSuccess()  throws LogicException {
        ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
        ProfessorBelongsToCollaboration professorBelongsToCollaboration = new ProfessorBelongsToCollaboration();
        professorBelongsToCollaboration.setIdColaboration(3);
        professorBelongsToCollaboration.setIdUser(6);
        professorBelongsToCollaboration.setIdUserMirrorClass(5);
        professorBelongsToCollaboration.setColaborationStatus("Concluida");
        
        int expectedResult = 1;
        int currentResult = professorBelongsToCollaborationDAO.addProfessorBelongsToCollaboration(professorBelongsToCollaboration);
        
        assertEquals(expectedResult, currentResult);
    }
    @Test
    public void testSetStatusToCollaboration() {
        int expectedResult = 1;
        ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
        try {
            int result = professorBelongsToCollaborationDAO.setStatusToCollaboration(1, "Iniciada");
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("No se ha podido cambiar el status de la colaboracion");
        }
    }
    
}
