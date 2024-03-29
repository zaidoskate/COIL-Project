/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import logic.domain.UvAccountRequest;

/**
 *
 * @author zaido
 */
public interface UvAccountRequestManagerInterface {
    public int insertUvAccountRequest(UvAccountRequest uvAccountRequest);
    public int deleteUvAccountRequest(int personalNumber);
}