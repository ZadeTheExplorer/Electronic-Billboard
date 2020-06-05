package Request;

import ElectronicBillboardObject.User;
import Server.SessionToken;

import java.io.Serializable;

public class AddUserResquest extends Request implements Serializable {
    private User user;
    public AddUserResquest(User user, SessionToken token) {
        super(token);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
