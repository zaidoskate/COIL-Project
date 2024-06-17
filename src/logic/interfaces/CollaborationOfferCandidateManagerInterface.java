package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.CollaborationOfferCandidate;

public interface CollaborationOfferCandidateManagerInterface {
    int InsertCollaborationOfferCandidate(CollaborationOfferCandidate collaborationOfferCandidate) throws LogicException;
    int deleteCollaborationOffer(int idOfferCollaboration) throws LogicException;
    boolean professorHasAppliedForOffer(int idUser, int idCollaborationOffer) throws LogicException;
    ArrayList<CollaborationOfferCandidate> GetCollaborationOfferCandidateByIdCollaborationOffer(int idCollaboration) throws LogicException;
}
