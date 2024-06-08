package DAOs;

import java.io.File;
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
        concludedCollaboration.setIdColaboration(3);
        concludedCollaboration.setIdUser(6);
        
        ConcludedColaborationDAO concludedColaborationDAO = new ConcludedColaborationDAO();
        int currentResult = concludedColaborationDAO.addConcludedCollaboration(concludedCollaboration);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testUpdateVisibilitySuccess() throws LogicException {
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(4);
        concludedCollaboration.setVisibility("Visible");
        
        ConcludedColaborationDAO concludedColaborationDAO = new ConcludedColaborationDAO();
        int currentResult = concludedColaborationDAO.updateVisibility(concludedCollaboration);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testUpdateRatingSuccess() throws LogicException {
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(4);
        concludedCollaboration.setRating(5);
        
        ConcludedColaborationDAO concludedColaborationDAO = new ConcludedColaborationDAO();
        int currentResult = concludedColaborationDAO.updateRating(concludedCollaboration);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testUpdateCertificatesSuccess() {
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(1);
        concludedCollaboration.setCertificatesFile(new File("../../constancias.zip"));
        
        ConcludedColaborationDAO concludedColaborationDAO = new ConcludedColaborationDAO();
        try {
            int currentResult = concludedColaborationDAO.uploadCertificates(concludedCollaboration);
            int expectedResult = 1;
            assertEquals(expectedResult, currentResult);
        } catch(LogicException logicException) {
            fail("no se han podido cargar las constancias");
        }
    }
    
    @Test
    public void testObtainCertificatesSuccess() throws LogicException {
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(1);
        String outputPath = "../../constancias_descargadas.zip";
        
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
        collaborationExpected.setIdColaboration(4);
        collaborationExpected.setIdUser(3);
        concludedCollaborationsExpected.add(collaborationExpected);
        assertEquals(concludedCollaborationsExpected, concludedCollaborationsResult);
    }
    
    @Test
    public void testHasCertificatesUploadedSuccess() {
        ConcludedColaborationDAO concludedCollaborationDAO = new ConcludedColaborationDAO();
        boolean expectedResult = true;
        try {
            boolean result = concludedCollaborationDAO.hasCertificatesUploaded(4);
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("Error al consultar si la colaboracion tiene constancias cargadas");
        }
    }
}
