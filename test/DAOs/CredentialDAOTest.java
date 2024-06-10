package DAOs;

import org.junit.Test;
import static org.junit.Assert.*;
import logic.DAOs.CredentialDAO;
import logic.LogicException;
import logic.domain.Credential;

public class CredentialDAOTest {
    
    public CredentialDAOTest() {
    }
    
    @Test
    public void testInsertCredentialSuccess()  throws LogicException {
        CredentialDAO credentialDAO = new CredentialDAO();
        Credential credential = new Credential();
        credential.setIdUser(7);
        credential.setUser("guille");
        credential.setPassword("zamatl");
        
        int currentResult = credentialDAO.insertCredential(credential);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetIdUserByCredentialSuccess()  throws LogicException {
        CredentialDAO credentialDAO = new CredentialDAO();
        Credential credential = new Credential();
        credential.setUser("jtlapa");
        credential.setPassword("zamatl");
        
        int currentResult = credentialDAO.getIdUserByCredential(credential);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testCountCredentialsByUserSuccess() throws LogicException {
        
        CredentialDAO credentialDAO = new CredentialDAO();
        
        int currentResult = credentialDAO.countCredentialsByUser("jtlapa");
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
}
