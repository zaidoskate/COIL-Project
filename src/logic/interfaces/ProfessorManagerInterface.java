package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.Professor;
public interface ProfessorManagerInterface {
    public int insertProfessor(Professor professor) throws LogicException;
    public ArrayList<String> getUniversityFromAProfessor(int idUser) throws LogicException;
}
