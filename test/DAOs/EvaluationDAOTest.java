/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import logic.domain.Evaluation;
import logic.DAOs.EvaluationDAO;
import logic.LogicException;

/**
 *
 * @author zaido
 */
public class EvaluationDAOTest {
    public EvaluationDAOTest(){
    }
    
    @Test
    public void testInsertEvaluationSuccess() {
        Evaluation evaluation = new Evaluation();
        evaluation.setIdOfferCollaboration(1);
        evaluation.setIdCoordinator(1);
        evaluation.setDate("2024-04-12");
        evaluation.setReason("Falta de documentos");
        
        EvaluationDAO evaluationDAO = new EvaluationDAO();
        try{
            int result = evaluationDAO.insertEvaluation(evaluation);
            assertEquals(1, result);
        } catch(LogicException logicException) {
            fail("No se ha registrado correctamente la evaluacion" + logicException.getMessage());
        }
    }
    
    @Test
    public void testGetEvaluationByIdOfferCollaborationSuccess() {
        Evaluation evaluation = new Evaluation();
        evaluation.setIdOfferCollaboration(1);
        evaluation.setIdCoordinator(1);
        evaluation.setDate("2024-04-12");
        evaluation.setReason("Falta de documentos");
        
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
        
        evaluation1.setIdOfferCollaboration(1);
        evaluation1.setIdCoordinator(1);
        evaluation1.setDate("2024-04-12");
        evaluation1.setReason("Falta de documentos");
        
        evaluation2.setIdOfferCollaboration(2);
        evaluation2.setIdCoordinator(1);
        evaluation2.setDate("2024-04-13");
        
        evaluationsExpected.add(evaluation1);
        evaluationsExpected.add(evaluation2);
        
        EvaluationDAO evaluationDAO = new EvaluationDAO();
        
        try{
            ArrayList<Evaluation> actualEvaluations = evaluationDAO.getEvaluationByIdCoordinator(1);
            assertEquals(evaluationsExpected, actualEvaluations);
        } catch(LogicException logicException) {
            fail("No se han podido obtener las evaluaciones del coordinador" + logicException.getMessage());
        }
    }
    
}
