package logic.domain;

/**
 *
 * @author zaido
 */
public class Collaboration {
    private int idColaboration;
    private String interestTopic;
    private String colaborationName;
    private String endDate;
    private String startDate;
    private String language;

    /**
     *
     * @return
     */
    public int getIdColaboration() {
        return idColaboration;
    }

    /**
     *
     * @param idColaboration
     */
    public void setIdColaboration(int idColaboration) {
        this.idColaboration = idColaboration;
    }

    /**
     *
     * @return
     */
    public String getInterestTopic() {
        return interestTopic;
    }

    /**
     *
     * @param interestTopic
     */
    public void setInterestTopic(String interestTopic) {
        this.interestTopic = interestTopic;
    }

    /**
     *
     * @return
     */
    public String getColaborationName() {
        return colaborationName;
    }

    /**
     *
     * @param colaborationName
     */
    public void setColaborationName(String colaborationName) {
        this.colaborationName = colaborationName;
    }

    /**
     *
     * @return
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
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
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        Collaboration collaborationToCompare = (Collaboration) object;
        if (collaborationToCompare.getIdColaboration() != this.getIdColaboration()) {
            return false;
        }
        else if (!collaborationToCompare.getColaborationName().equals(this.getColaborationName())) {
            return false;
        }
        else if (!collaborationToCompare.getStartDate().equals(this.getStartDate())) {
            return false;
        }
        return true;
    }
}
