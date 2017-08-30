package javaapp1;

import org.junit.*;

public class HelloControllerTest {
    private HelloController helloController;

    @Before
    public void setup() {
        helloController = new HelloController();
    }

    @Test
    public void test() {
        Assert.assertEquals(helloController.index(), "Greetings from Spring Boot!!!");
    }
}
