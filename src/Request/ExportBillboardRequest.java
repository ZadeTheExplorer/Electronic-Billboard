package Request;

import java.io.Serializable;

public class ExportBillboardRequest implements Serializable {
    private final String billboardName;
    public ExportBillboardRequest(String billboardName) {
       this.billboardName = billboardName;
    }
    public String getBillboardName() {
        return billboardName;
    }
}
