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
    
    @Test
    public void testGetUniversitiesSuccess() throws LogicException {
        ArrayList<University> universitiesExpected = new ArrayList<>();
        University university;
        university = new University();
        university.setUniversityId(1);
        university.setName("Universidad Nacional Autónoma de México");
        university.setCountry("México");
        universitiesExpected.add(university);
        university = new University();
        university.setUniversityId(2);
        university.setName("Instituto Politécnico Nacional");
        university.setCountry("México");
        universitiesExpected.add(university);
        university = new University();
        university.setUniversityId(3);
        university.setName("Benemerita Universidad Autónoma de Puebla");
        university.setCountry("México");
        universitiesExpected.add(university);
        university = new University();
        university.setUniversityId(4);
        university.setName("Universidad de Lima");
        university.setCountry("Perú");
        universitiesExpected.add(university);
        university = new University();
        university.setUniversityId(5);
        university.setName("Universidad la Gran Colombia");
        university.setCountry("Colombia");
        universitiesExpected.add(university);
        
        UniversityDAO universityDAO = new UniversityDAO();
        ArrayList<University> universitiesResult;
        universitiesResult = universityDAO.getUniversities();
        
        assertEquals(universitiesExpected, universitiesResult);
    }
}
