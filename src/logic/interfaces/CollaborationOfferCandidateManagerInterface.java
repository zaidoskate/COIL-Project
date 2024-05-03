/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.CollaborationOfferCandidate;

/**
 *
 * @author chima
 */
public interface CollaborationOfferCandidateManagerInterface {
    int InsertCollaborationOfferCandidate(CollaborationOfferCandidate collaborationOfferCandidate) throws LogicException;
    boolean professorHasAppliedForOffer(int idUser, int idCollaborationOffer) throws LogicException;
    ArrayList<CollaborationOfferCandidate> GetCollaborationOfferCandidateByIdCollaborationOffer(int idCollaboration) throws LogicException;
    int deleteCollaborationOffer(int idOfferCollaboration) throws LogicException;
}
