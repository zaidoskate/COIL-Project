
package logic.interfaces;

import logic.domain.StartupDocumentation;

public interface StartupDocumentationManagerInterface {
    public int addStartupDocumentation(StartupDocumentation startupDocumentation);
    
    public int uploadSyllabus(StartupDocumentation startupDocumentation);
    public int uploadStudentsList(StartupDocumentation startupDocumentation);
    public int uploadMirrorStudentsList(StartupDocumentation startupDocumentation);
    
    public int obtainSyllabus(StartupDocumentation startupDocumentation, String outputPath);
    public int obtainStudentsList(StartupDocumentation startupDocumentation, String outputPath);
    public int obtainMirrorStudentsList(StartupDocumentation startupDocumentation, String outputPath);
}
