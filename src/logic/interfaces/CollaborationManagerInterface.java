package logic.interfaces;

import logic.domain.Collaboration;
import logic.LogicException;
import java.util.ArrayList;
import logic.domain.ProfessorBelongsToCollaboration;

public interface CollaborationManagerInterface {

    int addColaboration(Collaboration colaboration) throws LogicException;
    int startCollaboration(int idCollaboration) throws LogicException;
    int concludeCollaboration(int idCollaboration) throws LogicException;
    int updateEndDateByIdCollaboration(int idCollaboration) throws LogicException;
    Collaboration getColaborationById(int idCollaboration) throws LogicException;
    ArrayList<Collaboration> getAllCollaborations() throws LogicException;
    ArrayList<Collaboration> getActiveCollaborations() throws LogicException;
    ArrayList<Collaboration> getProfessorConcludedCollaborations(ArrayList<ProfessorBelongsToCollaboration> professorCollaborations) throws LogicException;
}
