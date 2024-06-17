package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.University;

public interface UniversityManagerInterface {
    public int insertUniversity(University university) throws LogicException;
    public ArrayList<University> getUniversities() throws LogicException;
    public boolean checkUniversityRegistered(String universityName) throws LogicException;
}
