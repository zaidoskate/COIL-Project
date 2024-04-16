/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.domain;

/**
 *
 * @author chima
 */
public class CollaborationOfferCandidate {
    private int idCollaboration;
    private int idUser;

    public int getIdCollaboration() {
        return idCollaboration;
    }

    public void setIdCollaboration(int idCollaboration) {
        this.idCollaboration = idCollaboration;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    @Override 
    public boolean equals (Object object) {
        CollaborationOfferCandidate collaborationOfferCandidatesToCompare = (CollaborationOfferCandidate) object;
        if (this.idCollaboration != collaborationOfferCandidatesToCompare.getIdCollaboration()) {
            return false;
        }
        if (this.idUser != collaborationOfferCandidatesToCompare.getIdUser()) {
            return false;
        }
        return true;
    }
    
}
