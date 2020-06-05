package Request;

import Server.SessionToken;

import java.io.Serializable;

/**
 * The type Delete user request.
 */
public class DeleteUserRequest extends Request implements Serializable {
    private String username;

    /**
     * Instantiates a new Delete user request.
     *
     * @param username the username
     * @param token    the token
     */
    public DeleteUserRequest(String username, SessionToken token) {
        super(token);
        this.username = username;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }
}
