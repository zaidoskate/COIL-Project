package DAOs;

import logic.LogicException;
import logic.domain.StartupDocumentation;
import logic.DAOs.StartupDocumentationDAO;
import org.junit.Test;
import static org.junit.Assert.*;

public class StartupDocumentationDAOTest {
    public StartupDocumentationDAOTest() {
        
    }
    
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
        startupDocumentation.setIdColaboration(1);
        startupDocumentation.setSyllabusPath("C:\\Users\\chuch\\Downloads\\Practica2.pdf");
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.uploadSyllabus(startupDocumentation);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testUploadStudentsListSuccess() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(133);
        startupDocumentation.setStudentsListPath("C:\\Users\\chuch\\Downloads\\Practica2.pdf");
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.uploadStudentsList(startupDocumentation);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testUploadMirrorStudentsListSuccess() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(1);
        startupDocumentation.setMirrorClassStudentsListPath("C:\\Users\\chuch\\Downloads\\Practica2.pdf");
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.uploadMirrorStudentsList(startupDocumentation);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testObtainSyllabusSuccess()  throws LogicException{
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(1);
        String outputPath = "C:\\Users\\chuch\\OneDrive\\Desktop\\PruebaJava1.pdf";
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.obtainSyllabus(startupDocumentation, outputPath);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testObtainStudentsListSuccess() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(1);
        String outputPath = "C:\\Users\\chuch\\OneDrive\\Desktop\\PruebaJava2.pdf";
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.obtainStudentsList(startupDocumentation, outputPath);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testObtainMirrorStudentsListSuccess() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(1);
        String outputPath = "C:\\Users\\chuch\\OneDrive\\Desktop\\PruebaJava3.pdf";
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.obtainMirrorStudentsList(startupDocumentation, outputPath);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testHasFileUploadedSucess() throws LogicException {
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO();
        try {
            boolean result = startupDocumentationDAO.hasFileUploaded("listaEstudiantado", 11);
            assertFalse(result);
        } catch(LogicException logicException) {
            fail("No se ha podido obtener si el tipo de archivo ya tiene un archivo cargado");
        }
    }
    
    @Test
    public void testDeleteUploadedFile() {
        int expectedResult = 1;
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO();
        try {
            int result = startupDocumentationDAO.deleteUploadedFile("listaEstudiantado", 11);
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("No se ha podido eliminar el archivo cargado");
        }
    }
    
    @Test
    public void testIsCollaborationRegistratedSuccess() {
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO();
        try {
            boolean result = startupDocumentationDAO.isCollaborationRegistrated(11);
            assertTrue(result);
        } catch(LogicException logicException) {
            fail("No se ha podido saber si la colaboracion ya esta registrada con su documentacion de inicio");
        }
    }
}
