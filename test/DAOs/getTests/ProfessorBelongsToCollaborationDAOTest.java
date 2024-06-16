package DAOs.getTests;

import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.LogicException;
import logic.domain.ProfessorBelongsToCollaboration;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProfessorBelongsToCollaborationDAOTest {
    
    
    @Test
    public void testGetProfessorCollaborationIdSuccess() {
        ProfessorBelongsToCollaboration professorBelongsToCollaboration = new ProfessorBelongsToCollaboration();
        professorBelongsToCollaboration.setIdColaboration(2);
        professorBelongsToCollaboration.setIdUser(3);
        professorBelongsToCollaboration.setIdUserMirrorClass(4);
        professorBelongsToCollaboration.setColaborationStatus("Iniciada");
        
        ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
        try {
            ProfessorBelongsToCollaboration professorBelongsCollaborationObtained = professorBelongsToCollaborationDAO.getProfessorPendingCollaboration(3);
            assertEquals(professorBelongsToCollaboration, professorBelongsCollaborationObtained);
        } catch (LogicException logicException) {
            fail("No se ha podido obtener el id de la colaboracion a la que pertenece el profesor");
        }
    }
}
