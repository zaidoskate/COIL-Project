/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.domain;

/**
 *
 * @author zaido
 */
public class Colaboration {
    private int idColaboration;
    private int numStudents;
    private int idColaborationOffer;
    private String area;
    private String colaborationName;
    private String endDate;
    private String startDate;
    private String language;

    public void setIdColaboration(int idColaboration) {
        this.idColaboration = idColaboration;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    public void setIdColaborationOffer(int idColaborationOffer) {
        this.idColaborationOffer = idColaborationOffer;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setColaborationName(String colaborationName) {
        this.colaborationName = colaborationName;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getIdColaboration() {
        return idColaboration;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public int getIdColaborationOffer() {
        return idColaborationOffer;
    }

    public String getArea() {
        return area;
    }

    public String getColaborationName() {
        return colaborationName;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getLanguage() {
        return language;
    }
    
}
