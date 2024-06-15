package DAOs.insertTests;

import logic.DAOs.FinalDocumentationDAO;
import logic.LogicException;
import logic.domain.FinalDocumentation;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class FinalDocumentationDAOTest {
    
    @Test
    public void testAddFinalDocumentationSuccess() {
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(3);
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        int expectedResult = 1;
        try {
            int result = finalDocumentationDAO.addFinalDocumentation(finalDocumentation);
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("Error al insertar la documentacion final");
        }
    }
    
    @Test
    public void testUploadProfessorFeedbackSuccess() {
        String filePath = "../../uploadfeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(4);
        finalDocumentation.setProfessorFeedback(filePath);
        try {
            int result = finalDocumentationDAO.uploadProfessorFeedback(finalDocumentation);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha cargado el feedback del profesor " + logicException.getMessage());
        }
    }
    
    @Test
    public void testUploadMirrorProfessorFeedbackSuccess() {
        String filePath = "../../MirrorProfessorFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(4);
        finalDocumentation.setMirrorProfessorFeedback(filePath);
        try {
            int result = finalDocumentationDAO.uploadMirrorProfessorFeedback(finalDocumentation);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha cargado el feedback del profesor espejo " + logicException.getMessage());
        }
    }
    
    @Test
    public void testUploadStudentsFeedbackSuccess() {
        String filePath = "../../StudentsFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(4);
        finalDocumentation.setStudentsFeedback(filePath);
        try{
            int result = finalDocumentationDAO.uploadStudentsFeedback(finalDocumentation);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha cargado el feedback de los estudiantes " +logicException.getMessage());
        }
    }
    
    @Test
    public void testUploadMirrorStudentsFeedbackSuccess() {
        String filePath = "../../MirrorStudentsFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(4);
        finalDocumentation.setMirrorStudentsFeedback(filePath);
        try{
            int result = finalDocumentationDAO.uploadMirrorStudentsFeedback(finalDocumentation);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha cargado el feedback de los estudiantes espejo " +logicException.getMessage());
        }
    }
    
    @After
    public void tearDown() {
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        try {
            int result = finalDocumentationDAO.deleteUploadedFile("feedbackProfesor", 4);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha podido eliminar el archivo de feedback del profesor: " + logicException.getMessage());
        }

        try {
            int result = finalDocumentationDAO.deleteUploadedFile("feedbackProfesorEspejo", 4);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha podido eliminar el archivo de feedback del profesor espejo: " + logicException.getMessage());
        }

        try {
            int result = finalDocumentationDAO.deleteUploadedFile("feedbackEstudiantado", 4);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha podido eliminar el archivo de feedback de los estudiantes: " + logicException.getMessage());
        }

        try {
            int result = finalDocumentationDAO.deleteUploadedFile("feedbackEstudiantadoEspejo", 4);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha podido eliminar el archivo de feedback de los estudiantes espejo: " + logicException.getMessage());
        }
    }
}
