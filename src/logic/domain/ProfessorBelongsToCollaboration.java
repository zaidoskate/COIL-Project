package logic.domain;

/**
 *
 * @author zaido
 */
public class ProfessorBelongsToCollaboration {
    private int idUser;
    private int idUserMirrorClass;
    private int idColaboration;
    private String colaborationStatus;

    /**
     *
     * @return
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     *
     * @param idUser
     */
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    /**
     *
     * @return
     */
    public int getIdUserMirrorClass() {
        return idUserMirrorClass;
    }

    /**
     *
     * @param idUserMirrorClass
     */
    public void setIdUserMirrorClass(int idUserMirrorClass) {
        this.idUserMirrorClass = idUserMirrorClass;
    }

    /**
     *
     * @return
     */
    public int getIdColaboration() {
        return idColaboration;
    }

    /**
     *
     * @param idColaboration
     */
    public void setIdColaboration(int idColaboration) {
        this.idColaboration = idColaboration;
    }

    /**
     *
     * @return
     */
    public String getColaborationStatus() {
        return colaborationStatus;
    }

    /**
     *
     * @param colaborationStatus
     */
    public void setColaborationStatus(String colaborationStatus) {
        this.colaborationStatus = colaborationStatus;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        ProfessorBelongsToCollaboration professorBelongsToCollaboration = (ProfessorBelongsToCollaboration) object;
        if (this.idColaboration != professorBelongsToCollaboration.getIdColaboration()) {
            return false;
        }
        if (this.idUser != professorBelongsToCollaboration.getIdUser()) {
            return false;
        }
        if (this.idUserMirrorClass != professorBelongsToCollaboration.getIdUserMirrorClass()) {
            return false;
        }
        return true;
    }
}