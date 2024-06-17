package logic.domain;

/**
 *
 * @author chuch
 */
public class ExternalAccountRequest extends AccountRequest{

    private int idUniversity;

    /**
     *
     * @param idUniversity
     */
    public void setIdUniversity(int idUniversity) {
        this.idUniversity = idUniversity;
    }

    /**
     *
     * @return
     */
    public int getIdUniversity() {
        return idUniversity;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        ExternalAccountRequest accountRequestToCompare = (ExternalAccountRequest) object;
        if (this.getIdRequest() != accountRequestToCompare.getIdRequest()) { 
            return false;
        }
        if (this.getIdUniversity() != accountRequestToCompare.getIdUniversity()) {
            return false;
        }
        if (!this.getEmail().equals(accountRequestToCompare.getEmail())) {
            return false;
        }
        if (!this.getName().equals(accountRequestToCompare.getName())) {
            return false;
        }
        if (!this.getLastName().equals(accountRequestToCompare.getLastName())) {
            return false;
        }
        return true;
    }
}
