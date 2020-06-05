package Request;

import Server.SessionToken;

import java.io.Serializable;
import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Date;

/**
 * The type Set schedule request.
 */
public class SetScheduleRequest extends Request implements Serializable {
    private String billboardName;
    private Time startTime;
    private Time duration;
    private String dayOfWeek;

    /**
     * Instantiates a new Set schedule request.
     *
     * @param billboardName the billboard name
     * @param startTime     the start time
     * @param duration      the duration
     * @param dayOfWeek     the day of week
     * @param token         the token
     */
    public SetScheduleRequest(String billboardName, Time startTime, Time duration, String dayOfWeek, SessionToken token){
        super(token);
        this.billboardName = billboardName;
        this.startTime = startTime;
        this.dayOfWeek = dayOfWeek;
        this.duration = duration;
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

    /**
     * Gets duration.
     *
     * @return the duration
     */
    public Time getDuration() {
        return duration;
    }

    /**
     * Gets day of week.
     *
     * @return the day of week
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

}
