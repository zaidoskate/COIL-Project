/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import logic.LogicException;
import logic.domain.Coordinator;

/**
 *
 * @author chima
 */
public interface CoordinatorManagerInterface {
    int insertCoordinator(Coordinator coordinator) throws LogicException;
    int getIdCoordinatorByIdUser(int idUser) throws LogicException;
    
}
