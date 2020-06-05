package Request;

import Server.SessionToken;

import java.io.Serializable;

/**
 * The type Get billboard request.
 */
public class GetBillboardRequest extends Request implements Serializable {
    private String billboardName;

    /**
     * Instantiates a new Get billboard request.
     *
     * @param billboardName the billboard name
     * @param token         the token
     */
    public GetBillboardRequest (String billboardName, SessionToken token) {
        super(token);
        this.billboardName = billboardName;
    }

    /**
     * Gets billboard name.
     *
     * @return the billboard name
     */
    public String getBillboardName() {
        return billboardName;
    }
}
