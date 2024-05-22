/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.model;

/**
 *
 * @author zaido
 */
public class CollaborationInformation {
    
    private String collaborationName;
    private String closeDate;
    private String startDate;
    private String language;
    private String topicsOfInterest;
    private String collaborationStatus;
    private String professorUniversity;
    private String mirrorProfessorUniversity;
    private int idCollaboration;
    private int idUser;
    private int idMirrorUser;
    
    private static final CollaborationInformation collaboration = new CollaborationInformation();

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdMirrorUser() {
        return idMirrorUser;
    }

    public void setIdMirrorUser(int idMirrorUser) {
        this.idMirrorUser = idMirrorUser;
    }

    public String getCollaborationStatus() {
        return collaborationStatus;
    }

    public void setCollaborationStatus(String collaborationStatus) {
        this.collaborationStatus = collaborationStatus;
    }

    public static CollaborationInformation getCollaboration() {
        return collaboration;
    }

    public String getCollaborationName() {
        return collaborationName;
    }

    public void setCollaborationName(String collaborationName) {
        this.collaborationName = collaborationName;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTopicsOfInterest() {
        return topicsOfInterest;
    }

    public void setTopicsOfInterest(String topicsOfInterest) {
        this.topicsOfInterest = topicsOfInterest;
    }

    public int getIdCollaboration() {
        return idCollaboration;
    }

    public void setIdCollaboration(int idCollaboration) {
        this.idCollaboration = idCollaboration;
    }

    public String getProfessorUniversity() {
        return professorUniversity;
    }

    public void setProfessorUniversity(String professorUniversity) {
        this.professorUniversity = professorUniversity;
    }
    
    public String getMirrorProfessorUniversity() {
        return mirrorProfessorUniversity;
    }

    public void setMirrorProfessorUniversity(String mirrorProfessorUniversity) {
        this.mirrorProfessorUniversity = mirrorProfessorUniversity;
    }
}
