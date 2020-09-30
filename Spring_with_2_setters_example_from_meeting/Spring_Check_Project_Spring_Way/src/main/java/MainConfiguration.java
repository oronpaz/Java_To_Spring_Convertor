import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
public class MainConfiguration {
@Bean
public Manager manager() {
Manager manager = new Manager();
            manager.setId(3);
return manager;
}

@Bean
public B b() {
B b = new B();
return b;
}

@Bean
public A a() {
A a = new A();
return a;
}

    
}
