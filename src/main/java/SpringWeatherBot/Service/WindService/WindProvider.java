package SpringWeatherBot.Service.WindService;

import SpringWeatherBot.DTO.intenal.WindDto;
import SpringWeatherBot.Enums.WindDirection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WindProvider {
    public WindDto getWind(Integer speed, Integer degree){
        WindDirection windDirection = speed > 0 ? getWindDirection(degree) : WindDirection.DEFAULT;

        WindDto windDto = new WindDto();
        windDto.setDirection(windDirection);
        windDto.setSpeed(speed);
        return windDto;
    }

    private WindDirection getWindDirection(Integer degree){
        if (degree <= 22)
            return WindDirection.NORTH;
        if (degree <= 67)
            return WindDirection.NORTH_EAST;
        if (degree <= 112)
            return WindDirection.EAST;
        if (degree <= 157)
            return WindDirection.SOUTH_EAST;
        if (degree <= 202)
            return WindDirection.SOUTH;
        if (degree <= 247)
            return WindDirection.SOUTH_WEST;
        if (degree <= 292)
            return WindDirection.WEST;
        if (degree <= 337)
            return WindDirection.NORTH_WEST;
        if (degree <= 360) {
            return WindDirection.NORTH;
        }

        log.error("Wrong wind degree", degree);
        throw new IllegalArgumentException();
    }
}
