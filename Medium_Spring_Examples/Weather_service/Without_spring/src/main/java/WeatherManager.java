import weatherReports.ForecastDetails;
import weatherReports.WeatherDetails;
import weatherReports.WeatherSummary;

import java.util.Scanner;

public class WeatherManager {
    private WeatherService weatherService;

    public WeatherManager(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public void run() {
        boolean run = true;

        while(run) {
            StringBuilder actionChoice = new StringBuilder();
            String[] inputs;
            try {
                inputs = displayMenuAndGetInput(actionChoice);
            }
            catch (Exception ex) {
                System.out.println("You insert illegal input, try again!");
                continue;
            }

            switch (actionChoice.toString()) {
                case "1": {
                    try {
                        WeatherDetails weatherDetails = weatherService.getWeatherForTodayByCountryAndCity(inputs);
                        System.out.println(weatherDetails);
                    }
                    catch (Exception ex) {
                        System.out.println("Sorry, country and ity are not supported");
                    }

                    break;
                }
                case "2": {
                    ForecastDetails forecastDetails = weatherService.getForecastByCountryAndCity(inputs);
                    System.out.println(forecastDetails);
                    break;
                }
                case "3": {
                    WeatherSummary weatherSummary = weatherService.getWeatherSummaryForToday();
                    System.out.println(weatherSummary);
                    break;
                }
                case "4": {
                    run = false;
                    break;
                }
                default: return;
            }
        }

    }

    private String[] displayMenuAndGetInput(StringBuilder actionChoice) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Weather Menu: \nInsert 1 to get Weather or 2 to get Forecast or 3 to today weather summary or 4 to exit");
        String choice = scanner.nextLine();
        if(choice.equals("4")) {
            actionChoice.append("4");
            return null;
        }
        else if(choice.equals("1") || choice.equals("2") || choice.equals("3")) {
            actionChoice.append(choice);
        }
        else {
            throw new Exception();
        }

        if(!choice.equals("3")) {
            System.out.println("Please insert country and city to get required details\nExample Spain - Barcelona");
            String input = scanner.nextLine();
            String[] elements = input.split("-");
            elements[0] = elements[0].trim();
            elements[1] = elements[1].trim();
            return elements;
        }

        return null;

    }
}
