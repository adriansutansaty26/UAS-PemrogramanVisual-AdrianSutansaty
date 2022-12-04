package contactmanagement.utilities;

import java.util.Random;

public class StringUtils {
    public static String randomString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        
        while (salt.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        
        String saltStr = salt.toString();
        return saltStr;
    }
    
    public static String randomString(int length, String saltCharacter) {
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        
        while (salt.length() < length) {
            int index = (int) (rnd.nextFloat() * saltCharacter.length());
            salt.append(saltCharacter.charAt(index));
        }
        
        String saltStr = salt.toString();
        return saltStr;
    }
}
