/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import logic.LogicException;
import logic.domain.ExternalAccountRequest;
/**
 *
 * @author zaido
 */
public interface ExternalAccountRequestManagerInterface {
    public int insertExternalAccountRequest(ExternalAccountRequest externalAccountRequest) throws LogicException;
    public int deleteExternalAccountRequest(ExternalAccountRequest externalAccountRequest) throws LogicException;
}
