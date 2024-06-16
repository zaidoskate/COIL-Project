package DAOs.insertTests;

import logic.DAOs.UvProfessorDAO;
import logic.LogicException;
import logic.domain.UvProfessor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class UvProfessorDAOTest {
    @Test
    public void testInsertUvProfessorSuccess() {
        UvProfessor uvProfessor = new UvProfessor();
        uvProfessor.setPersonalNumber("77138");
        uvProfessor.setIdUser(5);
        uvProfessor.setIdDepartment("FEIX");
        
        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        try {
            int result = uvProfessorDAO.insertUvProfessor(uvProfessor);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("Error al insertar el profesor UV: " + logicException.getMessage());
        }
        
        
    }
    
    @Test(expected = LogicException.class)
    public void testInsertUvProfessorFailed() throws LogicException {
        UvProfessor uvProfessor = new UvProfessor();
        uvProfessor.setIdUser(11);
        uvProfessor.setIdDepartment("FEIX");
        
        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        int result = uvProfessorDAO.insertUvProfessor(uvProfessor);
        assertEquals(-1, result);
    }
}
