package DAOs.insertTests;

import logic.DAOs.UniversityDAO;
import logic.LogicException;
import logic.domain.University;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class UniversityDAOTest {
    
    @Test
    public void testInsertUniversitySuccess() {
        University university = new University();
        university.setName("Universidad Autónoma de Nuevo León");
        university.setCountry("Mexico");
        
        UniversityDAO universityDAO = new UniversityDAO();
        try {
            int result = universityDAO.insertUniversity(university);
            assertEquals(1, result);
        } catch (LogicException logicException) {
            fail("No se ha podido insertar la universidad" + logicException.getMessage());
        }
        
    }
    
    @Test(expected = LogicException.class)
    public void testInsertUniversityFailed() throws LogicException {
        University university = new University();
        
        UniversityDAO universityDAO = new UniversityDAO();
        int result = universityDAO.insertUniversity(university);
        
        assertEquals(0, result);
    }
}
