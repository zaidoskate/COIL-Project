package DAOs.insertTests;

import logic.DAOs.EvidenceDAO;
import logic.LogicException;
import logic.domain.Evidence;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class EvidenceDAOTest {
    
    @Test
    public void testUploadEvidenceSuccess() throws LogicException {
        String filePath = "../../EVIDENCIA1.pdf";
        EvidenceDAO evidenceDAO = new EvidenceDAO();
        Evidence evidence = new Evidence();
        evidence.setIdFolderEvidence(1);
        evidence.setName("EVIDENCIA 2");
        evidence.setAuthor("Marcio");
        evidence.setDateOfCreation("2024-07-01");
        evidence.setFile(filePath);
        int result = evidenceDAO.uploadEvidence(evidence);
        assertEquals(1, result);
    }
}
