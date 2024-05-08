package logic.domain;

public class ExternalAccountRequest extends AccountRequest{

    private int idUniversity;

    public void setIdUniversity(int idUniversity) {
        this.idUniversity = idUniversity;
    }

    public int getIdUniversity() {
        return idUniversity;
    }
    @Override
    public boolean equals(Object object) {
        ExternalAccountRequest accountRequestToCompare = (ExternalAccountRequest) object;
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
