package logic.domain;

public class UvProfessor extends Professor {

    private String personalNumber;
    private String idDepartment;

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }
    
    public String getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }
    
    @Override
    public boolean equals (Object object) {
        UvProfessor uvProfessorToCompare = (UvProfessor) object;
        if(! this.personalNumber.equals(uvProfessorToCompare.getPersonalNumber())) {
            return false;
        }
        if(! this.idDepartment.equals(uvProfessorToCompare.getIdDepartment())) {
            return false;
        }
        return true;
    }
   
}
