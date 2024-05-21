package DAOs;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.FinalDocumentation;
import logic.DAOs.FinalDocumentationDAO;
import logic.DAOs.StartupDocumentationDAO;
import logic.LogicException;

public class FinalDocumentationDAOTest {
    public FinalDocumentationDAOTest(){
    }
    
    @Test
    public void testAddFinalDocumentationSuccess() {
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
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
        String filePath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\DisenioEnConstruccion.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
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
        String filePath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\uploadFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
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
        String filePath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\uploadFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
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
        String filePath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\uploadFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
        finalDocumentation.setMirrorStudentsFeedback(filePath);
        try{
            int result = finalDocumentationDAO.uploadMirrorStudentsFeedback(finalDocumentation);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha cargado el feedback de los estudiantes espejo " +logicException.getMessage());
        }
    }
    
    @Test
    public void testObtainProfessorFeedbackSuccess() {
        String outputPath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\testPdf.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
        try{
            int result = finalDocumentationDAO.obtainProfessorFeedback(finalDocumentation, outputPath);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha descargado el feedback del profesor " + logicException.getMessage());
        }
    }
    
    @Test
    public void testObtainMirrorProfessorFeedbackSuccess() {
        String outputPath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\MirrorProfessorFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
        try {
            int result = finalDocumentationDAO.obtainMirrorProfessorFeedback(finalDocumentation, outputPath);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha descargado feedback del profesor espejo " + logicException.getMessage());
        }
    }
    
    @Test
    public void testObtainStudentsFeedbackSuccess() {
        String outputPath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\StudentsFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
        try {
            int result = finalDocumentationDAO.obtainStudentsFeedback(finalDocumentation, outputPath);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha descargado el feedback de los estudiantes " + logicException.getMessage());
        }
    }
    
    @Test
    public void testObtainMirrorStudentsFeedbackSuccess() {
        String outputPath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\MirrorStudentsFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
        try {
            int result = finalDocumentationDAO.obtainMirrorStudentsFeedback(finalDocumentation, outputPath);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha descargado el feedback de los estudiantes espejo " + logicException.getMessage());
        }
    }
    
    @Test
    public void testDeleteUploadedFileSuccess() {
        int expectedResult = 1;
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        try {
            int result = finalDocumentationDAO.deleteUploadedFile("feedbackProfesor", 11);
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("No se ha podido eliminar el archivo cargado");
        }
    }
    
    @Test
    public void testIsCollaborationRegistratedSuccess() {
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        try {
            boolean result = finalDocumentationDAO.isCollaborationRegistrated(1);
            assertTrue(result);
        } catch(LogicException logicException) {
            fail("No se ha podido saber si la colaboracion ya esta registrada con su documentacion final");
        }
    }

    @Test
    public void testHasFileUploadedSuccess() {
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        try {
            boolean result = finalDocumentationDAO.hasFileUploaded("feedbackProfesor", 1);
            assertFalse(result);
        } catch(LogicException logicException) {
            fail("No se ha podido obtener si el tipo de archivo ya tiene un archivo cargado");
        }
    }
}
