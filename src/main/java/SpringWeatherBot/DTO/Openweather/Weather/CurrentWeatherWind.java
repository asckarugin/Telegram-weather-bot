package SpringWeatherBot.DTO.Openweather.Weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentWeatherWind {
    private Integer speed;
    @JsonProperty("deg")
    private Integer degree;
}
