package logic.interfaces;

import logic.domain.Belongs;
import logic.domain.Professor;
import logic.domain.Colaboration;
import java.util.ArrayList;
        
public interface BelongsManagerInterface {
    ArrayList<Colaboration> getColaborationsByProfessor(Professor professor);
    ArrayList<Professor> getProfessorsByColaboration(Colaboration colaboration);
    int insertBelongs(Belongs belongs);
    
}
