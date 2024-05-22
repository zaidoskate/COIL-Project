package logic.interfaces;

import java.util.ArrayList;
import logic.domain.ProfessorBelongsToCollaboration;
import logic.LogicException;
        
/**
 *
 * @author zaido
 */
public interface ProfessorBelongsToCollaborationManagerInterface {

    /**
     *
     * @param belongs
     * @return
     * @throws LogicException
     */
    int addProfessorBelongsToCollaboration(ProfessorBelongsToCollaboration belongs) throws LogicException;

    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    int deleteProfessorBelongsToCollaborationByIdCollaboration(int idCollaboration) throws LogicException;

    /**
     *
     * @param idCollaboration
     * @param status
     * @return
     * @throws LogicException
     */
    int setStatusToCollaboration(int idCollaboration, String status) throws LogicException;

    /**
     *
     * @param idUser
     * @return
     * @throws LogicException
     */
    ProfessorBelongsToCollaboration getProfessorPendingCollaboration(int idUser) throws LogicException;

    /**
     *
     * @return
     * @throws LogicException
     */
    ArrayList<ProfessorBelongsToCollaboration> getOnHoldCollaborations() throws LogicException;

    /**
     *
     * @param idUser
     * @return
     * @throws LogicException
     */
    ArrayList<ProfessorBelongsToCollaboration> getConcludedCollaborationsByIdUser(int idUser) throws LogicException;
}
