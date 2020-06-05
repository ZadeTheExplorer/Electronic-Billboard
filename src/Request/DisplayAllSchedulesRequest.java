package Request;

import Server.SessionToken;

import java.io.Serializable;

public class DisplayAllSchedulesRequest extends Request implements Serializable {

    public DisplayAllSchedulesRequest(SessionToken token) {
        super(token);

    }
}
