package logic.domain;

/**
 *
 * @author zaido
 */
public class ExternalProfessor extends Professor{
    private int idUniversity; 

    /**
     *
     * @return
     */
    public int getIdUniversity() {
        return idUniversity;
    }

    /**
     *
     * @param idUniversity
     */
    public void setIdUniversity(int idUniversity) {
        this.idUniversity = idUniversity;
    }
    
    /**
     *
     * @param object
     * @return
     */
    @Override 
    public boolean equals (Object object) {
        ExternalProfessor externalProfessorToCompare = (ExternalProfessor) object;
        if (this.idUniversity != externalProfessorToCompare.getIdUniversity()) {
            return false;
        }
        return true;
    }
}
