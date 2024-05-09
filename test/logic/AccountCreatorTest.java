package logic;

import org.junit.Test;
import logic.domain.UvAccountRequest;
import logic.domain.ExternalAccountRequest;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AccountCreatorTest {
    
    @Test
    public void testCreateUVAccountSuccess() throws LogicException {
        UvAccountRequest uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setIdRequest(9);
        uvAccountRequest.setIdDepartment("FEIX");
        uvAccountRequest.setPersonalNumber("07061");
        uvAccountRequest.setEmail("chimalg2001@outlook.com");
        uvAccountRequest.setName("Marcio Miguel");
        uvAccountRequest.setLastName("González Chimal");
        boolean result = AccountCreator.createUVAccount(uvAccountRequest);
        assertTrue(result);
    }
    
    @Test
    public void testCreateUVAccountFailed() throws LogicException {
        UvAccountRequest uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setIdRequest(9);
        uvAccountRequest.setIdDepartment("FEIX");
        uvAccountRequest.setPersonalNumber("07061");
        uvAccountRequest.setEmail("chimalg2001@outlook.com");
        uvAccountRequest.setName("Marcio Miguel");
        uvAccountRequest.setLastName("González Chimal");
        boolean result = AccountCreator.createUVAccount(uvAccountRequest);
        assertFalse(result);
    }
    
    @Test
    public void testCreateExternalAccountSuccess() throws LogicException {
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setIdRequest(6);
        externalAccountRequest.setIdUniversity(4);
        externalAccountRequest.setName("Jesus");
        externalAccountRequest.setLastName("Tlapa");
        externalAccountRequest.setEmail("chuchitotlapa@hotmail.com");
        boolean result = AccountCreator.createExternalAccount(externalAccountRequest);
        assertTrue(result);
    }
    
    @Test
    public void testCreateExternalAccountFailed() throws LogicException {
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setIdRequest(10);
        externalAccountRequest.setIdUniversity(4);
        externalAccountRequest.setName("Jesus");
        externalAccountRequest.setLastName("Tlapa");
        externalAccountRequest.setEmail("chuchitotlapa@hotmail.com");
        boolean result = AccountCreator.createExternalAccount(externalAccountRequest);
        assertFalse(result);
    }
}
