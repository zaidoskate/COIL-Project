package logic.domain;

public class ExternalAccountRequestData extends ExternalAccountRequest {
    private String university;

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
    
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
