package DAOs.deleteTests;

import logic.DAOs.EvidenceDAO;
import logic.LogicException;
import logic.domain.Evidence;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class EvidenceDAOTest {
    
    @Before
    public void setUp() throws LogicException {
        String filePath = "../../EVIDENCIA1.pdf";
        EvidenceDAO evidenceDAO = new EvidenceDAO();
        Evidence evidence = new Evidence();
        evidence.setIdFolderEvidence(1);
        evidence.setName("EVIDENCIA 1");
        evidence.setAuthor("Marcio");
        evidence.setDateOfCreation("2024-07-01");
        evidence.setFile(filePath);
        int result = evidenceDAO.uploadEvidence(evidence);
        assertEquals(1, result);
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
