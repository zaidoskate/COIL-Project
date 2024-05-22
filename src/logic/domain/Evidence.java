package logic.domain;

/**
 *
 * @author chuch
 */
public class Evidence {
    private int idFolderEvidence;
    private String name;
    private String author;
    private String dateOfCreation;
    private String file;

    /**
     *
     * @return
     */
    public int getIdFolderEvidence() {
        return idFolderEvidence;
    }

    /**
     *
     * @param idFolderEvidence
     */
    public void setIdFolderEvidence(int idFolderEvidence) {
        this.idFolderEvidence = idFolderEvidence;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return
     */
    public String getDateOfCreation() {
        return dateOfCreation;
    }

    /**
     *
     * @param dateOfCreation
     */
    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    /**
     *
     * @return
     */
    public String getFile() {
        return file;
    }

    /**
     *
     * @param file
     */
    public void setFile(String file) {
        this.file = file;
    }
    
    @Override
    public boolean equals(Object object) {
        Evidence evidenceToCompare = (Evidence) object;
        if(this.idFolderEvidence != evidenceToCompare.getIdFolderEvidence()) {
            return false;
        }
        if(!this.author.equals(evidenceToCompare.getAuthor())) {
            return false;
        }
        if(!this.name.equals(evidenceToCompare.getName())) {
            return false;
        }
        return true;
    }
}
