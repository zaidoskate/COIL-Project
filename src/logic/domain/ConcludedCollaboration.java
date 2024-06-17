package logic.domain;

import java.io.File;

public class ConcludedCollaboration {
    private int idColaboration;
    private int idUser;
    private int numberStudents;
    private int rating;
    private String visibility;
    private String certificatesPath;
    private String conclusion;
    private File certificatesFile;

    public int getIdColaboration() {
        return idColaboration;
    }

    public void setIdColaboration(int idColaboration) {
        this.idColaboration = idColaboration;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(int numberStudents) {
        this.numberStudents = numberStudents;
    }

    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getCertificatesPath() {
        return certificatesPath;
    }

    public void setCertificatesPath(String certificatesPath) {
        this.certificatesPath = certificatesPath;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public File getCertificatesFile() {
        return certificatesFile;
    }
    public void setCertificatesFile(File certificatesFile) {
        this.certificatesFile = certificatesFile;
    }
    
    @Override
    public boolean equals(Object object) {
        ConcludedCollaboration concludedCollaborationToCompare = (ConcludedCollaboration) object;
        if (this.getIdColaboration() != concludedCollaborationToCompare.getIdColaboration()) {
            return false;
        }
        if (this.getIdUser() != concludedCollaborationToCompare.getIdUser()) {
            return false;
        }
        return true;
    }
}
