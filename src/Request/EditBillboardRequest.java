package Request;

import ElectronicBillboardObject.Billboard;
import Server.SessionToken;

import java.io.Serializable;

public class EditBillboardRequest extends Request implements Serializable {
    private Billboard billboard;
    public EditBillboardRequest(Billboard billboard, SessionToken token) {
        super(token);
        this.billboard = billboard;
    }

    public Billboard getBillboard() {
        return billboard;
    }
}
