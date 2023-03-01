package project;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiCom implements WeatherModel {

    //http://api.weatherapi.com/v1/forecast.json?key=4cd92cee09b7459293b201035230103&q=London&days=1&aqi=no&alerts=no
    // см. https://www.weatherapi.com/api-explorer.aspx#forecast
    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public void getWeather(String selectedCity, Period period) throws IOException {

        URL myUrl = new URL(setUrl(selectedCity,period));
        HttpURLConnection myUrlCon = (HttpURLConnection) myUrl.openConnection();


        Request request = new Request.Builder()
                .url(myUrl)
                .build();

        switch (period) {
            case ONE_DAY:
                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
                String weatherResponse1 = oneDayForecastResponse.body().string();
                System.out.println(weatherResponse1);
                break;
            case FIVE_DAYS:
                Response fiveDaysForecastResponse = okHttpClient.newCall(request).execute();
                String weatherResponse5 = fiveDaysForecastResponse.body().string();
                System.out.println(weatherResponse5);
                break;
        }

 }

    private String setUrl(String selectCity, Period period) throws IOException {
        //http://api.weatherapi.com/v1/forecast.json?key=4cd92cee09b7459293b201035230103&q=London&days=1&aqi=no&alerts=no
        //http://api.weatherapi.com/v1/forecast.json?key=4cd92cee09b7459293b201035230103&q=London&days=5&aqi=no&alerts=no

        StringBuilder url = new StringBuilder("http://api.weatherapi.com/v1/forecast.json?key=4cd92cee09b7459293b201035230103&q=");
        url.append(selectCity);
        switch (period) {
            case ONE_DAY:
                url.append("&days=1&aqi=no&alerts=no");
                break;
            case FIVE_DAYS:
                url.append("&days=5&aqi=no&alerts=no");
                break;
        }
        return url.toString();
    }
}
