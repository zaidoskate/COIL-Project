/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOs;
import dataaccess.DatabaseConnection;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.EvidenceFolder;
import logic.DAOs.EvidenceFolderDAO;

/**
 *
 * @author chima
 */
public class EvidenceFolderDAOTest {
    
    public EvidenceFolderDAOTest() {
    }

    @Test
    public void testInsertEvidenceFolderSuccess() {
        EvidenceFolder evidenceFolder = new EvidenceFolder();
        evidenceFolder.setIdCollaboration(12345);
        evidenceFolder.setIdEvidenceFolder(01234);
        evidenceFolder.setName("Evidencias Colaboración Random");
        evidenceFolder.setDescription("Es una prueba");
        evidenceFolder.setCreationDate("2024-03-21");
       

        EvidenceFolderDAO evidenceFolderDAO = new EvidenceFolderDAO();
        int result = evidenceFolderDAO.insertEvidenceFolder(evidenceFolder);
        
        assertNotEquals(0, result);
    }
    
    @Test
    public void testGetEvidenceFolderByIdCollaborationSuccess() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        EvidenceFolderDAO evidenceFolderDAO = new EvidenceFolderDAO();
        EvidenceFolder expectedEvidenceFolder = new EvidenceFolder();
        expectedEvidenceFolder.setIdCollaboration(12345);
        expectedEvidenceFolder.setIdEvidenceFolder(668);
        expectedEvidenceFolder.setName("Evidencias Colaboración Random");
        expectedEvidenceFolder.setDescription("Es una prueba");
        expectedEvidenceFolder.setCreationDate("2024-03-21");
        
        EvidenceFolder currentEvidenceFolder = evidenceFolderDAO.getEvidenceFolderByIdCollaboration(12345);
        assertEquals(expectedEvidenceFolder, currentEvidenceFolder);
    }
    
}
