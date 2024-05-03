package logic.interfaces;

import logic.domain.ProfessorBelongsToCollaboration;
import logic.domain.Collaboration;
import java.util.ArrayList;
import logic.LogicException;
import logic.domain.Professor;
        
public interface ProfessorBelongsToCollaborationManagerInterface {
    int addProfessorBelongsToCollaboration(ProfessorBelongsToCollaboration belongs) throws LogicException ;
    int deleteProfessorBelongsToCollaborationByIdCollaboration(int idCollaboration) throws LogicException ;
    int setStartedStatusToCollaboration(int idCollaboration) throws LogicException;
    ProfessorBelongsToCollaboration getProfessorPendingCollaboration(int idUser) throws LogicException;
}
