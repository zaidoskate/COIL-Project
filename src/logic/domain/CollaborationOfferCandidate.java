package logic.domain;

/**
 *
 * @author zaido
 */
public class CollaborationOfferCandidate {
    private int idCollaboration;
    private int idUser;

    /**
     *
     * @return
     */
    public int getIdCollaboration() {
        return idCollaboration;
    }

    /**
     *
     * @param idCollaboration
     */
    public void setIdCollaboration(int idCollaboration) {
        this.idCollaboration = idCollaboration;
    }

    /**
     *
     * @return
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     *
     * @param idUser
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    /**
     *
     * @param object
     * @return
     */
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
