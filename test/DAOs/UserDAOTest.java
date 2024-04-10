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
    public void testAddUserSuccess() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UserDAO userDAO = new UserDAO(databaseConnection);
        User user = new User();
        user.setIdUser(11);
        user.setName("Zaid");
        user.setLastName("Vazquez Ramirez");
        user.setEmail("zaid@gmail.com");
        
        int currentResult = userDAO.addUser(user);
        int expectedResult = 11;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetUserByIdSuccess() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UserDAO userDAO = new UserDAO(databaseConnection);
        User expectedUser = new User();
        expectedUser.setIdUser(12345);
        expectedUser.setName("Zaid");
        expectedUser.setLastName("Vazquez Ramirez");
        expectedUser.setEmail("zaid@gmail.com");
        
        User currentUser = userDAO.getUserById(12345);
        assertEquals(expectedUser, currentUser);
    }
    
    @Test
    public void testGetUserByNameSuccess() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UserDAO userDAO = new UserDAO(databaseConnection);
        User expectedUser = new User();
        expectedUser.setIdUser(12345);
        expectedUser.setName("Zaid");
        expectedUser.setLastName("Vazquez Ramirez");
        expectedUser.setEmail("zaid@gmail.com");
        
        User currentUser = userDAO.getUserByName("Zaid");
        assertEquals(expectedUser, currentUser);
    }
}
