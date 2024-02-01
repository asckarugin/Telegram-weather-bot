package SpringWeatherBot.Enums;

import lombok.Getter;

@Getter
public enum WeatherCondition {
    THUNDERSTORM("Гроза", "\uD83C\uDF29"),
    DRIZZLE("Небольшой дождь", "\uD83C\uDF27"),
    RAIN("Дождь", "\uD83C\uDF27"),
    SNOW("Снег", "\uD83C\uDF28"),
    MIST("Небольшой туман", "\uD83C\uDF2B"),
    FOG("Туман", "\uD83C\uDF2B"),
    CLEAR("Ясно", "\u2600"),
    CLOUDS("Облачно", "\u2601");

    private final String emoji;
    private final String description;

    WeatherCondition(String emoji, String description) {
        this.emoji = emoji;
        this.description = description;
    }
}
