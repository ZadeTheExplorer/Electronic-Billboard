package Billboard.Request;

import Billboard.Billboard;

public class EditBillboardRequest {
    private Billboard billboard;
    public EditBillboardRequest(Billboard billboard) {
        this.billboard = billboard;
    }

    public Billboard getBillboard() {
        return billboard;
    }
}
