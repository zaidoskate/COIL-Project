/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.SQLException;
import logic.LogicException;
import logic.domain.UvProfessor;
import logic.DAOs.UvProfessorDAO;
/**
 *
 * @author zaido
 */
public class UvProfessorDAOTest {
    
    public UvProfessorDAOTest() {
    }
    
    @Test
    public void testInsertUvProfessorSuccess() {
        UvProfessor uvProfessor = new UvProfessor();
        uvProfessor.setPersonalNumber("7770138");
        uvProfessor.setIdUser(3);
        uvProfessor.setIdDepartment("FEIX");
        
        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        try {
            int result = uvProfessorDAO.insertUvProfessor(uvProfessor);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("Error al insertar el profesor UV: " + logicException.getMessage());
        }
        
        
    }
    
    @Test(expected = LogicException.class)
    public void testInsertUvProfessorFailed() throws LogicException {
        UvProfessor uvProfessor = new UvProfessor();
        uvProfessor.setIdUser(3);
        uvProfessor.setIdDepartment("FEIX");
        
        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        int result = uvProfessorDAO.insertUvProfessor(uvProfessor);
        assertEquals(-1, result);
    }
    
    @Test
    public void testGetUvProfessorByIdUserSuccess() throws SQLException{
        UvProfessor uvProfessorExpected = new UvProfessor();
        uvProfessorExpected.setPersonalNumber("8889991");
        uvProfessorExpected.setIdUser(4);
        uvProfessorExpected.setIdDepartment("FEIX");

        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        try {
            UvProfessor uvProfessorResult = uvProfessorDAO.getUvProfessorByIdUser(4);
            assertEquals(uvProfessorExpected, uvProfessorResult);
        } catch (LogicException logicException) {
            fail("Error al obtener el profesor UV: " + logicException.getMessage());
        }
    }
    
    @Test
    public void testGetInexistentProfessor() throws LogicException{
        UvProfessor uvProfessorWrong = new UvProfessor();
        uvProfessorWrong.setPersonalNumber("10109090");
        uvProfessorWrong.setIdUser(4);
        uvProfessorWrong.setIdDepartment("FEIX");

        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        UvProfessor uvProfessorResult = uvProfessorDAO.getUvProfessorByIdUser(4);

        assertNotEquals(uvProfessorWrong, uvProfessorResult);
    }
}
