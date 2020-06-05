package Request;

import Server.SessionToken;

import java.io.Serializable;

public class SetUserPrivilegesRequest extends Request implements Serializable {
    private final String username;
    private final String[] privileges;
    public SetUserPrivilegesRequest(String username, String[] privileges, SessionToken token) {
        super(token);
        this.username = username;
        this.privileges = privileges;
    }

    public String getUsername() {
        return username;
    }
    public String[] getPrivileges() {return privileges;}
}
