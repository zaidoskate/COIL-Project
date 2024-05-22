package logic.interfaces;

import logic.LogicException;
import logic.domain.StartupDocumentation;

/**
 *
 * @author zaido
 */
public interface StartupDocumentationManagerInterface {

    /**
     *
     * @param startupDocumentation
     * @return
     * @throws LogicException
     */
    public int addStartupDocumentation(StartupDocumentation startupDocumentation) throws LogicException;
    
    /**
     *
     * @param startupDocumentation
     * @return
     * @throws LogicException
     */
    public int uploadSyllabus(StartupDocumentation startupDocumentation) throws LogicException;

    /**
     *
     * @param startupDocumentation
     * @return
     * @throws LogicException
     */
    public int uploadStudentsList(StartupDocumentation startupDocumentation) throws LogicException;

    /**
     *
     * @param startupDocumentation
     * @return
     * @throws LogicException
     */
    public int uploadMirrorStudentsList(StartupDocumentation startupDocumentation) throws LogicException;
    
    /**
     *
     * @param startupDocumentation
     * @param outputPath
     * @return
     * @throws LogicException
     */
    public int obtainSyllabus(StartupDocumentation startupDocumentation, String outputPath)  throws LogicException;

    /**
     *
     * @param startupDocumentation
     * @param outputPath
     * @return
     * @throws LogicException
     */
    public int obtainStudentsList(StartupDocumentation startupDocumentation, String outputPath) throws LogicException;

    /**
     *
     * @param startupDocumentation
     * @param outputPath
     * @return
     * @throws LogicException
     */
    public int obtainMirrorStudentsList(StartupDocumentation startupDocumentation, String outputPath) throws LogicException;
    
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
     * @param fileType
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    public boolean hasFileUploaded(String fileType, int idCollaboration) throws LogicException;

    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    public boolean isCollaborationRegistrated(int idCollaboration) throws LogicException;
}
