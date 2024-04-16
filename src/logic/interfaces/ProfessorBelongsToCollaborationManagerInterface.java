package logic.interfaces;

import logic.domain.ProfessorBelongsToCollaboration;
import logic.domain.Collaboration;
import java.util.ArrayList;
import logic.domain.Professor;
        
public interface ProfessorBelongsToCollaborationManagerInterface {
    int addProfessorBelongsToCollaboration(ProfessorBelongsToCollaboration belongs);
    int deleteProfessorBelongsToCollaborationByIdCollaboration(int idCollaboration);
    Professor getMirrorProfessorByIdCollaboration(int idCollaboration);
    ArrayList<Collaboration> getCollaborationsByStatus(String statusCollaboration);
}
