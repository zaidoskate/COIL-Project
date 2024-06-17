package logic.interfaces;

import logic.LogicException;
import logic.domain.FinalDocumentation;
public interface FinalDocumentationManagerInterface {
    public int addFinalDocumentation(FinalDocumentation finalDocumentation) throws LogicException;
    public int uploadProfessorFeedback(FinalDocumentation finalDocumentation) throws LogicException;
    public int uploadMirrorProfessorFeedback(FinalDocumentation finalDocumentation) throws LogicException;
    public int uploadStudentsFeedback(FinalDocumentation finalDocumentation) throws LogicException;
    public int uploadMirrorStudentsFeedback(FinalDocumentation finalDocumentation) throws LogicException;
    public int obtainProfessorFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException;
    public int obtainMirrorProfessorFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException;
    public int obtainStudentsFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException;
    public int obtainMirrorStudentsFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException;
    public int deleteUploadedFile(String fileType, int idCollaboration) throws LogicException;
    public boolean isCollaborationRegistrated(int idCollaboration) throws LogicException;
    public boolean hasFileUploaded(String fileType, int idCollaboration) throws LogicException;
    
}
