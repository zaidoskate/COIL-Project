package DAOs.insertTests;

import logic.DAOs.CoordinatorDAO;
import logic.domain.Coordinator;
import logic.LogicException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class CoordinatorDAOTest {
    
    @Test
    public void testInsertCoordinatorSuccess() {
        Coordinator coordinator = new Coordinator();
        coordinator.setIdUser(7);

        CoordinatorDAO coordinatorDAO = new CoordinatorDAO();
        int expectedResult = 1;
        try {
            int result = coordinatorDAO.insertCoordinator(coordinator);
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("No se ha podido agregar al coordinador");
        }
    }
}
