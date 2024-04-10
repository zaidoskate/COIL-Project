/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.domain;

/**
 *
 * @author chima
 */
public class EvidenceFolder {
    private int idCollaboration; 
    private int idEvidenceFolder;
    private String name; 
    private String description; 
    private String creationDate;

    public int getIdCollaboration() {
        return idCollaboration;
    }

    public void setIdCollaboration(int idCollaboration) {
        this.idCollaboration = idCollaboration;
    }

    public int getIdEvidenceFolder() {
        return idEvidenceFolder;
    }

    public void setIdEvidenceFolder(int idEvidenceFolder) {
        this.idEvidenceFolder = idEvidenceFolder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    
}
