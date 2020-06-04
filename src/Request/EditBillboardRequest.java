package Request;

import ElectronicBillboardObject.Billboard;

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
