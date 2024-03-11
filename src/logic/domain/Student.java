package logic.domain;

public class Student extends User{
    private String registrationNumber; 
    private String region;
    private int idUniversity;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    public int getIdUniversity() {
        return this.idUniversity;
    }
    
    public void setIdUniversity(int idUniversity) {
        this.idUniversity = idUniversity;
    }
    
}
