package logic.domain;

/**
 *
 * @author zaido
 */
public class UvProfessor extends Professor {

    private String personalNumber;
    private String idDepartment;

    /**
     *
     * @return
     */
    public String getPersonalNumber() {
        return personalNumber;
    }

    /**
     *
     * @param personalNumber
     */
    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }
    
    /**
     *
     * @return
     */
    public String getIdDepartment() {
        return idDepartment;
    }

    /**
     *
     * @param idDepartment
     */
    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }
    
    /**
     *
     * @param object
     * @return
     */
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
