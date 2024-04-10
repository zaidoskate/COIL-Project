/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import org.junit.Test;
import static org.junit.Assert.*;
import logic.domain.University;
import logic.DAOs.UniversityDAO;

/**
 *
 * @author zaido
 */
public class UniversityDAOTest {
    
    public UniversityDAOTest(){
    }
    
    @Test
    public void testInsertUniversitySuccess() {
        University university = new University();
        university.setUniversityId(2);
        university.setName("Universidad Autonoma de Mexico");
        university.setCountry("Mexico");
        university.setRepresentativeName("Enrique");
        university.setRepresentativeLastName("Graue Wiechers");
        university.setRepresentativeEmail("enriqueGraue@hotmail.com");
        university.setRepresentativePhone("5501992424");
        
        UniversityDAO universityDAO = new UniversityDAO();
        int result = universityDAO.insertUniversity(university);
        
        assertNotEquals(-1, result);
    }
}
