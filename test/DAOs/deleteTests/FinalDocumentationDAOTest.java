package DAOs.deleteTests;

import logic.DAOs.FinalDocumentationDAO;
import logic.LogicException;
import logic.domain.FinalDocumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class FinalDocumentationDAOTest {
    
    @Before
    public void setUp() {
        try {
            String filePath = "../../uploadfeedback.pdf";
            FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
            FinalDocumentation finalDocumentation = new FinalDocumentation();
            finalDocumentation.setIdColaboration(4);
            finalDocumentation.setProfessorFeedback(filePath);
            int result = finalDocumentationDAO.uploadProfessorFeedback(finalDocumentation);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha cargado el feedback del profesor " + logicException.getMessage());
        }
    }
    
    @Test
    public void testDeleteUploadedFileSuccess() {
        int expectedResult = 1;
        FinalDocumentationDAO finalDocumentationDAO = new FinalDocumentationDAO();
        try {
            int result = finalDocumentationDAO.deleteUploadedFile("feedbackProfesor", 4);
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("No se ha podido eliminar el archivo cargado");
        }
    }
}
