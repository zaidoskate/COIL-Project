package logic.domain;

public class ColaborationOffer {
    private int idOffer;
    private int idUser;
    private String applicationDate;
    private String correspondenceDate;
    private String revisionDate;
    private String status;

    public int getIdOffer() {
        return idOffer;
    }

    public void setIdOffer(int idOffer) {
        this.idOffer = idOffer;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(String applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getCorrespondenceDate() {
        return correspondenceDate;
    }

    public void setCorrespondenceDate(String correspondenceDate) {
        this.correspondenceDate = correspondenceDate;
    }

    public String getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}
