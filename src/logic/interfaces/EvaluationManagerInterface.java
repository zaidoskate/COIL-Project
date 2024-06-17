package logic.interfaces;

import java.util.ArrayList;
import logic.domain.Evaluation;
import logic.LogicException;
public interface EvaluationManagerInterface {
    public int insertEvaluationForApprovedOffer(Evaluation evaluation) throws LogicException;
    public int insertEvaluationForDeclinedOffer(Evaluation evaluation) throws LogicException;
    public int deleteEvaluation(int idCollaborationOffer) throws LogicException;
    public Evaluation getEvaluationByIdOfferCollaboration(int idOfferCollaboration) throws LogicException;
    public ArrayList<Evaluation> getEvaluationByIdCoordinator(int idCoordinator) throws LogicException;
    
}
