package Billboard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    @BeforeEach
     void createUser() throws Exception {
        ArrayList<String> permission = new ArrayList<>();
        permission.add("write");
        user = new User("user","password",permission,"user");
    }

    @Test
    void getUserName() {
        assertEquals("user",user.getUserName());
    }

    @Test
    void setUserName() {
        user.setUserName("user1");
        assertEquals("user1",user.getUserName());
    }

    @Test
    void getPassWord() {
        assertEquals("password",user.getPassWord());
    }

    @Test
    void setPassWord() {
        user.setPassWord("password1");
        assertEquals("password1",user.getPassWord());
    }

    @Test
    void checkPassword() {
        assertTrue(user.checkPassword("password"));
    }

    @Test
    void getPrivilege() {
        assertEquals("user",user.getPrivilege());
    }

    @Test
    void setPrivilege() {
        user.setPrivilege("admin");
        assertEquals("admin",user.getPrivilege());
    }
}