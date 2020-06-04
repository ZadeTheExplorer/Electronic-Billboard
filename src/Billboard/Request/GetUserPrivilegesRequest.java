package Billboard.Request;

import java.io.Serializable;

public class GetUserPrivilegesRequest implements Serializable {
    private String username;
    public GetUserPrivilegesRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}