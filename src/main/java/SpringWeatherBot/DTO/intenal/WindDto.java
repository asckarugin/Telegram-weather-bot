package SpringWeatherBot.DTO.intenal;

import SpringWeatherBot.Enums.WindDirection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WindDto {

    private WindDirection direction;
    private Integer speed;
}
