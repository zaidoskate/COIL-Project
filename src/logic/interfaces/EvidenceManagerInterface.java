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
     */
    public int uploadEvidence(Evidence evidence);
    /**
     *
     * @param evidence
     * @param outputPath
     * @return
     */
    public int obtainEvidence(Evidence evidence, String outputPath) throws LogicException;
    public ArrayList<Evidence> getAllEvidencesByIdCollaboration(int idCollaboration) throws LogicException;

}
