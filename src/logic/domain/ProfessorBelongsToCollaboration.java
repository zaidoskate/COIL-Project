package logic.domain;

public class ProfessorBelongsToCollaboration {
    private int idUser;
    private int idUserMirrorClass;
    private int idColaboration;
    private String colaborationStatus;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUserMirrorClass() {
        return idUserMirrorClass;
    }

    public void setIdUserMirrorClass(int idUserMirrorClass) {
        this.idUserMirrorClass = idUserMirrorClass;
    }

    public int getIdColaboration() {
        return idColaboration;
    }

    public void setIdColaboration(int idColaboration) {
        this.idColaboration = idColaboration;
    }

    public String getColaborationStatus() {
        return colaborationStatus;
    }

    public void setColaborationStatus(String colaborationStatus) {
        this.colaborationStatus = colaborationStatus;
    }

    @Override
    public boolean equals(Object object) {
        ProfessorBelongsToCollaboration professorBelongsToCollaboration = (ProfessorBelongsToCollaboration) object;
        if(this.idColaboration != professorBelongsToCollaboration.getIdColaboration()) {
            return false;
        }
        if(this.idUser != professorBelongsToCollaboration.getIdUser()) {
            return false;
        }
        if(this.idUserMirrorClass != professorBelongsToCollaboration.getIdUserMirrorClass()) {
            return false;
        }
        return true;
    }
}