package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.ConcludedCollaboration;

/**
 *
 * @author zaido
 */
public interface ConcludedCollaborationManagerInterface {

    /**
     *
     * @param concludedCollaboration
     * @return
     * @throws LogicException
     */
    public int addConcludedCollaboration(ConcludedCollaboration concludedCollaboration) throws LogicException;

    /**
     *
     * @param concludedCollaboration
     * @return
     * @throws LogicException
     */
    public int updateVisibility(ConcludedCollaboration concludedCollaboration) throws LogicException;

    /**
     *
     * @param concludedCollaboration
     * @return
     * @throws LogicException
     */
    public int updateRating(ConcludedCollaboration concludedCollaboration) throws LogicException;

    /**
     *
     * @param concludedCollaboration
     * @return
     * @throws LogicException
     */
    public int uploadCertificates(ConcludedCollaboration concludedCollaboration) throws LogicException;

    /**
     *
     * @param concludedCollaboration
     * @param outputPath
     * @return
     * @throws LogicException
     */
    public int obtainCertificates(ConcludedCollaboration concludedCollaboration, String outputPath) throws LogicException;

    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    public boolean hasCertificatesUploaded(int idCollaboration) throws LogicException;

    /**
     *
     * @return
     * @throws LogicException
     */
    public ArrayList<ConcludedCollaboration> getConcludedCollaborations() throws LogicException;
    
    /**
     *
     * @return
     * @throws LogicException
     */
    public ArrayList<ConcludedCollaboration> getConcludedCollaborationsByVisibility() throws LogicException;

    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    public ConcludedCollaboration getConcludedCollaborationById(int idCollaboration) throws LogicException;
}
