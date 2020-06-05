package Request;

import Server.SessionToken;

import java.io.Serializable;

/**
 * The type Display all users request.
 */
public class DisplayAllUsersRequest extends Request implements Serializable {
    /**
     * Instantiates a new Display all users request.
     *
     * @param token the token
     */
    public DisplayAllUsersRequest(SessionToken token){
        super(token);
    }
}
