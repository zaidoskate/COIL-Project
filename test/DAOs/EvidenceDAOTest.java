package DAOs;

import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.Evidence;
import logic.DAOs.EvidenceDAO;

public class EvidenceDAOTest {
    public EvidenceDAOTest() {
    }

    @Test
    public void testUploadEvidenceSuccess() {
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
        int result = evidenceDAO.obtainEvidence(evidence, outputPath);
        assertEquals(1, result);
    }
    
}
