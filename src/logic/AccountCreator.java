package logic;

import logic.domain.UvAccountRequest;
import logic.domain.ExternalAccountRequest;
import logic.DAOs.UvAccountRequestDAO;
import logic.DAOs.CredentialDAO;
import logic.DAOs.ExternalAccountRequestDAO;
import logic.DAOs.ExternalProfessorDAO;
import logic.DAOs.UserDAO;
import logic.DAOs.ProfessorDAO;
import logic.DAOs.UvProfessorDAO;
import logic.domain.AccountRequest;
import logic.domain.Credential;
import logic.domain.ExternalProfessor;
import logic.domain.Professor;
import logic.domain.User;
import logic.domain.UvProfessor;

public class AccountCreator {
    public static boolean createUVAccount(UvAccountRequest uvaccountRequest) throws LogicException {
        String username = CredentialGenerator.generateUser(uvaccountRequest.getName(), uvaccountRequest.getLastName());
        String password = CredentialGenerator.generatePassword();
        if(sendCredential(username, password, uvaccountRequest.getEmail())) {
            int idUser = registerUser(uvaccountRequest);
            registerCredential(username, password , idUser);
            registerProfessor(idUser);
            registerUvProfessor(uvaccountRequest, idUser);
            deleteAccountRequestUv(uvaccountRequest);
        } else {
            return false;
        }
        return true;
    }
    public static boolean createExternalAccount(ExternalAccountRequest externalAccountRequest) throws LogicException {
        String username = CredentialGenerator.generateUser(externalAccountRequest.getName(), externalAccountRequest.getLastName());
        String password = CredentialGenerator.generatePassword();
        if(sendCredential(username, password, externalAccountRequest.getEmail())) {
            int idUser = registerUser(externalAccountRequest);
            registerCredential(username, password , idUser);
            registerProfessor(idUser);
            registerExternalProfessor(externalAccountRequest, idUser);
            deleteAccountRequestExternal(externalAccountRequest);
        } else {
            return false;
        }
        return true;
    }
    
    private static boolean sendCredential(String username, String password, String email) throws LogicException {
        String body;
        body = "Te damos la bienvenida al Sistema COIL-VIC de la Universidad Veracruzana."
                + "\nTus datos de acceso son los siguientes:"
                + "\nUsuario: "+username
                + "\nContrasena: "+password;
        
        boolean result = MailSender.sendEmail(body, email);
    
        return result;
    }

    private static int registerUser(AccountRequest accountRequest) throws LogicException {
        User user = new User();
        user.setName(accountRequest.getName());
        user.setLastName(accountRequest.getLastName());
        user.setEmail(accountRequest.getEmail());
        
        UserDAO userDAO = new UserDAO();
        
        int idUser = userDAO.addUser(user);
        return idUser;
    }

    private static void registerCredential(String username, String password, int idUser) throws LogicException {
        Credential credential = new Credential();
        credential.setIdUser(idUser);
        credential.setUser(username);
        credential.setPassword(password);
        
        CredentialDAO credentialDAO = new CredentialDAO();
        credentialDAO.insertCredential(credential);
    }

    private static void registerProfessor(int idUser) throws LogicException {
        Professor professor = new Professor();
        professor.setIdUser(idUser);
        ProfessorDAO professorDAO = new ProfessorDAO();
        professorDAO.insertProfessor(professor);
    }

    private static void registerUvProfessor(UvAccountRequest uvaccountRequest, int idUser) throws LogicException {
        UvProfessor uvProfessor = new UvProfessor();
        uvProfessor.setIdUser(idUser);
        uvProfessor.setIdDepartment(uvaccountRequest.getIdDepartment());
        uvProfessor.setPersonalNumber(uvaccountRequest.getPersonalNumber());
        
        UvProfessorDAO uvProfessorDAO = new UvProfessorDAO();
        uvProfessorDAO.insertUvProfessor(uvProfessor);
    }
    
    private static void registerExternalProfessor(ExternalAccountRequest externalAccountRequest, int idUser) throws LogicException {
        ExternalProfessor externalProfessor = new ExternalProfessor();
        externalProfessor.setIdUser(idUser);
        externalProfessor.setIdUniversity(externalAccountRequest.getIdUniversity());
        
        ExternalProfessorDAO externalProfessorDAO = new ExternalProfessorDAO();
        externalProfessorDAO.insertExternalProfessor(externalProfessor);
    }

    private static void deleteAccountRequestUv(UvAccountRequest uvaccountRequest) throws LogicException{
        UvAccountRequestDAO uvAccountRequestDAO = new UvAccountRequestDAO();
        uvAccountRequestDAO.deleteUvAccountRequest(uvaccountRequest);
    }
    private static void deleteAccountRequestExternal(ExternalAccountRequest externalAccountRequest) throws LogicException{
        ExternalAccountRequestDAO externalAccountRequestDAO = new ExternalAccountRequestDAO();
        externalAccountRequestDAO.deleteExternalAccountRequest(externalAccountRequest);
    }
    
}
