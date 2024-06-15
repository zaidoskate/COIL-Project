package DAOs.insertTests;

import logic.DAOs.EvidenceFolderDAO;
import logic.domain.EvidenceFolder;
import logic.LogicException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EvidenceFolderDAOTest {
    
    @Test
    public void testInsertEvidenceFolderSuccess() throws LogicException{
        EvidenceFolder evidenceFolder = new EvidenceFolder();
        evidenceFolder.setIdCollaboration(3);
        evidenceFolder.setName("Examenes");
        evidenceFolder.setDescription("Examenes correspondientes al primer parcial");
        evidenceFolder.setCreationDate("2024-07-10");
       

        EvidenceFolderDAO evidenceFolderDAO = new EvidenceFolderDAO();
        int result = evidenceFolderDAO.insertEvidenceFolder(evidenceFolder);
        
        assertEquals(1, result);
    }
}
