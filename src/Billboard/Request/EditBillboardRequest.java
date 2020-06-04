package Billboard.Request;

import Billboard.Billboard;

import java.io.Serializable;

public class EditBillboardRequest implements Serializable {
    private Billboard billboard;
    public EditBillboardRequest(Billboard billboard) {
        this.billboard = billboard;
    }

    public Billboard getBillboard() {
        return billboard;
    }
}
