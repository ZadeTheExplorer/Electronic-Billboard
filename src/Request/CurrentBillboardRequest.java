package Request;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CurrentBillboardRequest implements Serializable {
    private final LocalDateTime time;
    //TODO
    public CurrentBillboardRequest(LocalDateTime time){
        this.time = time;
    }

    public LocalDateTime getTime() {
        return time;
    }
}