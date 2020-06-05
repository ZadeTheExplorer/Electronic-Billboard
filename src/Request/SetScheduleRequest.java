package Request;

import Server.SessionToken;

import java.io.Serializable;
import java.sql.Time;

public class SetScheduleRequest extends Request implements Serializable {
    private int scheduleId;
    private String billboardName;
    private Time startTime;
    private Time duration;
    public SetScheduleRequest(int scheduleId, String billboardName, Time startTime, Time duration, SessionToken token){
        super(token);
        this.scheduleId = scheduleId;
        this.billboardName = billboardName;
        this.startTime = startTime;
        this.duration = duration;
    }

    public String getBillboardName() {
        return billboardName;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public Time getStartTime() {
        return startTime;
    }
    public Time getDuration() {
        return duration;
    }
}
