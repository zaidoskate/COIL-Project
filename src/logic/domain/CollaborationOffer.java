package logic.domain;
public class CollaborationOffer {
    private int idCollaboration; 
    private String objective;
    private String TopicsOfInterest;
    private int numberOfStudents;
    private String profile;
    private String language;
    private String period;
    private String aditionalInfo;
    private String offerStatus; 
    private int idUser;

    public int getIdCollaboration() {
        return idCollaboration;
    }
    public void setIdCollaboration(int idCollaboration) {
        this.idCollaboration = idCollaboration;
    }
    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }
    public String getTopicsOfInterest() {
        return TopicsOfInterest;
    }
    public void setTopicsOfInterest(String TopicsOfInterest) {
        this.TopicsOfInterest = TopicsOfInterest;
    }
    public int getNumberOfStudents() {
        return numberOfStudents;
    }
    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }
    public String getAditionalInfo() {
        return aditionalInfo;
    }
    public void setAditionalInfo(String aditionalInfo) {
        this.aditionalInfo = aditionalInfo;
    }

    public String getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
    }

    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    @Override
    public boolean equals(Object object) {
        CollaborationOffer collaborationOfferToCompare = (CollaborationOffer) object;
        if (this.idCollaboration != collaborationOfferToCompare.getIdCollaboration()) {
            return false;
        }
        if (!this.period.equals(collaborationOfferToCompare.getPeriod())) {
            return false;
        }
        if (this.idUser != collaborationOfferToCompare.getIdUser()) {
            return false;
        }
        return true;
    }
}
