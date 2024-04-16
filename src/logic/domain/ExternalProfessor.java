package logic.domain;

public class ExternalProfessor extends Professor{
    private int idUniversity; 

    public int getIdUniversity() {
        return idUniversity;
    }

    public void setIdUniversity(int idUniversity) {
        this.idUniversity = idUniversity;
    }
    
    @Override 
    public boolean equals (Object object) {
        ExternalProfessor externalProfessorToCompare = (ExternalProfessor) object;
        if (this.idUniversity != externalProfessorToCompare.getIdUniversity()) {
            return false;
        }
        return true;
    }
}
