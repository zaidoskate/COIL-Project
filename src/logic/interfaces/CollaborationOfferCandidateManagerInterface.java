package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.CollaborationOfferCandidate;

/**
 *
 * @author zaido
 */
public interface CollaborationOfferCandidateManagerInterface {

    /**
     *
     * @param collaborationOfferCandidate
     * @return
     * @throws LogicException
     */
    int InsertCollaborationOfferCandidate(CollaborationOfferCandidate collaborationOfferCandidate) throws LogicException;

    /**
     *
     * @param idOfferCollaboration
     * @return
     * @throws LogicException
     */
    int deleteCollaborationOffer(int idOfferCollaboration) throws LogicException;

    /**
     *
     * @param idUser
     * @param idCollaborationOffer
     * @return
     * @throws LogicException
     */
    boolean professorHasAppliedForOffer(int idUser, int idCollaborationOffer) throws LogicException;

    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    ArrayList<CollaborationOfferCandidate> GetCollaborationOfferCandidateByIdCollaborationOffer(int idCollaboration) throws LogicException;
}
