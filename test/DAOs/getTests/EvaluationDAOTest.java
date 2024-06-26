package DAOs.getTests;

import java.util.ArrayList;
import logic.DAOs.EvaluationDAO;
import logic.LogicException;
import logic.domain.Evaluation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class EvaluationDAOTest {
    
    @Test
    public void testGetEvaluationByIdOfferCollaborationSuccess() {
        Evaluation evaluation = new Evaluation();
        evaluation.setIdOfferCollaboration(1);
        evaluation.setIdCoordinator(1);
        evaluation.setDate("2024-06-10");
        
        EvaluationDAO evaluationDAO = new EvaluationDAO();
        try{
            Evaluation evaluationResult = evaluationDAO.getEvaluationByIdOfferCollaboration(1);
            assertEquals(evaluation, evaluationResult);
        } catch(LogicException logicException) {
            fail("No se ha podido obtener la evaluacion " + logicException.getMessage());
        }
    }
    
    @Test
    public void testGetEvaluationByIdCoordinatorSuccess() {
        ArrayList<Evaluation> evaluationsExpected = new ArrayList<>();
        Evaluation evaluation1 = new Evaluation();
        Evaluation evaluation2 = new Evaluation();
        Evaluation evaluation3 = new Evaluation();
        
        evaluation1.setIdOfferCollaboration(1);
        evaluation1.setIdCoordinator(1);
        evaluation1.setDate("2024-06-10");
        
        evaluation2.setIdOfferCollaboration(2);
        evaluation2.setIdCoordinator(1);
        evaluation2.setDate("2024-06-10");
        
        evaluation3.setIdOfferCollaboration(3);
        evaluation3.setIdCoordinator(1);
        evaluation3.setDate("2024-06-10");
        evaluation3.setReason("Rechazada por incumplimiento de normas");
        
        evaluationsExpected.add(evaluation1);
        evaluationsExpected.add(evaluation2);
        evaluationsExpected.add(evaluation3);
        
        EvaluationDAO evaluationDAO = new EvaluationDAO();
        
        try{
            ArrayList<Evaluation> actualEvaluations = evaluationDAO.getEvaluationByIdCoordinator(1);
            assertEquals(evaluationsExpected, actualEvaluations);
        } catch(LogicException logicException) {
            fail("No se han podido obtener las evaluaciones del coordinador" + logicException.getMessage());
        }
    }
}
