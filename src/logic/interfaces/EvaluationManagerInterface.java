package logic.interfaces;

import java.util.ArrayList;
import logic.domain.Evaluation;
import logic.LogicException;
/**
 *
 * @author zaido
 */
public interface EvaluationManagerInterface {

    /**
     *
     * @param evaluation
     * @return
     * @throws LogicException
     */
    public int insertEvaluationForApprovedOffer(Evaluation evaluation) throws LogicException;

    /**
     *
     * @param evaluation
     * @return
     * @throws LogicException
     */
    public int insertEvaluationForDeclinedOffer(Evaluation evaluation) throws LogicException;

    /**
     *
     * @param idCollaborationOffer
     * @return
     * @throws LogicException
     */
    public int deleteEvaluation(int idCollaborationOffer) throws LogicException;

    /**
     *
     * @param idOfferCollaboration
     * @return
     * @throws LogicException
     */
    public Evaluation getEvaluationByIdOfferCollaboration(int idOfferCollaboration) throws LogicException;

    /**
     *
     * @param idCoordinator
     * @return
     * @throws LogicException
     */
    public ArrayList<Evaluation> getEvaluationByIdCoordinator(int idCoordinator) throws LogicException;
    
}
