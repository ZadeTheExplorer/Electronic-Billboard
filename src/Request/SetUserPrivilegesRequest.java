package Request;

import Server.SessionToken;

import java.io.Serializable;

/**
 * The type Set user privileges request.
 */
public class SetUserPrivilegesRequest extends Request implements Serializable {
    private final String username;
    private final String[] privileges;

    /**
     * Instantiates a new Set user privileges request.
     *
     * @param username   the username
     * @param privileges the privileges
     * @param token      the token
     */
    public SetUserPrivilegesRequest(String username, String[] privileges, SessionToken token) {
        super(token);
        this.username = username;
        this.privileges = privileges;
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
     * Get privileges string [ ].
     *
     * @return the string [ ]
     */
    public String[] getPrivileges() {return privileges;}
}
