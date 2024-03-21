/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.domain;

import java.io.File;

/**
 *
 * @author zaido
 */
public class FinalDocumentation {
    private int idColaboration;
    private File feedbackProfessor;
    private File feedbackMirrorProfessor;
    private File feedbackStudents;
    private File feedbackMirrorStudents;

    public void setIdColaboration(int idColaboration) {
        this.idColaboration = idColaboration;
    }

    public void setFeedbackProfessor(File feedbackProfessor) {
        this.feedbackProfessor = feedbackProfessor;
    }

    public void setFeedbackMirrorProfessor(File feedbackMirrorProfessor) {
        this.feedbackMirrorProfessor = feedbackMirrorProfessor;
    }

    public void setFeedbackStudents(File feedbackStudents) {
        this.feedbackStudents = feedbackStudents;
    }

    public void setFeedbackMirrorStudents(File feedbackMirrorStudents) {
        this.feedbackMirrorStudents = feedbackMirrorStudents;
    }

    public int getIdColaboration() {
        return idColaboration;
    }

    public File getFeedbackProfessor() {
        return feedbackProfessor;
    }

    public File getFeedbackMirrorProfessor() {
        return feedbackMirrorProfessor;
    }

    public File getFeedbackStudents() {
        return feedbackStudents;
    }

    public File getFeedbackMirrorStudents() {
        return feedbackMirrorStudents;
    }
}
