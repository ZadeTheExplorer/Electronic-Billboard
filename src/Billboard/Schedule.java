package Billboard;

import java.util.Date;

public class Schedule {
    private Date date;
    private String importantInfor;
    public Schedule(Date date){
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
