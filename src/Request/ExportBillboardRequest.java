package Request;

import java.io.Serializable;

/**
 * The type Export billboard request.
 */
public class ExportBillboardRequest implements Serializable {
    private final String billboardName;

    /**
     * Instantiates a new Export billboard request.
     *
     * @param billboardName the billboard name
     */
    public ExportBillboardRequest(String billboardName) {
       this.billboardName = billboardName;
    }

    /**
     * Gets billboard name.
     *
     * @return the billboard name
     */
    public String getBillboardName() {
        return billboardName;
    }
}
