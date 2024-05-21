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
    
    @Test
    public void testGetProfessorCollaborationIdSuccess() {
        ProfessorBelongsToCollaboration professorBelongsToCollaboration = new ProfessorBelongsToCollaboration();
        professorBelongsToCollaboration.setIdColaboration(11);
        professorBelongsToCollaboration.setIdUser(2);
        professorBelongsToCollaboration.setIdUserMirrorClass(1);
        professorBelongsToCollaboration.setColaborationStatus("Pendiente");
        
        ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
        try {
            ProfessorBelongsToCollaboration professorBelongsCollaborationObtained = professorBelongsToCollaborationDAO.getProfessorPendingCollaboration(2);
            assertEquals(professorBelongsToCollaboration, professorBelongsCollaborationObtained);
        } catch (LogicException logicException) {
            fail("No se ha podido obtener el id de la colaboracion a la que pertenece el profesor");
        }
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
