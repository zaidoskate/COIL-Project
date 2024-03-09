package logic.domain;

public class TakesACourse {
    private int idCourse;
    private int idUser;
    private String accreditationStatus;

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getAccreditationStatus() {
        return accreditationStatus;
    }

    public void setAccreditationStatus(String accreditationStatus) {
        this.accreditationStatus = accreditationStatus;
    }
    
}
