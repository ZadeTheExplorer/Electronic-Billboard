package Request;

import Server.SessionToken;

import java.io.Serializable;

public class DisplayAllBillboardsRequest extends Request implements Serializable {

    public DisplayAllBillboardsRequest(SessionToken token) {
        super(token);
    }
}
