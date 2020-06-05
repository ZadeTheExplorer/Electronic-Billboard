package Request;

import Server.SessionToken;

import java.io.Serializable;

/**
 * The type Request.
 */
public abstract class Request implements Serializable {
    private final SessionToken sessionToken;

    /**
     * Instantiates a new Request.
     *
     * @param sessionToken the session token
     */
    public Request(SessionToken sessionToken){
        this.sessionToken = sessionToken;
    }

    /**
     * Gets session token.
     *
     * @return the session token
     */
    public SessionToken getSessionToken() {
        return sessionToken;
    }
}
