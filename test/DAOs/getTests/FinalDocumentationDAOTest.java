package DAOs.getTests;

import logic.DAOs.FinalDocumentationDAO;
import logic.LogicException;
import logic.domain.FinalDocumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class FinalDocumentationDAOTest {

    @Before
    public void setUp() {
        try {
            String filePath = "../../uploadfeedback.pdf";
            FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
            FinalDocumentation finalDocumentation = new FinalDocumentation();
            finalDocumentation.setIdColaboration(4);
            finalDocumentation.setProfessorFeedback(filePath);
            int result = finalDocumentationDAO.uploadProfessorFeedback(finalDocumentation);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha cargado el feedback del profesor " + logicException.getMessage());
        }

        try {
            String filePath = "../../MirrorProfessorFeedback.pdf";
            FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
            FinalDocumentation finalDocumentation = new FinalDocumentation();
            finalDocumentation.setIdColaboration(4);
            finalDocumentation.setMirrorProfessorFeedback(filePath);
            int result = finalDocumentationDAO.uploadMirrorProfessorFeedback(finalDocumentation);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha cargado el feedback del profesor espejo " + logicException.getMessage());
        }

        try {
            String filePath = "../../StudentsFeedback.pdf";
            FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
            FinalDocumentation finalDocumentation = new FinalDocumentation();
            finalDocumentation.setIdColaboration(4);
            finalDocumentation.setStudentsFeedback(filePath);
            int result = finalDocumentationDAO.uploadStudentsFeedback(finalDocumentation);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha cargado el feedback de los estudiantes " + logicException.getMessage());
        }

        try {
            String filePath = "../../MirrorStudentsFeedback.pdf";
            FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
            FinalDocumentation finalDocumentation = new FinalDocumentation();
            finalDocumentation.setIdColaboration(4);
            finalDocumentation.setMirrorStudentsFeedback(filePath);
            int result = finalDocumentationDAO.uploadMirrorStudentsFeedback(finalDocumentation);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha cargado el feedback de los estudiantes espejo " + logicException.getMessage());
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

    @Test
    public void testObtainProfessorFeedbackSuccess() {
        String outputPath = "../../FeedbackProfesor_descargado.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(4);
        try {
            int result = finalDocumentationDAO.obtainProfessorFeedback(finalDocumentation, outputPath);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha descargado el feedback del profesor " + logicException.getMessage());
        }
    }

    @Test
    public void testObtainMirrorProfessorFeedbackSuccess() {
        String outputPath = "../../MirrorProfessorFeedback_descargado.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(4);
        try {
            int result = finalDocumentationDAO.obtainMirrorProfessorFeedback(finalDocumentation, outputPath);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha descargado feedback del profesor espejo " + logicException.getMessage());
        }
    }

    @Test
    public void testObtainStudentsFeedbackSuccess() {
        String outputPath = "../../StudentsFeedback_descargado.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(4);
        try {
            int result = finalDocumentationDAO.obtainStudentsFeedback(finalDocumentation, outputPath);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha descargado el feedback de los estudiantes " + logicException.getMessage());
        }
    }

    @Test
    public void testObtainMirrorStudentsFeedbackSuccess() {
        String outputPath = "../../MirrorStudentsFeedback_descargado.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(4);
        try {
            int result = finalDocumentationDAO.obtainMirrorStudentsFeedback(finalDocumentation, outputPath);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha descargado el feedback de los estudiantes espejo " + logicException.getMessage());
        }
    }

    @Test
    public void testIsCollaborationRegistratedSuccess() {
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        try {
            boolean result = finalDocumentationDAO.isCollaborationRegistrated(4);
            assertTrue(result);
        } catch (LogicException logicException) {
            fail("No se ha podido saber si la colaboracion ya está registrada con su documentacion final");
        }
    }

    @Test
    public void testHasFileUploadedSuccess() {
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        try {
            boolean result = finalDocumentationDAO.hasFileUploaded("feedbackProfesor", 4);
            assertTrue(result);
        } catch (LogicException logicException) {
            fail("No se ha podido obtener si el tipo de archivo ya tiene un archivo cargado");
        }
    }
}
