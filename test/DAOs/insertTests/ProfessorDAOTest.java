package DAOs.insertTests;

import logic.DAOs.ProfessorDAO;
import logic.LogicException;
import logic.domain.Professor;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class ProfessorDAOTest {
    
    @Test
    public void testInsertProfessorSuccess(){
        Professor professor = new Professor();
        professor.setIdUser(8);

        ProfessorDAO professorDAO = new ProfessorDAO();
        try{
            int result = professorDAO.insertProfessor(professor);
            assertEquals(1, result);
        } catch(LogicException sqlException) {
            fail("Error al insertar al profesor" + sqlException.getMessage());
        }
    }
    
    @Test(expected = LogicException.class)
    public void testInsertProfessorFail() throws LogicException{
        Professor professor = new Professor();

        ProfessorDAO professorDAO = new ProfessorDAO();
        int result = professorDAO.insertProfessor(professor);
        
        assertEquals(-1, result);
    }
    
}
