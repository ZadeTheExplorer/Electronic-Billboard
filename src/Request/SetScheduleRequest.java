package Request;

import Server.SessionToken;

import java.io.Serializable;
import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Date;

public class SetScheduleRequest extends Request implements Serializable {
    private String billboardName;
    private Time startTime;
    private Time duration;
    private String dayOfWeek;
    public SetScheduleRequest(String billboardName, Time startTime, Time duration, String dayOfWeek, SessionToken token){
        super(token);
        this.billboardName = billboardName;
        this.startTime = startTime;
        this.dayOfWeek = dayOfWeek;
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
    public String getDayOfWeek() {
        return dayOfWeek;
    }

}
