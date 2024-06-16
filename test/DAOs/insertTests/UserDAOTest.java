package DAOs.insertTests;

import logic.DAOs.UserDAO;
import logic.LogicException;
import logic.domain.User;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UserDAOTest {
    @Test
    public void testAddUserSuccess() throws LogicException{
        UserDAO userDAO = new UserDAO();
        User user = new User();
        user.setName("Miguel");
        user.setLastName("Soto");
        user.setEmail("msoto@hotmail.com");
        
        int currentResult = userDAO.addUser(user);
        int expectedResult = 9;
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
}
