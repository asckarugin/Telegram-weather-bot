package SpringWeatherBot.DTO.Openweather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherConditionResponse {
    @JsonProperty("main")
    private String condition;
}
