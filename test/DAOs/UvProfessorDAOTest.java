package DAOs;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.LogicException;
import logic.domain.UvProfessor;
import logic.DAOs.UvProfessorDAO;

public class UvProfessorDAOTest {
    
    public UvProfessorDAOTest() {
    }
    
    @Test
    public void testInsertUvProfessorSuccess() {
        UvProfessor uvProfessor = new UvProfessor();
        uvProfessor.setPersonalNumber("7770138");
        uvProfessor.setIdUser(1);
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
    public void testGetUvProfessorByIdUserSuccess() {
        UvProfessor uvProfessorExpected = new UvProfessor();
        uvProfessorExpected.setPersonalNumber("7770138");
        uvProfessorExpected.setIdUser(1);
        uvProfessorExpected.setIdDepartment("FEIX");

        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        try {
            UvProfessor uvProfessorResult = uvProfessorDAO.getUvProfessorByIdUser(1);
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
    
    @Test
    public void testGetDepartmentNameBelonging() {
        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        String departmentNameExpected = "Facultad de Estadistica e Informatica";
        try {
            String departmentNameObtained = uvProfessorDAO.getDepartmentNameBelonging(1);
            assertEquals(departmentNameExpected, departmentNameObtained);
        } catch (LogicException logicException) {
            fail("Error al obtener el nombre del departamento");
        }
    }
}
