package Billboard.Request;

public class GetUserPermissionRequest {
    private String username;
    public GetUserPermissionRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}