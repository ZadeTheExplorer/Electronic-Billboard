package Request;

import ElectronicBillboardObject.Billboard;
import Server.SessionToken;

import java.io.Serializable;

public class AddBillboardRequest extends Request implements Serializable {
    private Billboard billboard;

    public AddBillboardRequest(Billboard billboard, SessionToken token) {
        super(token);
        this.billboard = billboard;
    }

    public Billboard getBillboard() {
        return billboard;
    }
}
