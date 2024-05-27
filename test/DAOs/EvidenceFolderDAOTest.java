package DAOs;
import dataaccess.DatabaseConnection;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.EvidenceFolder;
import logic.DAOs.EvidenceFolderDAO;
import logic.LogicException;

public class EvidenceFolderDAOTest {
    
    public EvidenceFolderDAOTest() {
    }

    @Test
    public void testInsertEvidenceFolderSuccess() throws LogicException{
        EvidenceFolder evidenceFolder = new EvidenceFolder();
        evidenceFolder.setIdCollaboration(1);
        evidenceFolder.setName("Evidencias Colaboración Random");
        evidenceFolder.setDescription("Es una prueba");
        evidenceFolder.setCreationDate("2024-03-21");
       

        EvidenceFolderDAO evidenceFolderDAO = new EvidenceFolderDAO();
        int result = evidenceFolderDAO.insertEvidenceFolder(evidenceFolder);
        
        assertNotEquals(0, result);
    }
    
    @Test
    public void testGetEvidenceFolderByIdCollaborationSuccess() throws LogicException {
        
    }
    
}
