/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.UvAccountRequest;

/**
 *
 * @author zaido
 */
public interface UvAccountRequestManagerInterface {
    int insertUvAccountRequest(UvAccountRequest uvAccountRequest) throws LogicException;
    int deleteUvAccountRequest(UvAccountRequest uvAccountRequest) throws LogicException;
    ArrayList<UvAccountRequest> getUvAccountRequests() throws LogicException;
}
