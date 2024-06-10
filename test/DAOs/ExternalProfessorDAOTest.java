package DAOs;

import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.ExternalProfessor;
import logic.DAOs.ExternalProfessorDAO;
import logic.LogicException;

public class ExternalProfessorDAOTest {
    
    public ExternalProfessorDAOTest() {
    }

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
