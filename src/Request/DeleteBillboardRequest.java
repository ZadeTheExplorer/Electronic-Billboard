package Request;

import Server.SessionToken;

import java.io.Serializable;

/**
 * The type Delete billboard request.
 */
public class DeleteBillboardRequest extends Request implements Serializable {
    private String billboard;

    /**
     * Instantiates a new Delete billboard request.
     *
     * @param billboard the billboard
     * @param token     the token
     */
    public DeleteBillboardRequest(String billboard, SessionToken token) {
        super(token);
        this.billboard = billboard;
    }

    /**
     * Gets billboard.
     *
     * @return the billboard
     */
    public String getBillboard() {
        return billboard;
    }
}

