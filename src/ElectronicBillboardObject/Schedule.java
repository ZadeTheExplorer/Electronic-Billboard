package ElectronicBillboardObject;

import java.sql.Time;
import java.time.DayOfWeek;
import java.util.Date;

/**
 * The type Schedule.
 */
public class Schedule {
   private String billboardName;
   private String userName;
   private Time start;
   private Time duration;
   private DayOfWeek dayOfWeek;

    /**
     * Instantiates a new Schedule.
     *
     * @param billboardName the billboard name
     * @param userName      the user name
     * @param start         the start
     * @param duration      the duration
     * @param dayOfWeek     the day of week
     */
    public Schedule(String billboardName, String userName, Time start, Time duration, DayOfWeek dayOfWeek) {
        this.billboardName = billboardName;
        this.userName = userName;
        this.start = start;
        this.duration = duration;
        this.dayOfWeek = dayOfWeek;
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
     * Sets billboard name.
     *
     * @param billboardName the billboard name
     */
    public void setBillboardName(String billboardName) {
        this.billboardName = billboardName;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets start.
     *
     * @return the start
     */
    public Time getStart() {
        return start;
    }

    /**
     * Sets start.
     *
     * @param start the start
     */
    public void setStart(Time start) {
        this.start = start;
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
     * Sets duration.
     *
     * @param duration the duration
     */
    public void setDuration(Time duration) {
        this.duration = duration;
    }

    /**
     * Gets day of week.
     *
     * @return the day of week
     */
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Sets day of week.
     *
     * @param dayOfWeek the day of week
     */
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
