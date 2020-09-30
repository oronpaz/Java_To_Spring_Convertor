import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfiguration.class);
        RegisterManager registerManager = ctx.getBean("registerManager", RegisterManager.class);

        try {
            registerManager.run();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

}
