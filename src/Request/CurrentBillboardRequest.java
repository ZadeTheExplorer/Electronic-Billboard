package Request;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The type Current billboard request.
 */
public class CurrentBillboardRequest implements Serializable {
    private final LocalDateTime time;

    /**
     * Instantiates a new Current billboard request.
     *
     * @param time the time
     */
//TODO
    public CurrentBillboardRequest(LocalDateTime time){
        this.time = time;
    }

    /**
     * Gets time.
     *
     * @return the time
     */
    public LocalDateTime getTime() {
        return time;
    }
}
