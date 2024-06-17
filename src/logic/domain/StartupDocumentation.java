package logic.domain;

public class StartupDocumentation {
    private int idColaboration;
    private String studentsListPath;
    private String mirrorClassStudentsListPath;
    private String syllabusPath;

    public int getIdColaboration() {
        return idColaboration;
    }

    public void setIdColaboration(int idColaboration) {
        this.idColaboration = idColaboration;
    }

    public String getStudentsListPath() {
        return studentsListPath;
    }

    public void setStudentsListPath(String studentsListPath) {
        this.studentsListPath = studentsListPath;
    }

    public String getMirrorClassStudentsListPath() {
        return mirrorClassStudentsListPath;
    }

    public void setMirrorClassStudentsListPath(String mirrorClassStudentsListPath) {
        this.mirrorClassStudentsListPath = mirrorClassStudentsListPath;
    }

    public String getSyllabusPath() {
        return syllabusPath;
    }

    public void setSyllabusPath(String syllabusPath) {
        this.syllabusPath = syllabusPath;
    }
    
}
