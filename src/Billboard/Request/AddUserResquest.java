package Billboard.Request;

import Billboard.User;

public class AddUserResquest {
    private User user;
    public AddUserResquest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
