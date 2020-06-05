package Request;

import Server.SessionToken;

import java.io.Serializable;

public class GetUserPrivilegesRequest extends Request implements Serializable {
    private String username;
    public GetUserPrivilegesRequest(String username, SessionToken token) {
        super(token);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}