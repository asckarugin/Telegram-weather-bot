package SpringWeatherBot.Service.Message;

import SpringWeatherBot.DTO.Openweather.Forecast.ForecastCityResponse;
import SpringWeatherBot.DTO.Openweather.Forecast.ForecastDayResponse;
import SpringWeatherBot.DTO.Openweather.Forecast.ForecastDayTemperature;
import SpringWeatherBot.DTO.Openweather.Forecast.ForecastResponse;
import SpringWeatherBot.Enums.WeatherCondition;
import SpringWeatherBot.Service.Time.TimeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;

@Component
public class ForecastMessageFormat {
    private static final String FORECAST_HEADER_PATTERN = "*%s*";
    private static final String FORECAST_DAY_PATTERN = "\n%d %s: %s %s°C макс.";
    private static final String[] MONTHS = {"янв", "фев", "мар", "апр", "мая", "июн", "июл", "авг", "сен", "окт", "ноя", "дек"};

    private final TimeProvider timeProvider;

    @Autowired
    public ForecastMessageFormat(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public String formatMessage(ForecastResponse forecastResponse){
        ForecastCityResponse cityResponse = forecastResponse.getCity();

        StringBuilder result = new StringBuilder(String.format(FORECAST_HEADER_PATTERN, cityResponse.getName()));

        LocalDate now = timeProvider.today();

        for(int i = 0; i<5; i++){
            ForecastDayResponse dayResponse = forecastResponse.getDayForecast().get(i);
            LocalDate forecastOn = now.plusDays(i);
            WeatherCondition weatherCondition = WeatherCondition.valueOf(dayResponse.getConditions().get(0).getCondition().toUpperCase());
            ForecastDayTemperature temperature = dayResponse.getTemperature();
            result.append(String.format(FORECAST_DAY_PATTERN,
                    forecastOn.getDayOfMonth(),
                    MONTHS[forecastOn.getMonthValue()-1],
                    weatherCondition.getEmoji(),
                    dayResponse.getTemperature()));
        }
        return result.toString();
    }
}
