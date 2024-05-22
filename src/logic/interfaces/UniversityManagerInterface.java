package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.University;

/**
 *
 * @author chuch
 */
public interface UniversityManagerInterface {

    /**
     *
     * @param university
     * @return
     * @throws LogicException
     */
    public int insertUniversity(University university) throws LogicException;

    /**
     *
     * @return
     * @throws LogicException
     */
    public ArrayList<University> getUniversities() throws LogicException;
}
