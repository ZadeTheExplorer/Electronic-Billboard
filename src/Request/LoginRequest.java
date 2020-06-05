package Request;

import ElectronicBillboardObject.User;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

/**
 * The type Login request.
 */
public class LoginRequest implements Serializable {
    private String username;
    private String password;

    /**
     * Instantiates a new Login request.
     *
     * @param username the username
     * @param password the password
     */
    public LoginRequest(String username, String password){
        this.username = username;
        this.password = password;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets hash password.
     *
     * @return the hash password
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public String getHashPassword() throws NoSuchAlgorithmException {
        return User.hashedPassword(password);
    }
}
