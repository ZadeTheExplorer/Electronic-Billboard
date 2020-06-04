package Billboard;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Date;

public class Schedule {
   private String billboardName;
   private String userName;
   private Time start;
   private Time duration;
   private DayOfWeek dayOfWeek;

    public Schedule(String billboardName, String userName, Time start, Time duration, DayOfWeek dayOfWeek) {
        this.billboardName = billboardName;
        this.userName = userName;
        this.start = start;
        this.duration = duration;
        this.dayOfWeek = dayOfWeek;
    }

    public String getBillboardName() {
        return billboardName;
    }

    public void setBillboardName(String billboardName) {
        this.billboardName = billboardName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }
}
