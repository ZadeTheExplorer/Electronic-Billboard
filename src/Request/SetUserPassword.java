package Request;

import Server.SessionToken;

import java.io.Serializable;

/**
 * The type Set user password.
 */
public class SetUserPassword extends Request implements Serializable {
    private String username;
    private String password;

    /**
     * Instantiates a new Set user password.
     *
     * @param username the username
     * @param password the password
     * @param token    the token
     */
    public SetUserPassword(String username, String password, SessionToken token){
        super(token);
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
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }
}
