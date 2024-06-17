package logic.domain;
public class Collaboration {
    private int idColaboration;
    private String interestTopic;
    private String colaborationName;
    private String endDate;
    private String startDate;
    private String language;

    public int getIdColaboration() {
        return idColaboration;
    }

    public void setIdColaboration(int idColaboration) {
        this.idColaboration = idColaboration;
    }

    public String getInterestTopic() {
        return interestTopic;
    }

    public void setInterestTopic(String interestTopic) {
        this.interestTopic = interestTopic;
    }

    public String getColaborationName() {
        return colaborationName;
    }
    public void setColaborationName(String colaborationName) {
        this.colaborationName = colaborationName;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

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
