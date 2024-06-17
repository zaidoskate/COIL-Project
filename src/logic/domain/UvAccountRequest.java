package logic.domain;

public class UvAccountRequest extends AccountRequest{
    private String name;
    private String lastName;
    private String email;
    private String personalNumber;
    private String idDepartment;
    
    public String getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPersonalNumber() {
        return personalNumber;
    }
    
    @Override
    public boolean equals(Object object) {
        UvAccountRequest uvAccountRequestToCompare = (UvAccountRequest) object;
        if (this.getIdRequest() != uvAccountRequestToCompare.getIdRequest()) {
            return false;
        }
        if (!this.getIdDepartment().equals(uvAccountRequestToCompare.getIdDepartment())) {
            return false;
        }
        if (!this.getPersonalNumber().equals(uvAccountRequestToCompare.getPersonalNumber())) {
            return false;
        }
        return true;
    }
}
