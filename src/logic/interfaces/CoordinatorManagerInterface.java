package logic.interfaces;

import logic.LogicException;
import logic.domain.Coordinator;

/**
 *
 * @author chuch
 */
public interface CoordinatorManagerInterface {

    /**
     *
     * @param coordinator
     * @return
     * @throws LogicException
     */
    public int insertCoordinator(Coordinator coordinator) throws LogicException;

    /**
     *
     * @param idUser
     * @return
     * @throws LogicException
     */
    public int getIdCoordinatorByIdUser(int idUser) throws LogicException; 
}
