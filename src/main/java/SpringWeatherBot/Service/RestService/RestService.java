package SpringWeatherBot.Service.RestService;

import SpringWeatherBot.BotConfig.property.ForecastConfig;
import SpringWeatherBot.BotConfig.property.OpenWeatherConfig;
import SpringWeatherBot.DTO.Openweather.Forecast.ForecastDayResponse;
import SpringWeatherBot.DTO.Openweather.Forecast.ForecastResponse;
import SpringWeatherBot.DTO.Openweather.Weather.CurrentWeatherResponse;
import SpringWeatherBot.Service.Message.ForecastMessageFormat;
import SpringWeatherBot.Service.Message.WeatherMessageFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {
    private final RestTemplate restTemplate;

    private final OpenWeatherConfig openWeatherConfig;

    private final WeatherMessageFormat weatherMessageFormat;

    private final ForecastMessageFormat forecastMessageFormat;

    @Autowired
    public RestService(RestTemplate restTemplate, OpenWeatherConfig openWeatherConfig,
                       WeatherMessageFormat weatherMessageFormat,ForecastMessageFormat forecastMessageFormat) {
        this.restTemplate = restTemplate;
        this.openWeatherConfig = openWeatherConfig;
        this.weatherMessageFormat = weatherMessageFormat;
        this.forecastMessageFormat = forecastMessageFormat;
    }

    public String getWeather(String cityName){
        String url = String.format(openWeatherConfig.getWeatherUrl(), cityName, openWeatherConfig.getKey());
        CurrentWeatherResponse currentWeatherResponse = restTemplate.getForObject(url, CurrentWeatherResponse.class);
        return weatherMessageFormat.formatMessage(currentWeatherResponse);
    }

    public String getForecast(String cityName, int days){
        if (days == 0){
            return getWeather(cityName);
        }

        String url = String.format(openWeatherConfig.getForecastUrl(), cityName, openWeatherConfig.getKey());
        ForecastResponse forecastResponse = restTemplate.getForObject(url, ForecastResponse.class);
        return forecastMessageFormat.formatMessage(forecastResponse);
    }
}
