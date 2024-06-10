package DAOs;

import java.util.ArrayList;
import logic.LogicException;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.Professor;
import logic.DAOs.ProfessorDAO;

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
    
    @Test
    public void testGetUniversityFromAProfessorSuccess() {
        ProfessorDAO professorDAO = new ProfessorDAO();
            try {
                ArrayList<String> universityObtained = professorDAO.getUniversityFromAProfessor(3);
                ArrayList<String> expectedUniversity = new ArrayList<>();
                expectedUniversity.add("Universidad Veracruzana");
                expectedUniversity.add("Facultad de Estadistica e Informatica");
                assertEquals(expectedUniversity, universityObtained);
            } catch(LogicException logicException) {
                fail("Error al obtener la universidad del profesor: " + logicException.getMessage());
            }
    }
    
    @Test
    public void testGetUniversityFromANonProfessor() {
        ProfessorDAO professorDAO = new ProfessorDAO();
        try {
            ArrayList<String> universityObtained = professorDAO.getUniversityFromAProfessor(1);
            assertTrue(universityObtained.isEmpty());
        } catch(LogicException logicException) {
            fail("Error al obtener la universidad del profesor: " + logicException.getMessage());
        }
    }
}
