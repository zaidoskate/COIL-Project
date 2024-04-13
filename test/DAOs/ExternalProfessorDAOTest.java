/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOs;

import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.ExternalProfessor;
import logic.DAOs.ExternalProfessorDAO;
import dataaccess.DatabaseConnection;


/**
 *
 * @author chima
 */
public class ExternalProfessorDAOTest {
    
    public ExternalProfessorDAOTest() {
    }

    @Test
    public void testInsertExternalProfessorSuccess() {
        ExternalProfessor externalProfessor = new ExternalProfessor();
        externalProfessor.setIdUser(54321);
        externalProfessor.setIdUniversity(67890);

        ExternalProfessorDAO externalProfessorDAO = new ExternalProfessorDAO();
        int result = externalProfessorDAO.insertExternalProfessor(externalProfessor);
        
        assertNotEquals(0, result);
    }
    
    @Test
    public void testGetExternalProfessorByIdUniversity() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ExternalProfessorDAO externalProfessorDAO = new ExternalProfessorDAO();
        ExternalProfessor expectedExternalProfessor = new ExternalProfessor();
        expectedExternalProfessor.setIdUniversity(67890);

        
        ExternalProfessor currentExternalProfessor = externalProfessorDAO.getExternalProfessorByIdUniversity(67890);
        assertEquals(expectedExternalProfessor, currentExternalProfessor);
    }
    
}
