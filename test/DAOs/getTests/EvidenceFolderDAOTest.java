package DAOs.getTests;

import java.util.ArrayList;
import logic.DAOs.EvidenceFolderDAO;
import logic.domain.EvidenceFolder;
import logic.LogicException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class EvidenceFolderDAOTest {
    @Test
    public void testGetEvidenceFoldersByIdCollaborationSuccess() throws LogicException {
        EvidenceFolderDAO evidenceFolderDAO = new EvidenceFolderDAO();
        ArrayList<EvidenceFolder> foldersResult = evidenceFolderDAO.getEvidenceFoldersByIdCollaboration(3);
        
        ArrayList<EvidenceFolder> expectedFolders = new ArrayList<>();
        EvidenceFolder evidenceFolder = new EvidenceFolder();
        evidenceFolder.setIdCollaboration(3);
        evidenceFolder.setIdEvidenceFolder(1);
        evidenceFolder.setName("Actividades por equipo");
        evidenceFolder.setDescription("Actividades realizadas por veracruzanos");
        evidenceFolder.setCreationDate("2024-06-10");
        expectedFolders.add(evidenceFolder);
        
        evidenceFolder = new EvidenceFolder();
        evidenceFolder.setIdCollaboration(3);
        evidenceFolder.setIdEvidenceFolder(2);
        evidenceFolder.setName("Actividades individuales");
        evidenceFolder.setDescription("Actividades realizadas por veracruzanos");
        evidenceFolder.setCreationDate("2024-06-10");
        expectedFolders.add(evidenceFolder);
        
        assertEquals(expectedFolders, foldersResult);
    }
    
    @Test
    public void testCheckEvidenceFolderNameByCollaborationSuccess() {
        String folderName = "Folder evidencia";
        EvidenceFolderDAO evidenceFolderDAO = new EvidenceFolderDAO();
        int resultExpected = 0;
        try {
            int foldersObtained = evidenceFolderDAO.checkEvidenceFolderNameByCollaboration(folderName, 1);
            assertEquals(resultExpected, foldersObtained);
        } catch(LogicException logicException) {
            fail("No ha sido posible verificar si el nombre del folder ya existe");
        }
    }
}
