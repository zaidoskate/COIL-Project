package DAOs;

import java.security.NoSuchAlgorithmException;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.DAOs.CredentialDAO;
import logic.LogicException;
import logic.PasswordEncrypter;
import logic.domain.Credential;

public class CredentialDAOTest {
    
    public CredentialDAOTest() {
    }
    
    @Test
    public void TestInsertCredentialSuccess()  throws LogicException, NoSuchAlgorithmException{
        CredentialDAO credentialDAO = new CredentialDAO();
        Credential credential = new Credential();
        credential.setIdUser(13);
        credential.setUser("Usuario13");
        credential.setPassword(PasswordEncrypter.hashAPassword("password123"));
        
        int currentResult = credentialDAO.insertCredential(credential);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void TestgetIdUserByCredentialSuccess()  throws LogicException, NoSuchAlgorithmException {
        CredentialDAO credentialDAO = new CredentialDAO();
        Credential expectedCredential = new Credential();
        expectedCredential.setIdUser(13);
        expectedCredential.setUser("Usuario13");
        expectedCredential.setPassword(PasswordEncrypter.hashAPassword("password123"));
        
        Credential currentResult = credentialDAO.getCredentialByUser("Usuario13");
        
        assertEquals(expectedCredential, currentResult);
    }
}
