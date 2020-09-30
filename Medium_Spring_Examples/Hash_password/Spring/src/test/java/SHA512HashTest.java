import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;


public class SHA512HashTest {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfiguration.class);

    private SHA512Hash classUnderTest = ctx.getBean("sha512Hash", SHA512Hash.class);
    private String testPassword = "testPassword";

    @Test
    public void sha512HashTest() {
        String expectedResult = "8a5b8b4611dee46b3daf3531fabb2a73a93a2be376eaa240dc115dd5818bd24a533eeee9a46aaa27c8064516e489e60b75533506e774e1979228428c910af275";
        String actualResult = classUnderTest.hash(testPassword);
        assertEquals(expectedResult, actualResult);
    }
}
