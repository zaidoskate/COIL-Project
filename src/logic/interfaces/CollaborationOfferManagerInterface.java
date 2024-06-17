package logic.interfaces;

import logic.domain.CollaborationOffer;
import java.util.ArrayList;
import logic.LogicException;

public interface CollaborationOfferManagerInterface {
    int insertColaborationOffer(CollaborationOffer colaborationOffer) throws LogicException;
    int deleteCollaborationOffer(int idCollaborationOffer) throws LogicException;
    int evaluateCollaborationOffer(int idCollaborationOffer, String decision) throws LogicException;
    ArrayList<CollaborationOffer> getApprovedCollaborationOffer() throws LogicException;
    ArrayList<CollaborationOffer> getUnapprovedCollaborationOffer() throws LogicException;
    CollaborationOffer getProfessorApprovedOffer(int idUser) throws LogicException;
    boolean professorHasOffer(int idUser) throws LogicException;
}
