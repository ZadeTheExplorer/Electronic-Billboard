package Request;

import ElectronicBillboardObject.User;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public class LoginRequest implements Serializable {
    private String username;
    private String password;
    public LoginRequest(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }
    public String getHashPassword() throws NoSuchAlgorithmException {
        return User.hashedPassword(password);
    }
}
