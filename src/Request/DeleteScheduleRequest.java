package Request;

import Server.SessionToken;

import java.io.Serializable;
import java.sql.Time;

/**
 * The type Delete schedule request.
 */
public class DeleteScheduleRequest extends Request implements Serializable {
    private String billboardName;
    private Time startTime;

    /**
     * Instantiates a new Delete schedule request.
     *
     * @param billboardname the billboardname
     * @param start         the start
     * @param token         the token
     */
    public DeleteScheduleRequest(String billboardname, Time start, SessionToken token){
        super(token);
        this.billboardName = billboardname;
        this.startTime = start;
    }

    /**
     * Gets billboard name.
     *
     * @return the billboard name
     */
    public String getBillboardName() {
        return billboardName;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public Time getStartTime() {
        return startTime;
    }
}
