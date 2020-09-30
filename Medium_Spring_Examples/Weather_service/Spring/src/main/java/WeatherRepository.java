import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeatherRepository {
    private List<WeatherEntity> weatherEntityList;

    public WeatherRepository() {
        this.weatherEntityList = new ArrayList<>();
        populateList();
    }

    private void populateList() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        Date tomorrow = c.getTime();
        c.setTime(tomorrow);
        c.add(Calendar.DATE, 1);
        Date afterTomorrow = c.getTime();

        weatherEntityList.add(new WeatherEntity("Spain", "Barcelona", 18.0, date));
        weatherEntityList.add(new WeatherEntity("Spain", "Madrid", 20.0, date));
        weatherEntityList.add(new WeatherEntity("Israel", "Tel-Aviv", 39.5, date));
        weatherEntityList.add(new WeatherEntity("Israel", "Holon", 36.0, date));
        weatherEntityList.add(new WeatherEntity("Italy", "Milano", 14.0, date));
        weatherEntityList.add(new WeatherEntity("Italy", "Roma", 13.0, date));
        weatherEntityList.add(new WeatherEntity("Spain", "Barcelona", 14.0, tomorrow));
        weatherEntityList.add(new WeatherEntity("Spain", "Madrid", 22.0, tomorrow));
        weatherEntityList.add(new WeatherEntity("Israel", "Tel-Aviv", 35.0, tomorrow));
        weatherEntityList.add(new WeatherEntity("Israel", "Holon", 34.0, tomorrow));
        weatherEntityList.add(new WeatherEntity("Italy", "Milano", 17.0, tomorrow));
        weatherEntityList.add(new WeatherEntity("Italy", "Roma", 16.0, tomorrow));
        weatherEntityList.add(new WeatherEntity("Spain", "Barcelona", 17.0, afterTomorrow));
        weatherEntityList.add(new WeatherEntity("Spain", "Madrid", 24.0, afterTomorrow));
        weatherEntityList.add(new WeatherEntity("Israel", "Tel-Aviv", 40.0, afterTomorrow));
        weatherEntityList.add(new WeatherEntity("Israel", "Holon", 34.0, afterTomorrow));
        weatherEntityList.add(new WeatherEntity("Italy", "Milano", 16.0, afterTomorrow));
        weatherEntityList.add(new WeatherEntity("Italy", "Roma", 18.0, afterTomorrow));

    }

    public List<WeatherEntity> getWeatherEntityList() {
        return weatherEntityList;
    }
}
