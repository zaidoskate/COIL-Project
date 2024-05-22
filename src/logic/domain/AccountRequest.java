package logic.domain;

/**
 *
 * @author chuch
 */
public class AccountRequest {
    private int idRequest;
    private String email;
    private String name;
    private String lastName;

    /**
     *
     * @return
     */
    public int getIdRequest() {
        return idRequest;
    }

    /**
     *
     * @param idRequest
     */
    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
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
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
