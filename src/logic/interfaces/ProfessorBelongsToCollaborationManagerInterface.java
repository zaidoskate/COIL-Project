package logic.interfaces;

import java.util.ArrayList;
import logic.domain.ProfessorBelongsToCollaboration;
import logic.LogicException;
import logic.domain.User;
        
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
    
    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    public String getEmailProfessorByIdCollaboration(int idCollaboration) throws LogicException;
    
    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    ArrayList<User> getProfessorsDataByCollaboration(int idCollaboration) throws LogicException;

    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    public String getStatusByIdCollaboration(int idCollaboration) throws LogicException;
}
