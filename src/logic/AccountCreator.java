package logic;

import gui.SessionManager;
import logic.domain.UvAccountRequest;
import logic.domain.ExternalAccountRequest;
import logic.DAOs.UvAccountRequestDAO;
import logic.DAOs.CredentialDAO;
import logic.DAOs.ExternalAccountRequestDAO;
import logic.DAOs.ExternalProfessorDAO;
import logic.DAOs.PendingMailDAO;
import logic.DAOs.UserDAO;
import logic.DAOs.ProfessorDAO;
import logic.DAOs.UvProfessorDAO;
import logic.domain.AccountRequest;
import logic.domain.Credential;
import logic.domain.ExternalProfessor;
import logic.domain.PendingMail;
import logic.domain.Professor;
import logic.domain.User;
import logic.domain.UvProfessor;

public class AccountCreator {
    private static final SessionManager currentSession = SessionManager.getInstance();
    private static final PendingMailDAO PENDING_MAIL_DAO = new PendingMailDAO();
    private static final UserDAO USER_DAO = new UserDAO();
    private static final CredentialDAO CREDENTIAL_DAO = new CredentialDAO();
    private static final UvAccountRequestDAO UV_ACCOUNT_REQUEST_DAO = new UvAccountRequestDAO();
    private static final ExternalAccountRequestDAO EXTERNAL_ACCOUNT_REQUEST_DAO = new ExternalAccountRequestDAO();
    private static final UvProfessorDAO UV_PROFESSOR_DAO = new UvProfessorDAO();
    private static final ProfessorDAO PROFESSOR_DAO = new ProfessorDAO();
    private static final ExternalProfessorDAO EXTERNAL_PROFESSOR_DAO = new ExternalProfessorDAO();
    
    public static boolean createUVAccount(UvAccountRequest uvaccountRequest) throws LogicException {
        String username = CredentialGenerator.generateUser(uvaccountRequest.getName(), uvaccountRequest.getLastName());
        String password = CredentialGenerator.generatePassword();
        int idUser = registerUser(uvaccountRequest);
        registerCredential(username, password , idUser);
        registerProfessor(idUser);
        registerUvProfessor(uvaccountRequest, idUser);
        deleteAccountRequestUv(uvaccountRequest);
        if (sendCredential(username, password, uvaccountRequest.getEmail())) {
            return true;
        } else {
            return false;
        }
    }
    public static boolean createExternalAccount(ExternalAccountRequest externalAccountRequest) throws LogicException {
        String username = CredentialGenerator.generateUser(externalAccountRequest.getName(), externalAccountRequest.getLastName());
        String password = CredentialGenerator.generatePassword();
        int idUser = registerUser(externalAccountRequest);
        registerCredential(username, password , idUser);
        registerProfessor(idUser);
        registerExternalProfessor(externalAccountRequest, idUser);
        deleteAccountRequestExternal(externalAccountRequest);
        if (sendCredential(username, password, externalAccountRequest.getEmail())) {
            return true;
        } else {
            return false;
        }
    }
    
    private static boolean sendCredential(String username, String password, String email) throws LogicException {
        String body;
        body = "Te damos la bienvenida al Sistema COIL-VIC de la Universidad Veracruzana."
                + "\nTus datos de acceso son los siguientes:"
                + "\nUsuario: "+username
                + "\nContrasena: "+password;
        
        boolean result = false;
        try {
            result = MailSender.sendEmail(body, email);
        } catch (LogicException logicException) {
            savePendingMail(body, email);
            throw new LogicException(logicException.getMessage(), logicException);
        }
        return result;
    }

    private static void savePendingMail(String body, String email) throws LogicException{
        PendingMail pendingMail = new PendingMail();
        pendingMail.setContent(body);
        pendingMail.setDestinationEmail(email);
        pendingMail.setSubject("Cuenta de acceso Profesor");
        pendingMail.setIdUser(currentSession.getUserData().getIdUser());
        
        PENDING_MAIL_DAO.insertPendingMail(pendingMail);
    }
    
    private static int registerUser(AccountRequest accountRequest) throws LogicException {
        User user = new User();
        user.setName(accountRequest.getName());
        user.setLastName(accountRequest.getLastName());
        user.setEmail(accountRequest.getEmail());
        
        int idUser = USER_DAO.addUser(user);
        return idUser;
    }

    private static void registerCredential(String username, String password, int idUser) throws LogicException {
        Credential credential = new Credential();
        credential.setIdUser(idUser);
        credential.setUser(username);
        credential.setPassword(password);
        
        CREDENTIAL_DAO.insertCredential(credential);
    }

    private static void registerProfessor(int idUser) throws LogicException {
        Professor professor = new Professor();
        professor.setIdUser(idUser);
        PROFESSOR_DAO.insertProfessor(professor);
    }

    private static void registerUvProfessor(UvAccountRequest uvaccountRequest, int idUser) throws LogicException {
        UvProfessor uvProfessor = new UvProfessor();
        uvProfessor.setIdUser(idUser);
        uvProfessor.setIdDepartment(uvaccountRequest.getIdDepartment());
        uvProfessor.setPersonalNumber(uvaccountRequest.getPersonalNumber());
        
        UV_PROFESSOR_DAO.insertUvProfessor(uvProfessor);
    }
    
    private static void registerExternalProfessor(ExternalAccountRequest externalAccountRequest, int idUser) throws LogicException {
        ExternalProfessor externalProfessor = new ExternalProfessor();
        externalProfessor.setIdUser(idUser);
        externalProfessor.setIdUniversity(externalAccountRequest.getIdUniversity());
        
        EXTERNAL_PROFESSOR_DAO.insertExternalProfessor(externalProfessor);
    }

    private static void deleteAccountRequestUv(UvAccountRequest uvaccountRequest) throws LogicException{
        UV_ACCOUNT_REQUEST_DAO.deleteUvAccountRequest(uvaccountRequest);
    }
    private static void deleteAccountRequestExternal(ExternalAccountRequest externalAccountRequest) throws LogicException{
        EXTERNAL_ACCOUNT_REQUEST_DAO.deleteExternalAccountRequest(externalAccountRequest);
    }
    
}
