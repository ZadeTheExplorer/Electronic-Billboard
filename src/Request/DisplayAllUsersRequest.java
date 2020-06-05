package Request;

import Server.SessionToken;

import java.io.Serializable;

public class DisplayAllUsersRequest extends Request implements Serializable {
    public DisplayAllUsersRequest(SessionToken token){
        super(token);
    }
}
