package logic.domain;

/**
 *
 * @author chuch
 */
public class User {
    private int idUser;
    private String name;
    private String lastName;
    private String email;

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
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        User userToCompare = (User) object;
        if (this.idUser != userToCompare.getIdUser()) {
            return false;
        }
        if (! this.name.equals(userToCompare.getName())) {
            return false;
        }
        if (! this.lastName.equals(userToCompare.getLastName())) {
            return false;
        }
        if (! this.email.equals(userToCompare.getEmail())) {
            return false;
        }
        return true;
    }
}
