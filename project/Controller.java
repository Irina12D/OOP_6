package project;

import java.io.IOException;
import java.util.HashMap;

public class Controller {
    private WeatherModel weatherModel = new AccuWeatherModel();
    private WeatherApiCom weatherApiModel = new WeatherApiCom();
    private HashMap<Integer, Period> variants = new HashMap<>();

    public Controller() {
        variants.put(1, Period.ONE_DAY);
        variants.put(5, Period.FIVE_DAYS);
    }
    public void getWeather(String command, String city, String source) throws IOException {
        Integer userCommand = Integer.parseInt(command);
        Integer dataSource = Integer.parseInt(source);
        switch (variants.get(userCommand)) {
            case ONE_DAY:
                switch (dataSource) {
                    case 1: weatherModel.getWeather(city, Period.ONE_DAY);break;
                    case 2: weatherApiModel.getWeather(city, Period.ONE_DAY);break;
                }
                break;
            case FIVE_DAYS:
                switch (dataSource) {
                    case 1: weatherModel.getWeather(city, Period.FIVE_DAYS);break;
                    case 2: weatherApiModel.getWeather(city, Period.FIVE_DAYS);break;
                }
                break;
        }
    }
}
