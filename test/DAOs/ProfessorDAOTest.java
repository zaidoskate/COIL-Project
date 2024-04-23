/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import logic.LogicException;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.Professor;
import logic.DAOs.ProfessorDAO;
/**
 *
 * @author zaido
 */
public class ProfessorDAOTest {
    
    @Test
    public void testInsertProfessorSuccess(){
        Professor professor = new Professor();
        professor.setIdUser(2);

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
            String university = professorDAO.getUniversityFromAProfessor(1);
            assertEquals("Universidad Veracruzana", university);
        } catch(LogicException logicException) {
            fail("Error al obtener la universidad del profesor: " + logicException.getMessage());
        }
    }
    
    @Test
    public void testGetUniversityFromANonProfessor() {
        ProfessorDAO professorDAO = new ProfessorDAO();
        try {
            String university = professorDAO.getUniversityFromAProfessor(8);
            assertNull(university);
        } catch(LogicException logicException) {
            fail("Error al obtener la universidad del profesor: " + logicException.getMessage());
        }
    }
}
