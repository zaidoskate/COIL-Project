package logic.domain;

public class Evidence {
    private int idFolderEvidence;
    private String name;
    private String author;
    private String dateOfCreation;
    private String file;

    public int getIdFolderEvidence() {
        return idFolderEvidence;
    }
    public void setIdFolderEvidence(int idFolderEvidence) {
        this.idFolderEvidence = idFolderEvidence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getFile() {
        return file;
    }
    public void setFile(String file) {
        this.file = file;
    }
    
    @Override
    public boolean equals(Object object) {
        Evidence evidenceToCompare = (Evidence) object;
        if (!this.author.equals(evidenceToCompare.getAuthor())) {
            return false;
        }
        if (!this.name.equals(evidenceToCompare.getName())) {
            return false;
        }
        return true;
    }
}
