/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import logic.CredentialGenerator;

/**
 *
 * @author chuch
 */
public class CredentialGeneratorTest {
    
    @Test
    public void generateUserSuccess() throws LogicException {
        String name = "Ángel";
        String lastname = "Hernández hdez";
        String user = CredentialGenerator.generateUser(name, lastname);
        String expectedUser = "ahernandez1";
        assertEquals(expectedUser,user);
    }
}
