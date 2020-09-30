import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class A {
   private static ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);
    public void executeRun() {
      Manager manager = context.getBean("manager", Manager.class);
        manager.setName("The greatest manager");
        manager.run();
    }
}
