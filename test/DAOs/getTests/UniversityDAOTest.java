package DAOs.getTests;

import java.util.ArrayList;
import logic.DAOs.UniversityDAO;
import logic.LogicException;
import logic.domain.University;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

public class UniversityDAOTest {
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
    
    @Test
    public void testCheckUniversityRegisteredSuccess() {
        UniversityDAO universityDAO = new UniversityDAO();
        boolean expectedResult = false;
        try {
            boolean result = universityDAO.checkUniversityRegistered("Michigan College");
            assertEquals(expectedResult,result);
        } catch(LogicException logicException) {
            fail("No se ha podido consultar si la universidad ya esta registrada");
        }
    }
}
