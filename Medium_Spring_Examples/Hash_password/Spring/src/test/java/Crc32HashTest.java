import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class Crc32HashTest {
    private ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfiguration.class);

    private Crc32Hash classUnderTest = ctx.getBean("crc32Hash", Crc32Hash.class);
    private String testPassword = "testPassword";

    @Test
    public void crcHashTest() {
        String expectedResult = "3611bd64";
        String actualResult = classUnderTest.hash(testPassword);
        assertEquals(expectedResult, actualResult);
    }
}
