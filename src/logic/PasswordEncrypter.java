package logic;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncrypter {
    
     
    public static String hashAPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
        byte[] result = messageDigest.digest(password.getBytes());
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            stringBuffer.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return stringBuffer.toString();
    }
    
    public static boolean verifyTwoPasswords(String inputPassword, String hashedPassword) throws NoSuchAlgorithmException {
        String inputHashedPassword = hashAPassword(inputPassword);
        return inputHashedPassword.equals(hashedPassword);
    }
}
