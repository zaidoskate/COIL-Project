package DAOs.deleteTests;

import logic.LogicException;
import logic.DAOs.StartupDocumentationDAO;
import logic.domain.StartupDocumentation;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class StartupDocumentationDAOTest {
    @Before
    public void setUp() throws LogicException {
        StartupDocumentation startupDocumentation= new StartupDocumentation();
        startupDocumentation.setIdColaboration(2);
        startupDocumentation.setStudentsListPath("./../../StudentsList.pdf");
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO();
        int result = startupDocumentationDAO.uploadStudentsList(startupDocumentation);
        assertEquals(1, result);
    }
    
    @Test
    public void testDeleteUploadedFile() {
        int expectedResult = 1;
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO();
        try {
            int result = startupDocumentationDAO.deleteUploadedFile("listaEstudiantado", 2);
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("No se ha podido eliminar el archivo cargado");
        }
    }
    
}
