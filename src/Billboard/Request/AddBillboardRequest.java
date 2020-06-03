package Billboard.Request;

import Billboard.Billboard;

import java.io.Serializable;

public class AddBillboardRequest extends BillboardRequest implements Serializable {

    public AddBillboardRequest(Billboard billboard) {
        super(billboard);
    }

}
