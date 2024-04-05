/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.FinalDocumentation;
import logic.DAOs.FinalDocumentationDAO;

/**
 *
 * @author zaido
 */
public class FinalDocumentationDAOTest {
    public FinalDocumentationDAOTest(){
    }
    
    @Test
    public void testUploadProfessorFeedbackSuccess() {
        String filePath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\DisenioEnConstruccion.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
        finalDocumentation.setProfessorFeedback(filePath);
        int result = finalDocumentationDAO.uploadProfessorFeedback(finalDocumentation);
        assertEquals(1, result);
    }
    
    @Test
    public void testUploadMirrorProfessorFeedbackSuccess() {
        String filePath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\uploadFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
        finalDocumentation.setMirrorProfessorFeedback(filePath);
        int result = finalDocumentationDAO.uploadMirrorProfessorFeedback(finalDocumentation);
        assertEquals(1, result);
    }
    
    @Test
    public void testUploadStudentsFeedbackSuccess() {
        String filePath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\uploadFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
        finalDocumentation.setStudentsFeedback(filePath);
        int result = finalDocumentationDAO.uploadStudentsFeedback(finalDocumentation);
        assertEquals(1, result);
    }
    
    @Test
    public void testUploadMirrorStudentsFeedbackSuccess() {
        String filePath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\uploadFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
        finalDocumentation.setMirrorStudentsFeedback(filePath);
        int result = finalDocumentationDAO.uploadMirrorStudentsFeedback(finalDocumentation);
        assertEquals(1, result);
    }
    
    /* OBTAIN */
    
    @Test
    public void testObtainProfessorFeedbackSuccess() {
        String outputPath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\testPdf.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
        int result = finalDocumentationDAO.obtainProfessorFeedback(finalDocumentation, outputPath);
        assertEquals(1, result);
    }
    
    @Test
    public void testObtainMirrorProfessorFeedbackSuccess() {
        String outputPath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\MirrorProfessorFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
        int result = finalDocumentationDAO.obtainMirrorProfessorFeedback(finalDocumentation, outputPath);
        assertEquals(1, result);
    }
    
    @Test
    public void testObtainStudentsFeedbackSuccess() {
        String outputPath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\StudentsFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
        int result = finalDocumentationDAO.obtainStudentsFeedback(finalDocumentation, outputPath);
        assertEquals(1, result);
    }
    
    @Test
    public void testObtainMirrorStudentsFeedbackSuccess() {
        String outputPath = "C:\\Users\\zaido\\Desktop\\UV\\4TO\\PRCONSTRUCCION\\Proyecto\\MirrorStudentsFeedback.pdf";
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        FinalDocumentation finalDocumentation = new FinalDocumentation();
        finalDocumentation.setIdColaboration(1);
        int result = finalDocumentationDAO.obtainMirrorStudentsFeedback(finalDocumentation, outputPath);
        assertEquals(1, result);
    }

}
