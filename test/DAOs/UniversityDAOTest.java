package DAOs;

import java.util.ArrayList;
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
    
    @Test
    public void testGetUniversitiesSuccess() throws LogicException {
        ArrayList<University> universitiesExpected = new ArrayList<>();
        University university;
        university = new University();
        university.setUniversityId(1);
        university.setName("UNAM");
        university.setCountry("Mexico");
        universitiesExpected.add(university);
        university = new University();
        university.setUniversityId(2);
        university.setName("BUAP");
        university.setCountry("Mexico");
        universitiesExpected.add(university);
        university = new University();
        university.setUniversityId(3);
        university.setName("Harvard University");
        university.setCountry("Estados Unidos");
        universitiesExpected.add(university);
        university = new University();
        university.setUniversityId(4);
        university.setName("Universidad Noch");
        university.setCountry("Nochistlan");
        universitiesExpected.add(university);
        
        UniversityDAO universityDAO = new UniversityDAO();
        ArrayList<University> universitiesResult;
        universitiesResult = universityDAO.getUniversities();
        
        assertEquals(universitiesExpected, universitiesResult);
    }
}
