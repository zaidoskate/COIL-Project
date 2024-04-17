package DAOs;


import java.util.ArrayList;
import logic.DAOs.ConcludedColaborationDAO;
import logic.LogicException;
import logic.domain.ConcludedCollaboration;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConcludedCollaborationDAOTest {
    public ConcludedCollaborationDAOTest(){
        
    }
    
    @Test
    public void testAddConcludedCollaborationSuccess() throws LogicException{
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(154);
        concludedCollaboration.setIdUser(15);
        
        ConcludedColaborationDAO concludedColaborationDAO = new ConcludedColaborationDAO();
        int currentResult = concludedColaborationDAO.addConcludedCollaboration(concludedCollaboration);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testUpdateVisibilitySuccess() throws LogicException {
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(124);
        concludedCollaboration.setVisibility("Destacable");
        
        ConcludedColaborationDAO concludedColaborationDAO = new ConcludedColaborationDAO();
        int currentResult = concludedColaborationDAO.updateVisibility(concludedCollaboration);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testUpdateRatingSuccess() throws LogicException {
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(124);
        concludedCollaboration.setRating(9);
        
        ConcludedColaborationDAO concludedColaborationDAO = new ConcludedColaborationDAO();
        int currentResult = concludedColaborationDAO.updateRating(concludedCollaboration);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testUpdateCertificatesSuccess() throws LogicException {
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(124);
        concludedCollaboration.setCertificatesPath("C:\\Users\\chuch\\Downloads\\Practica2.pdf");
        
        ConcludedColaborationDAO concludedColaborationDAO = new ConcludedColaborationDAO();
        int currentResult = concludedColaborationDAO.uploadCertificates(concludedCollaboration);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testObtainCertificatesSuccess() throws LogicException {
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(124);
        String outputPath = "C:\\Users\\chuch\\OneDrive\\Desktop\\PruebaJava.pdf";
        
        ConcludedColaborationDAO concludedColaborationDAO = new ConcludedColaborationDAO();
        int currentResult = concludedColaborationDAO.obtainCertificates(concludedCollaboration, outputPath);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
        
    }
    
    @Test
    public void testGetConcludedCollaborations() throws LogicException {
        ConcludedColaborationDAO concludedCollaborationDAO = new ConcludedColaborationDAO();
        ArrayList<ConcludedCollaboration> concludedCollaborationsResult = concludedCollaborationDAO.getConcludedCollaborations();
        
        ArrayList<ConcludedCollaboration> concludedCollaborationsExpected = new ArrayList();
        
        ConcludedCollaboration collaborationExpected = new ConcludedCollaboration();
        collaborationExpected.setIdColaboration(124);
        collaborationExpected.setIdUser(11);
        concludedCollaborationsExpected.add(collaborationExpected);
        
        collaborationExpected = new ConcludedCollaboration();
        collaborationExpected.setIdColaboration(133);
        collaborationExpected.setIdUser(13);
        concludedCollaborationsExpected.add(collaborationExpected);
        
        collaborationExpected = new ConcludedCollaboration();
        collaborationExpected.setIdColaboration(154);
        collaborationExpected.setIdUser(15);
        concludedCollaborationsExpected.add(collaborationExpected);
        
        assertEquals(concludedCollaborationsExpected, concludedCollaborationsResult);
    }
    
}
