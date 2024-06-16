
package DAOs.insertTests;

import logic.DAOs.StartupDocumentationDAO;
import logic.LogicException;
import logic.domain.StartupDocumentation;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class StartupDocumentationDAOTest {
    @Test
    public void testAddStartupDocumentationSuccess() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(1);
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO();
        
        int currentResult = startupDocumentationDAO.addStartupDocumentation(startupDocumentation);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    @Test
    public void testUploadSyllabusSuccess() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(2);
        startupDocumentation.setSyllabusPath("../../Syllabus.pdf");
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.uploadSyllabus(startupDocumentation);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testUploadStudentsListSuccess() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(2);
        startupDocumentation.setStudentsListPath("../../StudentsList.pdf");
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.uploadStudentsList(startupDocumentation);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testUploadMirrorStudentsListSuccess() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(2);
        startupDocumentation.setMirrorClassStudentsListPath("../../MirrorStudentsList.pdf");
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.uploadMirrorStudentsList(startupDocumentation);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
}
