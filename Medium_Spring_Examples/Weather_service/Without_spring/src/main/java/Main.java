public class Main {
    public static void main(String[] args) {
        WeatherRepository weatherRepository = new WeatherRepository();
        WeatherService weatherService = new WeatherService(weatherRepository);
        WeatherManager manager = new WeatherManager(weatherService);
        manager.run();
    }

    public static WeatherMetaData getMetaData() {
        WeatherMetaData weatherMetaData = new WeatherMetaData();
        weatherMetaData.setCelsius(true);
        weatherMetaData.setLicense("3121231wd131231ed12dd1");
        return weatherMetaData;
    }
}
