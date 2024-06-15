package DAOs.getTests;

import logic.DAOs.CoordinatorDAO;
import logic.LogicException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class CoordinatorDAOTest {
    
    @Test
    public void testGetIdCoordinatorByIdUserSuccess() {
        int expectedResult = 1;
        
        CoordinatorDAO coordinatorDAO = new CoordinatorDAO();
        try {
            int result = coordinatorDAO.getIdCoordinatorByIdUser(2);
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("Error al recuperar el id coordinador");
        }
    }
}
