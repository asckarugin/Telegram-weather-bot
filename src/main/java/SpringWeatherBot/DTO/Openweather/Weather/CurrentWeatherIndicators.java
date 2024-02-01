package SpringWeatherBot.DTO.Openweather.Weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentWeatherIndicators {

    @JsonProperty("temp")
    private Integer temperature;

    @JsonProperty("feels_like")
    private Integer temperatureFeelings;

    private Integer humidity;
}
