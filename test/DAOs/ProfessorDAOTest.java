/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

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
        user.setEmail("james@gmail.com");

        UserDAO userDAO = new UserDAO();
        int idUser = userDAO.addUser(user);

        Professor professor = new Professor();
        professor.setAcademicArea("Informatica");
        professor.setIdUser(idUser);

        ProfessorDAO professorDAO = new ProfessorDAO();
        int result = professorDAO.insertProfessor(professor);
        
        assertEquals(1, result);
    }
}
