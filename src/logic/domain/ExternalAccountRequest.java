/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.domain;

/**
 *
 * @author zaido
 */
public class ExternalAccountRequest extends AccountRequest{

    private int idUniversity;

    public void setIdUniversity(int idUniversity) {
        this.idUniversity = idUniversity;
    }

    public int getIdUniversity() {
        return idUniversity;
    }
}
