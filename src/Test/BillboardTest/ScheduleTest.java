package Test.BillboardTest;

import ElectronicBillboardObject.Schedule;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Schedule test.
 */
class ScheduleTest {

    /**
     * Gets billboard name.
     */
    @Test
    void getBillboardName() {
        Schedule schedule = new Schedule("Testing","admin",
                Time.valueOf("09:00:00"),Time.valueOf("01:00:00"), DayOfWeek.FRIDAY);
        assertEquals("Testing",schedule.getBillboardName());
    }

    /**
     * Sets billboard name.
     */
    @Test
    void setBillboardName() {
        Schedule schedule = new Schedule("Testing","admin",
                Time.valueOf("09:00:00"),Time.valueOf("01:00:00"), DayOfWeek.FRIDAY);
        schedule.setBillboardName("New testing");
        assertEquals("New testing",schedule.getBillboardName());
    }

    /**
     * Gets user name.
     */
    @Test
    void getUserName() {
        Schedule schedule = new Schedule("Testing","admin",
                Time.valueOf("09:00:00"),Time.valueOf("01:00:00"), DayOfWeek.FRIDAY);
        schedule.setBillboardName("New testing");
        assertEquals("New testing",schedule.getBillboardName());
    }

    /**
     * Sets user name.
     */
    @Test
    void setUserName() {
        Schedule schedule = new Schedule("Testing","admin",
                Time.valueOf("09:00:00"),Time.valueOf("01:00:00"), DayOfWeek.FRIDAY);
        schedule.setUserName("user");
        assertEquals("user",schedule.getUserName());
    }

    /**
     * Gets start.
     */
    @Test
    void getStart() {
        Schedule schedule = new Schedule("Testing","admin",
                Time.valueOf("09:00:00"),Time.valueOf("01:00:00"), DayOfWeek.FRIDAY);
        assertEquals(Time.valueOf("09:00:00"),schedule.getStart());
    }

    /**
     * Sets start.
     */
    @Test
    void setStart() {
        Schedule schedule = new Schedule("Testing","admin",
                Time.valueOf("09:00:00"),Time.valueOf("01:00:00"), DayOfWeek.FRIDAY);
        schedule.setStart(Time.valueOf("10:00:00"));
        assertEquals(Time.valueOf("10:00:00"),schedule.getStart());
    }

    /**
     * Gets duration.
     */
    @Test
    void getDuration() {
        Schedule schedule = new Schedule("Testing","admin",
                Time.valueOf("09:00:00"),Time.valueOf("01:00:00"), DayOfWeek.FRIDAY);
        assertEquals(Time.valueOf("01:00:00"),schedule.getDuration());
    }

    /**
     * Sets duration.
     */
    @Test
    void setDuration() {
        Schedule schedule = new Schedule("Testing","admin",
                Time.valueOf("09:00:00"),Time.valueOf("01:00:00"), DayOfWeek.FRIDAY);
        schedule.setDuration(Time.valueOf("02:00:00"));
        assertEquals(Time.valueOf("02:00:00"),schedule.getDuration());
    }

    /**
     * Gets day of week.
     */
    @Test
    void getDayOfWeek() {
        Schedule schedule = new Schedule("Testing","admin",
                Time.valueOf("09:00:00"),Time.valueOf("01:00:00"), DayOfWeek.FRIDAY);
        assertEquals(DayOfWeek.FRIDAY,schedule.getDayOfWeek());
    }

    /**
     * Sets day of week.
     */
    @Test
    void setDayOfWeek() {
        Schedule schedule = new Schedule("Testing","admin",
                Time.valueOf("09:00:00"),Time.valueOf("01:00:00"), DayOfWeek.FRIDAY);
        schedule.setDayOfWeek(DayOfWeek.SATURDAY);
        assertEquals(DayOfWeek.SATURDAY,schedule.getDayOfWeek());
    }
}