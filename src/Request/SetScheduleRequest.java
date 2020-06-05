package Request;

import Server.SessionToken;

import java.io.Serializable;
import java.sql.Time;

public class SetScheduleRequest extends Request implements Serializable {
    private String billboardName;
    private Time startTime;
    private Time duration;
    public SetScheduleRequest(String billboardName, Time startTime, Time duration, SessionToken token){
        super(token);
        this.billboardName = billboardName;
        this.startTime = startTime;
        this.duration = duration;
    }

    public String getBillboardName() {
        return billboardName;
    }


    public Time getStartTime() {
        return startTime;
    }
    public Time getDuration() {
        return duration;
    }
}
