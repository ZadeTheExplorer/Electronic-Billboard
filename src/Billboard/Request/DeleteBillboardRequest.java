package Billboard.Request;

import Billboard.Billboard;

import java.io.Serializable;

public class DeleteBillboardRequest implements Serializable {
    private String billboard;
    public DeleteBillboardRequest(String billboard) {
        this.billboard = billboard;
    }

    public String getBillboard() {
        return billboard;
    }
}

