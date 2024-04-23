package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.Department;

public interface DepartmentManagerInterface {
    ArrayList<String> getRegionsNames() throws LogicException;
    ArrayList<Department> getDepartmentsByRegion(String region) throws LogicException;
}
