package Request;

import ElectronicBillboardObject.User;
import Server.SessionToken;

import java.io.Serializable;

/**
 * The type Add user resquest.
 */
public class AddUserResquest extends Request implements Serializable {
    private User user;

    /**
     * Instantiates a new Add user resquest.
     *
     * @param user  the user
     * @param token the token
     */
    public AddUserResquest(User user, SessionToken token) {
        super(token);
        this.user = user;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }
}
