package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.Evidence;

/**
 *
 * @author chuch
 */
public interface EvidenceManagerInterface {

    /**
     *
     * @param evidence
     * @return
     * @throws logic.LogicException
     */
    public int uploadEvidence(Evidence evidence) throws LogicException ;
    /**
     *
     * @param evidence
     * @param outputPath
     * @return
     * @throws logic.LogicException
     */
    public int obtainEvidence(Evidence evidence, String outputPath) throws LogicException;

    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    public ArrayList<Evidence> getAllEvidencesByIdCollaboration(int idCollaboration) throws LogicException;

    /**
     *
     * @param idCollaboration
     * @return
     * @throws LogicException
     */
    public ArrayList<Evidence> getEvidencesByIdFolder(int idCollaboration) throws LogicException;

    /**
     *
     * @param name
     * @return
     * @throws LogicException
     */
    public int deleteEvidenceByName(String name) throws LogicException;
}
