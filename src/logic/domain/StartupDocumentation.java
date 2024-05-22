package logic.domain;

/**
 *
 * @author zaido
 */
public class StartupDocumentation {
    private int idColaboration;
    private String studentsListPath;
    private String mirrorClassStudentsListPath;
    private String syllabusPath;

    /**
     *
     * @return
     */
    public int getIdColaboration() {
        return idColaboration;
    }

    /**
     *
     * @param idColaboration
     */
    public void setIdColaboration(int idColaboration) {
        this.idColaboration = idColaboration;
    }

    /**
     *
     * @return
     */
    public String getStudentsListPath() {
        return studentsListPath;
    }

    /**
     *
     * @param studentsListPath
     */
    public void setStudentsListPath(String studentsListPath) {
        this.studentsListPath = studentsListPath;
    }

    /**
     *
     * @return
     */
    public String getMirrorClassStudentsListPath() {
        return mirrorClassStudentsListPath;
    }

    /**
     *
     * @param mirrorClassStudentsListPath
     */
    public void setMirrorClassStudentsListPath(String mirrorClassStudentsListPath) {
        this.mirrorClassStudentsListPath = mirrorClassStudentsListPath;
    }

    /**
     *
     * @return
     */
    public String getSyllabusPath() {
        return syllabusPath;
    }

    /**
     *
     * @param syllabusPath
     */
    public void setSyllabusPath(String syllabusPath) {
        this.syllabusPath = syllabusPath;
    }
    
}
