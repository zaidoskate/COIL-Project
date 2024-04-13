/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;
import static org.junit.Assert.*;
import org.junit.Test;
import logic.domain.Evaluation;
import logic.DAOs.EvaluationDAO;

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
        int result = evaluationDAO.insertEvaluation(evaluation);
        
        assertEquals(1, result);
    }
    
    @Test
    public void testGetEvaluationByIdOfferCollaborationSuccess() {
        Evaluation evaluation = new Evaluation();
        evaluation.setIdOfferCollaboration(1);
        evaluation.setIdCoordinator(1);
        evaluation.setDate("2024-04-12");
        evaluation.setReason("Falta de documentos");
        
        EvaluationDAO evaluationDAO = new EvaluationDAO();
        Evaluation evaluationResult = evaluationDAO.getEvaluationByIdOfferCollaboration(1);
        
        assertEquals(evaluation, evaluationResult);
    }
}
