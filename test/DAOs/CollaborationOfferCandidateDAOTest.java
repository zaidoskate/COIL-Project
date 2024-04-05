/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOs;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.*;
import logic.domain.CollaborationOfferCandidate;
import logic.DAOs.CollaborationOfferCandidateDAO;

/**
 *
 * @author chima
 */
public class CollaborationOfferCandidateDAOTest {
    
    public CollaborationOfferCandidateDAOTest() {
    }

    @Test
    public void testInsertCollaborationOfferCandidateSuccess() {
        CollaborationOfferCandidateDAO collaborationOfferCandidateDAO = new CollaborationOfferCandidateDAO();
        CollaborationOfferCandidate collaborationOfferCandidate = new CollaborationOfferCandidate();
        collaborationOfferCandidate.setIdCollaboration(12345);
        collaborationOfferCandidate.setIdUser(54321);
        
        int currentResult = collaborationOfferCandidateDAO.InsertCollaborationOfferCandidate(collaborationOfferCandidate);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
}
