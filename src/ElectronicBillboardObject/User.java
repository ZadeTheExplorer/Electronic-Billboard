package ElectronicBillboardObject;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type User.
 */
public class User implements Serializable {
    private String userName;
    private ArrayList<String> privileges;
    private String salt;
    private String saltPass;

    /**
     * Instantiates a new User.
     *
     * @param name       the name
     * @param password   the password
     * @param privileges the privileges
     * @throws Exception the exception
     */
    public User(String name, String password, ArrayList<String> privileges) throws Exception{
        userName = name;
        this.salt = createSalt();
        String hashPass = hashedPassword(password);
        saltPass = saltedPassword(hashPass,salt);
        this.privileges = new ArrayList<>(privileges.size());
        this.privileges.addAll(privileges);
    }

    /**
     * Gets privileges.
     *
     * @return the privileges
     */
    public ArrayList<String> getPrivileges() {
        return privileges;
    }

    /**
     * Sets salt.
     *
     * @param salt the salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * Sets salt pass.
     *
     * @param saltPass the salt pass
     */
    public void setSaltPass(String saltPass) {
        this.saltPass = saltPass;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get salt string.
     *
     * @return the string
     */
    public String getSalt(){return salt;}

    /**
     * Get salt pass string.
     *
     * @return the string
     */
    public String getSaltPass(){return saltPass;}

    /**
     * Sets privileges.
     *
     * @param permission the permission
     */
    public void setPrivileges(ArrayList<String> permission) {
        this.privileges = privileges;
    }

    /**
     * Gets privilege.
     *
     * @return the privilege
     */
    public StringBuilder getPrivilege() {
        StringBuilder s = new StringBuilder();
        String prefix = "";
        for (String privilege : privileges ){
            s.append(prefix).append(privilege);
            prefix = ", ";
        }
        return s;
    }

    /**
     * Sets privilege.
     *
     * @param privileges the privileges
     */
    /* To update the user's privilege
    * @param acquired privilege
    */
    public void setPrivilege(ArrayList<String> privileges) {
        this.privileges = privileges;
    }

    /**
     * Hashed password string.
     *
     * @param inputPassword the input password
     * @return the string
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
//create a new hashed password
    public static String hashedPassword (String inputPassword) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hashedPassword = md.digest(inputPassword.getBytes());

        return byteToString(hashedPassword);
    }

    /**
     * Create salt string.
     *
     * @return the string
     */
// create a new salt
    public static String createSalt(){
        Random rng = new Random();
        byte[] saltBytes = new byte[32];
        rng.nextBytes(saltBytes);
        return byteToString(saltBytes);
    }

    /**
     * Salted password string.
     *
     * @param hashedPassword the hashed password
     * @param salt           the salt
     * @return the string
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
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
