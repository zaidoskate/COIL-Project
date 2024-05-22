/*
 * Click nbfs://nbhost/SystemStringSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemStringSystem/Templates/Classes/Class.java to edit this template
 */
package logic.domain;


/**
 *
 * @author zaido
 */
public class FinalDocumentation {
    private int idColaboration;
    private String professorFeedback;
    private String mirrorProfessorFeedback;
    private String studentsFeedback;
    private String mirrorStudentsFeedback;

    /**
     *
     * @param idColaboration
     */
    public void setIdColaboration(int idColaboration) {
        this.idColaboration = idColaboration;
    }

    /**
     *
     * @param professorFeedback
     */
    public void setProfessorFeedback(String professorFeedback) {
        this.professorFeedback = professorFeedback;
    }

    /**
     *
     * @param mirrorProfessorFeedback
     */
    public void setMirrorProfessorFeedback(String mirrorProfessorFeedback) {
        this.mirrorProfessorFeedback = mirrorProfessorFeedback;
    }

    /**
     *
     * @param studentsFeedback
     */
    public void setStudentsFeedback(String studentsFeedback) {
        this.studentsFeedback = studentsFeedback;
    }

    /**
     *
     * @param mirrorStudentsFeedback
     */
    public void setMirrorStudentsFeedback(String mirrorStudentsFeedback) {
        this.mirrorStudentsFeedback = mirrorStudentsFeedback;
    }

    /**
     *
     * @return
     */
    public int getIdColaboration() {
        return idColaboration;
    }

    /**
     *
     * @return
     */
    public String getProfessorFeedback() {
        return professorFeedback;
    }

    /**
     *
     * @return
     */
    public String getMirrorProfessorFeedback() {
        return mirrorProfessorFeedback;
    }

    /**
     *
     * @return
     */
    public String getStudentsFeedback() {
        return studentsFeedback;
    }

    /**
     *
     * @return
     */
    public String getMirrorStudentsFeedback() {
        return mirrorStudentsFeedback;
    }
}
