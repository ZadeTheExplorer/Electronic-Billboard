package Billboard;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

public class User {
    private String userName;
    private ArrayList<String> privileges;
    private String salt;
    private String saltPass;

    public User(String name, String password, ArrayList<String> privileges) throws Exception{
        userName = name;
        this.salt = createSalt();
        String hashPass = hashedPassword(password);
        saltPass = saltedPassword(hashPass,salt);
        this.privileges = new ArrayList<>(privileges.size());
        this.privileges.addAll(privileges);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSalt(){return salt;}

    public String getSaltPass(){return saltPass;}



    public void setPrivileges(ArrayList<String> permission) {
        this.privileges = privileges;
    }

    public StringBuilder getPrivilege() {
        StringBuilder s = new StringBuilder();
        String prefix = "";
        for (String privilege : privileges ){
            s.append(prefix).append(privilege);
            prefix = ", ";
        }
        return s;
    }

    /* To update the user's privilege
    * @param acquired privilege
    */
    public void setPrivilege(ArrayList<String> privileges) {
        this.privileges = privileges;
    }

    //create a new hashed password
    public static String hashedPassword (String inputPassword) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hashedPassword = md.digest(inputPassword.getBytes());

        return byteToString(hashedPassword);
    }

    // create a new salt
    public static String createSalt(){
        Random rng = new Random();
        byte[] saltBytes = new byte[32];
        rng.nextBytes(saltBytes);
        String saltString = byteToString(saltBytes);

        return saltString;
    }

    //create a new salted password
    public static String saltedPassword (String hashedPassword, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return byteToString(md.digest((hashedPassword + salt).getBytes()));
    }

    // turning byte to string
    private static String byteToString(byte[] hash){
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b & 0xFF));
        }

        return sb.toString();
    }

}
