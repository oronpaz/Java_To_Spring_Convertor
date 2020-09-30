import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static ApplicationContext context = new AnnotationConfigApplicationContext(MainConfiguration.class);

    public static void main(String[] args) {
         WeatherManager manager = context.getBean("weatherManager", WeatherManager.class);
         manager.run();
    }

    public static WeatherMetaData getMetaData() {
        WeatherMetaData weatherMetaData = new WeatherMetaData();
        weatherMetaData.setCelsius(true);
        weatherMetaData.setLicense("3121231wd131231ed12dd1");
        return weatherMetaData;
    }
}
