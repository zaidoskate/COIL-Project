package logic.domain;

public class Evaluation {
    private int idOfferCollaboration;
    private int idCoordinator;
    private String date;
    private String reason;
    
    public int getIdOfferCollaboration() {
        return idOfferCollaboration;
    }
    public void setIdOfferCollaboration(int idOfferCollaboration) {
        this.idOfferCollaboration = idOfferCollaboration;
    }
    public int getIdCoordinator() {
        return idCoordinator;
    }

    
    public void setIdCoordinator(int idCoordinator) {
        this.idCoordinator = idCoordinator;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    
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
