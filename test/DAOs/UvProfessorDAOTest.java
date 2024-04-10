/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.User;
import logic.DAOs.UserDAO;
import logic.domain.Professor;
import logic.DAOs.ProfessorDAO;
import logic.domain.UvProfessor;
import logic.DAOs.UvProfessorDAO;
/**
 *
 * @author zaido
 */
public class UvProfessorDAOTest {
    
    public UvProfessorDAOTest() {
    }
    
    @Test
    public void testInsertUvProfessorSuccess() {
        User user = new User();
        user.setIdUser(3);
        user.setName("Juan Carlos");
        user.setLastName("Perez Arriaga");
        user.setEmail("elrevo@gmail.com");
        
        UserDAO userDAO = new UserDAO();
        int idUser = userDAO.addUser(user);
        
        Professor professor = new Professor();
        professor.setAcademicArea("Informatica");
        professor.setIdUser(idUser);
        
        ProfessorDAO professorDAO = new ProfessorDAO();
        professorDAO.insertProfessor(professor);
        
        UvProfessor uvProfessor = new UvProfessor();
        uvProfessor.setAcademicArea("Informatica");
        uvProfessor.setPersonalNumber("7770138");
        uvProfessor.setRegion("Xalapa");
        uvProfessor.setIdUser(idUser);
        
        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        int result = uvProfessorDAO.insertUvProfessor(uvProfessor);
        
        assertEquals(1, result);
        
    }
    
    @Test
    public void testGetUvProfessorByIdUserSuccess() {
        UvProfessor uvProfessorExpected = new UvProfessor();
        uvProfessorExpected.setAcademicArea("Informatica");
        uvProfessorExpected.setPersonalNumber("8889991");
        uvProfessorExpected.setRegion("Xalapa");
        uvProfessorExpected.setIdUser(4);

        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        UvProfessor uvProfessorResult = uvProfessorDAO.getUvProfessorByIdUser(4);
        System.out.println("academicArea: " + uvProfessorResult.getAcademicArea() + "idUser:" + uvProfessorResult.getIdUser() + "Personal number : " + uvProfessorResult.getPersonalNumber());

        assertEquals(uvProfessorExpected, uvProfessorResult);
    }
}
