package logic.interfaces;

import logic.LogicException;
import logic.domain.FinalDocumentation;
/**
 *
 * @author zaido
 */
public interface FinalDocumentationManagerInterface {
    
    /**
     *
     * @param finalDocumentation
     * @return
     * @throws LogicException
     */
    public int addFinalDocumentation(FinalDocumentation finalDocumentation) throws LogicException;
    
    /**
     *
     * @param finalDocumentation
     * @return
     * @throws LogicException
     */
    public int uploadProfessorFeedback(FinalDocumentation finalDocumentation) throws LogicException;

    /**
     *
     * @param finalDocumentation
     * @return
     * @throws LogicException
     */
    public int uploadMirrorProfessorFeedback(FinalDocumentation finalDocumentation) throws LogicException;

    /**
     *
     * @param finalDocumentation
     * @return
     * @throws LogicException
     */
    public int uploadStudentsFeedback(FinalDocumentation finalDocumentation) throws LogicException;

    /**
     *
     * @param finalDocumentation
     * @return
     * @throws LogicException
     */
    public int uploadMirrorStudentsFeedback(FinalDocumentation finalDocumentation) throws LogicException;
    
    /**
     *
     * @param finalDocumentation
     * @param outputPath
     * @return
     * @throws LogicException
     */
    public int obtainProfessorFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException;

    /**
     *
     * @param finalDocumentation
     * @param outputPath
     * @return
     * @throws LogicException
     */
    public int obtainMirrorProfessorFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException;

    /**
     *
     * @param finalDocumentation
     * @param outputPath
     * @return
     * @throws LogicException
     */
    public int obtainStudentsFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException;

    /**
     *
     * @param finalDocumentation
     * @param outputPath
     * @return
     * @throws LogicException
     */
    public int obtainMirrorStudentsFeedback(FinalDocumentation finalDocumentation, String outputPath) throws LogicException;
    
    /**
     *
     * @param fileType
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    public int deleteUploadedFile(String fileType, int idCollaboration) throws LogicException;
    
    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    public boolean isCollaborationRegistrated(int idCollaboration) throws LogicException;

    /**
     *
     * @param fileType
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    public boolean hasFileUploaded(String fileType, int idCollaboration) throws LogicException;
    
}
