package Test.BillboardTest;

import ElectronicBillboardObject.User;
import org.junit.jupiter.api.Test;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type User test.
 */
class UserTest {
    /**
     * The Privileges.
     */
    ArrayList<String> privileges = new ArrayList<>();

    /**
     * Gets user name.
     *
     * @throws Exception the exception
     */
    @Test
    void getUserName() throws Exception {
        privileges.add("Create");
        privileges.add("Edit");
        privileges.add("Delete");
        User user = new User("jaden","jaden123",privileges);
        assertEquals("jaden",user.getUserName());
    }

    /**
     * Gets privileges.
     *
     * @throws Exception the exception
     */
    @Test
    void getPrivileges() throws Exception {
        privileges.add("Create");
        privileges.add("Edit");
        privileges.add("Delete");
        User user = new User("jaden","jaden123",privileges);
        assertEquals(privileges,user.getPrivileges());
    }

    /**
     * Sets user name.
     *
     * @throws Exception the exception
     */
    @Test
    void setUserName() throws Exception {
        privileges.add("Create");
        privileges.add("Edit");
        privileges.add("Delete");
        User user = new User("jaden","jaden123",privileges);
        assertEquals(privileges,user.getPrivileges());
    }
}