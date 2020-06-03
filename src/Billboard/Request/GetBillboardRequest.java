package Billboard.Request;

import java.io.Serializable;

public class GetBillboardRequest implements Serializable {
    private String billboardName;
    public GetBillboardRequest (String billboardName) {
        this.billboardName = billboardName;
    }
    public String getBillboardName() {
        return billboardName;
    }
}
