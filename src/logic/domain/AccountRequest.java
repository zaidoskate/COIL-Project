package logic.domain;

public class AccountRequest {
    private int idAccountRequest;
    private String academicArea;
    private String name;
    private String lastname;
    private String email;

    public int getIdAccountRequest() {
        return idAccountRequest;
    }

    public void setIdAccountRequest(int idAccountRequest) {
        this.idAccountRequest = idAccountRequest;
    }

    public String getAcademicArea() {
        return academicArea;
    }

    public void setAcademicArea(String academicArea) {
        this.academicArea = academicArea;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
