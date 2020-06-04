package Billboard.Request;

import Billboard.User;

import java.io.Serializable;

public class AddUserResquest implements Serializable {
    private User user;
    public AddUserResquest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
