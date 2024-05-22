package logic.interfaces;

import logic.domain.Collaboration;
import logic.LogicException;
import java.util.ArrayList;
import logic.domain.ProfessorBelongsToCollaboration;

/**
 *
 * @author zaido
 */
public interface CollaborationManagerInterface {

    /**
     *
     * @param colaboration
     * @return
     * @throws LogicException
     */
    int addColaboration(Collaboration colaboration) throws LogicException;

    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    int startCollaboration(int idCollaboration) throws LogicException;

    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    int concludeCollaboration(int idCollaboration) throws LogicException;

    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    int updateEndDateByIdCollaboration(int idCollaboration) throws LogicException;

    /**
     *
     * @param id
     * @return
     * @throws LogicException
     */
    Collaboration getColaborationById(int id) throws LogicException;

    /**
     *
     * @return
     * @throws LogicException
     */
    ArrayList<Collaboration> getAllCollaborations() throws LogicException;

    /**
     *
     * @return
     * @throws LogicException
     */
    ArrayList<Collaboration> getActiveCollaborations() throws LogicException;

    /**
     *
     * @param professorCollaborations
     * @return
     * @throws LogicException
     */
    ArrayList<Collaboration> getProfessorConcludedCollaborations(ArrayList<ProfessorBelongsToCollaboration> professorCollaborations) throws LogicException;
}
