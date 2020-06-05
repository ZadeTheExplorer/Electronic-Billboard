package Test.BillboardTest;

import ElectronicBillboardObject.Billboard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillboardTest {

    @Test
    void getName() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        assertEquals("Test Billboard",billboard.getName());
    }

    @Test
    void setName() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        billboard.setName("New name");
        assertEquals("New name",billboard.getName());
    }

    @Test
    void getCreator() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        assertEquals("Jaden",billboard.getCreator());
    }

    @Test
    void setCreator() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        billboard.setCreator("admin");
        assertEquals("admin",billboard.getCreator());
    }

    @Test
    void getBackgroundColor() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        assertEquals("#FF5733",billboard.getBackgroundColor());
    }

    @Test
    void setBackgroundColor() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        billboard.setBackgroundColor("#FCD6CE");
        assertEquals("#FCD6CE",billboard.getBackgroundColor());
    }

    @Test
    void getMessage() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        assertEquals("This is a billboard test",billboard.getMessage());
    }

    @Test
    void setMessage() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        billboard.setMessage("This is a new message");
        assertEquals("This is a new message",billboard.getMessage());
    }

    @Test
    void getMessageColor() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        assertEquals("#FF5733",billboard.getMessageColor());
    }

    @Test
    void setMessageColor() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        billboard.setMessageColor("#FCD6CE");
        assertEquals("#FCD6CE",billboard.getMessageColor());
    }

    @Test
    void getPicture() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        assertEquals("https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",billboard.getPicture());
    }

    @Test
    void setPicture() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        billboard.setPicture("https://newpic.com");
        assertEquals("https://newpic.com",billboard.getPicture());
    }

    @Test
    void getInformation() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        assertEquals("For testing",billboard.getInformation());
    }

    @Test
    void setInformation() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        billboard.setInformation("new information");
        assertEquals("new information",billboard.getInformation());
    }

    @Test
    void getInformationColor() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        assertEquals("#FF5733",billboard.getInformationColor());
    }

    @Test
    void setInformationColor() {
        Billboard billboard = new Billboard("Test Billboard","Jaden",
                "#FF5733","#FF5733","#FF5733","https://synergiseit.com.au/wp-content/uploads/2019/10/Cloud-computing.jpg",
                "This is a billboard test","For testing");
        billboard.setInformationColor("#FCD6CE");
        assertEquals("#FCD6CE",billboard.getInformationColor());
    }


//    @Test
//    void exportXML() {
//    }
}