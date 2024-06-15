package DAOs.getTests;

import java.util.ArrayList;
import logic.DAOs.DepartmentDAO;
import logic.LogicException;
import logic.domain.Department;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

public class DepartmentDAOTest {
    
    @Test
    public void testGetRegionNamesSuccess() throws LogicException {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        ArrayList<String> currentResult = departmentDAO.getRegionsNames();
        
        ArrayList<String> expectedResult = new ArrayList();
        expectedResult.add("Orizaba");
        expectedResult.add("Tuxpan");
        expectedResult.add("Xalapa");
        expectedResult.add("Veracruz");
        expectedResult.add("Coatzacoalcos");
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetRegionNamesFailed() throws LogicException {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        ArrayList<String> currentResult = departmentDAO.getRegionsNames();
        
        ArrayList<String> expectedResult = new ArrayList();
        expectedResult.add("Tuxpan");
        expectedResult.add("Coatzacoalcos");
        expectedResult.add("Xalapa");
        expectedResult.add("Orizaba");
        expectedResult.add("Veracruz");
        assertNotEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetDepartmentsByRegionSuccess() throws LogicException {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        ArrayList<Department> currentResult = departmentDAO.getDepartmentsByRegion("Coatzacoalcos");
        
        ArrayList<Department> expectedResult = new ArrayList();
        Department department;
        department = new Department();
        department.setIdDepartment("FBAC");
        department.setName("Facultad de Ingenieria en Sistemas de Produccion Agropecuaria");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        department = new Department();
        department.setIdDepartment("FCAC");
        department.setName("Facultad de Contaduria y Administracion");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        department = new Department();
        department.setIdDepartment("FCQC");
        department.setName("Facultad de Ciencias Quimicas");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        department = new Department();
        department.setIdDepartment("FEC");
        department.setName("Facultad de Enfermeria");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        department = new Department();
        department.setIdDepartment("FIC");
        department.setName("Facultad de Ingenieria");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        department = new Department();
        department.setIdDepartment("FMC");
        department.setName("Facultad de Medicina");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        department = new Department();
        department.setIdDepartment("FOC");
        department.setName("Facultad de Odontologia");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        department = new Department();
        department.setIdDepartment("FTFC");
        department.setName("Facultad de Trabajo Social");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        
        assertEquals(expectedResult, currentResult);
    }
    @Test
    public void testGetDepartmentsByRegionFailed() throws LogicException {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        ArrayList<Department> currentResult = departmentDAO.getDepartmentsByRegion("Xalapa");
        
        ArrayList<Department> expectedResult = new ArrayList();
        Department department;
        department = new Department();
        department.setIdDepartment("FBAC");
        department.setName("Facultad de Ingenieria en Sistemas de Produccion Agropecuaria");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        department = new Department();
        department.setIdDepartment("FCAC");
        department.setName("Facultad de Contaduria y Administracion");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        department = new Department();
        department.setIdDepartment("FCQC");
        department.setName("Facultad de Ciencias Quimicas");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        department = new Department();
        department.setIdDepartment("FEC");
        department.setName("Facultad de Enfermeria");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        department = new Department();
        department.setIdDepartment("FIC");
        department.setName("Facultad de Ingenieria");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        department = new Department();
        department.setIdDepartment("FMC");
        department.setName("Facultad de Medicina");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        department = new Department();
        department.setIdDepartment("FOC");
        department.setName("Facultad de Odontologia");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        department = new Department();
        department.setIdDepartment("FTFC");
        department.setName("Facultad de Trabajo Social");
        department.setRegion("Coatzacoalcos");
        expectedResult.add(department);
        
        assertNotEquals(expectedResult, currentResult);
        
    }
}
