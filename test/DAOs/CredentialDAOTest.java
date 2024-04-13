package DAOs;

import org.junit.Test;
import static org.junit.Assert.*;
import logic.DAOs.CredentialDAO;
import logic.domain.Credential;

public class CredentialDAOTest {
    
    public CredentialDAOTest() {
    }
    
    @Test
    public void TestInsertCredentialSuccess(){
        CredentialDAO credentialDAO = new CredentialDAO();
        Credential credential = new Credential();
        credential.setIdUser(12);
        credential.setUser("Usuario12");
        credential.setPassword("pswd1234");
        
        int currentResult = credentialDAO.insertCredential(credential);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void TestgetIdUserByCredentialSuccess(){
        CredentialDAO credentialDAO = new CredentialDAO();
        Credential credential = new Credential();
        credential.setUser("Usuario1");
        credential.setPassword("pswd1234");
        
        int currentResult = credentialDAO.getIdUserByCredential(credential);
        int expectedResult = 13;
        assertEquals(expectedResult, currentResult);
    }
}
