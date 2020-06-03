package Billboard.Request;

import java.io.Serializable;

public class SetUserPassword implements Serializable {
    private String username;
    private String password;
    public SetUserPassword(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
