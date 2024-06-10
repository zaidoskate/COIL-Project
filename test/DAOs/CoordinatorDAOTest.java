package DAOs;

import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.Coordinator;
import logic.DAOs.CoordinatorDAO;
import logic.LogicException;

public class CoordinatorDAOTest {
    
    public CoordinatorDAOTest() {
    }

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
    
