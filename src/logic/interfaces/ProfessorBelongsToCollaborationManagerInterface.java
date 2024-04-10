package logic.interfaces;

import logic.domain.ProfessorBelongsToCollaboration;
import logic.domain.Professor;
import logic.domain.Collaboration;
import java.util.ArrayList;
        
public interface ProfessorBelongsToCollaborationManagerInterface {
    int addBelongs(ProfessorBelongsToCollaboration belongs);
    ArrayList<Collaboration> getColaborationsByProfessor(Professor professor);
    Professor getProfessorByIdCollaboration(String idCollaboration);
    Professor getMirrorProfessorByIdCollaboration(String idCollaboration);
    
}
