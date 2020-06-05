package Test.BillboardTest;

import ElectronicBillboardObject.User;
import org.junit.jupiter.api.Test;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    ArrayList<String> privileges = new ArrayList<>();

    @Test
    void getUserName() throws Exception {
        privileges.add("Create");
        privileges.add("Edit");
        privileges.add("Delete");
        User user = new User("jaden","jaden123",privileges);
        assertEquals("jaden",user.getUserName());
    }
    @Test
    void getPrivileges() throws Exception {
        privileges.add("Create");
        privileges.add("Edit");
        privileges.add("Delete");
        User user = new User("jaden","jaden123",privileges);
        assertEquals(privileges,user.getPrivileges());
    }

    @Test
    void setUserName() throws Exception {
        privileges.add("Create");
        privileges.add("Edit");
        privileges.add("Delete");
        User user = new User("jaden","jaden123",privileges);
        assertEquals(privileges,user.getPrivileges());
    }
}