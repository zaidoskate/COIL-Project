package logic.domain;
public class AccountRequest {
    private int idRequest;
    private String email;
    private String name;
    private String lastName;

    public int getIdRequest() {
        return idRequest;
    }
    public void setIdRequest(int idRequest) {
        this.idRequest = idRequest;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
}
