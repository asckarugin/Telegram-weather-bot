package SpringWeatherBot.DTO.Openweather.Weather;

import SpringWeatherBot.DTO.Openweather.WeatherConditionResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CurrentWeatherResponse {

    @JsonProperty("weather")
    private List<WeatherConditionResponse> condition;

    @JsonProperty("main")
    private CurrentWeatherIndicators indicators;

    private CurrentWeatherWind wind;

    @JsonProperty("name")
    private String cityName;
}
