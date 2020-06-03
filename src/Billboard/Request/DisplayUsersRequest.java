package Billboard.Request;

public class DisplayUsersRequest {
    private String username;
    public DisplayUsersRequest(String username){
        this.username = username;
    }

    public String getUsername() {
            return username;
    }
}
