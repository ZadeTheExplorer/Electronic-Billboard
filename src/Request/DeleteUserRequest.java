package Request;

import Server.SessionToken;

import java.io.Serializable;

public class DeleteUserRequest extends Request implements Serializable {
    private String username;
    public DeleteUserRequest(String username, SessionToken token) {
        super(token);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
