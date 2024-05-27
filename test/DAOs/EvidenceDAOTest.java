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
        String filePath = "C:\\Users\\chima\\Documents\\4TO SEMESTRE\\PRINCIPIOS DE DISEÑO\\Actividad01_GonzalezMarcio.pdf";
        EvidenceDAO evidenceDAO = new EvidenceDAO();
        Evidence evidence = new Evidence();
        evidence.setIdFolderEvidence(668);
        evidence.setName("1");
        evidence.setAuthor("Yo");
        evidence.setDateOfCreation("2024-04-04");
        evidence.setFile(filePath);
        int result = evidenceDAO.uploadEvidence(evidence);
        assertEquals(1, result);
    }
    
    @Test
    public void testObtainEvidence(){
        String outputPath = "C:\\Users\\chima\\Desktop\\evidencia.pdf";
        EvidenceDAO evidenceDAO = new EvidenceDAO();
        Evidence evidence = new Evidence();
        evidence.setIdFolderEvidence(668);
        evidence.setName("1");
        evidence.setAuthor("Yo");
        evidence.setDateOfCreation("2024-04-04");
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
        
        EvidenceDAO evidenceDAO = new EvidenceDAO();
        try {
            ArrayList<Evidence> evidencesObtained = evidenceDAO.getAllEvidencesByIdCollaboration(1);
            assertEquals(evidencesExpected, evidencesObtained);
        } catch(LogicException logicException) {
            fail("Error al obtener todas las evidencias de la colaboracion");
        }
    }
    
    @Test
    public void testDeleteEvidenceByNameSuccess() throws LogicException {
        EvidenceDAO evidenceDAO = new EvidenceDAO();
        String name = "Actividad";
        int currentResult = evidenceDAO.deleteEvidenceByName(name);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
}
