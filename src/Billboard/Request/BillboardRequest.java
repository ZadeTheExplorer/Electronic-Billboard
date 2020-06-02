package Billboard.Request;

import Billboard.Billboard;

import java.io.Serializable;

public abstract class BillboardRequest implements Serializable {
    private Billboard billboard;
    public BillboardRequest(Billboard billboard) {
        this.billboard = billboard;

    }

    public Billboard getBillboard() {
        return billboard;
    }
}
