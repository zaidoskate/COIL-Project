package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.Department;
public interface DepartmentManagerInterface {
    public ArrayList<String> getRegionsNames() throws LogicException;
    public ArrayList<Department> getDepartmentsByRegion(String region) throws LogicException;
}
