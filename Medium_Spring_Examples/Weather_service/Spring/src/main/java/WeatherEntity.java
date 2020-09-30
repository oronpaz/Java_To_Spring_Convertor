import java.util.Date;

public class WeatherEntity {
    private String country;
    private String city;
    private double temperature;
    private Date date;

    public WeatherEntity(String country, String city, double temperature, Date date) {
        this.country = country;
        this.city = city;
        this.temperature = temperature;
        this.date = date;
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

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
