package DAOs;

import org.junit.Test;
import static org.junit.Assert.*;
import dataaccess.DatabaseConnection;
import logic.DAOs.CredentialDAO;
import logic.domain.Credential;

public class CredentialDAOTest {
    
    public CredentialDAOTest() {
    }
    
    @Test
    public void TestInsertCredentialSuccess(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        CredentialDAO credentialDAO = new CredentialDAO(databaseConnection);
        Credential credential = new Credential();
        credential.setIdUser(1234);
        credential.setUser("Usuario1");
        credential.setPassword("pswd1234");
        
        int currentResult = credentialDAO.insertCredential(credential);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void TestgetIdUserByCredentialSuccess(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        CredentialDAO credentialDAO = new CredentialDAO(databaseConnection);
        Credential credential = new Credential();
        credential.setUser("Usuario1");
        credential.setPassword("pswd1234");
        
        int currentResult = credentialDAO.getIdUserByCredential(credential);
        int expectedResult = 1234;
        assertEquals(expectedResult, currentResult);
    }
}
