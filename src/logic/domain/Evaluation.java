package logic.domain;

/**
 *
 * @author zaido
 */
public class Evaluation {
    private int idOfferCollaboration;
    private int idCoordinator;
    private String date;
    private String reason;
    
    /**
     *
     * @return
     */
    public int getIdOfferCollaboration() {
        return idOfferCollaboration;
    }

    /**
     *
     * @param idOfferCollaboration
     */
    public void setIdOfferCollaboration(int idOfferCollaboration) {
        this.idOfferCollaboration = idOfferCollaboration;
    }

    /**
     *
     * @return
     */
    public int getIdCoordinator() {
        return idCoordinator;
    }

    /**
     *
     * @param idCoordinator
     */
    public void setIdCoordinator(int idCoordinator) {
        this.idCoordinator = idCoordinator;
    }

    /**
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public String getReason() {
        return reason;
    }

    /**
     *
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        Evaluation evaluationToCompare = (Evaluation) object;
        if (this.idOfferCollaboration != evaluationToCompare.getIdOfferCollaboration()) {
            return false;
        }
        if (this.idCoordinator != evaluationToCompare.getIdCoordinator()) {
            return false;
        }
        if (! this.date.equals(evaluationToCompare.getDate())) {
            return false;
        }
        return true;
    }
    
}
