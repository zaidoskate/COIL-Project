package logic.domain;

public class Belongs {
    private int idUser;
    private int idColaboration;
    private String colaborationStatus;

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
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
}
