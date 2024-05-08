package DAOs;

import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.ExternalProfessor;
import logic.DAOs.ExternalProfessorDAO;
import dataaccess.DatabaseConnection;
import logic.LogicException;

public class ExternalProfessorDAOTest {
    
    public ExternalProfessorDAOTest() {
    }

    @Test
    public void testInsertExternalProfessorSuccess() throws LogicException{
        ExternalProfessor externalProfessor = new ExternalProfessor();
        externalProfessor.setIdUser(54321);
        externalProfessor.setIdUniversity(67890);

        ExternalProfessorDAO externalProfessorDAO = new ExternalProfessorDAO();
        int result = externalProfessorDAO.insertExternalProfessor(externalProfessor);
        
        assertNotEquals(0, result);
    }
    
    @Test
    public void testGetExternalProfessorByIdUniversity() throws LogicException{
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ExternalProfessorDAO externalProfessorDAO = new ExternalProfessorDAO();
        ExternalProfessor expectedExternalProfessor = new ExternalProfessor();
        expectedExternalProfessor.setIdUniversity(67890);

        
        ExternalProfessor currentExternalProfessor = externalProfessorDAO.getExternalProfessorByIdUniversity(67890);
        assertEquals(expectedExternalProfessor, currentExternalProfessor);
    }
    
}
