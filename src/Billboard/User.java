package Billboard;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class User {
    private String userName;
    private ArrayList<String> permission;
    private String privilege;
    private String salt;
    private String saltPass;


    public User(String name, String password, ArrayList<String> permission, String privilege) throws Exception{
        userName = name;
        this.salt = createSalt();
        String hashPass = hashedPassword(password);
        saltPass = saltedPassword(hashPass,salt);
        this.permission = new ArrayList<>(permission.size());
        this.permission.addAll(permission);
        this.privilege = privilege;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSalt(){return salt;}

    public String getSaltPass(){return saltPass;}

    public ArrayList<String> getPermission() {
        if(privilege.toUpperCase().compareTo("CREATE BILLBOARDS") == 0){

        }
        else if(privilege.toUpperCase().compareTo("EDIT ALL BILLBOARDS") == 0){

        }
        else if(privilege.toUpperCase().compareTo("SCHEDULE BILLBOARD") == 0){

        }
        else if(privilege.toUpperCase().compareTo("EDIT USER") == 0){

        }
        return permission;
    }

    public void setPermission(ArrayList<String> permission) {
        this.permission = permission;
    }

    public String getPrivilege() {
        return privilege;
    }

    /* To update the user's privilege
    * @param acquired privilege
    */
    public void setPrivilege(String privilege) {
        this.privilege = privilege;
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
