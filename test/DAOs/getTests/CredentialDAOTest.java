package DAOs.getTests;

import logic.DAOs.CredentialDAO;
import logic.domain.Credential;
import logic.LogicException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CredentialDAOTest {
    @Test
    public void testGetIdUserByCredentialSuccess()  throws LogicException {
        CredentialDAO credentialDAO = new CredentialDAO();
        Credential credential = new Credential();
        credential.setUser("jtlapa");
        credential.setPassword("zamatl");
        
        int currentResult = credentialDAO.getIdUserByCredential(credential);
        int expectedResult = 3;
        
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
