package SpringWeatherBot.BotConfig.property;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "forecast")
public class ForecastConfig {
    private List<Button> buttons;
    @Getter
    @Setter
    public static class Button{
        private String text;
        private Integer days;

    }
}
