package DAOs.insertTests;

import logic.DAOs.ExternalProfessorDAO;
import logic.LogicException;
import logic.domain.ExternalProfessor;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class ExternalProfessorDAOTest {
    
    @Test
    public void testInsertExternalProfessorSuccess() throws LogicException{
        ExternalProfessor externalProfessor = new ExternalProfessor();
        externalProfessor.setIdUser(3);
        externalProfessor.setIdUniversity(3);

        ExternalProfessorDAO externalProfessorDAO = new ExternalProfessorDAO();
        int result = externalProfessorDAO.insertExternalProfessor(externalProfessor);
        
        assertNotEquals(0, result);
    }
}
