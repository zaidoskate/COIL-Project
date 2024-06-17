package logic.interfaces;

import logic.LogicException;
import logic.domain.StartupDocumentation;
public interface StartupDocumentationManagerInterface {
    public int addStartupDocumentation(StartupDocumentation startupDocumentation) throws LogicException;
    public int uploadSyllabus(StartupDocumentation startupDocumentation) throws LogicException;
    public int uploadStudentsList(StartupDocumentation startupDocumentation) throws LogicException;
    public int uploadMirrorStudentsList(StartupDocumentation startupDocumentation) throws LogicException;
    public int obtainSyllabus(StartupDocumentation startupDocumentation, String outputPath)  throws LogicException;
    public int obtainStudentsList(StartupDocumentation startupDocumentation, String outputPath) throws LogicException;
    public int obtainMirrorStudentsList(StartupDocumentation startupDocumentation, String outputPath) throws LogicException;
    public int deleteUploadedFile(String fileType, int idCollaboration) throws LogicException;
    public boolean hasFileUploaded(String fileType, int idCollaboration) throws LogicException;
    public boolean isCollaborationRegistrated(int idCollaboration) throws LogicException;
}
