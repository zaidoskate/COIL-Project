package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.ConcludedCollaboration;

public interface ConcludedCollaborationManagerInterface {
    public int addConcludedCollaboration(ConcludedCollaboration concludedCollaboration) throws LogicException;
    public int updateVisibility(ConcludedCollaboration concludedCollaboration) throws LogicException;
    public int updateRating(ConcludedCollaboration concludedCollaboration) throws LogicException;
    public int uploadCertificates(ConcludedCollaboration concludedCollaboration) throws LogicException;
    public int obtainCertificates(ConcludedCollaboration concludedCollaboration, String outputPath) throws LogicException;
    public ArrayList<ConcludedCollaboration> getConcludedCollaborations() throws LogicException;
}
