package logic.domain;


public class FinalDocumentation {
    private int idColaboration;
    private String professorFeedback;
    private String mirrorProfessorFeedback;
    private String studentsFeedback;
    private String mirrorStudentsFeedback;

    public void setIdColaboration(int idColaboration) {
        this.idColaboration = idColaboration;
    }

    public void setProfessorFeedback(String professorFeedback) {
        this.professorFeedback = professorFeedback;
    }

    public void setMirrorProfessorFeedback(String mirrorProfessorFeedback) {
        this.mirrorProfessorFeedback = mirrorProfessorFeedback;
    }

    public void setStudentsFeedback(String studentsFeedback) {
        this.studentsFeedback = studentsFeedback;
    }

    public void setMirrorStudentsFeedback(String mirrorStudentsFeedback) {
        this.mirrorStudentsFeedback = mirrorStudentsFeedback;
    }

    public int getIdColaboration() {
        return idColaboration;
    }

    public String getProfessorFeedback() {
        return professorFeedback;
    }

    public String getMirrorProfessorFeedback() {
        return mirrorProfessorFeedback;
    }

    public String getStudentsFeedback() {
        return studentsFeedback;
    }

    public String getMirrorStudentsFeedback() {
        return mirrorStudentsFeedback;
    }
}
