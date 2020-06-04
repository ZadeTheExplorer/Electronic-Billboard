package Request;

public class SetUserPrivilegesRequest {
    private String username;
    public SetUserPrivilegesRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
