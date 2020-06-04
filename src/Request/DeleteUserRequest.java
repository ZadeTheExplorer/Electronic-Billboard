package Request;

public class DeleteUserRequest {
    private String username;
    public DeleteUserRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
