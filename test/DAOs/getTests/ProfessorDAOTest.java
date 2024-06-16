package DAOs.getTests;

import java.util.ArrayList;
import logic.LogicException;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.DAOs.ProfessorDAO;

public class ProfessorDAOTest {
    
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
