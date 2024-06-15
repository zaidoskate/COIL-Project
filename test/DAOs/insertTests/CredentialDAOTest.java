package DAOs.insertTests;

import logic.DAOs.CredentialDAO;
import logic.domain.Credential;
import logic.LogicException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CredentialDAOTest {
    
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
}
