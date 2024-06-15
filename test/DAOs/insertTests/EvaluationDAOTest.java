package DAOs.insertTests;

import logic.DAOs.EvaluationDAO;
import logic.LogicException;
import logic.domain.Evaluation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Test;

public class EvaluationDAOTest {

    @Test
    public void testInsertEvaluationForApprovedOfferSuccess() {
        Evaluation evaluation = new Evaluation();
        evaluation.setIdOfferCollaboration(4);
        evaluation.setIdCoordinator(1);
        
        EvaluationDAO evaluationDAO = new EvaluationDAO();
        try {
            int result = evaluationDAO.insertEvaluationForApprovedOffer(evaluation);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha registrado correctamente la evaluación: " + logicException.getMessage());
        }
    }
    
    @Test
    public void testInsertEvaluationForDeclinedOfferSuccess() {
        Evaluation evaluation = new Evaluation();
        evaluation.setIdOfferCollaboration(4);
        evaluation.setIdCoordinator(1);
        evaluation.setReason("Falta de documentos");
        
        EvaluationDAO evaluationDAO = new EvaluationDAO();
        try {
            int result = evaluationDAO.insertEvaluationForDeclinedOffer(evaluation);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha registrado correctamente la evaluación: " + logicException.getMessage());
        }
    }
    
    @After
    public void tearDown() {
        EvaluationDAO evaluationDAO = new EvaluationDAO();
        int expectedResult = 1;
        try {
            int result = evaluationDAO.deleteEvaluation(4);
            assertEquals(expectedResult, result);
        } catch (LogicException logicException) {
            fail("Error al eliminar la evaluación: " + logicException.getMessage());
        }
    }
}
