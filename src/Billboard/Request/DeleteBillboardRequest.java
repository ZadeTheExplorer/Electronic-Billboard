package Billboard.Request;

import Billboard.Billboard;

import java.io.Serializable;

public class DeleteBillboardRequest extends BillboardRequest implements Serializable {
    public DeleteBillboardRequest(Billboard billboard) {
        super(billboard);
    }
}
