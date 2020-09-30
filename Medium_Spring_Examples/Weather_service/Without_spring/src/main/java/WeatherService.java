import weatherReports.ForecastDetails;
import weatherReports.WeatherDetails;
import weatherReports.WeatherSummary;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeatherService {
    private WeatherRepository weatherRepository;
    private WeatherInfoIdGenerator weatherInfoIdGenerator = new WeatherInfoIdGenerator();
    private WeatherMetaData weatherMetaData;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        this.weatherMetaData = Main.getMetaData();
    }

    public WeatherDetails getWeatherForTodayByCountryAndCity(String[] inputs) throws Exception {
        List<WeatherEntity> entities = weatherRepository.getWeatherEntityList();
        try {
            double temperature = getTemperatureForToday(entities, inputs);
            WeatherDetails weatherDetails = createWeatherDetails();
            weatherDetails.setTemperature(temperature);
            weatherDetails.setCountry(inputs[0]);
            weatherDetails.setCity(inputs[1]);
            return weatherDetails;
        }
        catch (Exception ex) {
            throw new Exception();
        }
    }

    private WeatherDetails createWeatherDetails() {
        @Prototype
        WeatherDetails weatherDetails = new WeatherDetails();
        weatherDetails.setTimestamp(new Date());
        weatherDetails.setWeatherId(weatherInfoIdGenerator.getNextIdForWeatherDetails());
        return weatherDetails;
    }

    private double getTemperatureForToday(List<WeatherEntity> entities, String[] inputs) throws Exception {
        String country = inputs[0];
        String city = inputs[1];

        for(WeatherEntity entity : entities) {
            if(entity.getCountry().equals(country) && entity.getCity().equals(city)) {
                if(compareDays(entity.getDate())) {
                    return entity.getTemperature();
                }

            }
        }
        throw new Exception();
    }

    public ForecastDetails getForecastByCountryAndCity(String[] inputs) {
        List<WeatherEntity> entities = weatherRepository.getWeatherEntityList();
        return invokeForeCast(entities, inputs);
    }

    public WeatherSummary getWeatherSummaryForToday() {
        List<WeatherDetails> weatherDetailsList = new ArrayList<>();

        List<WeatherEntity> entities = weatherRepository.getWeatherEntityList();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        entities.stream().filter(weatherEntity -> isWeatherForDay(weatherEntity, day)).forEach(weatherEntity -> weatherDetailsList.add(createWeatherDetailsFromEntity(weatherEntity)));
        WeatherSummary weatherSummary = createWeatherSummary();
        weatherSummary.setWeatherDetailsList(weatherDetailsList);
        return weatherSummary;
    }

    private WeatherSummary createWeatherSummary() {
        @Prototype
        WeatherSummary weatherSummary = new WeatherSummary();
        weatherSummary.setId(weatherInfoIdGenerator.getNextIdForForeCastDetails());
        weatherSummary.setTimeStamp(new Date());
        return weatherSummary;
    }

    private WeatherDetails createWeatherDetailsFromEntity(WeatherEntity weatherEntity) {
        WeatherDetails weatherDetails = createWeatherDetails();
        weatherDetails.setTemperature(weatherEntity.getTemperature());
        weatherDetails.setCountry(weatherEntity.getCountry());
        weatherDetails.setCity(weatherEntity.getCity());
        return weatherDetails;
    }

    private boolean isWeatherForDay(WeatherEntity weatherEntity, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(weatherEntity.getDate());
        int entityDay = calendar.get(Calendar.DAY_OF_MONTH);
        return entityDay == day;
    }

    private boolean compareDays(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Calendar calendar1 = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.DAY_OF_YEAR) == calendar1.get(Calendar.DAY_OF_YEAR);
    }

    public ForecastDetails invokeForeCast(List<WeatherEntity> entities, String[] inputs) {
        ForecastDetails forecastDetails = createForecastDetails();
        List<WeatherDetails> weatherDetailsList = new ArrayList<>();

        for(WeatherEntity entity : entities) {
            if(entity.getCountry().equals(inputs[0]) && entity.getCity().equals(inputs[1])) {
                WeatherDetails weatherDetails = createWeatherDetails();
                weatherDetails.setCountry(entity.getCountry());
                weatherDetails.setCity(entity.getCity());
                weatherDetails.setTemperature(entity.getTemperature());
                weatherDetailsList.add(weatherDetails);
            }
        }
        forecastDetails.setWeatherDetailsList(weatherDetailsList);
        return forecastDetails;
    }

    private ForecastDetails createForecastDetails() {
        @Prototype
        ForecastDetails forecastDetails = new ForecastDetails();
        forecastDetails.setId(weatherInfoIdGenerator.getNextIdForForeCastDetails());
        forecastDetails.setTimeStamp(new Date());
        return forecastDetails;
    }
}
