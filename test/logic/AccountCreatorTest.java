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
        uvAccountRequest.setIdRequest(1);
        uvAccountRequest.setIdDepartment("FEX");
        uvAccountRequest.setPersonalNumber("09652");
        uvAccountRequest.setEmail("jesustlapahernandez@gmail.com");
        uvAccountRequest.setName("Frida");
        uvAccountRequest.setLastName("Tlapa Hernandez");
        boolean result = AccountCreator.createUVAccount(uvAccountRequest);
        assertTrue(result);
    }
    
    @Test(expected=LogicException.class)
    public void testCreateUVAccountFailed() throws LogicException {
        UvAccountRequest uvAccountRequest = new UvAccountRequest();
        uvAccountRequest.setIdRequest(9);
        uvAccountRequest.setIdDepartment("FEIdddddddddddddddddddddddddddddddddddddddddddddX");
        uvAccountRequest.setPersonalNumber("0706ddddddddddddddddddddddddddddddddddddddddddddddddddddddddd1");
        uvAccountRequest.setEmail("chimalg2001@outlook.com");
        uvAccountRequest.setName("Marciodddddddddddddddddddddddddddddddddddddddddddddddddddddddaaaaaaaaaaaaaaaaaaaaaaaaa Miguel");
        uvAccountRequest.setLastName("González Chimal");
        boolean result = AccountCreator.createUVAccount(uvAccountRequest);
        assertFalse(result);
    }
    
    @Test
    public void testCreateExternalAccountSuccess() throws LogicException {
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setIdRequest(1);
        externalAccountRequest.setIdUniversity(1);
        externalAccountRequest.setName("Cristy");
        externalAccountRequest.setLastName("Rodríguez");
        externalAccountRequest.setEmail("cristyzarg@gmail.com");
        boolean result = AccountCreator.createExternalAccount(externalAccountRequest);
        assertTrue(result);
    }
    
    @Test
    public void testCreateExternalAccountFailed() throws LogicException {
        ExternalAccountRequest externalAccountRequest = new ExternalAccountRequest();
        externalAccountRequest.setIdRequest(10);
        externalAccountRequest.setIdUniversity(4);
        externalAccountRequest.setName("Jessddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddus");
        externalAccountRequest.setLastName("Tlasssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssapa");
        externalAccountRequest.setEmail("chuchitotlapa@hotmail.com");
        boolean result = AccountCreator.createExternalAccount(externalAccountRequest);
        assertFalse(result);
    }
}
