package Request;

import ElectronicBillboardObject.Billboard;

import java.io.Serializable;

public class AddBillboardRequest implements Serializable {
    private Billboard billboard;
    public AddBillboardRequest(Billboard billboard) {
        this.billboard = billboard;
    }

    public Billboard getBillboard() {
        return billboard;
    }
}
