package logic.interfaces;

import logic.LogicException;
import logic.domain.Coordinator;

public interface CoordinatorManagerInterface {
    public int insertCoordinator(Coordinator coordinator) throws LogicException;
    public int getIdCoordinatorByIdUser(int idUser) throws LogicException; 
}
