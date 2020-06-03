package Billboard.Request;

import java.io.Serializable;
import java.sql.Time;

public class DeleteScheduleRequest implements Serializable {
    private String billboardName;
    private Time startTime;
    public DeleteScheduleRequest(String billboardname, Time start){
        this.billboardName = billboardname;
        this.startTime = start;
    }

    public String getBillboardName() {
        return billboardName;
    }
    public Time getStartTime() {
        return startTime;
    }
}
