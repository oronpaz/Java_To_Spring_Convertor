import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MainConfiguration.class);
        RegisterUtils registerUtils = ctx.getBean("registerUtils", RegisterUtils.class);

       try {
           registerUtils.run();
       }
       catch (Exception ex) {
           System.out.println("Configuration file isn't exists");
       }

    }

}
