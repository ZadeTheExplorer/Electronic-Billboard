package Billboard.Request;

import Billboard.Billboard;

import java.io.Serializable;

public class DeleteBillboardRequest implements Serializable {
    private Billboard billboard;
    public DeleteBillboardRequest(Billboard billboard) {
        this.billboard = billboard;
    }

    public Billboard getBillboard() {
        return billboard;
    }
}

