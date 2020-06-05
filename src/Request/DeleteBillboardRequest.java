package Request;

import Server.SessionToken;

import java.io.Serializable;

public class DeleteBillboardRequest extends Request implements Serializable {
    private String billboard;
    public DeleteBillboardRequest(String billboard, SessionToken token) {
        super(token);
        this.billboard = billboard;
    }

    public String getBillboard() {
        return billboard;
    }
}

