package Request;

import Server.SessionToken;

import java.io.Serializable;

public class SetUserPassword extends Request implements Serializable {
    private String username;
    private String password;
    public SetUserPassword(String username, String password, SessionToken token){
        super(token);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
