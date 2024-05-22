package logic.domain;

/**
 *
 * @author chuch
 */
public class ExternalAccountRequestData extends ExternalAccountRequest {
    private String university;

    /**
     *
     * @return
     */
    public String getUniversity() {
        return university;
    }

    /**
     *
     * @param university
     */
    public void setUniversity(String university) {
        this.university = university;
    }
    
    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        ExternalAccountRequestData accountRequestToCompare = (ExternalAccountRequestData) object;
        if(this.getIdRequest() != accountRequestToCompare.getIdRequest()) { 
            return false;
        }
        if(this.getIdUniversity() != accountRequestToCompare.getIdUniversity()) {
            return false;
        }
        if(!this.getEmail().equals(accountRequestToCompare.getEmail())) {
            return false;
        }
        if(!this.getName().equals(accountRequestToCompare.getName())) {
            return false;
        }
        if(!this.getLastName().equals(accountRequestToCompare.getLastName())) {
            return false;
        }
        return true;
    }
}
