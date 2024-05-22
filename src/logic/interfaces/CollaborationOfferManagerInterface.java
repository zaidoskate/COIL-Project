package logic.interfaces;

import logic.domain.CollaborationOffer;
import java.util.ArrayList;
import logic.LogicException;

/**
 *
 * @author zaido
 */
public interface CollaborationOfferManagerInterface {

    /**
     *
     * @param colaborationOffer
     * @return
     * @throws LogicException
     */
    int insertColaborationOffer(CollaborationOffer colaborationOffer) throws LogicException;

    /**
     *
     * @param idCollaborationOffer
     * @return
     * @throws LogicException
     */
    int deleteCollaborationOffer(int idCollaborationOffer) throws LogicException;

    /**
     *
     * @param idCollaborationOffer
     * @param decision
     * @return
     * @throws LogicException
     */
    int evaluateCollaborationOffer(int idCollaborationOffer, String decision) throws LogicException;

    /**
     *
     * @return
     * @throws LogicException
     */
    ArrayList<CollaborationOffer> getApprovedCollaborationOffer() throws LogicException;

    /**
     *
     * @return
     * @throws LogicException
     */
    ArrayList<CollaborationOffer> getUnapprovedCollaborationOffer() throws LogicException;

    /**
     *
     * @param idUser
     * @return
     * @throws LogicException
     */
    CollaborationOffer getProfessorApprovedOffer(int idUser) throws LogicException;

    /**
     *
     * @param idUser
     * @return
     * @throws LogicException
     */
    boolean professorHasOffer(int idUser) throws LogicException;
}
