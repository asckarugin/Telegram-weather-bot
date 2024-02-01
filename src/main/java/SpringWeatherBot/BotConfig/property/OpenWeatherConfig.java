package SpringWeatherBot.BotConfig.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@ConfigurationProperties(prefix = "open.weather")
@Configuration
public class OpenWeatherConfig {
    private String weatherUrl;
    private String forecastUrl;
    private String key;
}
