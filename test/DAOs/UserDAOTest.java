
package DAOs;

import logic.DAOs.UserDAO;
import logic.domain.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDAOTest {
    
    public UserDAOTest() {
    }
    
    @Test
    public void testAddUserSuccess() {
        UserDAO userDAO = new UserDAO();
        User user = new User();
        user.setIdUser(113);
        user.setName("Jesus");
        user.setLastName("Tlapa");
        user.setEmail("tlapa@gmail.com");
        
        int currentResult = userDAO.addUser(user);
        int expectedResult = 113;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetUserByIdSuccess() {
        UserDAO userDAO = new UserDAO();
        User expectedUser = new User();
        expectedUser.setIdUser(11);
        expectedUser.setName("Zaid");
        expectedUser.setLastName("Vazquez Ramirez");
        expectedUser.setEmail("zaid@gmail.com");
        
        User currentUser = userDAO.getUserById(11);
        assertEquals(expectedUser, currentUser);
    }
    
}
