package Request;

import Server.SessionToken;

import java.io.Serializable;
import java.sql.Time;

public class DeleteScheduleRequest extends Request implements Serializable {
    private String billboardName;
    private Time startTime;
    public DeleteScheduleRequest(String billboardname, Time start, SessionToken token){
        super(token);
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
