package SpringWeatherBot.DTO.Openweather.Forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForecastDayTemperature {
    @JsonProperty("temp_max")
    private Integer maxTemperature;
    @JsonProperty("temp_min")
    private Integer minTemperature;
}
