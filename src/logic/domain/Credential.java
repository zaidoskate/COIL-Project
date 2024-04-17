package logic.domain;

public class Credential {
    private String user;
    private String password;
    private int idUser;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object object) {
        Credential credentialToCompare = (Credential) object;
        if(this.getIdUser() != credentialToCompare.getIdUser()) {
            return false;
        }
        if(!this.getUser().equals(credentialToCompare.getUser())) {
            return false;
        }
        if(!this.getPassword().equals(credentialToCompare.getPassword())) {
            return false;
        }
        return true;
    }
}
