package SpringWeatherBot.Service.Message;

import SpringWeatherBot.DTO.Openweather.Weather.CurrentWeatherIndicators;
import SpringWeatherBot.DTO.Openweather.Weather.CurrentWeatherResponse;
import SpringWeatherBot.DTO.Openweather.Weather.CurrentWeatherWind;
import SpringWeatherBot.DTO.intenal.WindDto;
import SpringWeatherBot.Enums.WeatherCondition;
import SpringWeatherBot.Service.WindService.WindProvider;
import org.springframework.stereotype.Component;

@Component
public class WeatherMessageFormat{
    public static final String WEATHER_PATTERN = """
            *%s*
            %s %s
            Температура: %s°C
            Ощущается как: %s°C
            Ветер: %s %s м/c
            Влажность: %s%%
            """;

    private final WindProvider windProvider;

    public WeatherMessageFormat(WindProvider windProvider) {
        this.windProvider = windProvider;
    }

    public String formatMessage(CurrentWeatherResponse currentWeatherResponse){
        String providedWeatherCondition = currentWeatherResponse.getCondition().get(0).getCondition().toUpperCase();
        WeatherCondition weatherCondition = WeatherCondition.valueOf(providedWeatherCondition);

        CurrentWeatherWind weatherWind = currentWeatherResponse.getWind();
        WindDto wind = windProvider.getWind(weatherWind.getDegree(), weatherWind.getSpeed());

        CurrentWeatherIndicators weatherMain = currentWeatherResponse.getIndicators();

        return String.format(WEATHER_PATTERN,
                currentWeatherResponse.getCityName(),
                weatherCondition.getDescription(),
                weatherCondition.getEmoji(),
                weatherMain.getTemperature(),
                weatherMain.getTemperatureFeelings(),
                wind.getDirection().getEmoji(),
                wind.getSpeed()/100,
                weatherMain.getHumidity());
    }
}
