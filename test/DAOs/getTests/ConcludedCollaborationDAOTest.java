package DAOs.getTests;

import java.io.File;
import java.util.ArrayList;
import logic.DAOs.ConcludedCollaborationDAO;
import logic.LogicException;
import logic.domain.ConcludedCollaboration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class ConcludedCollaborationDAOTest {

    @Before
    public void setUp() {
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(4);
        concludedCollaboration.setCertificatesFile(new File("../../constancias.zip"));
        
        ConcludedCollaborationDAO concludedColaborationDAO = new ConcludedCollaborationDAO();
        try {
            int currentResult = concludedColaborationDAO.uploadCertificates(concludedCollaboration);
            int expectedResult = 1;
            assertEquals(expectedResult, currentResult);
        } catch(LogicException logicException) {
            fail("no se han podido cargar las constancias: " + logicException.getMessage());
        }
    }

    @Test
    public void testObtainCertificatesSuccess() throws LogicException {
        ConcludedCollaboration concludedCollaboration = new ConcludedCollaboration();
        concludedCollaboration.setIdColaboration(4);
        String outputPath = "../../constancias_descargadas.zip";
        
        ConcludedCollaborationDAO concludedColaborationDAO = new ConcludedCollaborationDAO();
        int currentResult = concludedColaborationDAO.obtainCertificates(concludedCollaboration, outputPath);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void testGetConcludedCollaborations() throws LogicException {
        ConcludedCollaborationDAO concludedCollaborationDAO = new ConcludedCollaborationDAO();
        ArrayList<ConcludedCollaboration> concludedCollaborationsResult = concludedCollaborationDAO.getConcludedCollaborations();
        
        ArrayList<ConcludedCollaboration> concludedCollaborationsExpected = new ArrayList<>();
        
        ConcludedCollaboration collaborationExpected = new ConcludedCollaboration();
        collaborationExpected.setIdColaboration(4);
        collaborationExpected.setIdUser(3);
        concludedCollaborationsExpected.add(collaborationExpected);
        
        assertEquals(concludedCollaborationsExpected, concludedCollaborationsResult);
    }
    
    @Test
    public void testHasCertificatesUploadedSuccess() {
        ConcludedCollaborationDAO concludedCollaborationDAO = new ConcludedCollaborationDAO();
        boolean expectedResult = true;
        try {
            boolean result = concludedCollaborationDAO.hasCertificatesUploaded(4);
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("Error al consultar si la colaboración tiene constancias cargadas: " + logicException.getMessage());
        }
    }
}
