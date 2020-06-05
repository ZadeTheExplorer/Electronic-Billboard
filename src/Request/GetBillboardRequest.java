package Request;

import Server.SessionToken;

import java.io.Serializable;

public class GetBillboardRequest extends Request implements Serializable {
    private String billboardName;
    public GetBillboardRequest (String billboardName, SessionToken token) {
        super(token);
        this.billboardName = billboardName;
    }
    public String getBillboardName() {
        return billboardName;
    }
}
