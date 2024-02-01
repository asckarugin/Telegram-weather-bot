package SpringWeatherBot.DTO.Openweather.Forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ForecastResponse {

    private ForecastCityResponse city;

    @JsonProperty("list")
    private List<ForecastDayResponse> dayForecast;
}
