/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOs;

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
    
}
