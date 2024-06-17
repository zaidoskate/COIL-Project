package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.Evidence;
public interface EvidenceManagerInterface {
    public int uploadEvidence(Evidence evidence) throws LogicException ;
    public int obtainEvidence(Evidence evidence, String outputPath) throws LogicException;
    public ArrayList<Evidence> getAllEvidencesByIdCollaboration(int idCollaboration) throws LogicException;
    public ArrayList<Evidence> getEvidencesByIdFolder(int idCollaboration) throws LogicException;
    public int deleteEvidenceByName(String name) throws LogicException;
}
