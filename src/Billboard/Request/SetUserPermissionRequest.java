package Billboard.Request;

public class SetUserPermissionRequest {
    private String username;
    public SetUserPermissionRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
