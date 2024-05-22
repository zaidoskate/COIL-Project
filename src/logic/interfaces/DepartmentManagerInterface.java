package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.Department;

/**
 *
 * @author chuch
 */
public interface DepartmentManagerInterface {

    /**
     *
     * @return
     * @throws LogicException
     */
    public ArrayList<String> getRegionsNames() throws LogicException;

    /**
     *
     * @param region
     * @return
     * @throws LogicException
     */
    public ArrayList<Department> getDepartmentsByRegion(String region) throws LogicException;
}
