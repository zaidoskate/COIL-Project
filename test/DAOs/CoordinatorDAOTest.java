/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOs;

import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.Coordinator;
import logic.DAOs.CoordinatorDAO;
import logic.LogicException;

/**
 *
 * @author chima
 */
public class CoordinatorDAOTest {
    
    public CoordinatorDAOTest() {
    }

    @Test
    public void testInsertCoordinatorSuccess() {
        Coordinator coordinator = new Coordinator();
        coordinator.setIdCoordinator(13579);
        coordinator.setIdUser(54321);

        CoordinatorDAO coordinatorDAO = new CoordinatorDAO();
        try {
            int result = coordinatorDAO.insertCoordinator(coordinator);
            assertNotEquals(0, result);
        } catch(LogicException logicException) {
            fail("No se ha podido agregar al coordinador");
        }
    }
    
    @Test
    public void testGetIdCoordinatorByIdUserSuccess() {
        int expectedResult = 1;
        
        CoordinatorDAO coordinatorDAO = new CoordinatorDAO();
        try {
            int result = coordinatorDAO.getIdCoordinatorByIdUser(3);
            assertEquals(expectedResult, result);
        } catch(LogicException logicException) {
            fail("Error al recuperar el id coordinador");
        }
    }
    
}
    
