package logic.interfaces;

import java.util.ArrayList;
import logic.domain.ProfessorBelongsToCollaboration;
import logic.LogicException;
import logic.domain.User;
        
public interface ProfessorBelongsToCollaborationManagerInterface {
    int addProfessorBelongsToCollaboration(ProfessorBelongsToCollaboration belongs) throws LogicException;
    int deleteProfessorBelongsToCollaborationByIdCollaboration(int idCollaboration) throws LogicException;
    int setStatusToCollaboration(int idCollaboration, String status) throws LogicException;
    ProfessorBelongsToCollaboration getProfessorPendingCollaboration(int idUser) throws LogicException;
    ArrayList<ProfessorBelongsToCollaboration> getOnHoldCollaborations() throws LogicException;
    ArrayList<ProfessorBelongsToCollaboration> getConcludedCollaborationsByIdUser(int idUser) throws LogicException;
    public String getEmailProfessorByIdCollaboration(int idCollaboration) throws LogicException;
    ArrayList<User> getProfessorsDataByCollaboration(int idCollaboration) throws LogicException;
    public String getStatusByIdCollaboration(int idCollaboration) throws LogicException;
}
