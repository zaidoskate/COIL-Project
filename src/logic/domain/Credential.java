package logic.domain;

/**
 *
 * @author chuch
 */
public class Credential {
    private String user;
    private String password;
    private int idUser;

    /**
     *
     * @return
     */
    public String getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
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
        Credential credentialToCompare = (Credential) object;
        if (this.getIdUser() != credentialToCompare.getIdUser()) {
            return false;
        }
        if (!this.getUser().equals(credentialToCompare.getUser())) {
            return false;
        }
        if (!this.getPassword().equals(credentialToCompare.getPassword())) {
            return false;
        }
        return true;
    }
}
