package logic.domain;

import java.io.File;

/**
 *
 * @author zaido
 */
public class ConcludedCollaboration {
    private int idColaboration;
    private int idUser;
    private int numberStudents;
    private int rating;
    private String visibility;
    private String certificatesPath;
    private String conclusion;
    private File certificatesFile;

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
    public int getNumberStudents() {
        return numberStudents;
    }

    /**
     *
     * @param numberStudents
     */
    public void setNumberStudents(int numberStudents) {
        this.numberStudents = numberStudents;
    }

    /**
     *
     * @return
     */
    public int getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     *
     * @param visibility
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     *
     * @return
     */
    public String getCertificatesPath() {
        return certificatesPath;
    }

    /**
     *
     * @param certificatesPath
     */
    public void setCertificatesPath(String certificatesPath) {
        this.certificatesPath = certificatesPath;
    }

    /**
     *
     * @return
     */
    public String getConclusion() {
        return conclusion;
    }

    /**
     *
     * @param conclusion
     */
    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    /**
     *
     * @return
     */
    public File getCertificatesFile() {
        return certificatesFile;
    }

    /**
     *
     * @param certificatesFile
     */
    public void setCertificatesFile(File certificatesFile) {
        this.certificatesFile = certificatesFile;
    }
    
    /**
     *
     * @param object
     * @return
     */
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
