package weatherReports;

import weatherReports.WeatherDetails;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForecastDetails {
    private List<WeatherDetails> weatherDetailsList = new ArrayList<>();
    private long id;
    private Date timeStamp;

    public void addDetails(WeatherDetails weatherDetails) {
        weatherDetailsList.add(weatherDetails);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<WeatherDetails> getWeatherDetailsList() {
        return weatherDetailsList;
    }

    public void setWeatherDetailsList(List<WeatherDetails> weatherDetailsList) {
        this.weatherDetailsList = weatherDetailsList;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for(WeatherDetails details : weatherDetailsList) {
            str.append(details);
            str.append("\n");
        }
        return str.toString();
    }
}
