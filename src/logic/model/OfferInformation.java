/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.model;

/**
 *
 * @author zaido
 */
public class OfferInformation {
    private int idOfferCollaboration;
    private int idUser;
    private int numberStudents;
    private String studentProfile;
    private String professorName;
    private String professorEmail;
    private String universityName;
    private String universityLocation;
    private String objective;
    private String topicsInterest;
    private String offerPeriod;
    private String offerLanguage;
    private String aditionalInformation;
    
    private static final OfferInformation offer = new OfferInformation();

    public int getIdOfferCollaboration() {
        return idOfferCollaboration;
    }

    public void setIdOfferCollaboration(int idOfferCollaboration) {
        this.idOfferCollaboration = idOfferCollaboration;
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

    public String getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(String studentProfile) {
        this.studentProfile = studentProfile;
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

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getTopicsInterest() {
        return topicsInterest;
    }

    public void setTopicsInterest(String topicsInterest) {
        this.topicsInterest = topicsInterest;
    }

    public String getOfferPeriod() {
        return offerPeriod;
    }

    public void setOfferPeriod(String offerPeriod) {
        this.offerPeriod = offerPeriod;
    }

    public String getOfferLanguage() {
        return offerLanguage;
    }

    public void setOfferLanguage(String offerLanguage) {
        this.offerLanguage = offerLanguage;
    }

    public String getAditionalInformation() {
        return aditionalInformation;
    }

    public void setAditionalInformation(String aditionalInformation) {
        this.aditionalInformation = aditionalInformation;
    }

    public static OfferInformation getOffer() {
        return offer;
    }
}
