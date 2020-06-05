package Billboard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Billboard test.
 */
class BillboardTest {

    /**
     * Gets description.
     */
    @Test
    void getDescription() {
        Billboard bb = new Billboard("Test","testing","www.test.com");
        assertEquals("testing",bb.getInformation());
    }

    /**
     * Sets description.
     */
    @Test
    void setDescription() {
        Billboard bb = new Billboard("Test","testing","www.test.com");
        bb.setInformation("another test");
        assertEquals("another test",bb.getInformation());
    }

    /**
     * Gets img url.
     */
    @Test
    void getImg_url() {
        Billboard bb = new Billboard("Test","testing","www.test.com");
        assertEquals("www.test.com",bb.getImg_url());
    }

    /**
     * Sets img url.
     */
    @Test
    void setImg_url() {
        Billboard bb = new Billboard("Test","testing","www.test.com");
        bb.setImg_url("www.image.com");
        assertEquals("www.image.com",bb.getImg_url());
    }
}