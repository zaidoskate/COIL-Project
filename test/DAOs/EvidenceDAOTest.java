package DAOs;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.Evidence;
import logic.DAOs.EvidenceDAO;
import logic.LogicException;

public class EvidenceDAOTest {
    public EvidenceDAOTest() {
    }

    @Test
    public void testUploadEvidenceSuccess() throws LogicException {
        String filePath = "../../EVIDENCIA1.pdf";
        EvidenceDAO evidenceDAO = new EvidenceDAO();
        Evidence evidence = new Evidence();
        evidence.setIdFolderEvidence(1);
        evidence.setName("EVIDENCIA 2");
        evidence.setAuthor("Marcio");
        evidence.setDateOfCreation("2024-01-01");
        evidence.setFile(filePath);
        int result = evidenceDAO.uploadEvidence(evidence);
        assertEquals(1, result);
    }
    
    @Test
    public void testObtainEvidence(){
        String outputPath = "../../evidence1_descargado.pdf";
        EvidenceDAO evidenceDAO = new EvidenceDAO();
        Evidence evidence = new Evidence();
        evidence.setIdFolderEvidence(1);
        evidence.setName("EVIDENCIA 1");
        evidence.setAuthor("Marcio");
        evidence.setDateOfCreation("2024-01-01");
        try {
            int result = evidenceDAO.obtainEvidence(evidence, outputPath);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("Error al descargar la evidencia");
        }
    }
    
    @Test
    public void testGetAllEvidenceByIdCollaborationSuccess() {
        ArrayList<Evidence> evidencesExpected = new ArrayList<>();
        Evidence evidence = new Evidence();
        evidence.setAuthor("Marcio");
        evidence.setName("EVIDENCIA 1");
        evidence.setIdFolderEvidence(1);
        evidencesExpected.add(evidence);
        
        EvidenceDAO evidenceDAO = new EvidenceDAO();
        try {
            ArrayList<Evidence> evidencesObtained = evidenceDAO.getAllEvidencesByIdCollaboration(3);
            assertEquals(evidencesExpected, evidencesObtained);
        } catch(LogicException logicException) {
            fail("Error al obtener todas las evidencias de la colaboracion");
        }
    }
    
    @Test
    public void testDeleteEvidenceByNameSuccess() throws LogicException {
        EvidenceDAO evidenceDAO = new EvidenceDAO();
        String name = "EVIDENCIA 1";
        int currentResult = evidenceDAO.deleteEvidenceByName(name);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
}
