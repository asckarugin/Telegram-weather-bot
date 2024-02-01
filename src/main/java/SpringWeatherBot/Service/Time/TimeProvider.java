package SpringWeatherBot.Service.Time;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static java.time.ZoneOffset.UTC;

@Component
public class TimeProvider {
    public LocalDate today(){
        return LocalDate.now(UTC);
    }
}
