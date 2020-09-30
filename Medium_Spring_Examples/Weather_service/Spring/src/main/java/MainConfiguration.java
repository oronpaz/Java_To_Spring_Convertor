import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import weatherReports.ForecastDetails;
import weatherReports.WeatherDetails;
import weatherReports.WeatherReportsConfiguration;
import weatherReports.WeatherSummary;

import java.util.Date;

@Configuration
@Import(WeatherReportsConfiguration.class)
public class MainConfiguration {
    @Autowired
    private WeatherReportsConfiguration weatherReportsConfiguration;

    @Bean
    public WeatherManager weatherManager() {
        return new WeatherManager(weatherService());
    }

    @Bean
    @Lazy
    public WeatherService weatherService() {
        return new WeatherService(weatherRepository());
    }

    @Bean
    public WeatherRepository weatherRepository() {
        return new WeatherRepository();
    }

    @Bean
    public WeatherInfoIdGenerator weatherInfoIdGenerator() {
        return new WeatherInfoIdGenerator();
    }

    @Bean
    @Scope("prototype")
    public WeatherDetails weatherDetails() {
        WeatherDetails weatherDetails = new WeatherDetails();
        weatherDetails.setTimestamp(new Date());
        weatherDetails.setWeatherId(weatherInfoIdGenerator().getNextIdForWeatherDetails());
        return weatherDetails;
    }

    @Bean
    @Scope("prototype")
    public ForecastDetails forecastDetails() {
        ForecastDetails forecastDetails = new ForecastDetails();
        forecastDetails.setWeatherDetailsList(weatherReportsConfiguration.weatherDetailsList());
        forecastDetails.setId(weatherInfoIdGenerator().getNextIdForForeCastDetails());
        forecastDetails.setTimeStamp(new Date());
        return forecastDetails;
    }

    @Bean
    @Scope("prototype")
    public WeatherSummary weatherSummary() {
        WeatherSummary weatherSummary = new WeatherSummary();
        weatherSummary.setWeatherDetailsList(weatherReportsConfiguration.weatherDetailsList());
        weatherSummary.setId(weatherInfoIdGenerator().getNextIdForForeCastDetails());
        weatherSummary.setTimeStamp(new Date());
        return weatherSummary;
    }
}
