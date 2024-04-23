package DAOs;

import java.security.NoSuchAlgorithmException;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.DAOs.CredentialDAO;
import logic.LogicException;
import logic.domain.Credential;

public class CredentialDAOTest {
    
    public CredentialDAOTest() {
    }
    
    @Test
    public void TestInsertCredentialSuccess()  throws LogicException, NoSuchAlgorithmException{
        CredentialDAO credentialDAO = new CredentialDAO();
        Credential credential = new Credential();
        credential.setIdUser(1);
        credential.setUser("Usuario1");
        credential.setPassword("password123");
        
        int currentResult = credentialDAO.insertCredential(credential);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void TestgetIdUserByCredentialSuccess()  throws LogicException, NoSuchAlgorithmException {
        CredentialDAO credentialDAO = new CredentialDAO();
        Credential credential = new Credential();
        credential.setUser("Usuario13");
        credential.setPassword("password123");
        
        int currentResult = credentialDAO.getIdUserByCredential(credential);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
}
