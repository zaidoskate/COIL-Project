package DAOs.deleteTests;

import logic.DAOs.EvaluationDAO;
import logic.LogicException;
import logic.domain.Evaluation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class EvaluationDAOTest {
    
    @Before
    public void setUp() {
        Evaluation evaluation = new Evaluation();
        evaluation.setIdOfferCollaboration(4);
        evaluation.setIdCoordinator(1);
        
        EvaluationDAO evaluationDAO = new EvaluationDAO();
        try {
            int result = evaluationDAO.insertEvaluationForApprovedOffer(evaluation);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha registrado correctamente la evaluación: " + logicException.getMessage());
        }
    }
    
    @Test
    public void testDeleteEvaluationSuccess() {
        EvaluationDAO evaluationDAO = new EvaluationDAO();
        int expectedResult = 1;
        try {
            int result = evaluationDAO.deleteEvaluation(3);
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("Error al eliminar la evaluación: " + logicException.getMessage());
        }
    }
}
