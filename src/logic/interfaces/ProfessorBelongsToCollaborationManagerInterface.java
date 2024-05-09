package logic.interfaces;

import logic.domain.ProfessorBelongsToCollaboration;
import logic.LogicException;
        
public interface ProfessorBelongsToCollaborationManagerInterface {
    int addProfessorBelongsToCollaboration(ProfessorBelongsToCollaboration belongs) throws LogicException;
    int deleteProfessorBelongsToCollaborationByIdCollaboration(int idCollaboration) throws LogicException;
    int setStartedStatusToCollaboration(int idCollaboration) throws LogicException;
    ProfessorBelongsToCollaboration getProfessorPendingCollaboration(int idUser) throws LogicException;
}
