/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.Professor;
import logic.DAOs.ProfessorDAO;
import logic.DAOs.UserDAO;
import logic.domain.User;
/**
 *
 * @author zaido
 */
public class ProfessorDAOTest {
    
    @Test
    public void testInsertProfessorSuccess(){
        User user = new User();
        user.setIdUser(5);
        user.setName("James");
        user.setLastName("Smith");
        user.setSurname("Johnson");
        user.setLanguage("English");
        user.setEmail("james@gmail.com");

        UserDAO userDAO = new UserDAO();
        int idUser = userDAO.InsertUser(user);

        Professor professor = new Professor();
        professor.setAcademicArea("Informatica");
        professor.setIdUser(idUser);

        ProfessorDAO professorDAO = new ProfessorDAO();
        int result = professorDAO.insertProfessor(professor);
        
        assertNotEquals(0, result);
    }
    
    /*@Test
    public void testGetProfessorByAcademicArea() {
        ArrayList<Professor> arrayExpected = new ArrayList<>();
        
    }*/
}
