public class WeatherInfoIdGenerator {
    private long indexWeatherDetails;
    private long indexForeCastDetails;

    public WeatherInfoIdGenerator() {
        indexWeatherDetails = 0;
        indexForeCastDetails = 0;
    }

    public long getNextIdForWeatherDetails() {
        indexWeatherDetails++;
        return indexWeatherDetails;
    }

    public long getNextIdForForeCastDetails() {
        indexForeCastDetails++;
        return indexForeCastDetails;
    }
}
