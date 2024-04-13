/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import logic.domain.ExternalAccountRequest;
/**
 *
 * @author zaido
 */
public interface ExternalAccountRequestManagerInterface {
    public int insertExternalAccountRequest(ExternalAccountRequest externalAccountRequest);
    public int deleteExternalAccountRequest(ExternalAccountRequest externalAccountRequest);
}
