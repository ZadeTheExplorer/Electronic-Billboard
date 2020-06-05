package Request;

import Server.SessionToken;

import java.io.Serializable;

public abstract class Request implements Serializable {
    private final SessionToken sessionToken;
    public Request(SessionToken sessionToken){
        this.sessionToken = sessionToken;
    }

    public SessionToken getSessionToken() {
        return sessionToken;
    }
}
