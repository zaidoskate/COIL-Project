package DAOs.getTests;

import logic.LogicException;
import logic.DAOs.StartupDocumentationDAO;
import logic.domain.StartupDocumentation;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class StartupDocumentationDAOTest {
    @Before
    public void setUpSyllabus() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(2);
        startupDocumentation.setSyllabusPath("../../Syllabus.pdf");
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.uploadSyllabus(startupDocumentation);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    @Test
    public void testObtainSyllabusSuccess()  throws LogicException{
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(2);
        String outputPath = "../../Syllabus_descargado.pdf";
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.obtainSyllabus(startupDocumentation, outputPath);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Before
    public void setUpStudentsList() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(2);
        startupDocumentation.setStudentsListPath("../../StudentsList.pdf");
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.uploadStudentsList(startupDocumentation);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    @Test
    public void testObtainStudentsListSuccess() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(2);
        String outputPath = "../../StudentsList_descargado.pdf";
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.obtainStudentsList(startupDocumentation, outputPath);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Before
    public void setUpMirrorStudentsList() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(2);
        startupDocumentation.setMirrorClassStudentsListPath("../../MirrorStudentsList.pdf");
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.uploadMirrorStudentsList(startupDocumentation);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    @Test
    public void testObtainMirrorStudentsListSuccess() throws LogicException {
        StartupDocumentation startupDocumentation = new StartupDocumentation();
        startupDocumentation.setIdColaboration(2);
        String outputPath = "../../MirrorStudentsList_descargado.pdf";
        
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO(); 
        int currentResult = startupDocumentationDAO.obtainMirrorStudentsList(startupDocumentation, outputPath);
        int expectedResult = 1;
        
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testHasFileUploadedSucess() {
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO();
        try {
            boolean result = startupDocumentationDAO.hasFileUploaded("listaEstudiantado", 1);
            assertFalse(result);
        } catch(LogicException logicException) {
            fail("No se ha podido obtener si el tipo de archivo ya tiene un archivo cargado");
        }
    }
    
    @Test
    public void testIsCollaborationRegistratedSuccess() {
        StartupDocumentationDAO startupDocumentationDAO = new StartupDocumentationDAO();
        try {
            boolean result = startupDocumentationDAO.isCollaborationRegistrated(2);
            assertTrue(result);
        } catch(LogicException logicException) {
            fail("No se ha podido saber si la colaboracion ya esta registrada con su documentacion de inicio");
        }
    }    
}
