/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import org.junit.Test;
import static org.junit.Assert.*;
import logic.DAOs.StudentDAO;
import logic.domain.User;
import logic.domain.Student;
import logic.DAOs.UserDAO;
import logic.domain.University;
import logic.DAOs.UniversityDAO;

/**
 *
 * @author zaido
 */

public class StudentDAOTest {
    
    public StudentDAOTest(){
    }
    
    @Test
    public void testInsertStudentSuccess() {
        User user = new User();
        user.setIdUser(1);
        user.setName("Jesus");
        user.setLastName("Tlapa");
        user.setSurname("Hernandez");
        user.setLanguage("Spanish");
        user.setEmail("tlapa11@gmail.com");
        
        UserDAO userDAO = new UserDAO();
        int userId = userDAO.InsertUser(user);
        
        University university = new University();
        university.setUniversityId(1);
        university.setName("Universidad Veracruzana");
        university.setCountry("Mexico");
        university.setRepresentative("Jorge Octavio Ocharan Hernandez");
        
        UniversityDAO universityDAO = new UniversityDAO();
        int universityId = universityDAO.insertUniversity(university);
        
        
        Student student = new Student();
        student.setRegistrationNumber("S22013652");
        student.setRegion("Mexico");
        student.setIdUser(userId);
        student.setIdUniversity(universityId);

        StudentDAO studentDAO = new StudentDAO();
        int result = studentDAO.insertStudent(student);

        assertEquals(1, result);
    }

    @Test
    public void testGetStudentByIdUser() {
        User user = new User();
        user.setIdUser(2);
        user.setName("Marcio");
        user.setLastName("Chimal");
        user.setSurname("Gonzalez");
        user.setLanguage("Spanish");
        user.setEmail("marcio@gmail.com");

        UserDAO userDAO = new UserDAO();
        int userId = userDAO.InsertUser(user);

        Student student = new Student();
        student.setRegistrationNumber("S22013697");
        student.setRegion("Mexico");
        student.setIdUser(userId);
        student.setIdUniversity(1);

        StudentDAO studentDAO = new StudentDAO();
        studentDAO.insertStudent(student);

        Student studentResult = studentDAO.getStudentByIdUser(userId);

        assertNotNull(studentResult);
        assertEquals("S22013697", studentResult.getRegistrationNumber());
        assertEquals("Mexico", studentResult.getRegion());
    }
}

