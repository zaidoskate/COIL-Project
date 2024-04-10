package logic.domain;

public class UvProfessor extends Professor {
    private String personalNumber;
    private String region;

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }
    
    public String getRegion() {
        return region;
    }
    
    public void setRegion(String region) {
        this.region =  region;
    }
    
    @Override
    public boolean equals (Object object) {
        UvProfessor uvProfessorToCompare = (UvProfessor) object;
        if(! this.personalNumber.equals(uvProfessorToCompare.getPersonalNumber())) {
            return false;
        }
        if(! this.region.equals(uvProfessorToCompare.getRegion())) {
            return false;
        }
        return true;
    }
   
}
