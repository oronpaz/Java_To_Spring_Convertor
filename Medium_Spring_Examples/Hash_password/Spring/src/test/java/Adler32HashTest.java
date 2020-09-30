import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class Adler32HashTest {

    private ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfiguration.class);

    private Adler32Hash classUnderTest = ctx.getBean("adler32Hash", Adler32Hash.class);
    private String testPassword = "testPassword";

    @Test
    public void adlerHashTest() {
        String expectedResult = "1405ee20";
        String actualResult = classUnderTest.hash(testPassword);
        assertEquals(expectedResult, actualResult);
    }
}
