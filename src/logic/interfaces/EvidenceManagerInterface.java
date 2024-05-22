package logic.interfaces;

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
    public int obtainEvidence(Evidence evidence, String outputPath);
}
