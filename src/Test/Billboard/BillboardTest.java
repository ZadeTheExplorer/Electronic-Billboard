package Billboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillboardTest {

    @Test
    void getDescription() {
        Billboard bb = new Billboard("Test","testing","www.test.com");
        assertEquals("testing",bb.getDescription());
    }

    @Test
    void setDescription() {
        Billboard bb = new Billboard("Test","testing","www.test.com");
        bb.setDescription("another test");
        assertEquals("another test",bb.getDescription());
    }

    @Test
    void getImg_url() {
        Billboard bb = new Billboard("Test","testing","www.test.com");
        assertEquals("www.test.com",bb.getImg_url());
    }

    @Test
    void setImg_url() {
        Billboard bb = new Billboard("Test","testing","www.test.com");
        bb.setImg_url("www.image.com");
        assertEquals("www.image.com",bb.getImg_url());
    }
}