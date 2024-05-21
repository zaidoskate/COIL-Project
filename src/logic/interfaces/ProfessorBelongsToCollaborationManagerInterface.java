package logic.interfaces;

import java.util.ArrayList;
import logic.domain.ProfessorBelongsToCollaboration;
import logic.LogicException;
        
public interface ProfessorBelongsToCollaborationManagerInterface {
    int addProfessorBelongsToCollaboration(ProfessorBelongsToCollaboration belongs) throws LogicException;
    int deleteProfessorBelongsToCollaborationByIdCollaboration(int idCollaboration) throws LogicException;
    int setStatusToCollaboration(int idCollaboration, String status) throws LogicException;
    ProfessorBelongsToCollaboration getProfessorPendingCollaboration(int idUser) throws LogicException;
    ArrayList<ProfessorBelongsToCollaboration> getOnHoldCollaborations() throws LogicException;
    ArrayList<ProfessorBelongsToCollaboration> getConcludedCollaborationsByIdUser(int idUser) throws LogicException;
}
