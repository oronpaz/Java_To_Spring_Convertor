package weatherReports;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.List;

@Configuration
public class WeatherReportsConfiguration {

    @Bean
    public List<WeatherDetails> weatherDetailsList() {
        List<WeatherDetails> weatherDetailsList = new ArrayList<>();
        return weatherDetailsList;
    }
}
