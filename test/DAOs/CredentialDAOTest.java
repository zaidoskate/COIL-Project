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
        credential.setIdUser(2);
        credential.setUser("usuario2");
        credential.setPassword("password123");
        
        int currentResult = credentialDAO.insertCredential(credential);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetIdUserByCredentialSuccess()  throws LogicException {
        CredentialDAO credentialDAO = new CredentialDAO();
        Credential credential = new Credential();
        credential.setUser("Usuario13");
        credential.setPassword("password123");
        
        int currentResult = credentialDAO.getIdUserByCredential(credential);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testCountCredentialsByUserSuccess() throws LogicException {
        
        CredentialDAO credentialDAO = new CredentialDAO();
        
        int currentResult = credentialDAO.countCredentialsByUser("Usuario1");
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
}
