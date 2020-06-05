package Request;

import Server.SessionToken;

import java.io.Serializable;

/**
 * The type Display all billboards request.
 */
public class DisplayAllBillboardsRequest extends Request implements Serializable {

    /**
     * Instantiates a new Display all billboards request.
     *
     * @param token the token
     */
    public DisplayAllBillboardsRequest(SessionToken token) {
        super(token);
    }
}
