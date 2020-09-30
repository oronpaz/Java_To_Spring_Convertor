package weatherReports;

import java.util.Calendar;
import java.util.Date;

public class WeatherDetails {
    private Date timestamp;
    private double temperature;
    private long weatherId;
    private String country;
    private String city;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public long getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(long weatherId) {
        this.weatherId = weatherId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        return String.format("Time: %s\nCountry: %s\nCity: %s\nTemperature: %.2f\n", calendar.getTime().toString(), country, city, temperature);
    }
}
