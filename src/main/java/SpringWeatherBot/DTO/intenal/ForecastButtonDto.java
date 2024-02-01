package SpringWeatherBot.DTO.intenal;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class ForecastButtonDto implements Serializable {

    private String city;
    private Integer days;
}
