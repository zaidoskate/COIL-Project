/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.domain;

/**
 *
 * @author zaido
 */
public class ColaborationOffer {
    private int idOffer;
    private int idUser;
    private String applicationDate;
    private String correspondenceDate;
    private String revisionDate;
    private String state;

    public void setIdOffer(int idOffer) {
        this.idOffer = idOffer;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setCorrespondenceDate(String correspondenceDate) {
        this.correspondenceDate = correspondenceDate;
    }

    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getIdOffer() {
        return idOffer;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public String getCorrespondenceDate() {
        return correspondenceDate;
    }

    public String getRevisionDate() {
        return revisionDate;
    }

    public String getState() {
        return state;
    }
    
}
