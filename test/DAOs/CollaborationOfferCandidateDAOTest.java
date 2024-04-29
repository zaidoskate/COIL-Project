/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DAOs;

import org.junit.Test;
import dataaccess.DatabaseConnection;
import java.util.ArrayList;
import static org.junit.Assert.*;
import static org.junit.Assert.*;
import logic.domain.CollaborationOfferCandidate;
import logic.DAOs.CollaborationOfferCandidateDAO;
import logic.LogicException;

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
        
        try {
            int currentResult = collaborationOfferCandidateDAO.InsertCollaborationOfferCandidate(collaborationOfferCandidate);
            int expectedResult = 1;
            assertEquals(expectedResult, currentResult);
        } catch(LogicException logicException) {
            fail("Error al insertar el candidato");
        }
    }
    
    @Test
    public void testProfessorHasAppliedForOfferSuccess() {
        CollaborationOfferCandidateDAO collaborationOfferCandidateDAO = new CollaborationOfferCandidateDAO();
        try {
            boolean result = collaborationOfferCandidateDAO.professorHasAppliedForOffer(2, 3);
            assertTrue(result);
        } catch (LogicException logicException) {
            fail("Error al saber si un profesor ya se postulo a esa oferta");
        }
    }
    
    @Test
    public void testGetCollaborationOfferCandidateByIdCollaborationOfferSuccess() {
        CollaborationOfferCandidate expectedCollaborationOfferCandidate = new CollaborationOfferCandidate();
        expectedCollaborationOfferCandidate.setIdCollaboration(3);
        expectedCollaborationOfferCandidate.setIdUser(1);
        ArrayList<CollaborationOfferCandidate> candidatesExpected = new ArrayList<>();
        candidatesExpected.add(expectedCollaborationOfferCandidate);
        
        CollaborationOfferCandidateDAO collaborationOfferCandidateDAO = new CollaborationOfferCandidateDAO();
        try {
            ArrayList<CollaborationOfferCandidate> candidatesObtained = collaborationOfferCandidateDAO.GetCollaborationOfferCandidateByIdCollaborationOffer(3);
            assertEquals(candidatesExpected, candidatesObtained);
        } catch(LogicException logicException) {
            fail("Error al recuperar los candidatos de la oferta");
        }
        
    }
}
