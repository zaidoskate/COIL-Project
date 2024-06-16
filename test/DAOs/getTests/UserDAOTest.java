
package DAOs.getTests;

import logic.DAOs.UserDAO;
import logic.LogicException;
import logic.domain.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserDAOTest {
    
    @Test
    public void testGetUserByIdSuccess() throws LogicException{
        UserDAO userDAO = new UserDAO();
        User expectedUser = new User();
        expectedUser.setIdUser(3);
        expectedUser.setName("Jesús");
        expectedUser.setLastName("Lorenzo");
        expectedUser.setEmail("jesustlapahernandez@gmail.com");
        
        User currentUser = userDAO.getUserById(3);
        assertEquals(expectedUser, currentUser);
    }
    
    @Test
    public void testGetTypeUserByIdSuccess() throws LogicException{
        UserDAO userDAO = new UserDAO();
        int id = 3;
        String typeUserExpected = "ProfesorUV";
        String typeUserCurrent = userDAO.getUserTypeById(id);
        assertEquals(typeUserExpected, typeUserCurrent);
    }
    
    @Test
    public void testCheckEmailRegisteredSuccess() throws LogicException {
        UserDAO userDAO = new UserDAO();
        try {
            boolean result = userDAO.checkEmailRegistered("jesustlapahernandez@gmail.com");
            assertTrue(result);
        } catch(LogicException logicException) {
            fail("Error al consultar si el correo ya existe");
        }
    }
}
