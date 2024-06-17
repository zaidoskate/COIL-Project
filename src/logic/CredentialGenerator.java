package logic;

import java.security.SecureRandom;
import java.text.Normalizer;
import logic.DAOs.CredentialDAO;

public class CredentialGenerator {
    private static final CredentialDAO CREDENTIAL_DAO = new CredentialDAO();
    
    public static String generateUser(String name, String lastname) throws LogicException {
        name = removeAccents(name);
        lastname = removeAccents(lastname);
        char firstLetter = name.charAt(0);
        String firstLastname = lastname.split(" ")[0];
        
        String user = ("" + firstLetter + firstLastname).toLowerCase();
        
        while (validateUserExistence(user) == 1) {
            user += "1";
        }
        
        return user;
    }
    
    public static String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 8;
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i=0; i<length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }
    
    private static int validateUserExistence(String user) throws LogicException{
        return CREDENTIAL_DAO.countCredentialsByUser(user);
    }
    
    private static String removeAccents(String word) {
        return Normalizer.normalize(word, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
