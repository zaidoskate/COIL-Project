package logic.interfaces;

import java.util.ArrayList;
import logic.domain.ConcludedCollaboration;

public interface ConcludedCollaborationManagerInterface {
    public int addConcludedCollaboration(ConcludedCollaboration concludedCollaboration);
    public int updateVisibility(ConcludedCollaboration concludedCollaboration);
    public int updateRating(ConcludedCollaboration concludedCollaboration);
    public int uploadCertificates(ConcludedCollaboration concludedCollaboration);
    public int obtainCertificates(ConcludedCollaboration concludedCollaboration, String outputPath);
}
