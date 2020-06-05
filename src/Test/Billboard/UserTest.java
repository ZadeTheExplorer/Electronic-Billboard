package Billboard;

import ElectronicBillboardObject.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User test.
 */
class UserTest {
    /**
     * The User.
     */
    User user;

    /**
     * Create user.
     *
     * @throws Exception the exception
     */
    @BeforeEach
     void createUser() throws Exception {
        ArrayList<String> permission = new ArrayList<>();
        permission.add("write");
        user = new User("user","password",permission);
    }

    /**
     * Gets user name.
     */
    @Test
    void getUserName() {
        assertEquals("user",user.getUserName());
    }

    /**
     * Sets user name.
     */
    @Test
    void setUserName() {
        user.setUserName("user1");
        assertEquals("user1",user.getUserName());
    }

    /**
     * Gets pass word.
     */
    @Test
    void getPassWord() {
        assertEquals("password",user.getPassWord());
    }

    /**
     * Sets pass word.
     */
    @Test
    void setPassWord() {
        user.setPassWord("password1");
        assertEquals("password1",user.getPassWord());
    }

    /**
     * Check password.
     */
    @Test
    void checkPassword() {
        assertTrue(user.checkPassword("password"));
    }

    /**
     * Gets privilege.
     */
    @Test
    void getPrivilege() {
        assertEquals("user",user.getPrivilege());
    }

    /**
     * Sets privilege.
     */
    @Test
    void setPrivilege() {
        user.setPrivilege("admin");
        assertEquals("admin",user.getPrivilege());
    }
}