package logic.domain;

public class User {
    private int idUser;
    private String name;
    private String lastName;
    private String language;
    private String email;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
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
        if (! this.language.equals(userToCompare.getLanguage())) {
            return false;
        }
        return true;
    }
}
