package DAOs;

import logic.LogicException;
import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.University;
import logic.DAOs.UniversityDAO;

public class UniversityDAOTest {
    
    public UniversityDAOTest(){
    }
    
    @Test
    public void testInsertUniversitySuccess() {
        University university = new University();
        university.setName("Universidad Autonoma de Mexico");
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
        university.setUniversityId(2);
        university.setCountry("Mexico");
        
        UniversityDAO universityDAO = new UniversityDAO();
        int result = universityDAO.insertUniversity(university);
        
        assertEquals(0, result);
    }
}
