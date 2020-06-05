package Request;

import Server.SessionToken;

import java.io.Serializable;

/**
 * The type Get user privileges request.
 */
public class GetUserPrivilegesRequest extends Request implements Serializable {
    private String username;

    /**
     * Instantiates a new Get user privileges request.
     *
     * @param username the username
     * @param token    the token
     */
    public GetUserPrivilegesRequest(String username, SessionToken token) {
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