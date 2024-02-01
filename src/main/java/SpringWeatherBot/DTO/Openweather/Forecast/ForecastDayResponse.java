package SpringWeatherBot.DTO.Openweather.Forecast;

import SpringWeatherBot.DTO.Openweather.WeatherConditionResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ForecastDayResponse {
    @JsonProperty("temp")
    private ForecastDayTemperature temperature;

    @JsonProperty("weather")
    private List<WeatherConditionResponse> conditions;
}
