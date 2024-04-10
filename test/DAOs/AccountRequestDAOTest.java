package DAOs;

import dataaccess.DatabaseConnection;
import logic.DAOs.AccountRequestDAO;
import logic.domain.AccountRequest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AccountRequestDAOTest {
    public AccountRequestDAOTest() {
    }
    
    @Test
    public void TestAddAccountRequestSuccess(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        AccountRequestDAO accountRequestDAO = new AccountRequestDAO(databaseConnection);
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setAcademicArea("Humanidades");
        accountRequest.setEmail("hola@ejemplo.com");
        accountRequest.setLastname("apellido");
        accountRequest.setName("nombre");
        
        int currentResult = accountRequestDAO.addAccountRequest(accountRequest);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }
    
    @Test
    public void TestDeleteAccountRequestByIdSuccess(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        AccountRequestDAO accountRequestDAO = new AccountRequestDAO(databaseConnection);
        int currentResult = accountRequestDAO.deleteAccountRequestById(1);
        int expectedResult = 1;
        assertEquals(expectedResult, currentResult);
    }    
    @Test

    public void TestgetAccountRequestByIdSuccess(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        AccountRequestDAO accountRequestDAO = new AccountRequestDAO(databaseConnection);
        
        AccountRequest currentResult = accountRequestDAO.getAccountRequestById(2);
        
        AccountRequest expectedResult = new AccountRequest();
        expectedResult.setIdAccountRequest(2);
        expectedResult.setAcademicArea("Humanidades");
        expectedResult.setName("nombre");
        expectedResult.setEmail("hola@ejemplo");
        expectedResult.setLastname("apellido");
        
        assertEquals(expectedResult, currentResult);
    }  
}
