package Request;

import ElectronicBillboardObject.Billboard;
import Server.SessionToken;

import java.io.Serializable;

/**
 * The type Edit billboard request.
 */
public class EditBillboardRequest extends Request implements Serializable {
    private Billboard billboard;

    /**
     * Instantiates a new Edit billboard request.
     *
     * @param billboard the billboard
     * @param token     the token
     */
    public EditBillboardRequest(Billboard billboard, SessionToken token) {
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
