package SpringWeatherBot;

import SpringWeatherBot.BotConfig.property.BotConfig;
import SpringWeatherBot.BotConfig.property.ForecastConfig;
import SpringWeatherBot.BotConfig.property.OpenWeatherConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
		BotConfig.class,
		OpenWeatherConfig.class,
		ForecastConfig.class
})

@SpringBootApplication
public class SpringWeatherBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWeatherBotApplication.class, args);
	}

}
