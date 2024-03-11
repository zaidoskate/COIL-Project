package logic.domain;

public class UvProfessor extends Professor {
    private String personalNumber; 

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }
    
    @Override
    public boolean equals (Object object) {
        UvProfessor uvProfessorToCompare = (UvProfessor) object;
        if(! this.personalNumber.equals(uvProfessorToCompare.getPersonalNumber())) {
            return false;
        }
        if(this.getIdUser() != uvProfessorToCompare.getIdUser()) {
            return false;
        }
        return true;
    }
   
}
