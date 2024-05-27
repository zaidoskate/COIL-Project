package logic.domain;

/**
 *
 * @author zaido
 */
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
    public String getObjective() {
        return objective;
    }

    /**
     *
     * @param objective
     */
    public void setObjective(String objective) {
        this.objective = objective;
    }

    /**
     *
     * @return
     */
    public String getTopicsOfInterest() {
        return TopicsOfInterest;
    }

    /**
     *
     * @param TopicsOfInterest
     */
    public void setTopicsOfInterest(String TopicsOfInterest) {
        this.TopicsOfInterest = TopicsOfInterest;
    }

    /**
     *
     * @return
     */
    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    /**
     *
     * @param numberOfStudents
     */
    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    /**
     *
     * @return
     */
    public String getProfile() {
        return profile;
    }

    /**
     *
     * @param profile
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     *
     * @return
     */
    public String getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     *
     * @return
     */
    public String getPeriod() {
        return period;
    }

    /**
     *
     * @param period
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     *
     * @return
     */
    public String getAditionalInfo() {
        return aditionalInfo;
    }

    /**
     *
     * @param aditionalInfo
     */
    public void setAditionalInfo(String aditionalInfo) {
        this.aditionalInfo = aditionalInfo;
    }

    /**
     *
     * @return
     */
    public String getOfferStatus() {
        return offerStatus;
    }

    /**
     *
     * @param offerStatus
     */
    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
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
    public boolean equals(Object object) {
        CollaborationOffer collaborationOfferToCompare = (CollaborationOffer) object;
        if(this.idCollaboration != collaborationOfferToCompare.getIdCollaboration()) {
            return false;
        }
        if(!this.period.equals(collaborationOfferToCompare.getPeriod())) {
            return false;
        }
        if(this.idUser != collaborationOfferToCompare.getIdUser()) {
            return false;
        }
        return true;
    }
}
