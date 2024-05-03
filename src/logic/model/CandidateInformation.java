/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.model;

/**
 *
 * @author zaido
 */
public class CandidateInformation {
    private int idUser;
    private String professorName;
    private String professorEmail;
    private String universityName;
    private String universityLocation;
    private static final CandidateInformation candidateInformation = new CandidateInformation();

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getProfessorEmail() {
        return professorEmail;
    }

    public void setProfessorEmail(String professorEmail) {
        this.professorEmail = professorEmail;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getUniversityLocation() {
        return universityLocation;
    }

    public void setUniversityLocation(String universityLocation) {
        this.universityLocation = universityLocation;
    }

    public static CandidateInformation getCandidateInformation() {
        return candidateInformation;
    }
    
}
