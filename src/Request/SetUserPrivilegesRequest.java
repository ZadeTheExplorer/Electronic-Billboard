package Request;

public class SetUserPrivilegesRequest {
    private final String username;
    private final String[] privileges;
    public SetUserPrivilegesRequest(String username, String[] privileges) {
        this.username = username;
        this.privileges = privileges;
    }

    public String getUsername() {
        return username;
    }
    public String[] getPrivileges() {return privileges;}
}
