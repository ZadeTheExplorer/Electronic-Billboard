package Request;

import ElectronicBillboardObject.Billboard;
import Server.SessionToken;

import java.io.Serializable;

/**
 * The type Add billboard request.
 */
public class AddBillboardRequest extends Request implements Serializable {
    private Billboard billboard;

    /**
     * Instantiates a new Add billboard request.
     *
     * @param billboard the billboard
     * @param token     the token
     */
    public AddBillboardRequest(Billboard billboard, SessionToken token) {
        super(token);
        this.billboard = billboard;
    }

    /**
     * Gets billboard.
     *
     * @return the billboard
     */
    public Billboard getBillboard() {
        return billboard;
    }
}
