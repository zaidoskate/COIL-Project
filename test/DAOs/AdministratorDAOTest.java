package DAOs;

import logic.DAOs.AdministratorDAO;
import logic.domain.Administrator;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AdministratorDAOTest {
    public AdministratorDAOTest() {
        
    }
    @Test
    public void testInsertAdministratorSuccess() {
        AdministratorDAO administratorDAO = new AdministratorDAO();
        Administrator admin = new Administrator();
        admin.setIdUser(1234);
        admin.setIdAdministrator(1111);
        
        int currentResult = administratorDAO.insertAdministrator(admin);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetAdministratorByIdUserSuccess() {
       
    }
    
    @Test
    public void testGetAdministratorByIdAdministratorSuccess() {
        
    }
}
