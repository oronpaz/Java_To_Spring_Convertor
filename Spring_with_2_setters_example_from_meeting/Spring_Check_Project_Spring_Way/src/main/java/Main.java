import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class Main {
   private static ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
    public static void main(String[] args) {
        System.out.println("Starting..");
      A a = context.getBean("a", A.class);
        a.executeRun();
      B b = context.getBean("b", B.class);
        b.executeRun();
        System.out.println("Finishing..");
    }
}
