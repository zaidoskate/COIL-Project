package DAOs.deleteTests;

import logic.DAOs.ProfessorBelongsToCollaborationDAO;
import logic.LogicException;
import org.junit.Test;
import static org.junit.Assert.*;

public class ProfessorBelongsToCollaborationDAOTest {
    
    @Test
    public void testDeleteProfessorBelongsToCollaborationSuccess()  throws LogicException {
        ProfessorBelongsToCollaborationDAO professorBelongsToCollaborationDAO = new ProfessorBelongsToCollaborationDAO();
        
        int expectedResult = 1;
        int currentResult = professorBelongsToCollaborationDAO.deleteProfessorBelongsToCollaborationByIdCollaboration(1);
        
        assertEquals(expectedResult, currentResult);
    }
    
}
