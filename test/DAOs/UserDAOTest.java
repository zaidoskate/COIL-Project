
package DAOs;

import logic.DAOs.UserDAO;
import logic.LogicException;
import logic.domain.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDAOTest {
    
    @Test
    public void testAddUserSuccess() throws LogicException{
        UserDAO userDAO = new UserDAO();
        User user = new User();
        user.setName("Miguel");
        user.setLastName("Soto");
        user.setEmail("msoto@hotmail.com");
        
        int currentResult = userDAO.addUser(user);
        int expectedResult = 7;
        assertEquals(expectedResult, currentResult);
    }
    @Test(expected = LogicException.class)
    public void testAddUserFailed() throws LogicException{
        UserDAO userDAO = new UserDAO();
        User user = new User();
        user.setName("Lorenzofgjhdsgjdgjdgfjhfgjdgjhdfghddsfhgdhdfghkhnnjjbkkjbkjdghsdsfghsfhdfhdfgh");
        user.setLastName("Hdezdsfhdghdgfhsazdfhdgshdsfghdfgdsfhdsfghdsghdsfghhfgjdgjhfbbhjdfgjhdszgfhdszh");
        user.setEmail("kjhvvvvvvvvvvvvvvvvvvvvvvvxggggggggggggggggggggggggggggggggllllllllll");
        
        int currentResult = userDAO.addUser(user);
        int expectedResult = -1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetUserByIdSuccess() throws LogicException{
        UserDAO userDAO = new UserDAO();
        User expectedUser = new User();
        expectedUser.setIdUser(1);
        expectedUser.setName("Jesus");
        expectedUser.setLastName("Tlapa");
        expectedUser.setEmail("tlapa@gmail.com");
        
        User currentUser = userDAO.getUserById(1);
        assertEquals(expectedUser, currentUser);
    }
    
    @Test
    public void testGetTypeUserByIdSuccess() throws LogicException{
        UserDAO userDAO = new UserDAO();
        int id = 2;
        String typeUserExpected = "ProfesorUV";
        String typeUserCurrent = userDAO.getUserTypeById(id);
        assertEquals(typeUserExpected, typeUserCurrent);
    }
}
