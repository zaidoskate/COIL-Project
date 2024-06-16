package DAOs.getTests;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.LogicException;
import logic.domain.UvProfessor;
import logic.DAOs.UvProfessorDAO;

public class UvProfessorDAOTest {
    @Test
    public void testGetUvProfessorByIdUserSuccess() {
        UvProfessor uvProfessorExpected = new UvProfessor();
        uvProfessorExpected.setPersonalNumber("10924");
        uvProfessorExpected.setIdUser(3);
        uvProfessorExpected.setIdDepartment("FEIX");

        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        try {
            UvProfessor uvProfessorResult = uvProfessorDAO.getUvProfessorByIdUser(3);
            assertEquals(uvProfessorExpected, uvProfessorResult);
        } catch (LogicException logicException) {
            fail("Error al obtener el profesor UV: " + logicException.getMessage());
        }
    }
    
    @Test
    public void testGetInexistentProfessor() throws LogicException{
        UvProfessor uvProfessorWrong = new UvProfessor();
        uvProfessorWrong.setPersonalNumber("11111");
        uvProfessorWrong.setIdUser(9);
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
            String departmentNameObtained = uvProfessorDAO.getDepartmentNameBelonging(3);
            assertEquals(departmentNameExpected, departmentNameObtained);
        } catch (LogicException logicException) {
            fail("Error al obtener el nombre del departamento");
        }
    }
    
    @Test
    public void testGetCollaborationCountByProfessorRegion() {
        int expectedResult = 2;
        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        try {
            int result = uvProfessorDAO.getCollaborationCountByProfessorRegion("Xalapa");
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("Error al obtener el count de profesores con colaboraciones basado en su region");
        }
    }
    
    @Test
    public void testGetCollaborationCountByProfessorAcademicArea() {
        int expectedResult = 1;
        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        try {
            int result = uvProfessorDAO.getCollaborationCountByProfessorAcademicArea(4);
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("Error al obtener el count de profesores con colaboraciones basado en su region");
        }
    }
}
