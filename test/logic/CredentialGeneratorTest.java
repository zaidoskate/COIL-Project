package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class CredentialGeneratorTest {
    
    @Test
    public void testGenerateUserSuccess() throws LogicException {
        String name = "Ángel";
        String lastname = "Hernández hdez";
        String currentUser = CredentialGenerator.generateUser(name, lastname);
        String expectedUser = "ahernandez";
        assertEquals(expectedUser,currentUser);
    }
    @Test
    public void testGenerateUserFailed() throws LogicException {
        String name = "José";
        String lastname = "Tlapa Huerta";
        String currentUser = CredentialGenerator.generateUser(name, lastname);
        String expectedUser = "jtlapa";
        assertNotEquals(expectedUser,currentUser);
    }
    
    @Test
    public void testGeneratePasswordSuccess() {
        String passwordGenerated = CredentialGenerator.generatePassword();
        int currentLength = passwordGenerated.length();
        int expectedLength = 8;
        
        assertEquals(expectedLength, currentLength);
    }
}
