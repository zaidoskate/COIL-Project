/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOs;

import dataaccess.DatabaseConnection;
import logic.DAOs.UserDAO;
import logic.domain.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chuch
 */
public class UserDAOTest {
    
    public UserDAOTest() {
    }
    
    @Test
    public void testInsertUserSuccess() {
        UserDAO userDAO = new UserDAO();
        User user = new User();
        user.setIdUser(12345);
        user.setName("Zaid");
        user.setLastName("Vazquez");
        user.setSurname("Ramirez");
        user.setLanguage("English");
        user.setEmail("zaid@gmail.com");
        
        int currentResult = userDAO.InsertUser(user);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetUserByIdSuccess() {
        UserDAO userDAO = new UserDAO();
        User expectedUser = new User();
        expectedUser.setIdUser(12345);
        expectedUser.setName("Zaid");
        expectedUser.setLastName("Vazquez");
        expectedUser.setSurname("Ramirez");
        expectedUser.setLanguage("English");
        expectedUser.setEmail("zaid@gmail.com");
        
        User currentUser = userDAO.getUserById(12345);
        assertEquals(expectedUser, currentUser);
    }
    
    @Test
    public void testGetUserByNameSuccess() {
        UserDAO userDAO = new UserDAO();
        User expectedUser = new User();
        expectedUser.setIdUser(12345);
        expectedUser.setName("Zaid");
        expectedUser.setLastName("Vazquez");
        expectedUser.setSurname("Ramirez");
        expectedUser.setLanguage("English");
        expectedUser.setEmail("zaid@gmail.com");
        
        User currentUser = userDAO.getUserByName("Zaid");
        assertEquals(expectedUser, currentUser);
    }
}
