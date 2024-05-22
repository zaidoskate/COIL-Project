package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.Professor;

/**
 *
 * @author chuch
 */
public interface ProfessorManagerInterface {

    /**
     *
     * @param professor
     * @return
     * @throws LogicException
     */
    public int insertProfessor(Professor professor) throws LogicException;

    /**
     *
     * @param idUser
     * @return
     * @throws LogicException
     */
    public ArrayList<String> getUniversityFromAProfessor(int idUser) throws LogicException;
}
