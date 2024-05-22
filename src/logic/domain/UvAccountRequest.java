package logic.domain;

/**
 *
 * @author chuch
 */
public class UvAccountRequest extends AccountRequest{
    private String name;
    private String lastName;
    private String email;
    private String personalNumber;
    private String idDepartment;
    
    /**
     *
     * @return
     */
    public String getIdDepartment() {
        return idDepartment;
    }

    /**
     *
     * @param idDepartment
     */
    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }
    
    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param personalNumber
     */
    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }
    
    /**
     *
     * @return
     */
    public String getPersonalNumber() {
        return personalNumber;
    }
    
    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        UvAccountRequest uvAccountRequestToCompare = (UvAccountRequest) object;
        if(this.getIdRequest() != uvAccountRequestToCompare.getIdRequest()) {
            return false;
        }
        if(!this.getIdDepartment().equals(uvAccountRequestToCompare.getIdDepartment())) {
            return false;
        }
        if(!this.getPersonalNumber().equals(uvAccountRequestToCompare.getPersonalNumber())) {
            return false;
        }
        return true;
    }
}
