package logic.domain;

/**
 *
 * @author chuch
 */
public class EvidenceFolder {
    private int idCollaboration; 
    private int idEvidenceFolder;
    private String name; 
    private String description; 
    private String creationDate;

    /**
     *
     * @return
     */
    public int getIdCollaboration() {
        return idCollaboration;
    }

    /**
     *
     * @param idCollaboration
     */
    public void setIdCollaboration(int idCollaboration) {
        this.idCollaboration = idCollaboration;
    }

    /**
     *
     * @return
     */
    public int getIdEvidenceFolder() {
        return idEvidenceFolder;
    }

    /**
     *
     * @param idEvidenceFolder
     */
    public void setIdEvidenceFolder(int idEvidenceFolder) {
        this.idEvidenceFolder = idEvidenceFolder;
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
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @param creationDate
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    
    /**
     *
     * @param object
     * @return
     */
    @Override 
    public boolean equals (Object object) {
        EvidenceFolder evidenceFolderToCompare = (EvidenceFolder) object;
        if (this.idCollaboration != evidenceFolderToCompare.getIdCollaboration()) {
            return false;
        }
        if (this.idEvidenceFolder != evidenceFolderToCompare.getIdEvidenceFolder()) {
            return false;
        }
        if (! this.name.equals(evidenceFolderToCompare.getName())) {
            return false;
        }
        if (! this.description.equals(evidenceFolderToCompare.getDescription())) {
            return false;
        }
        if (! this.creationDate.equals(evidenceFolderToCompare.getCreationDate())) {
            return false;
        }
        return true;
    }
    
    }
