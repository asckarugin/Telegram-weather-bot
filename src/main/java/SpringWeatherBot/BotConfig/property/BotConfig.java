package SpringWeatherBot.BotConfig.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Data
@ConfigurationProperties(prefix = "telegram.bot")
public class BotConfig {

    private String name;

    private String token;

}
