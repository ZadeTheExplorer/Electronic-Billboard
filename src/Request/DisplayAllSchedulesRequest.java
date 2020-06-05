package Request;

import Server.SessionToken;

import java.io.Serializable;

/**
 * The type Display all schedules request.
 */
public class DisplayAllSchedulesRequest extends Request implements Serializable {

    /**
     * Instantiates a new Display all schedules request.
     *
     * @param token the token
     */
    public DisplayAllSchedulesRequest(SessionToken token) {
        super(token);

    }
}
