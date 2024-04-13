/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

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
        professor.setIdUser(5);

        ProfessorDAO professorDAO = new ProfessorDAO();
        int result = professorDAO.insertProfessor(professor);
        
        assertEquals(1, result);
    }
    
    @Test
    public void testInsertProfessorFail() {
        Professor professor = new Professor();

        ProfessorDAO professorDAO = new ProfessorDAO();
        int result = professorDAO.insertProfessor(professor);
        
        assertEquals(-1, result);
    }
}
